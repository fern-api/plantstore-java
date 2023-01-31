package com.plant.store.api;

import com.plant.store.api.client.owner.ownerServiceClient;
import com.plant.store.api.client.plant.plantServiceClient;
import com.plant.store.api.core.BearerAuth;
import com.plant.store.api.core.Environment;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Supplier;

public final class PlantStoreApiClient {
  private final Supplier<ownerServiceClient> ownerServiceClient;

  private final Supplier<plantServiceClient> plantServiceClient;

  public PlantStoreApiClient(BearerAuth auth) {
    this(Environment.PRODUCTION, auth);
  }

  public PlantStoreApiClient(Environment environment, BearerAuth auth) {
    this.ownerServiceClient = memoize(() -> new ownerServiceClient(environment.getUrl(), auth));
    this.plantServiceClient = memoize(() -> new plantServiceClient(environment.getUrl(), auth));
  }

  public final ownerServiceClient owner() {
    return this.ownerServiceClient.get();
  }

  public final plantServiceClient plant() {
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
