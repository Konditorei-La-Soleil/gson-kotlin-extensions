package moe.lasoleil.gson.external

import com.google.gson.Gson
import com.google.gson.JsonElement
import moe.lasoleil.gson.parseJson
import moe.lasoleil.gson.util.DEFAULT_GSON
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
import okio.Buffer
import okio.BufferedSink
import java.nio.charset.Charset

private object MediaTypes {
    val Json = "application/json".toMediaType()
}

/**
 * MediaType: application/json
 */
val MediaType.Companion.Json get() = MediaTypes.Json

/**
 * Converts a nullable [JsonElement] into [RequestBody] with given [Charset] and [Gson].
 */
fun JsonElement?.toRequestBody(
    contentType: MediaType? = MediaType.Json,
    charset: Charset = Charsets.UTF_8,
    gson: Gson = DEFAULT_GSON,
): RequestBody = Buffer().also { it.writeJson(this, charset, gson) }.asRequestBody(contentType)

/**
 * Converts a nullable [T] into [RequestBody] with given [Charset] and [Gson].
 */
inline fun <reified T> RequestBody.Companion.json(
    value: T?,
    contentType: MediaType? = MediaType.Json,
    charset: Charset = Charsets.UTF_8,
    gson: Gson = DEFAULT_GSON,
): RequestBody = Buffer().also { it.writeJson(value, charset, gson) }.asRequestBody(contentType)

/**
 * Creates a [RequestBody] from [Buffer].
 *
 * All modifications to the buffer will be reflected in the request body.
 *
 * @param contentType The content type of the request body.
 * @return A [RequestBody] that reads from the given [Buffer], and allows reading multiple times.
 */
fun Buffer.asRequestBody(
    contentType: MediaType? = null,
): RequestBody = object : RequestBody() {
    override fun contentType() = contentType
    override fun contentLength() = size
    override fun writeTo(sink: BufferedSink) {
        // Allows reading multiple times
        sink.writeAll(copy())
    }
}

/**
 * Parse the JSON content of [RequestBody] into [JsonElement] and **close it**.
 */
fun ResponseBody.parseJson(): JsonElement =
    charStream().use { it.parseJson() }

/**
 * Parse the JSON content of [RequestBody] into [T] and **close it**.
 */
inline fun <reified T> ResponseBody.parseJson(gson: Gson = DEFAULT_GSON): T =
    charStream().use { it.parseJson(gson) }
