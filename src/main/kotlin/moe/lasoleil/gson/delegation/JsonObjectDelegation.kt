package moe.lasoleil.gson.delegation

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import moe.lasoleil.gson.set
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Delegates a **nullable** [JsonElement] variable/value from a [JsonObject].
 * When the key doesn't exist in the [JsonObject], null will be gotten.
 *
 * `null` value in JSON will represent as [com.google.gson.JsonNull].
 * If `null` is applied via `setValue` method (delegation), this member will be removed from the [JsonObject].
 *
 * Allows named and anonymous delegation.
 *
 * @see [JsonObject]
 * @see [com.google.gson.JsonNull]
 */
sealed interface JsonObjectDelegation : ReadWriteProperty<Any?, JsonElement?> {

    companion object {
        /**
         * Creates an anonymous delegation from the [JsonObject].
         *
         * The name of the member is the variable/value's reflection name.
         *
         * @see KProperty.name
         */
        @JvmStatic
        fun JsonObject.delegate(): JsonObjectDelegation = Anonymous(this)

        /**
         * Creates a named delegation from the [JsonObject].
         *
         * @param key The name of the member
         */
        @JvmStatic
        fun JsonObject.delegate(key: String): JsonObjectDelegation = Named(this, key)
    }

    private class Anonymous(
        private val jsonObject: JsonObject,
    ) : JsonObjectDelegation {
        override fun getValue(thisRef: Any?, property: KProperty<*>): JsonElement? {
            return jsonObject[property.name]
        }
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: JsonElement?) {
            if (value == null) {
                jsonObject.remove(property.name)
            } else {
                jsonObject[property.name] = value
            }
        }
    }

    private class Named(
        private val jsonObject: JsonObject,
        private val key: String,
    ) : JsonObjectDelegation {
        override fun getValue(thisRef: Any?, property: KProperty<*>): JsonElement? {
            return jsonObject[key]
        }
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: JsonElement?) {
            if (value == null) {
                jsonObject.remove(key)
            } else {
                jsonObject[key] = value
            }
        }
    }

}
