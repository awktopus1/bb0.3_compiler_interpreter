
open class CoreNode {
    open fun asString() = ""
}

class NumberNode(val token: Token<Any>?) : CoreNode() {

    override fun asString() = token!!.asString()

}

class StringNode(val token: Token<Any>?) : CoreNode() {

    override fun asString() = token!!.asString()

}

class ListNode(val elementNodes: List<CoreNode?>) : CoreNode() {

    override fun asString(): String {
        var list = mutableListOf<String>()
        elementNodes.forEach {
            list.add(it!!.asString())
        }
        val result = listAsString(list)
        return result
    }

}

class VarAccessNode(val varNameToken: Token<Any>?) : CoreNode() {

}

class VarAssignNode(val varNameToken: Token<Any>?, val valueNode: CoreNode) : CoreNode() {

}

class BinOpNode(val leftNode: CoreNode?, val opToken: Token<Any>?, val rightNode: CoreNode?) : CoreNode() {

    override fun asString() = "(" + leftNode!!.asString() + ", " + opToken!!.asString() + ", " + rightNode!!.asString() + ")"

}

class UnaryOpNode(val opToken: Token<Any>?, val node: CoreNode?) : CoreNode() {

    override fun asString() = "(" + opToken!!.asString() + ", " + node!!.asString() + ")"

}

class IfNode(val cases: CoreNode?, val elseCase: CoreNode) : CoreNode() {

}