package moe.lasoleil.gson.external

import moe.lasoleil.gson.builder.JsonObject
import moe.lasoleil.gson.get
import okio.Buffer
import org.junit.jupiter.api.Test
import java.nio.charset.Charset
import kotlin.test.assertEquals

private val GBK = Charset.forName("gbk")

class OkioTest {

    private data class PairIJ(val a: Int, val b: Long)

    @Test
    fun `test JsonElement write and parse`() {
        val source = Buffer().apply {
            writeJson(JsonObject {
                "example"(255)
                "type"("早上好")
            }, GBK)
        }

        val jsonElement = source.parseJson(GBK)
        assertEquals(255, jsonElement["example"].asInt)
        assertEquals("早上好", jsonElement["type"].asString)
    }

    @Test
    fun `test data class write and parse`() {
        val source = Buffer().apply {
            writeJson(PairIJ(514, 495))
        }
        val pair = source.parseJson<PairIJ>()
        assertEquals(514, pair.a)
        assertEquals(495, pair.b)
    }

}
