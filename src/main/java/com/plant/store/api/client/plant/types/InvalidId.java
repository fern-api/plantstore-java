package com.plant.store.api.client.plant.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.UUID;

@JsonDeserialize(
    builder = InvalidId.Builder.class
)
public final class InvalidId {
  private final UUID id;

  private final String message;

  private int _cachedHashCode;

  InvalidId(UUID id, String message) {
    this.id = id;
    this.message = message;
  }

  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  @JsonProperty("message")
  public String getMessage() {
    return message;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof InvalidId && equalTo((InvalidId) other);
  }

  private boolean equalTo(InvalidId other) {
    return id.equals(other.id) && message.equals(other.message);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.id, this.message);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "InvalidId{" + "id: " + id + ", message: " + message + "}";
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    MessageStage id(UUID id);

    Builder from(InvalidId other);
  }

  public interface MessageStage {
    _FinalStage message(String message);
  }

  public interface _FinalStage {
    InvalidId build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, MessageStage, _FinalStage {
    private UUID id;

    private String message;

    private Builder() {
    }

    @Override
    public Builder from(InvalidId other) {
      id(other.getId());
      message(other.getMessage());
      return this;
    }

    @Override
    @JsonSetter("id")
    public MessageStage id(UUID id) {
      this.id = id;
      return this;
    }

    @Override
    @JsonSetter("message")
    public _FinalStage message(String message) {
      this.message = message;
      return this;
    }

    @Override
    public InvalidId build() {
      return new InvalidId(id, message);
    }
  }
}
