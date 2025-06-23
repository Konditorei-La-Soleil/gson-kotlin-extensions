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

inline fun <reified T> typeToken(): TypeToken<T> = object : TypeToken<T>() {}

fun jsonArrayOf(vararg elements: JsonElement?): JsonArray =
    elements.toJsonArray()

fun Array<out JsonElement?>.toJsonArray(
    target: JsonArray = JsonArray(size),
): JsonArray {
    forEach(target::add)
    return target
}

fun Iterable<JsonElement?>.toJsonArray(
    target: JsonArray = if (this is Collection<*>) JsonArray(size) else JsonArray(),
): JsonArray {
    forEach(target::add)
    return target
}

fun Sequence<JsonElement?>.toJsonArray(
    target: JsonArray = JsonArray(),
): JsonArray {
    forEach(target::add)
    return target
}

fun jsonObjectOf(vararg pairs: Pair<String, JsonElement?>) =
    pairs.toJsonObject()

fun Map<String, JsonElement?>.toJsonObject(
    target: JsonObject = JsonObject(),
): JsonObject {
    forEach { target.add(it.key, it.value) }
    return target
}

fun Array<out Pair<String, JsonElement?>>.toJsonObject(
    target: JsonObject = JsonObject(),
): JsonObject {
    forEach { target.add(it.first, it.second) }
    return target
}

fun Iterable<Pair<String, JsonElement?>>.toJsonObject(
    target: JsonObject = JsonObject(),
): JsonObject {
    forEach { target.add(it.first, it.second) }
    return target
}

fun Sequence<Pair<String, JsonElement?>>.toJsonObject(
    target: JsonObject = JsonObject(),
): JsonObject {
    forEach { target.add(it.first, it.second) }
    return target
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