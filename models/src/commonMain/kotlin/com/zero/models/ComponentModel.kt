package com.zero.models

import arrow.core.Option
import arrow.core.none
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
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Polymorphic
@Serializable
abstract class ComponentModel {
    abstract val isRoot: Boolean
    var id: String = uuid4().toString() // TODO: val instead of var
}

object ComponentSerializer :
    KSerializer<Map<String, ComponentModel>> by MapSerializer(
        String.serializer(), ComponentModel.serializer()
    )

val ComponentSerializerModule = SerializersModule {
    polymorphic(ComponentModel::class) {
        /* Declare model definitions here */
        polymorphic(CoreComponentModel::class) {
            subclass(ButtonModel::class)
            subclass(TextModel::class)
        }

        polymorphic(LayoutComponentModel::class) {
            subclass(BoxModel::class)
            subclass(ColumnModel::class)
            subclass(RowModel::class)
        }
    }
}

class ComponentModelEngine {
    private val jsonBuilder = Json { serializersModule = ComponentSerializerModule }

    fun fromJsonString(json: Option<String>): Option<HashMap<String, ComponentModel>> {
        return try {
            json.map { HashMap(jsonBuilder.decodeFromString(ComponentSerializer, it)) }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            none()
        }
    }

    fun fromJsonObject(json: Option<JsonElement>): Option<HashMap<String, ComponentModel>> {
        return try {
            json.map { HashMap(jsonBuilder.decodeFromJsonElement(ComponentSerializer, it)) }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            none()
        }
    }

    fun toJsonObject(components: Option<HashMap<String, ComponentModel>>): Option<JsonElement> {
        return try {
            components.map { jsonBuilder.encodeToJsonElement(ComponentSerializer, it) }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            none()
        }
    }

    fun toJsonString(components: Option<HashMap<String, ComponentModel>>): Option<String> {
        return try {
            components.map { jsonBuilder.encodeToString(ComponentSerializer, it) }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            none()
        }
    }
}
