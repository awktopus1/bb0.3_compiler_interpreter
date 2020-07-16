while (true) {
    print("bb 0.3.0 > ")
    val text = readLine()

    val result = run_bb("<stdin>", text ?: "")

    if (result.error == null) {
        println(result.ast!!.asString())
    } else {
        println(result.error!!.asString())
    }
}