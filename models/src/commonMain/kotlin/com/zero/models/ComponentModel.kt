package com.zero.models

import arrow.core.Option
import arrow.core.none
import com.benasher44.uuid.uuid4
import com.zero.models.core.ButtonModel
import com.zero.models.core.CoreComponentModel
import com.zero.models.core.ScreenModel
import com.zero.models.core.TextModel
import com.zero.models.layouts.BoxModel
import com.zero.models.layouts.ColumnModel
import com.zero.models.layouts.LayoutComponentModel
import com.zero.models.layouts.RowModel
import kotlinx.serialization.KSerializer
import kotlinx.serialization.Polymorphic
import kotlinx.serialization.Serializable
import kotlinx.serialization.SerializationException
import kotlinx.serialization.builtins.ListSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.modules.SerializersModule
import kotlinx.serialization.modules.polymorphic
import kotlinx.serialization.modules.subclass

@Polymorphic
@Serializable
abstract class ComponentModel {
    val id: String = uuid4().toString()
}

object ComponentSerializer :
    KSerializer<List<ComponentModel>> by ListSerializer(ComponentModel.serializer())

val ComponentSerializerModule = SerializersModule {
    polymorphic(ComponentModel::class) {
        /* Declare model definitions here */
        polymorphic(CoreComponentModel::class) {
            subclass(ButtonModel::class)
            subclass(ScreenModel::class)
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
    private val jsonBuilder = Json {
        serializersModule = ComponentSerializerModule
    }

    fun fromJsonString(json: Option<String>): Option<List<ComponentModel>> {
        return try {
            json.map {
                jsonBuilder.decodeFromString(ComponentSerializer, it)
            }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            none()
        }
    }

    fun fromJsonObject(json: Option<JsonElement>): Option<List<ComponentModel>> {
        return try {
            json.map {
                jsonBuilder.decodeFromJsonElement(ComponentSerializer, it)
            }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            none()
        }
    }

    fun toJsonObject(components: Option<List<ComponentModel>>): Option<JsonElement> {
        return try {
            components.map {
                jsonBuilder.encodeToJsonElement(ComponentSerializer, it)
            }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            none()
        }
    }

    fun toJsonString(components: Option<List<ComponentModel>>): Option<String> {
        return try {
            components.map {
                jsonBuilder.encodeToString(ComponentSerializer, it)
            }
        } catch (ex: SerializationException) {
            // TODO: Handle or log exception
            none()
        }
    }
}
