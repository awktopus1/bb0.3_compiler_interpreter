fun listAsString(list: List<String>): String {
    var result = "["
    list.forEachIndexed { index, item -> 
        if (index == list.size - 1) {
            result += "$item]"
        } else {
            result += "$item, "
        }
    }
    return result
}