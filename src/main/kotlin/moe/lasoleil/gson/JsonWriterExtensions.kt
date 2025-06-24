@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson

import com.google.gson.stream.JsonWriter
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

inline fun JsonWriter.obj(block: () -> Unit) {
    try {
        beginObject()
        block()
    } finally {
        endObject()
    }
}

inline fun JsonWriter.arr(block: () -> Unit) {
    try {
        beginArray()
        block()
    } finally {
        endArray()
    }
}
