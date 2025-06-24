package moe.lasoleil.gson

import moe.lasoleil.gson.util.asWriter
import moe.lasoleil.gson.util.reader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReaderWriterTest {

    private data class TestObject(
        val name: String,
        val age: Int,
        val jobs: List<String>,
    )

    @Test
    fun `test JsonWriter extensions`() {
        val buffer = StringBuilder(32)
        buffer.asWriter().json().use {
            it.obj {
                it.name("name").value("Hans")
                it.name("age").value(42)
                it.name("jobs").arr {
                    it.value("Developer")
                    it.value("Teacher")
                }
            }
        }
        assertEquals(
            TestObject(
                name = "Hans",
                age = 42,
                jobs = listOf("Developer", "Teacher")
            ),
            buffer.parseJson<TestObject>()
        )
    }

    @Test
    fun `test JsonReader extensions`() {
        val json = """
            {
                "name": "Hans",
                "age": 42,
                "jobs": ["Developer", "Teacher"]
            }
        """.trimIndent()
        val jsonData = json.reader().json().use {
            var name = ""
            var age = 0
            val jobs = mutableListOf<String>()
            it.objEach { key ->
                when (key) {
                    "name" -> name = it.nextString()
                    "age" -> age = it.nextInt()
                    "jobs" -> it.arrEach { jobs += it.nextString() }
                }
            }
            TestObject(
                name = name,
                age = age,
                jobs = jobs
            )
        }
        assertEquals(jsonData, json.parseJson<TestObject>())
    }

}