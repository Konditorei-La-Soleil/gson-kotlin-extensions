@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject

/**
 * Assumes the [JsonElement] is [JsonArray] and get the element by [index].
 *
 * @throws IllegalStateException if the receiver is not [JsonArray]
 * @throws IndexOutOfBoundsException if [index] not in `0 until size`
 *
 * @see JsonArray
 * @see ArrayList
 */
inline operator fun JsonElement.get(index: Int): JsonElement = asJsonArray[index]

/**
 * Assumes the [JsonElement] is [JsonObject] and get the member by [key].
 *
 * @throws IllegalStateException if the receiver is not [JsonObject]
 *
 * @see JsonObject
 */
inline operator fun JsonElement.get(key: String): JsonElement = asJsonObject[key]

/**
 * Wrap of [JsonObject.add]
 */
inline operator fun JsonObject.set(key: String, value: JsonElement?) = add(key, value)

/**
 * Wrap of [JsonObject.addProperty]
 */
inline operator fun JsonObject.set(key: String, value: Boolean?) = addProperty(key, value)

/**
 * Wrap of [JsonObject.addProperty]
 */
inline operator fun JsonObject.set(key: String, value: Number?) = addProperty(key, value)

/**
 * Wrap of [JsonObject.addProperty]
 */
inline operator fun JsonObject.set(key: String, value: Char?) = addProperty(key, value)

/**
 * Wrap of [JsonObject.addProperty]
 */
inline operator fun JsonObject.set(key: String, value: String?) = addProperty(key, value)

/**
 * Creates a new [JsonArray] containing all elements from both sides of the operator.
 *
 * This method shallow copies the elements from original [JsonArray] without modifying them.
 */
operator fun JsonElement.plus(other: JsonArray) =
    JsonArray(1 + other.size()).apply {
        add(this@plus)
        addAll(other)
    }

/**
 * Creates a new [JsonArray] containing all elements from both sides of the operator.
 *
 * This method shallow copies the elements from original [JsonArray] without modifying them.
 */
operator fun JsonArray.plus(other: JsonElement?) =
    JsonArray(size() + 1).apply {
        addAll(this@plus)
        add(other)
    }

/**
 * Creates a new [JsonArray] containing all elements from both sides of the operator.
 *
 * This method shallow copies the elements from original [JsonArray] without modifying them.
 */
operator fun JsonArray.plus(other: JsonArray) =
    JsonArray(size() + other.size()).apply {
        addAll(this@plus)
        addAll(other)
    }

/**
 * Wrap of [JsonArray.add]
 */
operator fun JsonArray.plusAssign(other: JsonElement) = add(other)

/**
 * Wrap of [JsonArray.addAll]
 */
operator fun JsonArray.plusAssign(other: JsonArray) = addAll(other)

/**
 * Creates a new [JsonObject] containing all entries from both sides of the operator.
 *
 * This method shallow copies the elements from original [JsonObject] without modifying them.
 */
operator fun JsonObject.plus(other: JsonObject) =
    JsonObject().apply {
        val entries = entrySet()
        entries.addAll(this@plus.entrySet())
        entries.addAll(other.entrySet())
    }

/**
 * Add all entries of [other] into the receiver.
 *
 * This method shallow copies the elements from original [JsonObject] without modifying them.
 */
operator fun JsonObject.plusAssign(other: JsonObject) {
    other.entrySet().forEach { add(it.key, it.value) }
}
