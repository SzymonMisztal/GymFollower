package com.technosudo.gymfollower.extensions

fun String.appendArguments(
    vararg arguments: Any
): String {

    val matchResult = Regex("/\\{([^}]+)\\}").find(this)
    var result = matchResult?.let {
        this.substringBefore(it.value)
    } ?: this

    for(argument in arguments) {
        result = result.plus('/').plus(argument)
    }
    return result
}