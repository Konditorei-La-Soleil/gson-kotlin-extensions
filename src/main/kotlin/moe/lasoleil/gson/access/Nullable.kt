@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson.access

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

inline fun JsonArray?.orEmpty(): JsonArray = this ?: JsonArray(0)

inline fun JsonObject?.orEmpty(): JsonObject = this ?: JsonObject()

inline fun JsonArray.getOrNull(index: Int): JsonElement? = if (index in 0 until size()) get(index) else null
