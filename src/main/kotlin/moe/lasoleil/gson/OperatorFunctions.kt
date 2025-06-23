@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

inline operator fun JsonElement.get(index: Int): JsonElement = asJsonArray[index]

inline operator fun JsonElement.get(key: String): JsonElement = asJsonObject[key]

inline operator fun JsonObject.set(key: String, value: JsonElement?) = add(key, value)

inline operator fun JsonObject.set(key: String, value: Boolean?) = addProperty(key, value)

inline operator fun JsonObject.set(key: String, value: Number?) = addProperty(key, value)

inline operator fun JsonObject.set(key: String, value: Char?) = addProperty(key, value)

inline operator fun JsonObject.set(key: String, value: String?) = addProperty(key, value)

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
