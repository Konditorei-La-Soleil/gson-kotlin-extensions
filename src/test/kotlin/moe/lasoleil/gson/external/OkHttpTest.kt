package moe.lasoleil.gson.external

import moe.lasoleil.gson.builder.JsonObject
import moe.lasoleil.gson.get
import okhttp3.MediaType
import okhttp3.RequestBody
import okhttp3.ResponseBody.Companion.asResponseBody
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import java.io.EOFException
import kotlin.test.assertEquals
import kotlin.test.assertFalse

class OkHttpTest {

    private data class Student(
        val id: String,
        val grade: Int,
    )

    @Test
    fun `JSON request body test`() {
        val exampleJson = JsonObject {
            "name"("alice")
            "gender"("female")
        }
        val requestBody = exampleJson.toRequestBody()
        val buffer = okio.Buffer()
        assertFalse { requestBody.isOneShot() }
        requestBody.writeTo(buffer)
        assertEquals("""{"name":"alice","gender":"female"}""", buffer.readUtf8())
        buffer.clear()
        requestBody.writeTo(buffer)
        val readJson = buffer.parseJson()
        assertEquals("alice", readJson["name"].asString)
        assertEquals("female", readJson["gender"].asString)
    }

    @Test
    fun `JSON request body for data class test`() {
        val exampleStudent = Student("001", 6)
        val requestBody = RequestBody.json(exampleStudent)
        val buffer = okio.Buffer()
        assertFalse { requestBody.isOneShot() }
        requestBody.writeTo(buffer)
        assertEquals("""{"id":"001","grade":6}""", buffer.readUtf8())
        buffer.clear()
    }

    @Test
    fun `JSON response body test`() {
        val buffer = okio.Buffer()
        buffer.writeJson(JsonObject {
            "id"("001")
            "grade"(6)
        })
        val responseBody = buffer.asResponseBody(contentType = MediaType.Json, contentLength = buffer.size)
        val readJson = responseBody.parseJson()
        assertEquals("001", readJson["id"].asString)
        assertEquals(6, readJson["grade"].asInt)
        assertThrows<EOFException> { responseBody.source().readByte() }
    }

    @Test
    fun `JSON response body for data class test`() {
        val buffer = okio.Buffer()
        buffer.writeJson(JsonObject {
            "id"("001")
            "grade"(6)
        })
        val responseBody = buffer.asResponseBody(contentType = MediaType.Json, contentLength = buffer.size)
        val student = responseBody.parseJson<Student>()
        assertEquals("001", student.id)
        assertEquals(6, student.grade)
    }

}