@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson.parser

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.JsonParser
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import moe.lasoleil.gson.builder.typeToken
import moe.lasoleil.gson.util.DEFAULT_GSON
import moe.lasoleil.gson.util.reader
import java.io.Reader
import java.io.Writer

inline fun Reader.json(gson: Gson = DEFAULT_GSON): JsonReader =
    gson.newJsonReader(this)

inline fun Writer.json(gson: Gson = DEFAULT_GSON): JsonWriter =
    gson.newJsonWriter(this)

inline fun JsonReader.parseJson(): JsonElement =
    JsonParser.parseReader(this)

inline fun Reader.parseJson(): JsonElement =
    JsonParser.parseReader(this)

inline fun CharSequence.parseJson(): JsonElement =
    reader().parseJson()

inline fun <T> JsonWriter.write(value: T, typeAdapter: TypeAdapter<T>) {
    typeAdapter.write(this, value)
}

inline fun Appendable.writeJson(jsonElement: JsonElement?, gson: Gson = DEFAULT_GSON) {
    gson.toJson(jsonElement, this@writeJson)
}

inline fun JsonWriter.writeJson(jsonElement: JsonElement?, gson: Gson = DEFAULT_GSON) {
    gson.toJson(jsonElement, this@writeJson)
}

inline fun <reified T> Appendable.writeJson(value: T, gson: Gson = DEFAULT_GSON) {
    gson.toJson(value, typeToken<T>().type, this@writeJson)
}

inline fun <reified T> JsonWriter.writeJson(value: T, gson: Gson = DEFAULT_GSON) {
    gson.toJson(value, typeToken<T>().type, this@writeJson)
}
