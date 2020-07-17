open class Error(val posStart: Position?, val posEnd: Position?, val errorName: String, val details: String) {

    fun asString(): String {
        val fn = posStart!!.fileName
        val ln = posStart.line + 1
        return "$errorName: $details at File $fn, line $ln"
    }

}

class IllegalCharError(posStart: Position?, posEnd: Position?, details: String = "") : Error(posStart, posEnd, "Illegal Character", details) {

}

class InvalidSyntaxError(posStart: Position?, posEnd: Position?, details: String = "") : Error(posStart, posEnd, "Invalid Syntax", details) {

}