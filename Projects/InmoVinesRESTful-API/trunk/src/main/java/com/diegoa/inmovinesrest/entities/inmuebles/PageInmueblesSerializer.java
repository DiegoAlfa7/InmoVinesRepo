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

            //Caracteristicas basicas
            jsonGenerator.writeStartObject();
            jsonGenerator.writeNumberField("id", i.getId());
            jsonGenerator.writeStringField("descripcion", i.getDescripcion());
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
            jsonGenerator.writeEndObject();
            //Localizacion del inmueble
            jsonGenerator.writeObjectFieldStart("localizacion");
            //Comunidad
            jsonGenerator.writeObjectFieldStart("comunidad");
            jsonGenerator.writeNumberField("id", i.getLocalizacion().getComunidad().getId());
            jsonGenerator.writeStringField("comunidad", i.getLocalizacion().getComunidad().getComunidad());
            jsonGenerator.writeStringField("slug", i.getLocalizacion().getComunidad().getSlug());
            jsonGenerator.writeEndObject();
            //Provincia
            jsonGenerator.writeObjectFieldStart("provincia");
            jsonGenerator.writeNumberField("id", i.getLocalizacion().getProvincia().getId());
            jsonGenerator.writeStringField("provincia", i.getLocalizacion().getProvincia().getProvincia());
            jsonGenerator.writeStringField("slug", i.getLocalizacion().getProvincia().getSlug());
            jsonGenerator.writeEndObject();
            //Zona
            jsonGenerator.writeObjectFieldStart("zona");
            jsonGenerator.writeNumberField("id", i.getLocalizacion().getZona().getId());
            jsonGenerator.writeStringField("nombre", i.getLocalizacion().getZona().getNombre());
            jsonGenerator.writeStringField("activa", String.valueOf(i.getLocalizacion().getZona().getActiva()));
            jsonGenerator.writeStringField("nombreAdmin", i.getLocalizacion().getZona().getNombreAdmin());
            jsonGenerator.writeEndObject();
            //Poblacion
            jsonGenerator.writeObjectFieldStart("poblacion");
            jsonGenerator.writeNumberField("id", i.getLocalizacion().getPoblacion().getId());
            jsonGenerator.writeStringField("municipio", i.getLocalizacion().getPoblacion().getMunicipio());
            jsonGenerator.writeStringField("slug", i.getLocalizacion().getPoblacion().getSlug());
            jsonGenerator.writeEndObject();

            jsonGenerator.writeEndObject();

            jsonGenerator.writeEndObject();


        }

        jsonGenerator.writeEndArray();

        jsonGenerator.writeObjectFieldStart("pageable");
        jsonGenerator.writeObjectFieldStart("sort");
        jsonGenerator.writeStringField("unsorted", String.valueOf(!p.getPageable().getSort().isSorted()));
        jsonGenerator.writeStringField("sorted", String.valueOf(p.getPageable().getSort().isSorted()));
        jsonGenerator.writeEndObject();
        // TODO esto esta bien??
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
