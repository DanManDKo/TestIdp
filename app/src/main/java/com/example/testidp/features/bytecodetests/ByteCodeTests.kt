package com.example.testidp.features.bytecodetests

class ByteCodeTests(defaultConstructorParam: Double) {

    /**
     * The resource https://medium.com/android-news/inline-noinline-crossinline-what-do-they-mean-b13f48e113c2
     */

    constructor(constructorOneParam: String) : this(11.11) {
        val aaa = "Constructor one"
    }

    constructor(constructorTwoParam: String, param: Int) : this(22.22) {
        val aaa = "Constructor two"
    }

    companion object {
        private const val CONSTANT = "CONSTANT"
        fun someStaticFun(): String {
            return "This is a static fun"
        }
    }

    init {
        val aaa = "The init block"
        someFunWithArgument(2 + 2)
        higherOrderFunCaller()
    }

    fun someFun() {
        val sum = 2 + 2

        val valValue = "VAL value"
        var varValue = "VAR value"

        someFunWithArgument(sum)
    }

    fun someFunWithArgument(sum: Int) {
        sum.toString()
    }


    fun higherOrderFunCaller() {
        highOrderFun(
            lambda = {

            },
            lambdaParam = { param ->

            },
            lambdaParamReturn = { param ->
                param.hashCode()
            }

        )
        highOrderFunSingle {
            it.length
            println("Non-local control flow")
            return
        }
    }

    /**
     * Lambda should not be nullabe
     * Note that noinline lambdas do not support non-local control flow, i.e you won’t be able to propagate your return to the calling function.
    Crossinline
     */
    inline fun highOrderFun(
        lambda: () -> Unit,
        noinline lambdaParam: (String) -> Unit,
        lambdaParamReturn: (String) -> Int?
    ) {
        lambda.invoke()
        lambdaParam.invoke("LamdaParam")
        lambdaParamReturn.invoke("lambdaParamReturn")
        someFun()
        someFunWithArgument(11)
        someStaticFun()
    }

    /**
     * The inline fun cannot get access to the PRIVATE variables or func
     * But if identifier is internal and marked with @PublishedApi annotation the trick will work
     */
    inline fun highOrderFunSingle(param: (String) -> Unit) {
        val singleParamHighOrder = "singleParamHigh"
        param.invoke(singleParamHighOrder)

//        somePrivateVariable.length
//        somePrivateFun()

        someInternalVariable
    }

    private val somePrivateVariable = "I am a private variable"

    @PublishedApi
    internal val someInternalVariable = "I am an internal var"

    private fun somePrivateFun() {

    }

    /**
     * The crossinline marker is used to mark lambdas that mustn’t allow non-local returns, especially when such lambda is passed to another execution context such as a higher order function that is not inlined, a local object or a nested function. In other words, you won’t be able to do a return in such lambdas.
     */
    fun crossLineCaller() {
        crossLineTest {
            //return  //Error. Can't return from here.
        }
    }

    inline fun crossLineTest(crossinline param: (String) -> Unit) {

    }

    fun genericCaller() {
        doGeneric("aaa")
        doSomethingWithGeneric("bbb")
    }

    /**
     * https://stackoverflow.com/questions/45949584/how-does-the-reified-keyword-in-kotlin-work
     */
    //    Ok
    fun <T> doGeneric(value: T): String {
        return "Generic $value"
    }

    //    Error ??
    fun <T> doSomethingWithGeneric(value: T): String {
       return "Generic ${value!!::class.simpleName}"
    }

    inline fun <reified T>doSomethingWithGenericReified(value: T): String {
        return "Generic ${value!!::class.simpleName}"
    }
}