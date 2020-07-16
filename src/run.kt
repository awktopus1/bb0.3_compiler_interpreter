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
    result.ast = parser.parse()

    return result
}


class RunResult(val error: Error? = null, val tokens: List<Token<Any>>? = null, var ast: CoreNode? = null)