package moe.lasoleil.gson.external

import com.google.gson.Gson
import com.google.gson.JsonElement
import moe.lasoleil.gson.parseJson
import moe.lasoleil.gson.util.DEFAULT_GSON
import okhttp3.MediaType
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody
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
): RequestBody {
    val buffer = okio.Buffer()
    buffer.writeJson(this, charset, gson)
    return object : RequestBody() {
        override fun contentType() = contentType
        override fun contentLength() = buffer.size
        override fun writeTo(sink: BufferedSink) {
            // Allows reading multiple times
            sink.writeAll(buffer.clone())
        }
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
