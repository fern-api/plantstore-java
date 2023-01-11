package com.plant.store.api.client.plant.errors;

public final class PlantNotFoundError {
  private PlantNotFoundError() {
  }

  public static PlantNotFoundError of() {
    return new PlantNotFoundError();
  }
}
