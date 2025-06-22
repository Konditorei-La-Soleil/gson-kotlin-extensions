package moe.lasoleil.gson.builder

import com.google.gson.JsonArray
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract

@DslMarker
annotation class GsonBuilderDsl

@OptIn(ExperimentalContracts::class)
inline fun JsonArray(capacity: Int = 10, block: JsonArrayBuilder.() -> Unit): JsonArray {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val builder = JsonArrayBuilder(capacity)
    block(builder)
    return builder
}

private typealias JsonArrayBuilder = JsonArray

@OptIn(ExperimentalContracts::class)
inline fun JsonObject(block: JsonObjectBuilder.() -> Unit): JsonObject {
    contract {
        callsInPlace(block, InvocationKind.EXACTLY_ONCE)
    }
    val builder = JsonObjectBuilder(JsonObject())
    block(builder)
    return builder.build()
}

@GsonBuilderDsl
@JvmInline
value class JsonObjectBuilder(private val root: JsonObject) {

    operator fun String.invoke(value: JsonElement?) {
        root.add(this, value)
    }

    operator fun String.invoke(vararg values: JsonElement?) =
        invoke(values.toJsonArray())

    inline fun String.array(capacity: Int = 10, block: JsonArrayBuilder.() -> Unit) =
        invoke(JsonArrayBuilder(capacity).apply(block))

    inline operator fun String.invoke(block: JsonObjectBuilder.() -> Unit) =
        invoke(JsonObjectBuilder(JsonObject()).apply(block).build())

    fun String.value(): JsonElement? = root.get(this)

    fun String.dropKey() {
        root.remove(this)
    }

    fun build(): JsonObject = root

}

