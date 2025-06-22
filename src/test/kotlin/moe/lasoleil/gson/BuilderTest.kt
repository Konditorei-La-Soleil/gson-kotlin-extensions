package moe.lasoleil.gson

import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import moe.lasoleil.gson.builder.json
import org.junit.jupiter.api.Test
import kotlin.test.assertEquals

class BuilderTest {

    @Test
    fun `DSL and GSON direct usage should produce identical JsonObject`() {
        // Traditional GSON
        val gsonJson = JsonObject().apply {
            add("id", JsonPrimitive(123))
            add("name", JsonPrimitive("Alice"))
            add("active", JsonPrimitive(true))
            add("rating", JsonPrimitive(4.8))
            add("initial", JsonPrimitive('A'))

            val tags = JsonArray().apply {
                add(JsonPrimitive("kotlin"))
                add(JsonPrimitive("dsl"))
                add(JsonPrimitive("json"))
            }
            add("tags", tags)

            val profile = JsonObject().apply {
                add("email", JsonPrimitive("alice@example.com"))
                add("phone", JsonPrimitive("123-456-7890"))
                add("age", JsonPrimitive(29))
                add("isVerified", JsonPrimitive(false))

                val address = JsonObject().apply {
                    add("city", JsonPrimitive("Wonderland"))
                    add("zip", JsonPrimitive("00000"))
                }
                add("address", address)
            }
            add("profile", profile)

            val skills = JsonArray().apply {
                add(JsonPrimitive("Kotlin"))
                add(JsonPrimitive("Java"))
                add(JsonPrimitive("GSON"))
                add(JsonPrimitive("Coroutines"))
            }
            add("skills", skills)

            // simulate dropKey
            add("deprecatedField", JsonPrimitive("to be removed"))
            remove("deprecatedField")
        }


        // DSL-based JSON creation
        val dslJson = moe.lasoleil.gson.builder.JsonObject {
            "id"(123)
            "name"("Alice")
            "active"(true)
            "rating"(4.8)
            "initial"('A')

            "tags"("kotlin".json, "dsl".json, "json".json)

            "profile" {
                "email"("alice@example.com")
                "phone"("123-456-7890")
                "age"(29)
                "isVerified"(false)

                "address" {
                    "city"("Wonderland")
                    "zip"("00000")
                }
            }

            "skills".array {
                add("Kotlin")
                add("Java")
                add("GSON")
                add("Coroutines")
            }

            // Simulate key removal
            "deprecatedField"("to be removed")
            "deprecatedField".dropKey()
        }


        // Compare the two JSONs
        assertEquals(gsonJson.toString(), dslJson.toString())
    }
}
