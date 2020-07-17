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

    fun resAdvance(res: ParseResult) {
        res.register(RegisterWrapper(token = advance()))
    }

    fun parse(): ParseResult? {
        val result = expression()
        if (result!!.error == null && currentToken!!.type != TT_EOF) {
            return result.failure(InvalidSyntaxError(
                currentToken!!.startPosition, currentToken!!.endPosition,
                "Expected '+', '-', '*', or '/'"
            ))
        }
        return result
    }

    fun factor(): ParseResult? {
        val res = ParseResult()
        val result: NumberNode?

        if (listOf(TT_PLUS, TT_MINUS).contains(currentToken!!.type)) {
            val token = currentToken
            resAdvance(res)
            val factor = res.register(RegisterWrapper(factor())).node

            if (res.error != null) {
                return res
            }
            return res.success(UnaryOpNode(token, factor))
        }


        else if (currentToken!!.type == TT_INT || currentToken!!.type == TT_FLOAT) {
            result = NumberNode(currentToken)
            resAdvance(res)
            return res.success(result)
        }

        else if (currentToken!!.type == TT_LPAREN) {
            resAdvance(res)
            val expr = res.register(RegisterWrapper(expression())).node
            if (res.error != null) {
                return res
            }
            if (currentToken!!.type == TT_RPAREN) {
                resAdvance(res)
                return res.success(expr)
            } else {
                return res.failure(InvalidSyntaxError(
                    currentToken!!.startPosition, currentToken!!.endPosition,
                    "Expected ')'"
                ))
            }
        }

        return res.failure(InvalidSyntaxError(
            currentToken!!.startPosition,
            currentToken!!.endPosition,
            "Expected int or float")
        )

    }

    fun term(): ParseResult? {
        return binOp(::factor, listOf(TT_MUL, TT_DIV))
    }

    fun expression(): ParseResult? {
        return binOp(::term, listOf(TT_PLUS, TT_MINUS))
    }

    fun binOp(function: () -> ParseResult?, operations: List<String>) : ParseResult? {
        val res = ParseResult()
        var left: CoreNode? = res.register(RegisterWrapper(parseResult = function())).node

        if (res.error != null) {
            return res
        }

        while (operations.contains(currentToken!!.type)) {
            val opToken = currentToken
            resAdvance(res)
            val right = res.register(RegisterWrapper(function())).node

            if (res.error != null) {
                return res
            }

            left = BinOpNode(left, opToken, right)
        }

        return res.success(left)
    }
}