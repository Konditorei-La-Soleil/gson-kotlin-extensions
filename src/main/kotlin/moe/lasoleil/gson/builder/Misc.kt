package moe.lasoleil.gson.builder

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import com.google.gson.reflect.TypeToken
import kotlin.collections.forEach

inline val Boolean.json get() = JsonPrimitive(this)

inline val Number.json get() = JsonPrimitive(this)

inline val Char.json get() = JsonPrimitive(this)

inline val String.json get() = JsonPrimitive(this)

inline fun <reified T> TypeToken(): TypeToken<T> = object : TypeToken<T>() {}

fun JsonArray(vararg elements: JsonElement?): JsonArray =
    elements.toJsonArray()

fun Array<out JsonElement?>.toJsonArray(): JsonArray =
    JsonArray(size).apply {
        this@toJsonArray.forEach(::add)
    }

fun Iterable<JsonElement?>.toJsonArray(): JsonArray =
    (if (this is Collection<*>) JsonArray(size) else JsonArray()).apply {
        this@toJsonArray.forEach(::add)
    }

fun Sequence<JsonElement?>.toJsonArray(): JsonArray =
    JsonArray().apply {
        this@toJsonArray.forEach(::add)
    }

fun Map<String, JsonElement?>.toJsonObject(): JsonObject =
    JsonObject().apply {
        this@toJsonObject.forEach(::add)
    }

fun JsonObject(vararg pairs: Pair<String, JsonElement?>) =
    pairs.toJsonObject()

fun Array<out Pair<String, JsonElement?>>.toJsonObject() =
    JsonObject().apply {
        this@toJsonObject.forEach { add(it.first, it.second) }
    }

fun Iterable<Pair<String, JsonElement?>>.toJsonObject() =
    JsonObject().apply {
        this@toJsonObject.forEach { add(it.first, it.second) }
    }

fun Sequence<Pair<String, JsonElement?>>.toJsonObject() =
    JsonObject().apply {
        this@toJsonObject.forEach { add(it.first, it.second) }
    }

operator fun JsonArray.plus(other: JsonElement?) =
    JsonArray(size() + 1).apply {
        addAll(this@plus)
        add(other)
    }

operator fun JsonArray.plus(other: JsonArray) =
    JsonArray(size() + other.size()).apply {
        addAll(this@plus)
        addAll(other)
    }

operator fun JsonArray.plusAssign(other: JsonElement) = add(other)

operator fun JsonArray.plusAssign(other: JsonArray) = addAll(other)

operator fun JsonObject.plus(other: JsonObject) =
    JsonObject().apply {
        val entries = entrySet()
        entries.addAll(this@plus.entrySet())
        entries.addAll(other.entrySet())
    }

operator fun JsonObject.plusAssign(other: JsonObject) {
    other.entrySet().forEach { add(it.key, it.value) }
}