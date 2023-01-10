package com.plantstore.api.client.plant.endpoints;

import com.plantstore.api.core.BearerAuth;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class Find {
  private Find() {
  }

  public static final class Request {
    private final UUID plantId;

    private final Optional<BearerAuth> authOverride;

    private int _cachedHashCode;

    Request(UUID plantId, Optional<BearerAuth> authOverride) {
      this.plantId = plantId;
      this.authOverride = authOverride;
    }

    public UUID getPlantId() {
      return plantId;
    }

    public Optional<BearerAuth> getAuthOverride() {
      return authOverride;
    }

    @Override
    public boolean equals(Object other) {
      if (this == other) return true;
      return other instanceof Request && equalTo((Request) other);
    }

    private boolean equalTo(Request other) {
      return plantId.equals(other.plantId) && authOverride.equals(other.authOverride);
    }

    @Override
    public int hashCode() {
      if (_cachedHashCode == 0) {
        _cachedHashCode = Objects.hash(this.plantId, this.authOverride);
      }
      return _cachedHashCode;
    }

    @Override
    public String toString() {
      return "Find.Request{" + "plantId: " + plantId + ", authOverride: " + authOverride + "}";
    }

    public static PlantIdStage builder() {
      return new Builder();
    }

    public interface PlantIdStage {
      _FinalStage plantId(UUID plantId);

      Builder from(Request other);
    }

    public interface _FinalStage {
      Request build();

      _FinalStage authOverride(Optional<BearerAuth> authOverride);

      _FinalStage authOverride(BearerAuth authOverride);
    }

    public static final class Builder implements PlantIdStage, _FinalStage {
      private UUID plantId;

      private Optional<BearerAuth> authOverride = Optional.empty();

      private Builder() {
      }

      @Override
      public Builder from(Request other) {
        plantId(other.getPlantId());
        authOverride(other.getAuthOverride());
        return this;
      }

      @Override
      public _FinalStage plantId(UUID plantId) {
        this.plantId = plantId;
        return this;
      }

      @Override
      public _FinalStage authOverride(BearerAuth authOverride) {
        this.authOverride = Optional.of(authOverride);
        return this;
      }

      @Override
      public _FinalStage authOverride(Optional<BearerAuth> authOverride) {
        this.authOverride = authOverride;
        return this;
      }

      @Override
      public Request build() {
        return new Request(plantId, authOverride);
      }
    }
  }
}
