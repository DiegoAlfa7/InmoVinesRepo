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

        

    }
}
