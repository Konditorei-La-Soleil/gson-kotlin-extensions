package moe.lasoleil.gson

import com.google.gson.stream.JsonReader
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@ExperimentalContracts
inline fun JsonReader.obj(block: () -> Unit) {
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
inline fun JsonReader.objEach(block: (name: String) -> Unit) {
    obj {
        while (hasNext()) {
            block(nextName())
        }
    }
}

@ExperimentalContracts
inline fun JsonReader.arr(block: () -> Unit) {
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

@OptIn(ExperimentalContracts::class)
inline fun JsonReader.arrEach(block: () -> Unit) {
    arr {
        while (hasNext()) {
            block()
        }
    }
}
