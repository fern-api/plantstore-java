package com.plant.store.api.client.owner;

import com.plant.store.api.client.owner.endpoints.Add;
import com.plant.store.api.client.owner.endpoints.Delete;
import com.plant.store.api.client.owner.exceptions.AddException;
import com.plant.store.api.client.owner.exceptions.DeleteException;
import com.plant.store.api.client.owner.types.PlantOwner;
import com.plant.store.api.core.BearerAuth;
import java.lang.RuntimeException;
import java.lang.String;
import java.util.Optional;

public final class OwnerServiceClient {
  private final OwnerService service;

  private final Optional<BearerAuth> auth;

  public OwnerServiceClient(String url) {
    this.service = OwnerService.getClient(url);
    this.auth = Optional.empty();
  }

  public OwnerServiceClient(String url, BearerAuth auth) {
    this.service = OwnerService.getClient(url);
    this.auth = Optional.of(auth);
  }

  /**
   * <p>Add a new owner as a customer of the store.</p>
   * @param request Wrapper object for the request body that includes any path parameters, query parameters, and headers
   * @throws AddException Exception that wraps all possible endpoint errors 
   * @return PlantOwner
   */
  public PlantOwner add(Add.Request request) throws AddException {
    BearerAuth authValue = request.getAuthOverride().orElseGet(() -> this.auth.orElseThrow(() -> new RuntimeException("Auth is required")));
    return this.service.add(authValue, request.getBody());
  }

  public void delete(Delete.Request request) throws DeleteException {
    BearerAuth authValue = request.getAuthOverride().orElseGet(() -> this.auth.orElseThrow(() -> new RuntimeException("Auth is required")));
    this.service.delete(authValue, request.getOwnerId());
  }
}
