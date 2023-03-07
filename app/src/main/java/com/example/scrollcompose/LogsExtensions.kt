package com.example.scrollcompose

import android.util.Log

private fun getClassName(anyClass: Any): String {
    val tagLengthLimit = 23
    var name = anyClass::class.java.simpleName
    if (name.isEmpty()) {
        name = "Don't know"
    }

    if (name.length > tagLengthLimit) {
        name = name.substring(0, tagLengthLimit)
    }
    return name
}


fun Any.logE(log: String) {
    Log.e(getClassName(this), log)
}
