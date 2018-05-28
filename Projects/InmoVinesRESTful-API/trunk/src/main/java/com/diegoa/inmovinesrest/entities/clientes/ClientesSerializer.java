package com.diegoa.inmovinesrest.entities.clientes;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

public class ClientesSerializer extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        Clientes c = (Clientes) o;

        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", c.getId());
        jsonGenerator.writeStringField("name", c.getDatosPersonales().getNombre());
        jsonGenerator.writeStringField("surname", c.getDatosPersonales().getApellidos());
        jsonGenerator.writeStringField("DNI", c.getDatosPersonales().getDni());
        jsonGenerator.writeStringField("mail", c.getDatosPersonales().getMail());
        jsonGenerator.writeStringField("tlf", c.getDatosPersonales().getTelefono());
        jsonGenerator.writeFieldName("agenteEntrada");
        jsonGenerator.writeStartObject();
        jsonGenerator.writeStringField("nombre", c.getAgenteEntrada().getNombre());
        jsonGenerator.writeStringField("nombre", c.getAgenteEntrada().getApellidos());
        jsonGenerator.writeStringField("nombre", c.getAgenteEntrada().getMail());
        jsonGenerator.writeStringField("nombre", c.getAgenteEntrada().getFacebook());
        jsonGenerator.writeEndObject();
        jsonGenerator.writeEndObject();


    }
}
