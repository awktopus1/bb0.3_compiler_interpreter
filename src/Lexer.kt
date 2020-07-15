/*
Lexer class
 */

class Lexer(val fileName: String, val text: String) {
    var pos = Position(-1, 0, -1, fileName, text)
    var currentCharacter: Char? = null

    data class TokenResult(val result: List<Token<Any>>? = null, val error: Error? = null)

    init {
        advance()
    }

    fun advance() {
        pos.advance(currentCharacter)
        currentCharacter = if (pos.index < text.length) text[pos.index] else null
    }

    fun makeTokens(): TokenResult {
        val tokens = mutableListOf<Token<Any>>()

        while (currentCharacter != null) {
            if (" \t".contains(currentCharacter!!)) {
                advance()
            } else if (DIGITS.contains(currentCharacter!!)) {
                tokens.add(makeNumber())
                advance()
            } else if (currentCharacter == '+') {
                tokens.add(Token(TT_PLUS))
                advance()
            } else if (currentCharacter == '-') {
                tokens.add(Token(TT_MINUS))
                advance()
            } else if (currentCharacter == '*') {
                tokens.add(Token(TT_MUL))
                advance()
            } else if (currentCharacter == '/') {
                tokens.add(Token(TT_DIV))
                advance()
            } else if (currentCharacter == '(') {
                tokens.add(Token(TT_LPAREN))
                advance()
            } else if (currentCharacter == ')') {
                tokens.add(Token(TT_RPAREN))
                advance()
            } else {
                val char = currentCharacter
                val posStart = pos.copy()
                advance()
                return TokenResult(null, IllegalCharError(posStart, pos,"'$char'"))
            }
        }

        return TokenResult(tokens, null)
    }

    fun makeNumber(): Token<Any> {
        var numString = ""
        var dotCount = 0

        while (currentCharacter != null && "$DIGITS.".contains(currentCharacter!!)) {
            if (currentCharacter == '.') {
                if (dotCount == 1) break
                dotCount++
                numString += "."
                advance()
            } else {
                numString += currentCharacter
                advance()
            }
        }

        if (dotCount == 0) {
            return Token(TT_INT, numString.toInt())
        } else {
            return Token(TT_FLOAT, numString.toFloat())
        }
    }
}