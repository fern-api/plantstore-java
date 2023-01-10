package com.plantStore.api.client.owner.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Locale;

public final class OwnerAge {
  public static final OwnerAge CHILD = new OwnerAge(Value.CHILD, "child");

  public static final OwnerAge ADULT = new OwnerAge(Value.ADULT, "adult");

  public static final OwnerAge SENIOR = new OwnerAge(Value.SENIOR, "senior");

  private final Value value;

  private final String string;

  OwnerAge(Value value, String string) {
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
      || (other instanceof OwnerAge && this.string.equals(((OwnerAge) other).string));
  }

  @Override
  public int hashCode() {
    return this.string.hashCode();
  }

  public <T> T visit(Visitor<T> visitor) {
    switch (value) {
      case CHILD:
        return visitor.visitChild();
      case ADULT:
        return visitor.visitAdult();
      case SENIOR:
        return visitor.visitSenior();
      case UNKNOWN:
      default:
        return visitor.visitUnknown(string);
    }
  }

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  public static OwnerAge valueOf(String value) {
    String upperCasedValue = value.toUpperCase(Locale.ROOT);
    switch (upperCasedValue) {
      case "child":
        return CHILD;
      case "adult":
        return ADULT;
      case "senior":
        return SENIOR;
      default:
        return new OwnerAge(Value.UNKNOWN, upperCasedValue);
    }
  }

  public enum Value {
    CHILD,

    ADULT,

    SENIOR,

    UNKNOWN
  }

  public interface Visitor<T> {
    T visitChild();

    T visitAdult();

    T visitSenior();

    T visitUnknown(String unknownType);
  }
}
