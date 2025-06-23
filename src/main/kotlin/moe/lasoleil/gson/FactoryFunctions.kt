package moe.lasoleil.gson

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.TypeAdapter
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

inline fun <reified T> typeToken(): TypeToken<T> = object : TypeToken<T>() {}

inline fun <reified T> TypeAdapter(
    crossinline write: JsonWriter.(value: T?) -> Unit,
    crossinline read: JsonReader.() -> T?
): TypeAdapter<T> = object : TypeAdapter<T>() {
    override fun write(out: JsonWriter, value: T?) = write(out, value)
    override fun read(`in`: JsonReader): T? = read(`in`)
}

fun jsonArrayOf(vararg elements: JsonElement?): JsonArray =
    elements.toJsonArray()

fun jsonObjectOf(vararg pairs: Pair<String, JsonElement?>): JsonObject =
    pairs.toJsonObject()
