# gson-kotlin-extensions [![](https://jitpack.io/v/Konditorei-La-Soleil/gson-kotlin-extensions.svg)](https://jitpack.io/#Konditorei-La-Soleil/gson-kotlin-extensions)

Kotlin extensions for [Gson](https://github.com/google/gson), including adaptations for [OkHttp](https://github.com/square/okhttp)

Note: now this library is **unstable**. All APIs might be changed/renamed/removed.

`build.gradle.kts`

```kotlin
repositories {
    maven(url = "https://jitpack.io")
}

dependencies {
    implementation("com.github.Konditorei-La-Soleil:gson-kotlin-extensions:$version")
}
```

## Extensions

- Non-threadsafe `Reader` of a `CharSequence`
- Create a JsonPrimitive via `value.json`: `"Hello".json`
- Use `[]` to access a `JsonElement`: `myElement[0]["name"].asInt`
- Use `[]` to `add` and `addProperty` on a `JsonObject`

## Wrapped methods

- `Appendable.asWriter(): Writer`
- `Reader.json(): JsonReader`
- `Writer.json(): JsonWriter`
- `JsonReader.parseJson(): JsonElement`
- `Reader.parseJson(): JsonElement`
- `CharSequence.parseJson(): JsonElement`
- `BufferedSource.parseJson(Charset = UTF_8): JsonElement`
- `JsonWriter.write<T>(T, TypeAdapter<T>)`

- `Gson.fromJson<T>(JsonElement): T`
- `Gson.fromJson<T>(JsonReader): T`
- `Gson.fromJson<T>(Reader): T`
- `Gson.fromJson<T>(CharSequence): T`
- `Gson.fromJson<T>(BufferedSource, Charset = UTF_8): T`

## Builders

- Build a `JsonObject` with DSL:
    ```kotlin
    val json = JsonObject {
        "name"("Vanilla".json)
        "age"(18.json)
        "type" {
            "gender"('F'.json)
        }
    }
    ```
- Create `JsonObject` and `JsonArray` from existing `Array` / `Iterable` / `Sequence` / `Map`
- `plus` and `plusAssign` operators for `JsonObject` and `JsonArray`

## Delegations

- Map a property (readonly/readwrite) with given function, like `Optional.map`
- Delegate a variable or value from a `JsonObject`: `val name by myObject.delegate().map { it.asString }`
