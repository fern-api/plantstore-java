package com.plant.store.api.client.plant.errors;

public final class InvalidResponseError {
  private InvalidResponseError() {
  }

  public static InvalidResponseError of() {
    return new InvalidResponseError();
  }
}
