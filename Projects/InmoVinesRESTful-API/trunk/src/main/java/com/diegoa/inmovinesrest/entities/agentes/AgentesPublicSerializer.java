package com.diegoa.inmovinesrest.entities.agentes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Daniel Arroyo
 */
public class AgentesPublicSerializer extends JsonSerializer {


    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializers) throws IOException {

        Agentes agentes = (Agentes) o;

        jsonGenerator.writeStartObject();

        jsonGenerator.writeNumberField("id", agentes.getId());
        jsonGenerator.writeStringField("nombre", agentes.getNombre());
        jsonGenerator.writeStringField("apellidos", agentes.getApellidos());
        jsonGenerator.writeStringField("mail", agentes.getMail());
        jsonGenerator.writeStringField("tlf", agentes.getTlf());
        jsonGenerator.writeStringField("twitter", agentes.getTwitter());
        jsonGenerator.writeStringField("facebook", agentes.getFacebook());
        jsonGenerator.writeStringField("linkedin", agentes.getLinkedin());
        jsonGenerator.writeStringField("instagram", agentes.getInstagram());
        jsonGenerator.writeStringField("eslogan", agentes.getEslogan());

        jsonGenerator.writeEndObject();


    }
}
