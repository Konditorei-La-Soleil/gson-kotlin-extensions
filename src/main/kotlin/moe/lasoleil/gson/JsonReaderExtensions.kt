package moe.lasoleil.gson

import com.google.gson.stream.JsonReader

inline fun JsonReader.obj(block: () -> Unit) {
    try {
        beginObject()
        block()
    } finally {
        endObject()
    }
}

inline fun JsonReader.objEach(block: (name: String) -> Unit) {
    obj {
        while (hasNext()) {
            block(nextName())
        }
    }
}

inline fun JsonReader.arr(block: () -> Unit) {
    try {
        beginArray()
        block()
    } finally {
        endArray()
    }
}

inline fun JsonReader.arrEach(block: () -> Unit) {
    arr {
        while (hasNext()) {
            block()
        }
    }
}
