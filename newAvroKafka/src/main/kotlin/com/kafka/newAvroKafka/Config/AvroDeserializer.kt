package com.kafka.newAvroKafka.Config

import org.apache.avro.generic.GenericRecord
import org.apache.avro.io.DatumReader
import org.apache.avro.io.Decoder
import org.apache.avro.io.DecoderFactory
import org.apache.avro.specific.SpecificDatumReader
import org.apache.avro.specific.SpecificRecordBase
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Deserializer
import java.lang.Exception

class AvroDeserializer<T : SpecificRecordBase>(
    var targetType : Class<T>
) :Deserializer<T>{

    override fun configure(configs: MutableMap<String, *>?, isKey: Boolean) {
        super.configure(configs, isKey)
    }

    override fun deserialize(topic: String?, bytes: ByteArray?): T? {
        var returnObj : T? = null
        try {
            if (bytes!=null) {
                var datumReader: DatumReader<GenericRecord> = SpecificDatumReader(targetType.newInstance().getSchema())
                var decoder: Decoder = DecoderFactory.get().binaryDecoder(bytes, null)
                returnObj = datumReader.read(null, decoder) as T
            }

            }catch (e:Exception)
        {
            throw SerializationException("Unable to Deserialize data $bytes")
        }
        return returnObj
    }

    override fun close() {
        super.close()
    }


}