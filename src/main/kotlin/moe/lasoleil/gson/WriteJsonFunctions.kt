@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.stream.JsonWriter
import moe.lasoleil.gson.util.DEFAULT_GSON
import java.io.Flushable
import java.io.Writer

inline fun Writer.json(gson: Gson = DEFAULT_GSON): JsonWriter =
    gson.newJsonWriter(this)

inline fun Appendable.writeJson(
    jsonElement: JsonElement?,
    gson: Gson = DEFAULT_GSON,
    flush: Boolean = true,
) {
    gson.toJson(jsonElement, this)
    if (flush && this is Flushable) flush()
}

inline fun JsonWriter.writeJson(
    jsonElement: JsonElement?,
    gson: Gson = DEFAULT_GSON,
    flush: Boolean = true,
) {
    gson.toJson(jsonElement, this)
    if (flush) flush()
}

inline fun <reified T> Appendable.writeJson(
    value: T,
    gson: Gson = DEFAULT_GSON,
    flush: Boolean = true,
) {
    gson.toJson(value, typeToken<T>().type, this)
    if (flush && this is Flushable) flush()
}

inline fun <reified T> JsonWriter.writeJson(
    value: T,
    gson: Gson = DEFAULT_GSON,
    flush: Boolean = true,
) {
    gson.toJson(value, typeToken<T>().type, this)
    if (flush) flush()
}
