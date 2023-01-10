package com.plantStore.api.client.plant.exceptions;

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
import com.plantStore.api.client.plant.errors.InvalidResponseError;
import java.io.IOException;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(
    using = AddException.Deserializer.class
)
public final class AddException extends Exception {
  private final Value value;

  private int statusCode;

  private AddException(Value value, int statusCode) {
    this.value = value;
    this.statusCode = statusCode;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public int getStatusCode() {
    return this.statusCode;
  }

  public static AddException invalidResponseError(InvalidResponseError value) {
    return new AddException(new InvalidResponseErrorValue(value), 405);
  }

  public static AddException other(Object unknownValue, int statusCode) {
    return new AddException(new UnknownErrorValue(unknownValue), statusCode);
  }

  public boolean isInvalidResponseError() {
    return value instanceof InvalidResponseErrorValue;
  }

  public boolean _isOther() {
    return value instanceof UnknownErrorValue;
  }

  public Optional<InvalidResponseError> getInvalidResponseError() {
    if (isInvalidResponseError()) {
      return Optional.of(((InvalidResponseErrorValue) value).value);
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
    T visitInvalidResponseError(InvalidResponseError invalidResponseError);

    T _visitOther(Object otherType);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "_error",
      visible = true,
      defaultImpl = UnknownErrorValue.class
  )
  @JsonSubTypes(@JsonSubTypes.Type(InvalidResponseErrorValue.class))
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("InvalidResponseError")
  private static final class InvalidResponseErrorValue implements Value {
    private InvalidResponseError value;

    private String errorInstanceId;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private InvalidResponseErrorValue(@JsonProperty("value") InvalidResponseError value,
        @JsonProperty("_errorInstanceId") String errorInstanceId) {
      this.value = value;
      this.errorInstanceId = errorInstanceId;
    }

    private InvalidResponseErrorValue(InvalidResponseError value) {
      this.value = value;
      this.errorInstanceId = UUID.randomUUID().toString();
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitInvalidResponseError(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof InvalidResponseErrorValue && equalTo((InvalidResponseErrorValue) other);
    }

    private boolean equalTo(InvalidResponseErrorValue other) {
      return value.equals(other.value) && errorInstanceId.equals(other.errorInstanceId);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value, this.errorInstanceId);
    }

    @Override
    public String toString() {
      return "AddException{" + "value: " + value + ", errorInstanceId: " + errorInstanceId + "}";
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
      return "AddException{" + "unknownValue: " + unknownValue + "}";
    }
  }

  static final class Deserializer extends JsonDeserializer<AddException> {
    @Override
    public AddException deserialize(JsonParser p, DeserializationContext ctx) throws IOException {
      Value value = ctx.readValue(p, Value.class);
      int statusCode = (int) ctx.getAttribute("statusCode");
      return new AddException(value, statusCode);
    }
  }
}
