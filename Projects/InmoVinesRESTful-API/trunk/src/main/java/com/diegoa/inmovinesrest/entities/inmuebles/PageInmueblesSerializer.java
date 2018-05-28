package com.diegoa.inmovinesrest.entities.inmuebles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.domain.Page;

import java.io.IOException;

public class PageInmueblesSerializer extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {


        Page<Inmuebles> p = (Page<Inmuebles>) o;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("content");

        for (Inmuebles i : p.getContent()) {

            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", i.getId());


            jsonGenerator.writeStringField("tipo_vivienda", i.getTipos().getNombre());
            jsonGenerator.writeStringField("tipo_gestion", i.getGestiones().getNombre());
            jsonGenerator.writeEndObject();
        }

        jsonGenerator.writeEndArray();

        jsonGenerator.writeObjectFieldStart("pageable");
        jsonGenerator.writeObjectFieldStart("sort");
        jsonGenerator.writeStringField("unsorted", String.valueOf(!p.getPageable().getSort().isSorted()));
        jsonGenerator.writeStringField("sorted", String.valueOf(p.getPageable().getSort().isSorted()));
        jsonGenerator.writeEndObject();
        // TODO esto esta bien??
        jsonGenerator.writeNumberField("offset",p.getPageable().getOffset());
        jsonGenerator.writeNumberField("pageNumber", p.getNumber());
        jsonGenerator.writeNumberField("pageSize", p.getTotalPages());
        jsonGenerator.writeStringField("paged", String.valueOf(p.getPageable().isPaged()));
        jsonGenerator.writeStringField("unpaged", String.valueOf(p.getPageable().isUnpaged()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("last",String.valueOf(p.isLast()));
        jsonGenerator.writeNumberField("totalElements", p.getTotalElements());
        jsonGenerator.writeNumberField("totalPages",p.getTotalPages());
        jsonGenerator.writeNumberField("size", p.getSize());
        jsonGenerator.writeNumberField("number", p.getNumber());
        jsonGenerator.writeObjectFieldStart("sort");
        jsonGenerator.writeStringField("unsorted", String.valueOf(!p.getPageable().getSort().isSorted()));
        jsonGenerator.writeStringField("sorted", String.valueOf(p.getPageable().getSort().isSorted()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeNumberField("numberOfElements",p.getNumberOfElements());
        jsonGenerator.writeStringField("first", String.valueOf(p.isFirst()));
        jsonGenerator.writeEndObject();





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
            "first": true

        */


    }
}
