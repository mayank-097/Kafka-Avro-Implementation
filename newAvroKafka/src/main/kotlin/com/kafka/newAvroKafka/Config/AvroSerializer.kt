package com.kafka.newAvroKafka.Config

import com.sun.prism.PixelFormat
import org.apache.avro.generic.GenericDatumWriter;
import org.apache.avro.generic.GenericRecord;
import org.apache.avro.io.BinaryEncoder;
import org.apache.avro.io.DatumWriter;
import org.apache.avro.io.EncoderFactory;
import org.apache.avro.specific.SpecificRecordBase;
import org.apache.kafka.common.errors.SerializationException
import org.apache.kafka.common.serialization.Serializer;
import org.springframework.context.annotation.Configuration
import org.springframework.messaging.handler.annotation.Payload
import java.io.ByteArrayOutputStream
import java.io.IOException
import javax.xml.bind.DatatypeConverter

class AvroSerializer<T : SpecificRecordBase> : Serializer<T>  {


    override fun serialize(topic: String?, payload: T): ByteArray? {

        var result: ByteArray? = null
        try {

            if(payload != null) {
                var byteArrayOutputStream: ByteArrayOutputStream = ByteArrayOutputStream()
                var binaryEncoder: BinaryEncoder = EncoderFactory.get().binaryEncoder(byteArrayOutputStream, null)
                var datumWriter: DatumWriter<T> = GenericDatumWriter(payload.schema)
                datumWriter.write(payload, binaryEncoder)
                binaryEncoder.flush()
                byteArrayOutputStream.close()

                result = byteArrayOutputStream.toByteArray()
                DatatypeConverter.printHexBinary(result)
            }
        }catch (e:IOException)
        {
            throw SerializationException("can't serialize data $payload for topic $topic")
        }
        return result

        }

    override fun close() {
        super.close()
    }

}
