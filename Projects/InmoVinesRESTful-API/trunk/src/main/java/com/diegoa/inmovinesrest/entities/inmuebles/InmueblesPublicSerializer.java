package com.diegoa.inmovinesrest.entities.inmuebles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Daniel Arroyo
 */
public class InmueblesPublicSerializer extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        Inmuebles i = (Inmuebles) o;

        jsonGenerator.writeStartObject();

        //Caracteristicas basicas
        jsonGenerator.writeNumberField("id", i.getId());
        jsonGenerator.writeNumberField("referencia", Long.valueOf(i.getReferencia()));
        jsonGenerator.writeStringField("descripcion", i.getDescripcion());
        jsonGenerator.writeStringField("textoReclamo", i.getTextoReclamo());
        jsonGenerator.writeNumberField("gastosComunidad", i.getGastosComunidad());
        jsonGenerator.writeNumberField("alturaEdificio", i.getAlturaEdificio());
        jsonGenerator.writeNumberField("precioCompra", i.getPrecioCompra());
        jsonGenerator.writeNumberField("precioAlquiler", i.getPrecioAlquiler());
        jsonGenerator.writeNumberField("precioTraspaso", i.getPrecioTraspaso());
        jsonGenerator.writeNumberField("precioAlquilerOpcionCompra", i.getPrecioAlquilerOpcionCompra());
        //Tipo
        jsonGenerator.writeObjectFieldStart("tipos");
        jsonGenerator.writeNumberField("id", i.getTipos().getId());
        jsonGenerator.writeStringField("nombre", i.getTipos().getNombre());
        jsonGenerator.writeNumberField("activa", i.getTipos().getActiva());
        jsonGenerator.writeEndObject();
        //Caracteristicas propias
        jsonGenerator.writeFieldName("caracteristicas");
        jsonGenerator.writeObject(i.getCaracteristicas());
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
        jsonGenerator.writeNumberField("idAgente", i.getAgente().getId());
        //Fin
        jsonGenerator.writeEndObject();

    }
}
