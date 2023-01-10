package com.plantstore.api.client.owner.types;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.plantstore.api.client.plant.types.Plant;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

@JsonDeserialize(
    builder = StoreCustomer.Builder.class
)
public final class StoreCustomer implements IStoreCustomer {
  private final String name;

  private final Optional<Integer> age;

  private final List<Plant> plants;

  private final double lifetimeSpend;

  private final UUID id;

  private int _cachedHashCode;

  StoreCustomer(String name, Optional<Integer> age, List<Plant> plants, double lifetimeSpend,
      UUID id) {
    this.name = name;
    this.age = age;
    this.plants = plants;
    this.lifetimeSpend = lifetimeSpend;
    this.id = id;
  }

  @JsonProperty("name")
  @Override
  public String getName() {
    return name;
  }

  @JsonProperty("age")
  @Override
  public Optional<Integer> getAge() {
    return age;
  }

  @JsonProperty("plants")
  @Override
  public List<Plant> getPlants() {
    return plants;
  }

  @JsonProperty("lifetime_spend")
  @Override
  public double getLifetimeSpend() {
    return lifetimeSpend;
  }

  @JsonProperty("id")
  @Override
  public UUID getId() {
    return id;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof StoreCustomer && equalTo((StoreCustomer) other);
  }

  private boolean equalTo(StoreCustomer other) {
    return name.equals(other.name) && age.equals(other.age) && plants.equals(other.plants) && lifetimeSpend == other.lifetimeSpend && id.equals(other.id);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.name, this.age, this.plants, this.lifetimeSpend, this.id);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "StoreCustomer{" + "name: " + name + ", age: " + age + ", plants: " + plants + ", lifetimeSpend: " + lifetimeSpend + ", id: " + id + "}";
  }

  public static NameStage builder() {
    return new Builder();
  }

  public interface NameStage {
    LifetimeSpendStage name(String name);

    Builder from(StoreCustomer other);
  }

  public interface LifetimeSpendStage {
    IdStage lifetimeSpend(double lifetimeSpend);
  }

  public interface IdStage {
    _FinalStage id(UUID id);
  }

  public interface _FinalStage {
    StoreCustomer build();

    _FinalStage age(Optional<Integer> age);

    _FinalStage age(Integer age);

    _FinalStage plants(List<Plant> plants);

    _FinalStage plants(Plant plants);

    _FinalStage addAllPlants(List<Plant> plants);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements NameStage, LifetimeSpendStage, IdStage, _FinalStage {
    private String name;

    private double lifetimeSpend;

    private UUID id;

    private List<Plant> plants = new ArrayList<>();

    private Optional<Integer> age = Optional.empty();

    private Builder() {
    }

    @Override
    public Builder from(StoreCustomer other) {
      name(other.getName());
      age(other.getAge());
      plants(other.getPlants());
      lifetimeSpend(other.getLifetimeSpend());
      id(other.getId());
      return this;
    }

    @Override
    @JsonSetter("name")
    public LifetimeSpendStage name(String name) {
      this.name = name;
      return this;
    }

    @Override
    @JsonSetter("lifetime_spend")
    public IdStage lifetimeSpend(double lifetimeSpend) {
      this.lifetimeSpend = lifetimeSpend;
      return this;
    }

    @Override
    @JsonSetter("id")
    public _FinalStage id(UUID id) {
      this.id = id;
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
    public _FinalStage age(Integer age) {
      this.age = Optional.of(age);
      return this;
    }

    @Override
    @JsonSetter(
        value = "age",
        nulls = Nulls.SKIP
    )
    public _FinalStage age(Optional<Integer> age) {
      this.age = age;
      return this;
    }

    @Override
    public StoreCustomer build() {
      return new StoreCustomer(name, age, plants, lifetimeSpend, id);
    }
  }
}
