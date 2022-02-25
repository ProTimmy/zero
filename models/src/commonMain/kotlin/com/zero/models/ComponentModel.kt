package com.zero.models

import com.benasher44.uuid.uuid4
import com.zero.models.core.ButtonModel
import com.zero.models.core.CoreComponentModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.BoxModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.LayoutComponentModel
import com.zero.models.layouts.RowModel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Polymorphic
interface ComponentModel {
    val isRoot: Boolean
    var id: String
}

//object ComponentSerializer :
//    KSerializer<Map<String, ComponentModel>> {
//
//    override val descriptor: SerialDescriptor
//        get() = TODO("Not yet implemented")
//
//    override fun serialize(encoder: Encoder, value: Map<String, ComponentModel>) {
//        encoder.
//    }
//
//    override fun deserialize(decoder: Decoder): Map<String, ComponentModel> {
//        TODO("Not yet implemented")
//    }
//}
//
//val ComponentSerializerModule = SerializersModule {
//    polymorphic(ComponentModel::class) {
//        /* Declare model definitions here */
//        polymorphic(CoreComponentModel::class) {
//            subclass(ButtonModel::class)
//            subclass(TextModel::class)
//        }
//
//        polymorphic(LayoutComponentModel::class) {
//            subclass(BoxModel::class)
//            subclass(ColumnModel::class)
//            subclass(RowModel::class)
//        }
//    }
//}
//
//class ComponentModelEngine {
//    private val jsonBuilder = Json { serializersModule = ComponentSerializerModule }
//
//    fun fromJsonString(json: String?): HashMap<String, ComponentModel>? {
//        return try {
//            json?.let { HashMap(jsonBuilder.decodeFromString(ComponentSerializer, it)) }
//        } catch (ex: SerializationException) {
//            // TODO: Handle or log exception
//            null
//        }
//    }
//
//    fun fromJsonObject(json: JsonElement?): HashMap<String, ComponentModel>? {
//        return try {
//            json?.let { HashMap(jsonBuilder.decodeFromJsonElement(ComponentSerializer, it)) }
//        } catch (ex: SerializationException) {
//            // TODO: Handle or log exception
//            null
//        }
//    }
//
//    fun toJsonObject(components: HashMap<String, ComponentModel>?): JsonElement? {
//        return try {
//            components?.let { jsonBuilder.encodeToJsonElement(ComponentSerializer, it) }
//        } catch (ex: SerializationException) {
//            // TODO: Handle or log exception
//            null
//        }
//    }
//
//    fun toJsonString(components: HashMap<String, ComponentModel>?): String? {
//        return try {
//            components?.let { jsonBuilder.encodeToString(ComponentSerializer, it) }
//        } catch (ex: SerializationException) {
//            // TODO: Handle or log exception
//            null
//        }
//    }
//}
