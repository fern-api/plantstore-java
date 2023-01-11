package com.plant.store.api.client.plant.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(
    builder = AddPlantRequest.Builder.class
)
public final class AddPlantRequest {
  private final Optional<UUID> id;

  private final Optional<PlantCategory> category;

  private final String name;

  private final Map<String, String> photoUrls;

  private final Optional<PlantStatus> status;

  private int _cachedHashCode;

  AddPlantRequest(Optional<UUID> id, Optional<PlantCategory> category, String name,
      Map<String, String> photoUrls, Optional<PlantStatus> status) {
    this.id = id;
    this.category = category;
    this.name = name;
    this.photoUrls = photoUrls;
    this.status = status;
  }

  @JsonProperty("id")
  public Optional<UUID> getId() {
    return id;
  }

  @JsonProperty("category")
  public Optional<PlantCategory> getCategory() {
    return category;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("photoUrls")
  public Map<String, String> getPhotoUrls() {
    return photoUrls;
  }

  @JsonProperty("status")
  public Optional<PlantStatus> getStatus() {
    return status;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof AddPlantRequest && equalTo((AddPlantRequest) other);
  }

  private boolean equalTo(AddPlantRequest other) {
    return id.equals(other.id) && category.equals(other.category) && name.equals(other.name) && photoUrls.equals(other.photoUrls) && status.equals(other.status);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.id, this.category, this.name, this.photoUrls, this.status);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "AddPlantRequest{" + "id: " + id + ", category: " + category + ", name: " + name + ", photoUrls: " + photoUrls + ", status: " + status + "}";
  }

  public static NameStage builder() {
    return new Builder();
  }

  public interface NameStage {
    _FinalStage name(String name);

    Builder from(AddPlantRequest other);
  }

  public interface _FinalStage {
    AddPlantRequest build();

    _FinalStage id(Optional<UUID> id);

    _FinalStage id(UUID id);

    _FinalStage category(Optional<PlantCategory> category);

    _FinalStage category(PlantCategory category);

    _FinalStage photoUrls(Map<String, String> photoUrls);

    _FinalStage putAllPhotoUrls(Map<String, String> photoUrls);

    _FinalStage photoUrls(String key, String value);

    _FinalStage status(Optional<PlantStatus> status);

    _FinalStage status(PlantStatus status);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements NameStage, _FinalStage {
    private String name;

    private Optional<PlantStatus> status = Optional.empty();

    private Map<String, String> photoUrls = new LinkedHashMap<>();

    private Optional<PlantCategory> category = Optional.empty();

    private Optional<UUID> id = Optional.empty();

    private Builder() {
    }

    @Override
    public Builder from(AddPlantRequest other) {
      id(other.getId());
      category(other.getCategory());
      name(other.getName());
      photoUrls(other.getPhotoUrls());
      status(other.getStatus());
      return this;
    }

    @Override
    @JsonSetter("name")
    public _FinalStage name(String name) {
      this.name = name;
      return this;
    }

    @Override
    public _FinalStage status(PlantStatus status) {
      this.status = Optional.of(status);
      return this;
    }

    @Override
    @JsonSetter(
        value = "status",
        nulls = Nulls.SKIP
    )
    public _FinalStage status(Optional<PlantStatus> status) {
      this.status = status;
      return this;
    }

    @Override
    public _FinalStage photoUrls(String key, String value) {
      this.photoUrls.put(key, value);
      return this;
    }

    @Override
    public _FinalStage putAllPhotoUrls(Map<String, String> photoUrls) {
      this.photoUrls.putAll(photoUrls);
      return this;
    }

    @Override
    @JsonSetter(
        value = "photoUrls",
        nulls = Nulls.SKIP
    )
    public _FinalStage photoUrls(Map<String, String> photoUrls) {
      this.photoUrls.clear();
      this.photoUrls.putAll(photoUrls);
      return this;
    }

    @Override
    public _FinalStage category(PlantCategory category) {
      this.category = Optional.of(category);
      return this;
    }

    @Override
    @JsonSetter(
        value = "category",
        nulls = Nulls.SKIP
    )
    public _FinalStage category(Optional<PlantCategory> category) {
      this.category = category;
      return this;
    }

    @Override
    public _FinalStage id(UUID id) {
      this.id = Optional.of(id);
      return this;
    }

    @Override
    @JsonSetter(
        value = "id",
        nulls = Nulls.SKIP
    )
    public _FinalStage id(Optional<UUID> id) {
      this.id = id;
      return this;
    }

    @Override
    public AddPlantRequest build() {
      return new AddPlantRequest(id, category, name, photoUrls, status);
    }
  }
}
