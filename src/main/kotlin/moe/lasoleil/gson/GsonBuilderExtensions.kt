package moe.lasoleil.gson

import com.google.gson.GsonBuilder
import com.google.gson.InstanceCreator
import com.google.gson.JsonDeserializer
import com.google.gson.JsonSerializer
import com.google.gson.TypeAdapter

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

