@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson.util

import com.google.gson.internal.Streams
import java.io.Writer
import kotlin.properties.ReadOnlyProperty
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * Wrap of [Streams.writerForAppendable]
 *
 * @return a wrapped [Writer] of the receiver.
 * If it's already a [Writer], returns itself.
 * @see Streams.AppendableWriter
 */
inline fun Appendable.asWriter(): Writer =
    Streams.writerForAppendable(this)

inline fun <T, R, V> ReadOnlyProperty<T, R>.map(
    crossinline transform: (R) -> V,
) = ReadOnlyProperty<T, V> { thisRef, property ->
    transform(this@map.getValue(thisRef, property))
}

inline fun <T, R, V> ReadWriteProperty<T, R>.map(
    crossinline getter: (R) -> V,
    crossinline setter: (V) -> R,
) = object : ReadWriteProperty<T, V> {
    override fun getValue(thisRef: T, property: KProperty<*>): V {
        return getter(this@map.getValue(thisRef, property))
    }
    override fun setValue(thisRef: T, property: KProperty<*>, value: V) {
        this@map.setValue(thisRef, property, setter(value))
    }
}
