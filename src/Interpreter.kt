import kotlin.reflect.jvm.internal.impl.types.AbstractTypeCheckerContext

class Interpreter() {

    fun visit(node: CoreNode?): Type  {
        val methodName: String? = "visit" + node!!.javaClass.name

        this.javaClass.methods.forEach {
            if (methodName == it.name) {
                val res = it.invoke(this, node)
                return if (res is Type) res else NoneType()
            }
        }

        return noVisitMethod(node)
    }

    public fun noVisitMethod(node: CoreNode?): Type {
        throw Exception("No visit method defined")
    }

    public fun visitNumberNode(node: NumberNode): Type {
        var res: Type = NoneType()
        if (node.token!!.value is Int) {
            res = NumberType(node.token.value.toString().toInt())
        } else if (node.token.value is Double) {
            res = NumberType(node.token.value.toString().toDouble())
        }
        res.setPosition(node.startPosition, node.endPosition)
        return res
    }

    public fun visitBinOpNode(node: BinOpNode): Type {
        val left = visit(node.leftNode)
        val right = visit(node.rightNode)
        var result: Type = NoneType()

        if (node.opToken!!.type == TT_PLUS) {
            result = left.addedTo(right)
        }
        else if (node.opToken.type == TT_MINUS) {
            result = left.subtractedBy(right)
        }
        else if (node.opToken.type == TT_MUL) {
            result = left.multipliedBy(right)
        }
        else if (node.opToken.type == TT_DIV) {
            result = left.dividedBy(right)
        }


        result.setPosition(node.startPosition, node.endPosition)

        return result
    }

    public fun visitUnaryOpNode(node: UnaryOpNode): Type {
        var number = visit(node.node)

        if (node.opToken!!.type == TT_MINUS) {
            number = number.multipliedBy(NumberType(-1))
        }

        number.setPosition(node.startPosition, node.endPosition)

        return number
    }




}