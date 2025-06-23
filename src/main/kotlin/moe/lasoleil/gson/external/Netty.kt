@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson.external

import com.google.gson.Gson
import com.google.gson.JsonElement
import io.netty.buffer.ByteBuf
import io.netty.buffer.ByteBufInputStream
import io.netty.buffer.ByteBufOutputStream
import moe.lasoleil.gson.fromJson
import moe.lasoleil.gson.parseJson
import moe.lasoleil.gson.writeJson
import moe.lasoleil.gson.util.DEFAULT_GSON
import java.nio.charset.Charset

inline fun ByteBuf.inputStream(): ByteBufInputStream =
    ByteBufInputStream(this)

inline fun ByteBuf.outputStream(): ByteBufOutputStream =
    ByteBufOutputStream(this)

inline fun <reified T> Gson.fromJson(source: ByteBuf, charset: Charset = Charsets.UTF_8): T =
    fromJson(source.inputStream().reader(charset))

inline fun <reified T> ByteBuf.parseJson(charset: Charset = Charsets.UTF_8, gson: Gson = DEFAULT_GSON): T =
    gson.fromJson(this, charset)

inline fun ByteBuf.parseJson(charset: Charset = Charsets.UTF_8): JsonElement =
    inputStream().reader(charset).parseJson()

inline fun ByteBuf.writeJson(jsonElement: JsonElement?, charset: Charset = Charsets.UTF_8, gson: Gson = DEFAULT_GSON) =
    outputStream().writer(charset).writeJson(jsonElement, gson)

inline fun <reified T> ByteBuf.writeJson(value: T, charset: Charset = Charsets.UTF_8, gson: Gson = DEFAULT_GSON) =
    outputStream().writer(charset).writeJson(value, gson)
