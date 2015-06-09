/**
* El código Scheme corresponde a listas de símbolos demarcadas por
* paréntesis ( ).
* Este parser lee el código parentizado y produce las listas de
* de símbolos correspondientes en Groovy.
*/
class Parser {
    def public parse(String code) {
        def stack = []
        def current = null
        def tokens = code.replace('(','( ').replace(')',' )').split(' ')
        tokens.each { token ->
            switch(token) {
                case '(':
                    stack.push(current)
                    current = []
                    break
                case ')':
                    def top = stack.pop()
                    current = top ? top << current : current
                    break
                default:
                    current << token
            }
        }
        current
        
    }
}


class Caster {
    def map

    def Caster(){ 
        this.map = [:] 
        this.map = [ '+' : 'addition', 
                    '*' : 'multiplication', 
                    '-' : 'substraction',
                    'quotient' : 'division',
                    'modulo' : 'module',
                    '=' : 'equals',
                    '<' : 'less',
                    '<=' : 'lessThan',
                    '>' : 'greater',
                    '>=' : 'greaterThan',
                    'and' : 'and',
                    'or' : 'or',
                    'not' : 'not',
                    'if' : 'fIf' ,
                    '#t' : 'true',
                    '#f' : 'false']
    }

    def getMap() { this.map }
    def setMap (String key, String value) { map.put(key, value) }
    def findByKey(String key) { this.map.get(key) }
    def strToInt(String a){ a as Integer }    
    def strToBool(String a) { this.findByKey(a).toBoolean() }

}


class Function {

    def fIf(Object condition, Object a, Object b) { condition? a : b}
    def add(Integer a, Integer b) { a + b }
    def mult(Integer a, Integer b) { a * b }
    def sub(Integer a, Integer b) { a - b }
    def div(Integer a, Integer b) { a / b }
    def mod(Integer a, Integer b) { a % b }
    def equ(Integer a, Integer b) { a == b }
    def less(Integer a, Integer b) { a < b }
    def lessThan(Integer a, Integer b) { a <= b }
    def greater(Integer a, Integer b) { a > b }
    def greaterThan(Integer a, Integer b) { a >= b }
    def and(Boolean a, Boolean b) { a && b }
    def or(Boolean a, Boolean b) { a || b}
    def not(Boolean a) { a ? false : true }  
} 

class Dispacher {

    Function foo = new Function()
    Caster cast = new Caster()
    
    def dispach (List list) {
        def condition = this.cast.findByKey(list[0])
        switch(condition) {
            case 'true':
                true
                break
            case 'false':
                false
                break
            case 'fIf':
                this.foo?.fIf( this.cast.strToBool(list[1]), this.dispach([list[2]]), this.dispach([list[3]]) )
                break
            case 'greater':
                this.foo?.greater(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'greaterThan':
                this.foo?.greaterThan(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'less':
                this.foo?.less(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'lessThan':
                this.foo?.lessThan(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'addition':
                this.foo?.add(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'multiplication':
                this.foo?.mult(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'substraction':
                this.foo?.sub(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'division':
                this.foo?.div(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'module':
                this.foo?.mod(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break
            case 'equals':
                this.foo?.equ(this.cast.strToInt(list[1]), this.cast.strToInt(list[2]) )
                break 
            case 'and':
                this.foo?.and( this.cast.strToBool(list[1]), this.cast.strToBool(list[2]) )  
                break
            case 'or':
                this.foo?.or( this.cast.strToBool(list[1]), this.cast.strToBool(list[2]) )  
                break
            case 'not':
                this.foo?.not( this.cast.strToBool(list[1]) )  
                break               
            default:
                this.cast.strToInt(list[0])
                break
        }
    }
}

Dispacher dis = new Dispacher()
Parser p = new Parser()
def bucle = true
while(bucle) {
    def scheme = System.console().readLine 'scheme> '
    if (scheme == 'exit') { bucle = false }
    else{ System.console().println( dis.dispach(p.parse(scheme))) }    
}



