package moe.lasoleil.gson

import moe.lasoleil.gson.builder.JsonObject
import moe.lasoleil.gson.util.asWriter
import moe.lasoleil.gson.util.reader
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ReadWriteTest {

    @Test
    fun `JsonElement read and write test`() {
        val jsonObject = JsonObject {
            "name"("John")
            "age"(26)
        }
        val buffer = StringBuilder()
        buffer.writeJson(jsonObject)
        val parsedObject = buffer.parseJson()
        assertEquals(jsonObject, parsedObject)
    }

    private data class Person(val name: String, val age: Int)

    @Test
    fun `data class read and write test`() {
        val person = Person("John", 26)
        val buffer = StringBuilder()
        buffer.writeJson(person)
        val parsedPerson = buffer.parseJson<Person>()
        assertEquals(person, parsedPerson)
    }

    @Test
    fun `JsonReader and JsonWriter test`() {
        val jsonObject = JsonObject {
            "name"("John")
            "age"(26)
        }
        val buffer = StringBuilder()
        buffer.asWriter().json().use {
            it.beginObject()
            it.name("name").value("John")
            it.name("age").value(26)
            it.endObject()
        }
        val parsedObject = buffer.reader().json().use {
            it.parseJson()
        }
        assertEquals(jsonObject, parsedObject)
        assertEquals(
            jsonObject.parse<Person>(),
            buffer.reader().json().parseJson<Person>()
        )

        buffer.clear()
        buffer.asWriter().json().use {
            it.writeJson(jsonObject)
        }
        val buffer2 = StringBuilder()
        buffer2.asWriter().json().use {
            it.writeJson(jsonObject.parse<Person>())
        }
        assertEquals("""{"name":"John","age":26}""", buffer2.toString())
        assertEquals(buffer.parseJson(), buffer2.parseJson())
    }

}