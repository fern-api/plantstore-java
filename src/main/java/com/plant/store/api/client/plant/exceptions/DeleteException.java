package com.plant.store.api.client.plant.exceptions;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.plant.store.api.client.plant.errors.InvalidIdSuppliedError;
import com.plant.store.api.client.plant.errors.PlantNotFoundError;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(
    using = DeleteException.Deserializer.class
)
public final class DeleteException extends Exception {
  private final Value value;

  private int statusCode;

  private DeleteException(Value value, int statusCode) {
    this.value = value;
    this.statusCode = statusCode;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public static DeleteException plantNotFoundError(PlantNotFoundError value) {
    return new DeleteException(new PlantNotFoundErrorValue(value), 404);
  }

  public static DeleteException invalidIdSuppliedError(InvalidIdSuppliedError value) {
    return new DeleteException(new InvalidIdSuppliedErrorValue(value), 400);
  }

  public static DeleteException other(Object unknownValue, int statusCode) {
    return new DeleteException(new UnknownErrorValue(unknownValue), statusCode);
  }

  public boolean isPlantNotFoundError() {
    return value instanceof PlantNotFoundErrorValue;
  }

  public boolean isInvalidIdSuppliedError() {
    return value instanceof InvalidIdSuppliedErrorValue;
  }

  public boolean _isOther() {
    return value instanceof UnknownErrorValue;
  }

  public Optional<PlantNotFoundError> getPlantNotFoundError() {
    if (isPlantNotFoundError()) {
      return Optional.of(((PlantNotFoundErrorValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<InvalidIdSuppliedError> getInvalidIdSuppliedError() {
    if (isInvalidIdSuppliedError()) {
      return Optional.of(((InvalidIdSuppliedErrorValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<Object> _getOther() {
    if (_isOther()) {
      return Optional.of(((UnknownErrorValue) value).unknownValue);
    }
    return Optional.empty();
  }

  public interface Visitor<T> {
    T visitPlantNotFoundError(PlantNotFoundError plantNotFoundError);

    T visitInvalidIdSuppliedError(InvalidIdSuppliedError invalidIdSuppliedError);

    T _visitOther(Object otherType);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "_error",
      visible = true,
      defaultImpl = UnknownErrorValue.class
  )
  @JsonSubTypes({
      @JsonSubTypes.Type(PlantNotFoundErrorValue.class),
      @JsonSubTypes.Type(InvalidIdSuppliedErrorValue.class)
  })
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("PlantNotFoundError")
  private static final class PlantNotFoundErrorValue implements Value {
    private PlantNotFoundError value;

    private String errorInstanceId;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private PlantNotFoundErrorValue(@JsonProperty("value") PlantNotFoundError value,
        @JsonProperty("_errorInstanceId") String errorInstanceId) {
      this.value = value;
      this.errorInstanceId = errorInstanceId;
    }

    private PlantNotFoundErrorValue(PlantNotFoundError value) {
      this.value = value;
      this.errorInstanceId = UUID.randomUUID().toString();
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitPlantNotFoundError(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof PlantNotFoundErrorValue && equalTo((PlantNotFoundErrorValue) other);
    }

    private boolean equalTo(PlantNotFoundErrorValue other) {
      return value.equals(other.value) && errorInstanceId.equals(other.errorInstanceId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value, this.errorInstanceId);
    }

    @Override
    public String toString() {
      return "DeleteException{" + "value: " + value + ", errorInstanceId: " + errorInstanceId + "}";
    }
  }

  @JsonTypeName("InvalidIdSuppliedError")
  private static final class InvalidIdSuppliedErrorValue implements Value {
    private InvalidIdSuppliedError value;

    private String errorInstanceId;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private InvalidIdSuppliedErrorValue(@JsonProperty("value") InvalidIdSuppliedError value,
        @JsonProperty("_errorInstanceId") String errorInstanceId) {
      this.value = value;
      this.errorInstanceId = errorInstanceId;
    }

    private InvalidIdSuppliedErrorValue(InvalidIdSuppliedError value) {
      this.value = value;
      this.errorInstanceId = UUID.randomUUID().toString();
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitInvalidIdSuppliedError(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof InvalidIdSuppliedErrorValue && equalTo((InvalidIdSuppliedErrorValue) other);
    }

    private boolean equalTo(InvalidIdSuppliedErrorValue other) {
      return value.equals(other.value) && errorInstanceId.equals(other.errorInstanceId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value, this.errorInstanceId);
    }

    @Override
    public String toString() {
      return "DeleteException{" + "value: " + value + ", errorInstanceId: " + errorInstanceId + "}";
    }
  }

  private static final class UnknownErrorValue implements Value {
    private Object unknownValue;

    @JsonCreator(
        mode = JsonCreator.Mode.DELEGATING
    )
    UnknownErrorValue(@JsonProperty("unknownValue") Object unknownValue) {
      this.unknownValue = unknownValue;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor._visitOther(unknownValue);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof UnknownErrorValue && equalTo((UnknownErrorValue) other);
    }

    private boolean equalTo(UnknownErrorValue other) {
      return unknownValue.equals(other.unknownValue);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.unknownValue);
    }

    @Override
    public String toString() {
      return "DeleteException{" + "unknownValue: " + unknownValue + "}";
    }
  }

  static final class Deserializer extends JsonDeserializer<DeleteException> {
    @Override
    public DeleteException deserialize(JsonParser p, DeserializationContext ctx) throws
        IOException {
      Value value = ctx.readValue(p, Value.class);
      int statusCode = (int) ctx.getAttribute("statusCode");
      return new DeleteException(value, statusCode);
    }
  }
}
