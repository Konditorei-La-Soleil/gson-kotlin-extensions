package moe.lasoleil.gson.external

import io.netty.buffer.Unpooled
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import moe.lasoleil.gson.util.DEFAULT_GSON
import org.junit.jupiter.api.Test
import java.nio.charset.Charset
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

private data class TestData(val key: String)

class NettyTest {

    private val testGson = DEFAULT_GSON
    private val testCharset = Charset.forName("GBK")
    private val testString = "test string"
    private val testJson = """{"key":"value"}"""
    private val testJsonElement: JsonElement = JsonParser.parseString(testJson)

    @Test
    fun `inputStream should create ByteBufInputStream`() {
        val buffer = Unpooled.wrappedBuffer(testString.toByteArray(testCharset))
        val inputStream = buffer.inputStream()
        assertNotNull(inputStream)
        assertEquals(testString, inputStream.reader(testCharset).readText())
    }

    @Test
    fun `outputStream should create ByteBufOutputStream`() {
        val buffer = Unpooled.buffer()
        val outputStream = buffer.outputStream()
        assertNotNull(outputStream)
        outputStream.writer(testCharset).use { it.write(testString) }
        assertEquals(testString, buffer.toString(testCharset))
    }

    @Test
    fun `fromJson should deserialize object`() {
        val buffer = Unpooled.wrappedBuffer(testJson.toByteArray(testCharset))
        val result: TestData = testGson.fromJson(buffer, testCharset)
        assertEquals("value", result.key)
    }

    @Test
    fun `parseJson with reified type should deserialize object`() {
        val buffer = Unpooled.wrappedBuffer(testJson.toByteArray(testCharset))
        val result: TestData = buffer.parseJson(testCharset, testGson)
        assertEquals("value", result.key)
    }

    @Test
    fun `parseJson without type should return JsonElement`() {
        val buffer = Unpooled.wrappedBuffer(testJson.toByteArray(testCharset))
        val result: JsonElement = buffer.parseJson(testCharset)
        assertTrue(result.isJsonObject)
        assertEquals("value", (result as JsonObject).get("key").asString)
    }

    @Test
    fun `writeJson with JsonElement should serialize to buffer`() {
        val buffer = Unpooled.buffer()
        buffer.writeJson(testJsonElement, testCharset, testGson)
        assertEquals(testJson, buffer.toString(testCharset).trim())
    }

    @Test
    fun `writeJson with object should serialize to buffer`() {
        val testData = TestData("value")
        val buffer = Unpooled.buffer()
        buffer.writeJson(testData, testCharset, testGson)
        assertEquals(testJson, buffer.toString(testCharset).trim())
    }

    @Test
    fun `should use default charset and gson when not specified`() {
        // Test parseJson with defaults
        val buffer1 = Unpooled.wrappedBuffer(testJson.toByteArray(Charsets.UTF_8))
        val result1 = buffer1.parseJson<TestData>()
        assertEquals("value", result1.key)

        // Test writeJson with defaults
        val buffer2 = Unpooled.buffer()
        buffer2.writeJson(testJsonElement)
        assertEquals(testJson, buffer2.toString(Charsets.UTF_8).trim())
    }
}