package com.plant.store.api.client.owner;

import com.fern.java.jersey.contracts.OptionalAwareContract;
import com.plant.store.api.client.owner.exceptions.AddException;
import com.plant.store.api.client.owner.exceptions.DeleteException;
import com.plant.store.api.client.owner.types.AddOwnerRequest;
import com.plant.store.api.client.owner.types.PlantOwner;
import com.plant.store.api.core.BearerAuth;
import com.plant.store.api.core.ObjectMappers;
import feign.Feign;
import feign.jackson.JacksonDecoder;
import feign.jackson.JacksonEncoder;
import feign.jaxrs.JAXRSContract;
import java.lang.String;
import java.util.UUID;
import javax.ws.rs.Consumes;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@Path("/owner")
interface OwnerService {
  @POST
  @Path("/")
  PlantOwner add(@HeaderParam("Authorization") BearerAuth auth, AddOwnerRequest body) throws
      AddException;

  @POST
  @Path("/{ownerId}")
  void delete(@HeaderParam("Authorization") BearerAuth auth, @PathParam("ownerId") UUID ownerId)
      throws DeleteException;

  static OwnerService getClient(String url) {
    return Feign.builder()
        .contract(new OptionalAwareContract(new JAXRSContract()))
        .decoder(new JacksonDecoder(ObjectMappers.JSON_MAPPER))
        .encoder(new JacksonEncoder(ObjectMappers.JSON_MAPPER))
        .errorDecoder(new OwnerServiceErrorDecoder()).target(OwnerService.class, url);
  }
}
