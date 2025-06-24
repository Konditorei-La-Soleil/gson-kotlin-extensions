package moe.lasoleil.gson

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import kotlin.collections.forEach

inline val Boolean.json get() = JsonPrimitive(this)

inline val Number.json get() = JsonPrimitive(this)

inline val Char.json get() = JsonPrimitive(this)

inline val String.json get() = JsonPrimitive(this)

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

fun Map<String, JsonElement?>.toJsonObject(
    target: JsonObject = JsonObject(),
): JsonObject {
    forEach { target.add(it.key, it.value) }
    return target
}

@JvmName("toJsonObjectWithEntries")
fun Array<out Map.Entry<String, JsonElement?>>.toJsonObject(
    target: JsonObject = JsonObject(),
): JsonObject {
    forEach { target.add(it.key, it.value) }
    return target
}

@JvmName("toJsonObjectWithEntries")
fun Iterable<Map.Entry<String, JsonElement?>>.toJsonObject(
    target: JsonObject = JsonObject(),
): JsonObject {
    forEach { target.add(it.key, it.value) }
    return target
}

@JvmName("toJsonObjectWithEntries")
fun Sequence<Map.Entry<String, JsonElement?>>.toJsonObject(
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
