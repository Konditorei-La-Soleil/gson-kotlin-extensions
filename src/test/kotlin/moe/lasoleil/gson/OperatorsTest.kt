package moe.lasoleil.gson

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class OperatorsTest {

    @Test
    fun `test JsonArray plusAssign`() {
        val array = JsonArray()
        array += 1.json
        assertEquals(1, array.size())
        assertEquals(1, array[0].asInt)
        array += jsonArrayOf(2.json, 3.json)
        assertEquals(3, array.size())
        assertEquals(2, array[1].asInt)
        assertEquals(3, array[2].asInt)
    }

    @Test
    fun `test JsonArray plus`() {
        val array1 = jsonArrayOf(1.json, 2.json)
        val array2 = jsonArrayOf(3.json, 4.json)
        val array3 = array1 + array2 + 5.json
        assertEquals(5, array3.size())
        assertEquals(2, array1.size())
        assertEquals(2, array2.size())
    }

    @Test
    fun `test JsonObject plusAssign`() {
        val obj = JsonObject()
        obj += jsonObjectOf("key1" to 1.json)
        assertEquals(1, obj.size())
        assertEquals(1, obj["key1"].asInt)
    }

    @Test
    fun `test JsonObject plus`() {
        val obj1 = jsonObjectOf("key1" to 1.json)
        val obj2 = jsonObjectOf("key2" to 2.json)
        val obj3 = obj1 + obj2 + jsonObjectOf("key3" to 3.json)
        assertEquals(3, obj3.size())
        assertEquals(1, obj1.size())
        assertEquals(1, obj2.size())
    }


}