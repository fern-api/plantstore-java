package com.plant.store.api.client.owner.errors;

public final class OwnerNotFoundError {
  private OwnerNotFoundError() {
  }

  public static OwnerNotFoundError of() {
    return new OwnerNotFoundError();
  }
}
