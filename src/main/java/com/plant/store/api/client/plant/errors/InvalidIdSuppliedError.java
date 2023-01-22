package com.plant.store.api.client.plant.errors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import com.plant.store.api.client.plant.types.InvalidId;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;

public final class InvalidIdSuppliedError {
  private final InvalidId value;

  private InvalidIdSuppliedError(InvalidId value) {
    this.value = value;
  }

  @JsonValue
  public InvalidId get() {
    return this.value;
  }

  @Override
  public boolean equals(Object other) {
    return this == other || (other instanceof InvalidIdSuppliedError && this.value.equals(((InvalidIdSuppliedError) other).value));
  }

  @Override
  public int hashCode() {
    return value.hashCode();
  }

  @Override
  public String toString() {
    return value.toString();
  }

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  public static InvalidIdSuppliedError of(InvalidId value) {
    return new InvalidIdSuppliedError(value);
  }
}
