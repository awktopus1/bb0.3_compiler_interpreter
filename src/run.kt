fun run_bb(fileName: String, text: String): RunResult {

    //Generate Tokens
    val lexer = Lexer(fileName, text)
    val tokenResult = lexer.makeTokens()

    val result = RunResult(error = tokenResult.error, tokens = tokenResult.result)

    if (result.error != null) {
        return result
    }

    //Generate Abstract Syntax Tree (AST)
    val parser = Parser(result.tokens)
    val parseResult = parser.parse()
    result.ast = parseResult!!.node
    result.error = parseResult.error

    return result
}


data class RunResult(var error: Error? = null, val tokens: List<Token<Any>>? = null, var ast: CoreNode? = null)