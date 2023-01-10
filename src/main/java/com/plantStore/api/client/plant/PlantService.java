package com.plantStore.api.client.plant;

import com.fern.java.jersey.contracts.OptionalAwareContract;
import com.plantStore.api.client.plant.exceptions.AddException;
import com.plantStore.api.client.plant.exceptions.DeleteException;
import com.plantStore.api.client.plant.exceptions.FindException;
import com.plantStore.api.client.plant.types.AddPlantRequest;
import com.plantStore.api.client.plant.types.Plant;
import com.plantStore.api.core.BearerAuth;
import com.plantStore.api.core.ObjectMappers;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import java.lang.String;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/plant")
interface PlantService {
  @POST
  @Path("/")
  void add(@HeaderParam("Authorization") BearerAuth auth, AddPlantRequest body) throws AddException;

  @GET
  @Path("/{plantId}")
  Plant find(@HeaderParam("Authorization") BearerAuth auth, @PathParam("plantId") UUID plantId)
      throws FindException;

  @DELETE
  @Path("/{plantId}")
  void delete(@HeaderParam("Authorization") BearerAuth auth, @PathParam("plantId") UUID plantId)
      throws DeleteException;

  static PlantService getClient(String url) {
    return Feign.builder()
        .contract(new OptionalAwareContract(new JAXRSContract()))
        .decoder(new JacksonDecoder(ObjectMappers.JSON_MAPPER))
        .encoder(new JacksonEncoder(ObjectMappers.JSON_MAPPER))
        .errorDecoder(new PlantServiceErrorDecoder()).target(PlantService.class, url);
  }
}
