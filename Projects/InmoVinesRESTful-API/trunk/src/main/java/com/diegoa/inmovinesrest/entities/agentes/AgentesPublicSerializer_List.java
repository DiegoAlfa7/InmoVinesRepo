package com.diegoa.inmovinesrest.entities.agentes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import org.springframework.data.domain.Page;

import java.io.IOException;
import java.util.List;

/**
 * @author Daniel Arroyo
 */
public class AgentesPublicSerializer_List extends JsonSerializer {


    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        List<Agentes> agentes = (List<Agentes>) o;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeArrayFieldStart("list");

        for (Agentes a : agentes) {

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


        jsonGenerator.writeEndObject();


    }
}
