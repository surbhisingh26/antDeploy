/*package com.surbhi.webProject1.pojo;

import java.io.IOException;
import java.io.UncheckedIOException;

import org.bson.BsonReader;
import org.bson.BsonWriter;
import org.bson.RawBsonDocument;
import org.bson.codecs.Codec;
import org.bson.codecs.DecoderContext;
import org.bson.codecs.EncoderContext;
import org.bson.codecs.configuration.CodecRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;

class JacksonCodec<T> implements Codec<T> {
    private final ObjectMapper objectMapper;
    private final Codec<RawBsonDocument> rawBsonDocumentCodec;
    private final Class<T> type;

    public JacksonCodec(ObjectMapper objectMapper,
                        CodecRegistry codecRegistry,
                        Class<T> type) {
        this.objectMapper = objectMapper;
        this.rawBsonDocumentCodec = codecRegistry.get(RawBsonDocument.class);
        this.type = type;
    }

    public T decode(BsonReader reader, DecoderContext decoderContext) {
        try {

            RawBsonDocument document = rawBsonDocumentCodec.decode(reader, decoderContext);
            String json = document.toJson();
            return objectMapper.readValue(json, type);
        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public void encode(BsonWriter writer, Object value, EncoderContext encoderContext) {
        try {

            String json = objectMapper.writeValueAsString(value);

            rawBsonDocumentCodec.encode(writer, RawBsonDocument.parse(json), encoderContext);

        } catch (IOException e) {
            throw new UncheckedIOException(e);
        }
    }

    public Class<T> getEncoderClass() {
        return this.type;
    }
}
*/