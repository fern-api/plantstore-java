package com.plant.store.api.client.plant.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Locale;

public final class PlantStatus {
  public static final PlantStatus AVAILABLE = new PlantStatus(Value.AVAILABLE, "available");

  public static final PlantStatus SOLD = new PlantStatus(Value.SOLD, "sold");

  public static final PlantStatus PENDING = new PlantStatus(Value.PENDING, "pending");

  private final Value value;

  private final String string;

  PlantStatus(Value value, String string) {
    this.value = value;
    this.string = string;
  }

  public Value getEnumValue() {
    return value;
  }

  @Override
  @JsonValue
  public String toString() {
    return this.string;
  }

  @Override
  public boolean equals(Object other) {
    return (this == other) 
      || (other instanceof PlantStatus && this.string.equals(((PlantStatus) other).string));
  }

  @Override
  public int hashCode() {
    return this.string.hashCode();
  }

  public <T> T visit(Visitor<T> visitor) {
    switch (value) {
      case AVAILABLE:
        return visitor.visitAvailable();
      case SOLD:
        return visitor.visitSold();
      case PENDING:
        return visitor.visitPending();
      case UNKNOWN:
      default:
        return visitor.visitUnknown(string);
    }
  }

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  public static PlantStatus valueOf(String value) {
    String upperCasedValue = value.toUpperCase(Locale.ROOT);
    switch (upperCasedValue) {
      case "available":
        return AVAILABLE;
      case "sold":
        return SOLD;
      case "pending":
        return PENDING;
      default:
        return new PlantStatus(Value.UNKNOWN, upperCasedValue);
    }
  }

  public enum Value {
    AVAILABLE,

    PENDING,

    SOLD,

    UNKNOWN
  }

  public interface Visitor<T> {
    T visitAvailable();

    T visitPending();

    T visitSold();

    T visitUnknown(String unknownType);
  }
}
