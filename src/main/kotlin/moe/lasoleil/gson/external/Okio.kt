@file:Suppress("NOTHING_TO_INLINE")
package moe.lasoleil.gson.external

import com.google.gson.Gson
import com.google.gson.JsonElement
import moe.lasoleil.gson.fromJson
import moe.lasoleil.gson.parseJson
import moe.lasoleil.gson.writeJson
import moe.lasoleil.gson.util.DEFAULT_GSON
import okio.BufferedSink
import okio.BufferedSource
import java.nio.charset.Charset

inline fun <reified T> Gson.fromJson(source: BufferedSource, charset: Charset = Charsets.UTF_8): T =
    fromJson(source.inputStream().reader(charset))

inline fun <reified T> BufferedSource.parseJson(charset: Charset = Charsets.UTF_8, gson: Gson = DEFAULT_GSON): T =
    gson.fromJson(this, charset)

inline fun BufferedSource.parseJson(charset: Charset = Charsets.UTF_8): JsonElement =
    inputStream().reader(charset).parseJson()

inline fun BufferedSink.writeJson(jsonElement: JsonElement?, charset: Charset = Charsets.UTF_8, gson: Gson = DEFAULT_GSON) =
    outputStream().writer(charset).writeJson(jsonElement, gson)

inline fun <reified T> BufferedSink.writeJson(value: T, charset: Charset = Charsets.UTF_8, gson: Gson = DEFAULT_GSON) =
    outputStream().writer(charset).writeJson(value, gson)
