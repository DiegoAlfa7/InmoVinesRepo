package com.diegoa.inmovinesrest.entities.inmuebles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.domain.Page;

import java.io.IOException;

public class InmueblesPublicSerializerCard_Page extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {


        Page<Inmuebles> p = (Page<Inmuebles>) o;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("content");

        for (Inmuebles i : p.getContent()) {

            //Caracteristicas basicas
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", i.getId());
            jsonGenerator.writeStringField("textoReclamo", i.getTextoReclamo());
            jsonGenerator.writeNumberField("gastosComunidad", i.getGastosComunidad());
            jsonGenerator.writeStringField("tipo_vivienda", i.getTipos().getNombre());
            jsonGenerator.writeStringField("tipo_gestion", i.getGestiones().getNombre());
            //Caracteristicas principales del inmueble
            jsonGenerator.writeObjectFieldStart("caracteristicas");
            jsonGenerator.writeNumberField("nHabitaciones", i.getCaracteristicas().getnHabitaciones());
            jsonGenerator.writeNumberField("nBanos", i.getCaracteristicas().getnBanos());
            jsonGenerator.writeNumberField("m2Utiles", i.getCaracteristicas().getM2Utiles());
            jsonGenerator.writeStringField("garaje", String.valueOf(i.getCaracteristicas().getGaraje()));
            jsonGenerator.writeStringField("ascensor", String.valueOf(i.getCaracteristicas().getAscensor()));
            jsonGenerator.writeStringField("eficienciaEnergeticaTipo", i.getCaracteristicas().getEficienciaEnergeticaTipo());
            jsonGenerator.writeEndObject();
            //Localizacion del inmueble
            jsonGenerator.writeObjectFieldStart("localizacion");
            //Comunidad
            jsonGenerator.writeFieldName("comunidad");
            jsonGenerator.writeObject(i.getLocalizacion().getComunidad());
            //Provincia
            jsonGenerator.writeFieldName("provincia");
            jsonGenerator.writeObject(i.getLocalizacion().getProvincia());
            //Zona
            jsonGenerator.writeFieldName("zona");
            jsonGenerator.writeObject(i.getLocalizacion().getZona());
            //Fin localizacion
            jsonGenerator.writeEndObject();
            //Agente
            jsonGenerator.writeObjectFieldStart("agente");
            jsonGenerator.writeNumberField("id", i.getAgente().getId());

            jsonGenerator.writeEndObject();

            jsonGenerator.writeEndObject();

        }

        jsonGenerator.writeEndArray();

        jsonGenerator.writeObjectFieldStart("pageable");
        jsonGenerator.writeObjectFieldStart("sort");
        jsonGenerator.writeStringField("unsorted", String.valueOf(!p.getPageable().getSort().isSorted()));
        jsonGenerator.writeStringField("sorted", String.valueOf(p.getPageable().getSort().isSorted()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeNumberField("offset", p.getPageable().getOffset());
        jsonGenerator.writeNumberField("pageNumber", p.getNumber());
        jsonGenerator.writeNumberField("pageSize", p.getTotalPages());
        jsonGenerator.writeStringField("paged", String.valueOf(p.getPageable().isPaged()));
        jsonGenerator.writeStringField("unpaged", String.valueOf(p.getPageable().isUnpaged()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeStringField("last", String.valueOf(p.isLast()));
        jsonGenerator.writeNumberField("totalElements", p.getTotalElements());
        jsonGenerator.writeNumberField("totalPages", p.getTotalPages());
        jsonGenerator.writeNumberField("size", p.getSize());
        jsonGenerator.writeNumberField("number", p.getNumber());
        jsonGenerator.writeObjectFieldStart("sort");
        jsonGenerator.writeStringField("unsorted", String.valueOf(!p.getPageable().getSort().isSorted()));
        jsonGenerator.writeStringField("sorted", String.valueOf(p.getPageable().getSort().isSorted()));
        jsonGenerator.writeEndObject();

        jsonGenerator.writeNumberField("numberOfElements", p.getNumberOfElements());
        jsonGenerator.writeStringField("first", String.valueOf(p.isFirst()));
        jsonGenerator.writeEndObject();

    }
}
