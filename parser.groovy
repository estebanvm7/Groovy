class Global {

    def Object a
    def Object b
    def Object c
    def map = [:] 
    
    def Global (){
        this.map = [ '+'         : new FunctionAdd(a,b), 
            '*'         : new FunctionMult(a,b), 
            '-'         : new FunctionSub(a,b),
            '='         : new FunctionEqu(a,b),
            '<'         : new FunctionLess(a,b),
            '>'         : new FunctionGreater(a,b),
            '<='        : new FunctionLessThan(a,b),
            '>='        : new FunctionGreaterThan(a,b),
            'if'        : new FunctionIf(a,b,c), 
            'or'        : new FunctionOr(a,b),
            'and'       : new FunctionAnd(a,b),
            'not'       : new FunctionNot(a),
            'modulo'    : new FunctionMod(a,b),
            'quotient'  : new FunctionDiv(a,b) ]
    }
}
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

interface FunctionDefinition {
    def execute ()
}

class FunctionAdd implements FunctionDefinition{
    Object a
    Object b
    def FunctionAdd(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a + this.b
    }
    
    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionMult implements FunctionDefinition{
    Object a
    Object b
    def FunctionMult(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a * this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionDiv implements FunctionDefinition{
    Object a
    Object b
    def FunctionDiv(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        (this.a).intdiv(this.b)
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionSub implements FunctionDefinition{
    Object a
    Object b
    def FunctionSub(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a - this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionMod implements FunctionDefinition{
    Object a
    Object b
    def FunctionMod(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a % this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionEqu implements FunctionDefinition{
    Object a
    Object b
    def FunctionEqu(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a == this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionLess implements FunctionDefinition{
    Object a
    Object b
    def FunctionLess(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a < this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionLessThan implements FunctionDefinition{
    Object a
    Object b
    def FunctionLessThan(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a <= this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionGreater implements FunctionDefinition{
    Object a
    Object b
    def FunctionGreater(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a > this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionGreaterThan implements FunctionDefinition{
    Object a
    Object b
    def FunctionGreaterThan(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a >= this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionAnd implements FunctionDefinition{
    Object a
    Object b
    def FunctionAnd(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a && this.b
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionOr implements FunctionDefinition{
    Object a
    Object b
    def FunctionOr(Object a,Object b) {
        this.a = a
        this.b = b
    }
    @Override
    def execute () {
        this.a || this.b
    }
    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
}

class FunctionNot implements FunctionDefinition{
    Object a
    def FunctionNot(Object a) {
        this.a = a
    }
    @Override
    def execute () {
        !this.a
    }

    def setA(Object a) { this.a = a }
}

class FunctionIf implements FunctionDefinition{
    Object a
    Object b
    Object c
    def FunctionIf(Object a,Object b, Object c) {
        this.a = a
        this.b = b
        this.c = c
    }
    @Override
    def execute () {
        a? b:c
    }

    def setA(Object a) { this.a = a } 
    def setB(Object b) { this.b = b }
    def setC(Object c) { this.c = c }
}
class LiteralInteger {
    def Integer num
    def LiteralInteger(String a){ this.num = a as Integer }
    def getNumber(){ this.num }
}

class LiteralBool {
    def Boolean bool
    def LiteralBool(String var){ 
        if(var == "#t"){
            this.bool = true 
        }
        else if (var == "#f"){
            this.bool = false
        }
    }
    def getBool(){ this.bool }
}


/**def bucle = true
while(bucle) {
    def scheme = System.console().readLine 'scheme> '
    if (scheme == 'exit') { bucle = false }
    else{ System.console().println()) }    
}**/
//def lista = ['+','2','3']
//          ['not' , '#t']

// map.get(list[0])



class FunctionStack {
    Global global = new Global()
    /*def parseList = []
    
    def setParseList (String input) { this.parseList = new Parser().parse(input) }
    def getParseList () { this.parseList }*/

    def analize(Object parseList){
        println(parseList)
        if ( !map.containsKey(parseList.head()) ){
        println("no mapa")
            if (parseList.head().contains('#')){ new LiteralBool(parseList.head()).getBool() }
            else{ new LiteralInteger(parseList.head()).getNumber() }
        }
        else if (parseList.size() == 2){
            def variable = global.map.get(parseList[0])
            def newList = parseList[1]
            variable.setA(analize(newList))
            variable.execute()
        }
    }
}


// 1
    // ['12'],['#t']
// 2
    // ['+', '1','2']   -   ['if', '#t', '2', '3']    - ['not','#t']
// 3
    // ['+',['module', '10', '2'] , ['1']]

/*def variable = map.get('if')
variable.setA(new LiteralBool('#t').getBool())
variable.setB(new LiteralInteger('7').getNumber())
variable.setC(new LiteralInteger('2').getNumber())
println (variable.execute())*/

FunctionStack f = new FunctionStack()
Parser par = new Parser()
def list = par.parse('(#t)')
println (f.analize(list))