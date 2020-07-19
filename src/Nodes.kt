
open class CoreNode {
    open fun asString() = ""

    open val startPosition: Position? = null
    open val endPosition: Position? = null
}

class NumberNode(val token: Token<Any>?) : CoreNode() {

    override val startPosition = token!!.startPosition
    override val endPosition = token!!.endPosition

    override fun asString() = token!!.asString()

}

class StringNode(val token: Token<Any>?) : CoreNode() {

    override val startPosition = token!!.startPosition
    override val endPosition = token!!.endPosition

    override fun asString() = token!!.asString()

}

class ListNode(val elementNodes: List<CoreNode?>) : CoreNode() {

    override fun asString(): String {
        val list = mutableListOf<String>()
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

    override val startPosition = leftNode!!.startPosition
    override val endPosition = rightNode!!.endPosition

}

class UnaryOpNode(val opToken: Token<Any>?, val node: CoreNode?) : CoreNode() {

    override fun asString() = "(" + opToken!!.asString() + ", " + node!!.asString() + ")"

    override val startPosition = opToken!!.startPosition
    override val endPosition = node!!.endPosition

}

class IfNode(val cases: CoreNode?, val elseCase: CoreNode) : CoreNode() {

}