open class Type {

    var startPosition: Position? = null
    var endPosition: Position? = null
    var context = ""

    fun setPosition(startPos: Position?, endPos: Position?) {
        startPosition = startPos
        endPosition = endPos
    }

    open fun asString() = "any"

    open fun addedTo(other: Type): Type {
        return NoneType()
    }
    open fun subtractedBy(other: Type): Type {
        return NoneType()
    }
    open fun multipliedBy(other: Type): Type {
        return NoneType()
    }
    open fun dividedBy(other: Type): Type {
        return NoneType()
    }

}

class NoneType: Type() {

    override fun asString() = "null"

}

class NumberType(val value: Number) : Type() {

    override fun asString() = value.toString()

    override fun addedTo(other: Type): Type {
        if (other is NumberType) {
            if (value is Int && other.value is Int) {
                return NumberType(value + other.value)
            }
            return NumberType(value.toDouble() + other.value.toDouble())
        }
        return NoneType()
    }

    override fun subtractedBy(other: Type): Type {
        if (other is NumberType) {
            if (value is Int && other.value is Int) {
                return NumberType(value - other.value)
            }
            return NumberType(value.toDouble() - other.value.toDouble())
        }
        return NoneType()
    }

    override fun multipliedBy(other: Type): Type {
        if (other is NumberType) {
            if (value is Int && other.value is Int) {
                return NumberType(value * other.value)
            }
            return NumberType(value.toDouble() * other.value.toDouble())
        }
        return NoneType()
    }

    override fun dividedBy(other: Type): Type {
        if (other is NumberType) {
            return NumberType(value.toDouble() / other.value.toDouble())
        }
        return NoneType()
    }

}