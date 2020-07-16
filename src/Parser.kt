class Parser(val tokens: List<Token<Any>>?) {
    var tokenIndex = -1
    var currentToken: Token<Any>? = null

    init {
        advance()
    }

    fun advance(): Token<Any>? {
        tokenIndex++
        if (tokenIndex < tokens!!.size) {
            currentToken = tokens[tokenIndex]
        }
        return currentToken
    }

    fun parse(): CoreNode? {
        val result = expression()
        return result
    }

    fun factor(): NumberNode? {
        var result: NumberNode? = null
        if (currentToken!!.type == TT_INT || currentToken!!.type == TT_FLOAT) {
            result = NumberNode(currentToken)
            advance()
        }
        return result
    }

    fun term(): CoreNode? {
        return binOp(::factor, listOf(TT_MUL, TT_DIV))
    }

    fun expression(): CoreNode? {
        return binOp(::term, listOf(TT_PLUS, TT_MINUS))
    }

    fun binOp(function: () -> CoreNode?, operations: List<String>) : CoreNode? {
        var left: CoreNode? = function()

        while (operations.contains(currentToken!!.type)) {
            val opToken = currentToken
            advance()
            val right = function()
            left = BinOpNode(left, opToken, right)
        }

        return left
    }
}