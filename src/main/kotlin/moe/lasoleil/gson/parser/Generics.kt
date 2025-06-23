package moe.lasoleil.gson.parser

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.stream.JsonReader
import moe.lasoleil.gson.builder.typeToken
import moe.lasoleil.gson.util.DEFAULT_GSON
import moe.lasoleil.gson.util.reader
import okio.BufferedSource
import java.io.Reader
import java.nio.charset.Charset

inline fun <reified T> Gson.fromJson(source: JsonElement): T =
    fromJson(source, typeToken<T>().type)

inline fun <reified T> JsonElement.parse(gson: Gson = DEFAULT_GSON): T =
    gson.fromJson(this)

inline fun <reified T> Gson.fromJson(source: JsonReader): T =
    fromJson(source, typeToken<T>().type)

inline fun <reified T> Gson.fromJson(source: Reader): T =
    fromJson(source, typeToken<T>().type)

inline fun <reified T> Gson.fromJson(source: CharSequence): T =
    fromJson(source.reader())

inline fun <reified T> Gson.fromJson(source: BufferedSource, charset: Charset = Charsets.UTF_8): T =
    fromJson(source.inputStream().reader(charset))

inline fun <reified T> JsonReader.parseJson(gson: Gson = DEFAULT_GSON): T =
    gson.fromJson(this)

inline fun <reified T> Reader.parseJson(gson: Gson = DEFAULT_GSON): T =
    gson.fromJson(this)

inline fun <reified T> CharSequence.parseJson(gson: Gson = DEFAULT_GSON): T =
    gson.fromJson(this)

inline fun <reified T> BufferedSource.parseJson(charset: Charset = Charsets.UTF_8, gson: Gson = DEFAULT_GSON): T =
    gson.fromJson(this, charset)
