while (true) {
    print("bb 0.3.0 > ")
    val text = readLine()

    val result = run_bb("<stdin>", text ?: "")

    val lexList = mutableListOf<String>()
    result.tokens!!.forEach {
        lexList.add(it.asString())
    }
    println(listAsString(lexList))
    if (result.ast != null) {
        println(result.ast!!.asString())
    }

    if (result.error != null) {
        println(result.error!!.asString())
    }

    if (result.interpreterResult != null) {
        println(result.interpreterResult!!.asString())
    }
}