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
                    'if' : 'fIf' ]
    }

    def getMap() { this.map }
    def setMap (String key, String value) { map.put(key, value) }
    def findByKey(String key) { this.map.get(key) }


}


class Function {

    //def fIf(Object a, Object b,Object condition) {}
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
        println condition
        def param1 = list[1] as Integer
        def param2 =  list[2] as Integer
        switch(condition) {
            case 'addition':
                this.foo?.add(param1, param2)
                break
            case 'multiplication':
                this.foo?.mult(param1, param2)
                break
            case 'substraction':
                this.foo?.sub(param1, param2)
                break
            case 'division':
                this.foo?.div(param1, param2)
                break
            case 'module':
                this.foo?.mod(param1, param2)
                break
            case 'equals':
                this.foo?.equ(param1, param2)
                break                
            default:
                break
        }

    }
}

Dispacher dis = new Dispacher()
Parser p = new Parser()
dis.dispach(p.parse('(= 3 3)'))