package com.codergm.util

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import java.time.LocalDate
import java.util.*

object CustomSerializers {
    object UuidSerializer : KSerializer<UUID> {
        override val descriptor = PrimitiveSerialDescriptor("UUID", PrimitiveKind.STRING)
        override fun serialize(encoder: Encoder, value: UUID) = encoder.encodeString(value.toString())
        override fun deserialize(decoder: Decoder): UUID = UUID.fromString(decoder.decodeString())
    }

    object LocalDateSerializer : KSerializer<LocalDate> {
        override val descriptor = PrimitiveSerialDescriptor("LocalDate", PrimitiveKind.STRING)
        override fun serialize(encoder: Encoder, value: LocalDate) = encoder.encodeString(value.toString())
        override fun deserialize(decoder: Decoder): LocalDate = LocalDate.parse(decoder.decodeString())
    }
}