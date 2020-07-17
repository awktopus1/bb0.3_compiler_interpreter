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
            } else if (currentCharacter == '+') {
                tokens.add(Token(TT_PLUS, _startPosition = pos))
                advance()
            } else if (currentCharacter == '-') {
                tokens.add(Token(TT_MINUS, _startPosition = pos))
                advance()
            } else if (currentCharacter == '*') {
                tokens.add(Token(TT_MUL, _startPosition = pos))
                advance()
            } else if (currentCharacter == '/') {
                tokens.add(Token(TT_DIV, _startPosition = pos))
                advance()
            } else if (currentCharacter == '(') {
                tokens.add(Token(TT_LPAREN, _startPosition = pos))
                advance()
            } else if (currentCharacter == ')') {
                tokens.add(Token(TT_RPAREN, _startPosition = pos))
                advance()
            } else {
                val char = currentCharacter
                val posStart = pos.copy()
                advance()
                return TokenResult(null, IllegalCharError(posStart, pos,"'$char'"))
            }
        }

        tokens.add(Token(TT_EOF, _startPosition = pos))
        return TokenResult(tokens, null)
    }

    fun makeNumber(): Token<Any> {
        var numString = ""
        var dotCount = 0
        val startPosition = pos.copy()

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
            return Token(TT_INT, numString.toInt(), startPosition, pos)
        } else {
            return Token(TT_FLOAT, numString.toDouble(), startPosition, pos)
        }
    }
}