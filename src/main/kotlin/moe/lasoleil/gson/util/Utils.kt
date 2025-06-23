@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson.util

import com.google.gson.internal.Streams
import java.io.Writer

inline fun Appendable.asWriter(): Writer =
    Streams.writerForAppendable(this)