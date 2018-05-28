package com.diegoa.inmovinesrest.entities.inmuebles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class InmueblesSerializer extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        Inmuebles i = (Inmuebles) o;

        jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id",i.getId());
            jsonGenerator.writeStringField("referencia", i.getReferencia());
            jsonGenerator.writeStringField("descripcion", i.getDescripcion());
            jsonGenerator.writeStringField("textoReclamo", i.getTextoReclamo());
            jsonGenerator.writeStringField("tipo_vivienda", i.getTipos().getNombre());
            jsonGenerator.writeStringField("tipo_gestion", i.getGestiones().getNombre());
            jsonGenerator.writeObjectFieldStart("caracteristicas");
                jsonGenerator.writeObject(i.getCaracteristicas());
            jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();


    }
}
