@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson

import com.google.gson.stream.JsonWriter
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
inline fun JsonWriter.obj(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    try {
        beginObject()
        block()
    } finally {
        endObject()
    }
}

@ExperimentalContracts
inline fun JsonWriter.arr(block: () -> Unit) {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }

    try {
        beginArray()
        block()
    } finally {
        endArray()
    }
}
