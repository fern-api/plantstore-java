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
    builder = StoreEmployee.Builder.class
)
public final class StoreEmployee implements IStoreCustomer {
  private final String name;

  private final Optional<Integer> age;

  private final List<Plant> plants;

  private final double lifetimeSpend;

  private final UUID id;

  private final UUID employeeId;

  private int _cachedHashCode;

  StoreEmployee(String name, Optional<Integer> age, List<Plant> plants, double lifetimeSpend,
      UUID id, UUID employeeId) {
    this.name = name;
    this.age = age;
    this.plants = plants;
    this.lifetimeSpend = lifetimeSpend;
    this.id = id;
    this.employeeId = employeeId;
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

  @JsonProperty("employeeId")
  public UUID getEmployeeId() {
    return employeeId;
  }

  @Override
  public boolean equals(Object other) {
    if (this == other) return true;
    return other instanceof StoreEmployee && equalTo((StoreEmployee) other);
  }

  private boolean equalTo(StoreEmployee other) {
    return name.equals(other.name) && age.equals(other.age) && plants.equals(other.plants) && lifetimeSpend == other.lifetimeSpend && id.equals(other.id) && employeeId.equals(other.employeeId);
  }

  @Override
  public int hashCode() {
    if (_cachedHashCode == 0) {
      _cachedHashCode = Objects.hash(this.name, this.age, this.plants, this.lifetimeSpend, this.id, this.employeeId);
    }
    return _cachedHashCode;
  }

  @Override
  public String toString() {
    return "StoreEmployee{" + "name: " + name + ", age: " + age + ", plants: " + plants + ", lifetimeSpend: " + lifetimeSpend + ", id: " + id + ", employeeId: " + employeeId + "}";
  }

  public static NameStage builder() {
    return new Builder();
  }

  public interface NameStage {
    LifetimeSpendStage name(String name);

    Builder from(StoreEmployee other);
  }

  public interface LifetimeSpendStage {
    IdStage lifetimeSpend(double lifetimeSpend);
  }

  public interface IdStage {
    EmployeeIdStage id(UUID id);
  }

  public interface EmployeeIdStage {
    _FinalStage employeeId(UUID employeeId);
  }

  public interface _FinalStage {
    StoreEmployee build();

    _FinalStage age(Optional<Integer> age);

    _FinalStage age(Integer age);

    _FinalStage plants(List<Plant> plants);

    _FinalStage plants(Plant plants);

    _FinalStage addAllPlants(List<Plant> plants);
  }

  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  public static final class Builder implements NameStage, LifetimeSpendStage, IdStage, EmployeeIdStage, _FinalStage {
    private String name;

    private double lifetimeSpend;

    private UUID id;

    private UUID employeeId;

    private List<Plant> plants = new ArrayList<>();

    private Optional<Integer> age = Optional.empty();

    private Builder() {
    }

    @Override
    public Builder from(StoreEmployee other) {
      name(other.getName());
      age(other.getAge());
      plants(other.getPlants());
      lifetimeSpend(other.getLifetimeSpend());
      id(other.getId());
      employeeId(other.getEmployeeId());
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
    public EmployeeIdStage id(UUID id) {
      this.id = id;
      return this;
    }

    @Override
    @JsonSetter("employeeId")
    public _FinalStage employeeId(UUID employeeId) {
      this.employeeId = employeeId;
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
    public StoreEmployee build() {
      return new StoreEmployee(name, age, plants, lifetimeSpend, id, employeeId);
    }
  }
}
