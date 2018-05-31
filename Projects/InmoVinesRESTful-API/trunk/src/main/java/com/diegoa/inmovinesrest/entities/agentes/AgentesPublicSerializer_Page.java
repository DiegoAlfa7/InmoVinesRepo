package com.diegoa.inmovinesrest.entities.agentes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.domain.Page;

import java.io.IOException;

/**
 * @author Daniel Arroyo
 */
public class AgentesPublicSerializer_Page extends JsonSerializer {


    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        Page<Agentes> agentes = (Page<Agentes>) o;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("content");

        for (Agentes a : agentes.getContent()) {

            jsonGenerator.writeStartObject();

            jsonGenerator.writeNumberField("id", a.getId());
            jsonGenerator.writeStringField("nombre", a.getNombre());
            jsonGenerator.writeStringField("apellidos", a.getApellidos());
            jsonGenerator.writeStringField("mail", a.getMail());
            jsonGenerator.writeStringField("tlf", a.getTlf());
            jsonGenerator.writeStringField("twitter", a.getTwitter());
            jsonGenerator.writeStringField("facebook", a.getFacebook());
            jsonGenerator.writeStringField("linkedin", a.getLinkedin());
            jsonGenerator.writeStringField("instagram", a.getInstagram());
            jsonGenerator.writeStringField("foto", a.getFoto());
            jsonGenerator.writeStringField("eslogan", a.getEslogan());
            jsonGenerator.writeStringField("codigo_agente", a.getCodigoAgente());
            jsonGenerator.writeStringField("activacion", String.valueOf(a.getActivacion()));
            jsonGenerator.writeNumberField("permisos", a.getPermisos());
            jsonGenerator.writeNumberField("id_cargo", a.getIdCargo().getIdCargo());
            //Inmuebles

            jsonGenerator.writeEndObject();

        }

        jsonGenerator.writeEndArray();

        jsonGenerator.writeObjectFieldStart("pageable");
        jsonGenerator.writeObjectFieldStart("sort");
        jsonGenerator.writeStringField("unsorted", String.valueOf(!agentes.getPageable().getSort().isSorted()));
        jsonGenerator.writeStringField("sorted", String.valueOf(agentes.getPageable().getSort().isSorted()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeNumberField("offset", agentes.getPageable().getOffset());
        jsonGenerator.writeNumberField("pageNumber", agentes.getNumber());
        jsonGenerator.writeNumberField("pageSize", agentes.getTotalPages());
        jsonGenerator.writeStringField("paged", String.valueOf(agentes.getPageable().isPaged()));
        jsonGenerator.writeStringField("unpaged", String.valueOf(agentes.getPageable().isUnpaged()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("last", String.valueOf(agentes.isLast()));
        jsonGenerator.writeNumberField("totalElements", agentes.getTotalElements());
        jsonGenerator.writeNumberField("totalPages", agentes.getTotalPages());
        jsonGenerator.writeNumberField("size", agentes.getSize());
        jsonGenerator.writeNumberField("number", agentes.getNumber());
        jsonGenerator.writeObjectFieldStart("sort");
        jsonGenerator.writeStringField("unsorted", String.valueOf(!agentes.getPageable().getSort().isSorted()));
        jsonGenerator.writeStringField("sorted", String.valueOf(agentes.getPageable().getSort().isSorted()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeNumberField("numberOfElements", agentes.getNumberOfElements());
        jsonGenerator.writeStringField("first", String.valueOf(agentes.isFirst()));
        jsonGenerator.writeEndObject();


    }
}
