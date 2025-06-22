@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson.access

import com.google.gson.JsonElement
import com.google.gson.JsonObject

inline operator fun JsonElement.get(index: Int): JsonElement = asJsonArray[index]

inline operator fun JsonElement.get(key: String): JsonElement = asJsonObject[key]

inline operator fun JsonObject.set(key: String, value: JsonElement?) = add(key, value)

inline operator fun JsonObject.set(key: String, value: Boolean?) = addProperty(key, value)

inline operator fun JsonObject.set(key: String, value: Number?) = addProperty(key, value)

inline operator fun JsonObject.set(key: String, value: Char?) = addProperty(key, value)

inline operator fun JsonObject.set(key: String, value: String?) = addProperty(key, value)
