/*package com.surbhi.webProject1.pojo;

import org.bson.codecs.Codec;
import org.bson.codecs.configuration.CodecProvider;
import org.bson.codecs.configuration.CodecRegistry;

import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonCodecProvider implements CodecProvider {
    private final ObjectMapper objectMapper;

    public JacksonCodecProvider(final ObjectMapper bsonObjectMapper) {
        this.objectMapper = bsonObjectMapper;
    }

    public <T> Codec<T> get(final Class<T> type, final CodecRegistry registry) {

            return new JacksonCodec<T>(objectMapper, registry, type);

    }

}*/