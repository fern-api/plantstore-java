package com.plantstore.api;

import com.plantstore.api.client.owner.OwnerServiceClient;
import com.plantstore.api.client.plant.PlantServiceClient;
import com.plantstore.api.core.BearerAuth;
import com.plantstore.api.core.Environment;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public final class PlantstoreApiClient {
  private final Supplier<OwnerServiceClient> ownerServiceClient;

  private final Supplier<PlantServiceClient> plantServiceClient;

  public PlantstoreApiClient(BearerAuth auth) {
    this(Environment.PRODUCTION, auth);
  }

  public PlantstoreApiClient(Environment environment, BearerAuth auth) {
    this.ownerServiceClient = memoize(() -> new OwnerServiceClient(environment.getUrl(), auth));
    this.plantServiceClient = memoize(() -> new PlantServiceClient(environment.getUrl(), auth));
  }

  public final OwnerServiceClient owner() {
    return this.ownerServiceClient.get();
  }

  public final PlantServiceClient plant() {
    return this.plantServiceClient.get();
  }

  private static <T> Supplier<T> memoize(Supplier<T> delegate) {
    AtomicReference<T> value = new AtomicReference<>();
    return () ->  {
      T val = value.get();
      if (val == null) {
        val = value.updateAndGet(cur -> cur == null ? Objects.requireNonNull(delegate.get()) : cur);
      }
      return val;
    } ;
  }
}
