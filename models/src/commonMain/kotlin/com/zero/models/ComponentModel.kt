package com.zero.models

import com.zero.models.core.ButtonModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.BoxModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.RowModel
import kotlinx.serialization.*
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Polymorphic
@Serializable
abstract class ComponentModel {
    abstract val id: String
    abstract val isRoot: Boolean

    internal companion object {
        // Core Components
        const val BUTTON_MODEL = "BUTTON"
        const val TEXT_MODEL = "TEXT"

        // Layout Components
        const val BOX_MODEL = "BOX"
        const val COLUMN_MODEL = "COLUMN"
        const val ROW_MODEL = "ROW"
    }
}

object ComponentSerializer : KSerializer<List<ComponentModel>> by ListSerializer(ComponentModel.serializer())

val ComponentSerializerModule = SerializersModule {
    polymorphic(ComponentModel::class) {
        /* Declare model definitions here */
        // Core Components
        subclass(ButtonModel::class)
        subclass(TextModel::class)

        // Core Components
        subclass(BoxModel::class)
        subclass(ColumnModel::class)
        subclass(RowModel::class)
    }
}

class ComponentModelEngine {
    private val jsonBuilder = Json { serializersModule = ComponentSerializerModule }

    fun fromJsonString(json: String?): List<ComponentModel>? {
        return try {
            json?.let { jsonBuilder.decodeFromString(ComponentSerializer, it) }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            null
        }
    }

    fun fromJsonObject(json: JsonElement?): List<ComponentModel>? {
        return try {
            json?.let { jsonBuilder.decodeFromJsonElement(ComponentSerializer, it) }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            null
        }
    }

    fun toJsonObject(components: List<ComponentModel>?): JsonElement? {
        return try {
            components?.let { jsonBuilder.encodeToJsonElement(ComponentSerializer, it) }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            null
        }
    }

    fun toJsonString(components: List<ComponentModel>?): String? {
        return try {
            components?.let { jsonBuilder.encodeToString(ComponentSerializer, it) }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            null
        }
    }
}
