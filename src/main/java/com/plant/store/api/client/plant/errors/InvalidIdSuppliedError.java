package com.plant.store.api.client.plant.errors;

public final class InvalidIdSuppliedError {
  private InvalidIdSuppliedError() {
  }

  public static InvalidIdSuppliedError of() {
    return new InvalidIdSuppliedError();
  }
}
