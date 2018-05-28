package com.diegoa.inmovinesrest.entities.inmuebles;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;

/**
 * @author Daniel Arroyo
 */
public class InmueblesSerializer extends JsonSerializer {
    @Override
    public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {

        Inmuebles i = (Inmuebles) o;

        //Caracteristicas basicas
        jsonGenerator.writeStartObject();
        jsonGenerator.writeNumberField("id", i.getId());
        jsonGenerator.writeStringField("descripcion", i.getDescripcion());
        jsonGenerator.writeStringField("textoReclamo", i.getTextoReclamo());
        jsonGenerator.writeNumberField("precioCompra", i.getPrecioCompra());
        jsonGenerator.writeNumberField("precioAlquiler", i.getPrecioAlquiler());
        jsonGenerator.writeNumberField("precioTraspaso", i.getPrecioTraspaso());
        jsonGenerator.writeNumberField("precioAlquilerOpcionCompra", i.getPrecioAlquilerOpcionCompra());
        jsonGenerator.writeNumberField("precioAlquilerOpcionCompra", i.getGastosComunidad());
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
        jsonGenerator.writeFieldName("comunidad");
        jsonGenerator.writeObject(i.getLocalizacion().getComunidad());
        //Provincia
        jsonGenerator.writeFieldName("provincia");
        jsonGenerator.writeObject(i.getLocalizacion().getProvincia());
        //Municipio
        jsonGenerator.writeFieldName("poblacion");
        jsonGenerator.writeObject(i.getLocalizacion().getPoblacion());
        //Zona
        jsonGenerator.writeFieldName("zona");
        jsonGenerator.writeObject(i.getLocalizacion().getZona());


        jsonGenerator.writeEndObject();

        jsonGenerator.writeEndObject();


//        {
//            "content": [
//            {
//                "id": 420,
//                    "referenciaCatastral": "55467",
//                    "referencia": "55467",
//                    "descripcion": "dsadfgyesvstv",
//                    "textoReclamo": "fsadfadfadfadfasdf",
//                    "gastosComunidad": 200,
//                    "alturaEdificio": 2,
//                    "precioCompra": 420000,
//                    "precioAlquiler": 420000,
//                    "precioTraspaso": 560000,
//                    "precioAlquilerOpcionCompra": 780,
//                    "tipos": {
//                "id": 1,
//                        "nombre": "Piso",
//                        "activa": 1
//            },
//                "gestiones": {
//                "id": 1,
//                        "nombre": "Venta"
//            },
//                "caracteristicas": {
//                        "nHabitaciones": 4,
//                        "nBanos": 2,
//                        "nAseos": 0,
//                        "m2Utiles": 45,
//                        "m2Construidos": 54,
//                        "m2Terreno": 60,
//                        "estadoConservacion": 1,
//                        "visible": true,
//                        "zonaDeportiva": true,
//                        "amueblado": true,
//                        "garaje": true,
//                        "calefaccion": false,
//                        "aireAcondicionado": false,
//                        "piscina": true,
//                        "jardin": false,
//                        "trastero": true,
//                        "ascensor": true,
//                        "terraza": false,
//                        "pisoBanco": true,
//                        "vpo": false,
//                        "reservado": false,
//                        "eficienciaEnergeticaTipo": "E",
//                        "eficienciaEnergeticaEntramite01": false,
//                        "eficienciaEnergeticaFecvalid": "2018-05-22",
//                        "eficienciaEnergeticaEmisiones": 380,
//                        "orientacionSolar": "Norte",
//                        "suelos": "Parquét",
//                        "carpinteriaExterior": "Climalit",
//                        "carpinteriaInterior": "Roble"
//            },
//                "localizacion": {
//                "comunidad": {
//                    "id": 1,
//                            "slug": "andalucia",
//                            "comunidad": "Andalucía"
//                },
//                "provincia": {
//                    "id": 5,
//                            "slug": "almeria",
//                            "provincia": "Almería"
//                },
//                "zona": {
//                    "id": 2,
//                            "nombre": "prueba1",
//                            "activa": true,
//                            "nombreAdmin": "Diego Alfaro"
//                },
//                "cp": 23456,
//                        "latitud": 6923.234234,
//                        "longitud": 2344.96236,
//                        "direccion": {
//                    "direccionTipoVia": "calle",
//                            "direccionCalle": "mariposas",
//                            "direccionNumero": 3,
//                            "direccionPiso": "3º",
//                            "direccionLetra": "B",
//                            "direccionEscalera": "4"
//                },
//                "poblacion": {
//                    "id": 382,
//                            "municipio": "Adanero",
//                            "slug": "adanero",
//                            "latitud": 40.9459131,
//                            "longitud": -4.6066595
//                }
//            }
//            }

    }
}
