class Global {

    static def Object a
    static def Object b
    static def Object c
    static def map = [:] 
    
    def static setMap (){
        this.map = [ 
            '+'         : new FunctionAdd(a,b), 
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
            'quotient'  : new FunctionDiv(a,b)]
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

class FunctionDefine implements FunctionDefinition{
    Object parameter
    Object invoke
    Object res
    Integer param = 0

    def FunctionDefine(List list, List list2){
        this.parameter = list
        this.invoke = list2
    }
    @Override
    execute(){ this.res }
    
     def setDefinitions(Object res) { this.res = res } 


}

class FunctionAdd implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionMult implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionDiv implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionSub implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionMod implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionEqu implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionLess implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionLessThan implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionGreater implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionGreaterThan implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionAnd implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionOr implements FunctionDefinition{
    Object a
    Object b
    Integer param = 2
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
    def getParam(){ this.param }
}

class FunctionNot implements FunctionDefinition{
    Object a
    Integer param = 1
    def FunctionNot(Object a) {
        this.a = a
    }
    @Override
    def execute () {
        !this.a
    }

    def setA(Object a) { this.a = a }
    def getParam(){ this.param }
}

class FunctionIf implements FunctionDefinition{
    Object a
    Object b
    Object c
    Integer param = 3
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
    def getParam(){ this.param }
    def getA(){ this.a }
}

class LiteralInteger implements FunctionDefinition{
    def Integer a
    LiteralInteger(Object a){ setA(a)}
    @Override
    def execute () { a }
    def setA(Object a) { this.a = a as Integer} 
}

class LiteralBool implements FunctionDefinition{
    def Boolean a
    LiteralBool(Object a){ setA(a) }
    @Override
    def execute () { a }
    def setA(Object a) {
        if(a == '#t' || a == ['#t']){ this.a = true }
        else if (a =='#f' || a == ['#f']){ this.a = false }
        else if (a == true){ this.a = true }
        else{ this.a = false} 
    }  
} 

class FunctionStack {

    def next(List list, int pos){
        try { return list[pos] }
        catch(Exception e) {}
    }

    def change(List list, String old, String newS){
        Collections.replaceAll(list, old, newS)
    }
    
    def find (list){
        while (true){
            try{list = next(list, 1)}
            catch(Exception e){return list} 
        }
    }

    def analize(Object parseList){
        println("entrada "+parseList)
        if (parseList == null){return}
        if(parseList[0] == 'define'){
            def ret = parseList[1][0]
            def temp = parseList[1]
            Global.map.put(ret, new FunctionDefine(parseList[2],temp))
            return "Function: $ret"
        }
        def first = Global.map.containsKey(parseList[0])
        if (!first){
            try { return new LiteralInteger(parseList).execute() }
            catch(Exception e) { 
                try { return new LiteralInteger(parseList[0]).execute()  }
                catch(Exception e2) { return new LiteralBool(parseList).execute() }
            }
        }
        else{
            def variable = Global.map.get(parseList[0])
            switch(variable.getParam()) {
                case 1:
                    variable.setA(analize(next(parseList, 1)))
                    break
                case 2:
                    def a = variable.setA(analize(next(parseList, 1)))
                    variable.setB(analize(next(parseList, 2)))
                    variable.setA(a)
                    break
                case 3:
                    println("next "+next(parseList, 1))
                    variable.setA(analize(next(parseList, 1)))
                    if (variable.getA()){ variable.setB(analize(next(parseList, 2))) }
                    else{ variable.setC(analize(next(parseList, 3))) }
                    break
                case 0:
                    def para = variable.parameter.join(" , ")
                    println(para)
                    println("1: "+variable.invoke[1])
                    println("2: "+find(parseList))
                    println("invoke: "+variable.invoke)
                    def para2 = para.replace(variable.invoke[1],find(parseList))
                    println(para2)
                    def para3 = Arrays.asList(para2.split("\\s*,\\s*"))
                    println("analize "+para3 )
                    variable.setDefinitions(analize(para3))   
                    break
                    
                    //FunctionDefinition(FunctionAdd(FunctionAdd(n,1),1))  
                    
            }
            variable.execute()    
        }
    }
}


FunctionStack f = new FunctionStack()
Parser par = new Parser()
Global.setMap()
def bucle = true
while(bucle) {
    def scheme = System.console().readLine 'scheme> '
    if (scheme == 'exit') { bucle = false }
    else{
        def list = par.parse(scheme)
        System.console().println(f.analize(list)) 
    }    
}