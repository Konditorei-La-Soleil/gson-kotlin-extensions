package moe.lasoleil.gson

import moe.lasoleil.gson.access.JsonObjectDelegation.Companion.delegate
import moe.lasoleil.gson.access.get
import moe.lasoleil.gson.builder.JsonObject
import moe.lasoleil.gson.builder.json
import org.junit.jupiter.api.Test
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
                "phone"(null)  // null JSON value
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


class AccessTest {

    @Test
    fun `readonly delegation`() {
        val user by MY_OBJECT.delegate()
        assertTrue { user.isJsonObject }
        assertTrue { user["id"].asInt == 12345 }
        assertTrue { user["name"].asString == "Alice" }
        val profile by user.asJsonObject.delegate("profile")
        assertTrue { profile.isJsonObject }
        assertTrue { profile["age"].asInt == 30 }
    }

    @Test
    fun `readwrite delegation`() {
        val copied = MY_OBJECT.deepCopy()
        var tags by copied.delegate()
        assertTrue { tags.isJsonArray }
        tags.asJsonArray.remove(0)
        assertTrue { copied["tags"].asJsonArray.size() == 2 }
    }

}