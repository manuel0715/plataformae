package com.plataformae.ws.util;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;
import com.plataformae.ws.db.entity.Departamento;
import com.plataformae.ws.db.entity.Municipio;

import java.io.IOException;

public class IdToEntityDeserializer extends JsonDeserializer<Object> {

    @Override
    public Object deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        Long id = p.getLongValue();
        if (ctxt.getActiveView() == Departamento.class) {
            return new Departamento(id);
        } else if (ctxt.getActiveView() == Municipio.class) {
            Municipio municipio = new Municipio();
            municipio.setId(id);
            return municipio;
        }
        return null;
    }

    public IdToEntityDeserializer() {

    }
}
