@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson.parser

import com.google.gson.JsonElement
import com.google.gson.JsonIOException
import com.google.gson.JsonParser
import com.google.gson.JsonSyntaxException
import com.google.gson.TypeAdapter
import com.google.gson.internal.Streams
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import moe.lasoleil.gson.util.reader
import okio.BufferedSource
import java.io.Reader
import java.io.Writer
import java.nio.charset.Charset

inline fun Reader.json(): JsonReader = JsonReader(this)

inline fun Writer.json(): JsonWriter = JsonWriter(this)

inline fun Appendable.writer(): Writer = Streams.writerForAppendable(this)

@Throws(JsonIOException::class, JsonSyntaxException::class)
inline fun JsonReader.parseJson(): JsonElement =
    JsonParser.parseReader(this)

@Throws(JsonIOException::class, JsonSyntaxException::class)
inline fun Reader.parseJson(): JsonElement =
    JsonParser.parseReader(this)

@Throws(JsonSyntaxException::class)
inline fun CharSequence.parseJson(): JsonElement =
    reader().parseJson()

@Throws(JsonIOException::class, JsonSyntaxException::class)
inline fun BufferedSource.parseJson(charset: Charset = Charsets.UTF_8): JsonElement =
    inputStream().reader(charset).parseJson()

inline fun <T> JsonWriter.write(value: T, typeAdapter: TypeAdapter<T>) = typeAdapter.write(this, value)
