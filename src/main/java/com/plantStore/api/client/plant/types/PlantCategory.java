package com.plantStore.api.client.plant.types;

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
    builder = PlantCategory.Builder.class
)
public final class PlantCategory {
  private final UUID id;

  private final String name;

  private int _cachedHashCode;

  PlantCategory(UUID id, String name) {
    this.id = id;
    this.name = name;
  }

  @JsonProperty("id")
  public UUID getId() {
    return id;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof PlantCategory && equalTo((PlantCategory) other);
  }

  private boolean equalTo(PlantCategory other) {
    return id.equals(other.id) && name.equals(other.name);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.id, this.name);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "PlantCategory{" + "id: " + id + ", name: " + name + "}";
  }

  public static IdStage builder() {
    return new Builder();
  }

  public interface IdStage {
    NameStage id(UUID id);

    Builder from(PlantCategory other);
  }

  public interface NameStage {
    _FinalStage name(String name);
  }

  public interface _FinalStage {
    PlantCategory build();
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements IdStage, NameStage, _FinalStage {
    private UUID id;

    private String name;

    private Builder() {
    }

    @Override
    public Builder from(PlantCategory other) {
      id(other.getId());
      name(other.getName());
      return this;
    }

    @Override
    @JsonSetter("id")
    public NameStage id(UUID id) {
      this.id = id;
      return this;
    }

    @Override
    @JsonSetter("name")
    public _FinalStage name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public PlantCategory build() {
      return new PlantCategory(id, name);
    }
  }
}
