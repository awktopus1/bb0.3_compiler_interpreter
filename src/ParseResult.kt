const val T_PR = 0
const val T_NODE = 1

class ParseResult(var node: CoreNode? = null, var error: Error? = null) {

    fun register(rw: RegisterWrapper): RegisterWrapper {
        if (rw.parseResult != null) {
            if (rw.parseResult.error != null) {
                error = rw.parseResult.error
            }
            return RegisterWrapper(node=rw.parseResult.node)
        }
        return rw
    }

    fun success(n: CoreNode?): ParseResult {
        node = n
        return this
    }

    fun failure(e: Error?): ParseResult {
        error = e
        return this
    }

}

data class RegisterWrapper(val parseResult: ParseResult? = null, val token: Token<Any>? = null, val node: CoreNode? = null)