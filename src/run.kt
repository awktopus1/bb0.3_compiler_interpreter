fun run_bb(fileName: String, text: String): Lexer.TokenResult {
    val lexer = Lexer(fileName, text)
    val tokenResult = lexer.makeTokens()

    return tokenResult
}