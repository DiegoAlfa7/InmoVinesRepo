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

       jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("content");

            for(Inmuebles i: p.getContent()) {

                jsonGenerator.writeStartObject();
                jsonGenerator.writeNumberField("id", i.getId());
                jsonGenerator.writeStringField("tipo_vivienda", i.getTipos().getNombre());
                jsonGenerator.writeStringField("tipo_gestion", i.getGestiones().getNombre());
                jsonGenerator.writeEndObject();
            }

            jsonGenerator.writeEndArray();

       /* "pageable": {
            "sort": {
                "unsorted": true,
                        "sorted": false
            },
            "offset": 0,
                    "pageNumber": 0,
                    "pageSize": 20,
                    "paged": true,
                    "unpaged": false
        },
        "last": true,
                "totalElements": 1,
                "totalPages": 1,
                "size": 20,
                "number": 0,
                "sort": {
            "unsorted": true,
                    "sorted": false
        },
        "numberOfElements": 1,
                "first": true*/
        jsonGenerator.writeEndObject();


    }
}
