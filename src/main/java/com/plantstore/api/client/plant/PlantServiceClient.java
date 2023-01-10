package com.plantstore.api.client.plant;

import com.plantstore.api.client.plant.endpoints.Add;
import com.plantstore.api.client.plant.endpoints.Delete;
import com.plantstore.api.client.plant.endpoints.Find;
import com.plantstore.api.client.plant.exceptions.AddException;
import com.plantstore.api.client.plant.exceptions.DeleteException;
import com.plantstore.api.client.plant.exceptions.FindException;
import com.plantstore.api.client.plant.types.Plant;
import com.plantstore.api.core.BearerAuth;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.Optional;

public final class PlantServiceClient {
  private final PlantService service;

  private final Optional<BearerAuth> auth;

  public PlantServiceClient(String url) {
    this.service = PlantService.getClient(url);
    this.auth = Optional.empty();
  }

  public PlantServiceClient(String url, BearerAuth auth) {
    this.service = PlantService.getClient(url);
    this.auth = Optional.of(auth);
  }

  /**
   * <p>Add a new plant to the store.</p>
   * @param request Wrapper object for the request body that includes any path parameters, query parameters, and headers
   * @throws AddException Exception that wraps all possible endpoint errors 
   */
  public void add(Add.Request request) throws AddException {
    BearerAuth authValue = request.getAuthOverride().orElseGet(() -> this.auth.orElseThrow(() -> new RuntimeException("Auth is required")));
    this.service.add(authValue, request.getBody());
  }

  public Plant find(Find.Request request) throws FindException {
    BearerAuth authValue = request.getAuthOverride().orElseGet(() -> this.auth.orElseThrow(() -> new RuntimeException("Auth is required")));
    return this.service.find(authValue, request.getPlantId());
  }

  public void delete(Delete.Request request) throws DeleteException {
    BearerAuth authValue = request.getAuthOverride().orElseGet(() -> this.auth.orElseThrow(() -> new RuntimeException("Auth is required")));
    this.service.delete(authValue, request.getPlantId());
  }
}
