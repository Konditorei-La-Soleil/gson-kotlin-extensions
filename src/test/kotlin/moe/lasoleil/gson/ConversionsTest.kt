package moe.lasoleil.gson

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class ConversionsTest {

    @Test
    fun `test primitive json extensions`() {
        // Boolean
        assertEquals(JsonPrimitive(true), true.json)
        assertEquals(JsonPrimitive(false), false.json)

        // Number
        assertEquals(JsonPrimitive(42), 42.json)
        assertEquals(JsonPrimitive(3.14), 3.14.json)

        // Char
        assertEquals(JsonPrimitive('a'), 'a'.json)
        assertEquals(JsonPrimitive('Z'), 'Z'.json)

        // String
        assertEquals(JsonPrimitive("test"), "test".json)
        assertEquals(JsonPrimitive(""), "".json)
    }

    @Test
    fun `test array toJsonArray`() {
        val array = arrayOf(1.json, "test".json, null)
        val jsonArray = array.toJsonArray()

        assertEquals(3, jsonArray.size())
        assertEquals(JsonPrimitive(1), jsonArray[0])
        assertEquals(JsonPrimitive("test"), jsonArray[1])
        assertTrue(jsonArray[2].isJsonNull)

        // Test with existing target
        val target = JsonArray()
        target.add(true.json)
        array.toJsonArray(target)
        assertEquals(4, target.size())
        assertEquals(JsonPrimitive(true), target[0])
    }

    @Test
    fun `test iterable toJsonArray`() {
        val list = listOf(1.json, "test".json, null)
        val jsonArray = list.toJsonArray()

        assertEquals(3, jsonArray.size())
        assertEquals(JsonPrimitive(1), jsonArray[0])
        assertEquals(JsonPrimitive("test"), jsonArray[1])
        assertTrue(jsonArray[2].isJsonNull)

        // Test with existing target
        val target = JsonArray()
        target.add(false.json)
        list.toJsonArray(target)
        assertEquals(4, target.size())
        assertEquals(JsonPrimitive(false), target[0])
    }

    @Test
    fun `test sequence toJsonArray`() {
        val sequence = sequenceOf(1.json, "test".json, null)
        val jsonArray = sequence.toJsonArray()

        assertEquals(3, jsonArray.size())
        assertEquals(JsonPrimitive(1), jsonArray[0])
        assertEquals(JsonPrimitive("test"), jsonArray[1])
        assertTrue(jsonArray[2].isJsonNull)

        // Test with existing target
        val target = JsonArray()
        target.add('a'.json)
        sequence.toJsonArray(target)
        assertEquals(4, target.size())
        assertEquals(JsonPrimitive('a'), target[0])
    }

    @Test
    fun `test map toJsonObject`() {
        val map = mapOf(
            "number" to 1.json,
            "string" to "test".json,
            "null" to null
        )
        val jsonObject = map.toJsonObject()

        assertEquals(3, jsonObject.size())
        assertEquals(JsonPrimitive(1), jsonObject["number"])
        assertEquals(JsonPrimitive("test"), jsonObject["string"])
        assertTrue(jsonObject["null"].isJsonNull)

        // Test with existing target
        val target = JsonObject()
        target.add("bool", true.json)
        map.toJsonObject(target)
        assertEquals(4, target.size())
        assertEquals(JsonPrimitive(true), target["bool"])
    }

    @Test
    fun `test array of pairs toJsonObject`() {
        val array = arrayOf(
            "number" to 1.json,
            "string" to "test".json,
            "null" to null
        )
        val jsonObject = array.toJsonObject()

        assertEquals(3, jsonObject.size())
        assertEquals(JsonPrimitive(1), jsonObject["number"])
        assertEquals(JsonPrimitive("test"), jsonObject["string"])
        assertTrue(jsonObject["null"].isJsonNull)

        // Test with existing target
        val target = JsonObject()
        target.add("bool", false.json)
        array.toJsonObject(target)
        assertEquals(4, target.size())
        assertEquals(JsonPrimitive(false), target["bool"])
    }

    @Test
    fun `test iterable of pairs toJsonObject`() {
        val list = listOf(
            "number" to 1.json,
            "string" to "test".json,
            "null" to null
        )
        val jsonObject = list.toJsonObject()

        assertEquals(3, jsonObject.size())
        assertEquals(JsonPrimitive(1), jsonObject["number"])
        assertEquals(JsonPrimitive("test"), jsonObject["string"])
        assertTrue(jsonObject["null"].isJsonNull)

        // Test with existing target
        val target = JsonObject()
        target.add("char", 'a'.json)
        list.toJsonObject(target)
        assertEquals(4, target.size())
        assertEquals(JsonPrimitive('a'), target["char"])
    }

    @Test
    fun `test sequence of pairs toJsonObject`() {
        val sequence = sequenceOf(
            "number" to 1.json,
            "string" to "test".json,
            "null" to null
        )
        val jsonObject = sequence.toJsonObject()

        assertEquals(3, jsonObject.size())
        assertEquals(JsonPrimitive(1), jsonObject["number"])
        assertEquals(JsonPrimitive("test"), jsonObject["string"])
        assertTrue(jsonObject["null"].isJsonNull)

        // Test with existing target
        val target = JsonObject()
        target.add("double", 3.14.json)
        sequence.toJsonObject(target)
        assertEquals(4, target.size())
        assertEquals(JsonPrimitive(3.14), target["double"])
    }
}