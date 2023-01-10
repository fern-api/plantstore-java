package com.plantstore.api.client.owner.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.plantstore.api.client.plant.types.Plant;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@JsonDeserialize(
    builder = AddOwnerRequest.Builder.class
)
public final class AddOwnerRequest {
  private final String name;

  private final OwnerAge age;

  private final List<Plant> plants;

  private int _cachedHashCode;

  AddOwnerRequest(String name, OwnerAge age, List<Plant> plants) {
    this.name = name;
    this.age = age;
    this.plants = plants;
  }

  @JsonProperty("name")
  public String getName() {
    return name;
  }

  @JsonProperty("age")
  public OwnerAge getAge() {
    return age;
  }

  @JsonProperty("plants")
  public List<Plant> getPlants() {
    return plants;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof AddOwnerRequest && equalTo((AddOwnerRequest) other);
  }

  private boolean equalTo(AddOwnerRequest other) {
    return name.equals(other.name) && age.equals(other.age) && plants.equals(other.plants);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.name, this.age, this.plants);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "AddOwnerRequest{" + "name: " + name + ", age: " + age + ", plants: " + plants + "}";
  }

  public static NameStage builder() {
    return new Builder();
  }

  public interface NameStage {
    AgeStage name(String name);

    Builder from(AddOwnerRequest other);
  }

  public interface AgeStage {
    _FinalStage age(OwnerAge age);
  }

  public interface _FinalStage {
    AddOwnerRequest build();

    _FinalStage plants(List<Plant> plants);

    _FinalStage plants(Plant plants);

    _FinalStage addAllPlants(List<Plant> plants);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements NameStage, AgeStage, _FinalStage {
    private String name;

    private OwnerAge age;

    private List<Plant> plants = new ArrayList<>();

    private Builder() {
    }

    @Override
    public Builder from(AddOwnerRequest other) {
      name(other.getName());
      age(other.getAge());
      plants(other.getPlants());
      return this;
    }

    @Override
    @JsonSetter("name")
    public AgeStage name(String name) {
      this.name = name;
      return this;
    }

    @Override
    @JsonSetter("age")
    public _FinalStage age(OwnerAge age) {
      this.age = age;
      return this;
    }

    @Override
    public _FinalStage addAllPlants(List<Plant> plants) {
      this.plants.addAll(plants);
      return this;
    }

    @Override
    public _FinalStage plants(Plant plants) {
      this.plants.add(plants);
      return this;
    }

    @Override
    @JsonSetter(
        value = "plants",
        nulls = Nulls.SKIP
    )
    public _FinalStage plants(List<Plant> plants) {
      this.plants.clear();
      this.plants.addAll(plants);
      return this;
    }

    @Override
    public AddOwnerRequest build() {
      return new AddOwnerRequest(name, age, plants);
    }
  }
}
