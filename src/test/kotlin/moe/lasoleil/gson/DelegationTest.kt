package moe.lasoleil.gson

import com.google.gson.JsonNull
import moe.lasoleil.gson.builder.JsonArray
import moe.lasoleil.gson.delegation.JsonObjectDelegation.Companion.delegate
import moe.lasoleil.gson.builder.JsonObject
import moe.lasoleil.gson.util.map
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertTrue

private val MY_OBJECT = JsonObject {
    "user" {
        "id"(12345.json)
        "name"("Alice".json)
        "active"(true.json)

        "roles"("admin".json, "editor".json)

        "profile" {
            "age"(30.json)
            "gender"('F'.json)
            "contact" {
                "email"("alice@example.com".json)
                "phone"(JsonNull.INSTANCE)  // null JSON value
            }
        }
    }

    "tags".array {
        add("kotlin".json)
        add("dsl".json)
        add("json".json)
    }

    "emptyList" {
        // empty array
    }

    "config" {
        // empty object
    }

    "score"(99.json, 85.json, 92.json)
}


class DelegationTest {

    @Test
    fun `readonly delegation`() {
        val user by MY_OBJECT.delegate().map { it!! }
        assertTrue { user.isJsonObject }
        assertTrue { user["id"].asInt == 12345 }
        assertTrue { user["name"].asString == "Alice" }
        val profile by user.asJsonObject.delegate("profile").map { it!! }
        assertTrue { profile.isJsonObject }
        assertTrue { profile["age"].asInt == 30 }
    }

    @Test
    fun `readwrite delegation`() {
        val copied = MY_OBJECT.deepCopy()
        var tags by copied.delegate()
        assertTrue { tags!!.isJsonArray }
        tags!!.asJsonArray.remove(0)
        assertTrue { copied["tags"].asJsonArray.size() == 2 }
        tags = JsonArray {
            add("kotlin")
        }
        assertEquals("kotlin", copied["tags"][0].asString)
        tags = null
        assertFalse { copied.has("tags") }
    }

}