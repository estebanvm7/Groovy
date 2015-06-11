map = [:] 
map = [ '+' : FunctionAdd(a,b), 
        '*' : FunctionMult(a,b), 
        '-' : FunctionSub(a,b),
        'quotient' : FunctionDiv(a,b),
        'modulo' : FunctionMod(a,b),
        '=' : FunctionEqu(a,b),
        '<' : FunctionLess(a,b),
        '<=' : FunctionLessThan(a,b),
        '>' : FunctionGreater(a,b),
        '>=' : FunctionGreaterThan(a,b),
        'and' : FunctionAnd(a,b),
        'or' : FunctionOr(a,b),
        'not' : FunctionNot(a),
        'if' : FunctionIf(a,b,c) ]

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
        this.a / this.b
    }
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
}
class LiteralInteger {
    def Integer a
    def LiteralInteger(String b){ this.a = b as Integer }
    def getA(){ this.a }
}

class LiteralBool {
    def Boolean a
    def LiteralBool(String b){ 
        if(b == "#t"){
            this.a = true 
        }

        else if (b == "#f"){
            this.a = false
        }
    }
    def getA(){ this.a }
}

/**def bucle = true
while(bucle) {
    def scheme = System.console().readLine 'scheme> '
    if (scheme == 'exit') { bucle = false }
    else{ System.console().println()) }    
}**/

FunctionDefinition suma = new FunctionIf(new LiteralBool("#f").getA(),new LiteralInteger("-3").getA(),4)
println(suma.execute())