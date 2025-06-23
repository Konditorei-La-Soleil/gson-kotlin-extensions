@file:Suppress("NOTHING_TO_INLINE")

package moe.lasoleil.gson

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * Returns the original [JsonArray] or a new empty one if it's null.
 */
inline fun JsonArray?.orEmpty(): JsonArray =
    this ?: JsonArray(0)

/**
 * Returns the original [JsonObject] or a new empty one if it's null.
 */
inline fun JsonObject?.orEmpty(): JsonObject =
    this ?: JsonObject()

/**
 * Returns the element at [index] of the [JsonArray], or null if [index] is out of range.
 */
inline fun JsonArray.getOrNull(index: Int): JsonElement? =
    if (index in 0 until size()) get(index) else null
