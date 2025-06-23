package moe.lasoleil.gson.builder

import com.google.gson.GsonBuilder
import com.google.gson.InstanceCreator
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer
import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter

inline fun <reified T> GsonBuilder.registerTypeHierarchyAdapter(typeAdapter: TypeAdapter<T>): GsonBuilder =
    registerTypeHierarchyAdapter(T::class.java, typeAdapter)

inline fun <reified T> GsonBuilder.registerTypeHierarchySerializer(typeAdapter: JsonSerializer<T>): GsonBuilder =
    registerTypeHierarchyAdapter(T::class.java, typeAdapter)

inline fun <reified T> GsonBuilder.registerTypeHierarchyDeserializer(typeAdapter: JsonDeserializer<T>): GsonBuilder =
    registerTypeHierarchyAdapter(T::class.java, typeAdapter)

inline fun <reified T> GsonBuilder.registerTypeAdapter(typeAdapter: TypeAdapter<T>): GsonBuilder =
    registerTypeAdapter(typeToken<T>().type, typeAdapter)

inline fun <reified T> GsonBuilder.registerTypeSerializer(typeAdapter: JsonSerializer<T>): GsonBuilder =
    registerTypeAdapter(typeToken<T>().type, typeAdapter)

inline fun <reified T> GsonBuilder.registerTypeDeserializer(typeAdapter: JsonDeserializer<T>): GsonBuilder =
    registerTypeAdapter(typeToken<T>().type, typeAdapter)

inline fun <reified T> GsonBuilder.registerTypeCreator(typeAdapter: InstanceCreator<T>): GsonBuilder =
    registerTypeAdapter(typeToken<T>().type, typeAdapter)

inline fun <reified T> TypeAdapter(
    crossinline write: JsonWriter.(value: T?) -> Unit,
    crossinline read: JsonReader.() -> T?
): TypeAdapter<T> = object : TypeAdapter<T>() {
    override fun write(out: JsonWriter, value: T?) = write(out, value)
    override fun read(`in`: JsonReader): T? = read(`in`)
}
