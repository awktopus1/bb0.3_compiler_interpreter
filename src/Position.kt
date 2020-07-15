class Position(var index: Int, var line: Int, var column: Int, val fileName: String, val fileText: String) {

    fun advance(currentCharacter: Char?) {
        index++
        column++

        if (currentCharacter == '\n') {
            line++
            column = 0
        }
    }

    fun copy() = Position(index, line, column, fileName, fileText)
}