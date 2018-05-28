package com.diegoa.inmovinesrest.entities.inmuebles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

public class ListInmueblesSerializer extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
        Page<Inmuebles> p = (Page<Inmuebles>) o;
            jsonGenerator.writeStartArray();
        for(Inmuebles i: p.getContent()) {

            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", i.getId());
            jsonGenerator.writeStringField("tipo_vivienda", i.getTipos().getNombre());
            jsonGenerator.writeStringField("tipo_gestion", i.getGestiones().getNombre());
            jsonGenerator.writeEndObject();
        }
        jsonGenerator.writeEndArray();

    }
}
