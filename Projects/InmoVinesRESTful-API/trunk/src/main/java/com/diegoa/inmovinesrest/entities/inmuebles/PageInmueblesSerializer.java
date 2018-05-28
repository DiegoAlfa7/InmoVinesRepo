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
        jsonGenerator.writeStringField("offset",String.valueOf(p.getPageable().getOffset()));
        jsonGenerator.writeStringField("pageNumber", String.valueOf(p.getNumber()));
        jsonGenerator.writeStringField("pageSize", String.valueOf(p.getTotalPages()));
        jsonGenerator.writeStringField("paged", String.valueOf(p.getPageable().isPaged()));
        jsonGenerator.writeStringField("unpaged", String.valueOf(p.getPageable().isUnpaged()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("last",String.valueOf(p.isLast()));
        jsonGenerator.writeStringField("totalElements", String.valueOf(p.getTotalElements()));
        jsonGenerator.writeStringField("totalPages", String.valueOf(p.getTotalPages()));
        jsonGenerator.writeStringField("size", String.valueOf(p.getSize()));
        jsonGenerator.writeStringField("number", String.valueOf(p.getNumber()));
        jsonGenerator.writeObjectFieldStart("sort");
        jsonGenerator.writeStringField("unsorted", String.valueOf(!p.getPageable().getSort().isSorted()));
        jsonGenerator.writeStringField("sorted", String.valueOf(p.getPageable().getSort().isSorted()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("numberOfElements",String.valueOf(p.getNumberOfElements()));
        jsonGenerator.writeStringField("firsts", String.valueOf(p.isFirst()));
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
