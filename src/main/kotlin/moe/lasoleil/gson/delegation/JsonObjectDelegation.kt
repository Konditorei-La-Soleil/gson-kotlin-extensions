package moe.lasoleil.gson.delegation

import com.google.gson.JsonElement
import com.google.gson.JsonObject
import moe.lasoleil.gson.set
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

sealed interface JsonObjectDelegation : ReadWriteProperty<Any?, JsonElement> {

    companion object {
        @JvmStatic
        fun JsonObject.delegate(): JsonObjectDelegation = Anonymous(this)

        @JvmStatic
        fun JsonObject.delegate(key: String): JsonObjectDelegation = Named(this, key)
    }

    private class Named(
        private val jsonObject: JsonObject,
        private val key: String,
    ) : JsonObjectDelegation {
        override fun getValue(thisRef: Any?, property: KProperty<*>): JsonElement {
            return jsonObject[key]
        }
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: JsonElement) {
            jsonObject[key] = value
        }
    }

    private class Anonymous(
        private val jsonObject: JsonObject,
    ) : JsonObjectDelegation {
        override fun getValue(thisRef: Any?, property: KProperty<*>): JsonElement {
            return jsonObject[property.name]
        }
        override fun setValue(thisRef: Any?, property: KProperty<*>, value: JsonElement) {
            jsonObject[property.name] = value
        }
    }
}
