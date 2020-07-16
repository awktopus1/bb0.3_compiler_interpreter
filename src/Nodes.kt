
open class ParentNode {
    open fun asString() = ""
}

class NumberNode(val token: Token<Number>) : ParentNode() {

    override fun asString() = token.asString()

}

class StringNode(val token: Token<String>) : ParentNode() {

    override fun asString() = token.asString()

}

class ListNode(val elementNodes: List<ParentNode>) : ParentNode() {

    override fun asString(): String {
        var result = "["
        elementNodes.forEach {
            result += it.asString() + ", "
        }
        return "$result]"
    }

}

class VarAccessNode(val varNameToken: Token<Any>) : ParentNode() {

}

class VarAssignNode(val varNameToken: Token<Any>, val valueNode: ParentNode) : ParentNode() {

}

class BinOpNode(val leftNode: ParentNode, val opToken: Token<Any>, val rightNode: ParentNode) : ParentNode() {

    override fun asString() = leftNode.asString() + ", " + opToken.asString() + ", " + rightNode.asString()

}

class UnaryOpNode(val opToken: Token<Any>, val node: ParentNode) : ParentNode() {

}

class IfNode(val cases: ParentNode, val elseCase: ParentNode) : ParentNode() {

}