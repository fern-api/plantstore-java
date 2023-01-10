package com.plantstore.api.client.owner.types;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.fasterxml.jackson.annotation.JsonUnwrapped;
import com.fasterxml.jackson.annotation.JsonValue;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;

public final class PlantOwner {
  private final Value value;

  @JsonCreator(
      mode = JsonCreator.Mode.DELEGATING
  )
  private PlantOwner(Value value) {
    this.value = value;
  }

  public <T> T visit(Visitor<T> visitor) {
    return value.visit(visitor);
  }

  public static PlantOwner customer(StoreCustomer value) {
    return new PlantOwner(new CustomerValue(value));
  }

  public static PlantOwner employee(StoreEmployee value) {
    return new PlantOwner(new EmployeeValue(value));
  }

  public boolean isCustomer() {
    return value instanceof CustomerValue;
  }

  public boolean isEmployee() {
    return value instanceof EmployeeValue;
  }

  public boolean _isUnknown() {
    return value instanceof _UnknownValue;
  }

  public Optional<StoreCustomer> getCustomer() {
    if (isCustomer()) {
      return Optional.of(((CustomerValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<StoreEmployee> getEmployee() {
    if (isEmployee()) {
      return Optional.of(((EmployeeValue) value).value);
    }
    return Optional.empty();
  }

  public Optional<Object> _getUnknown() {
    if (_isUnknown()) {
      return Optional.of(((_UnknownValue) value).value);
    }
    return Optional.empty();
  }

  @JsonValue
  private Value getValue() {
    return this.value;
  }

  public interface Visitor<T> {
    T visitCustomer(StoreCustomer customer);

    T visitEmployee(StoreEmployee employee);

    T _visitUnknown(Object unknownType);
  }

  @JsonTypeInfo(
      use = JsonTypeInfo.Id.NAME,
      property = "type",
      visible = true,
      defaultImpl = _UnknownValue.class
  )
  @JsonSubTypes({
      @JsonSubTypes.Type(CustomerValue.class),
      @JsonSubTypes.Type(EmployeeValue.class)
  })
  @JsonIgnoreProperties(
      ignoreUnknown = true
  )
  private interface Value {
    <T> T visit(Visitor<T> visitor);
  }

  @JsonTypeName("customer")
  private static final class CustomerValue implements Value {
    @JsonUnwrapped
    private StoreCustomer value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private CustomerValue() {
    }

    private CustomerValue(StoreCustomer value) {
      this.value = value;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitCustomer(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof CustomerValue && equalTo((CustomerValue) other);
    }

    private boolean equalTo(CustomerValue other) {
      return value.equals(other.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @Override
    public String toString() {
      return "PlantOwner{" + "value: " + value + "}";
    }
  }

  @JsonTypeName("employee")
  private static final class EmployeeValue implements Value {
    @JsonUnwrapped
    private StoreEmployee value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private EmployeeValue() {
    }

    private EmployeeValue(StoreEmployee value) {
      this.value = value;
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor.visitEmployee(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof EmployeeValue && equalTo((EmployeeValue) other);
    }

    private boolean equalTo(EmployeeValue other) {
      return value.equals(other.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.value);
    }

    @Override
    public String toString() {
      return "PlantOwner{" + "value: " + value + "}";
    }
  }

  private static final class _UnknownValue implements Value {
    private String type;

    @JsonValue
    private Object value;

    @JsonCreator(
        mode = JsonCreator.Mode.PROPERTIES
    )
    private _UnknownValue(@JsonProperty("value") Object value) {
    }

    @Override
    public <T> T visit(Visitor<T> visitor) {
      return visitor._visitUnknown(value);
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof _UnknownValue && equalTo((_UnknownValue) other);
    }

    private boolean equalTo(_UnknownValue other) {
      return type.equals(other.type) && value.equals(other.value);
    }

    @Override
    public int hashCode() {
      return Objects.hash(this.type, this.value);
    }

    @Override
    public String toString() {
      return "PlantOwner{" + "type: " + type + ", value: " + value + "}";
    }
  }
}
