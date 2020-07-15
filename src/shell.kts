while (true) {
    print("bb 0.3.0 > ")
    val text = readLine()

    val tokenResult = run_bb("<stdin>", text ?: "")

    if (tokenResult.error == null) {
        print("[")
        tokenResult.result!!.forEach {
            val s = it.asString()
            print("$s, ")
        }
        println("]")
    } else {
        println(tokenResult.error!!.asString())
    }
}