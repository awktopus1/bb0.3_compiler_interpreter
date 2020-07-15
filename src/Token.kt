/*
Token definitions and Token superclass
 */


// Token types
const val TT_INT          = "INT"
const val TT_FLOAT        = "FLOAT"
const val TT_STRING       = "STRING"
const val TT_IDENTIFIER   = "IDENTIFIER"
const val TT_KEYWORD      = "KEYWORD"
const val TT_PLUS         = "PLUS"
const val TT_MINUS        = "MINUS"
const val TT_MUL          = "MUL"
const val TT_DIV          = "DIV"
const val TT_POW          = "POW"
const val TT_EQ           = "EQ"
const val TT_REF          = "REF"
const val TT_LPAREN       = "LPAREN"
const val TT_RPAREN       = "RPAREN"
const val TT_LBRACKET     = "LBRACKET"
const val TT_RBRACKET     = "RBRACKET"
const val TT_EE           = "EE"
const val TT_NE           = "NE"
const val TT_LT           = "LT"
const val TT_GT           = "GT"
const val TT_LTE          = "LTE"
const val TT_GTE          = "GTE"
const val TT_COMMA        = "COMMA"
const val TT_NEWLINE      = "NEWLINE"
const val TT_TAB          = "TAB"
const val TT_EOF          = "EOF"


// Token class
class Token<T>(val type: String, val value: T? = null) {

    fun asString(): String {
        if (value != null) {
            return "$type:$value"
        }
        return type
    }
}
