package com.plantstore.api.client.owner.endpoints;

import com.plantstore.api.core.BearerAuth;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;

public final class Delete {
  private Delete() {
  }

  public static final class Request {
    private final UUID ownerId;

    private final Optional<BearerAuth> authOverride;

    private int _cachedHashCode;

    Request(UUID ownerId, Optional<BearerAuth> authOverride) {
      this.ownerId = ownerId;
      this.authOverride = authOverride;
    }

    public UUID getOwnerId() {
      return ownerId;
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
      return ownerId.equals(other.ownerId) && authOverride.equals(other.authOverride);
    }

    @Override
    public int hashCode() {
      if (_cachedHashCode == 0) {
        _cachedHashCode = Objects.hash(this.ownerId, this.authOverride);
      }
      return _cachedHashCode;
    }

    @Override
    public String toString() {
      return "Delete.Request{" + "ownerId: " + ownerId + ", authOverride: " + authOverride + "}";
    }

    public static OwnerIdStage builder() {
      return new Builder();
    }

    public interface OwnerIdStage {
      _FinalStage ownerId(UUID ownerId);

      Builder from(Request other);
    }

    public interface _FinalStage {
      Request build();

      _FinalStage authOverride(Optional<BearerAuth> authOverride);

      _FinalStage authOverride(BearerAuth authOverride);
    }

    public static final class Builder implements OwnerIdStage, _FinalStage {
      private UUID ownerId;

      private Optional<BearerAuth> authOverride = Optional.empty();

      private Builder() {
      }

      @Override
      public Builder from(Request other) {
        ownerId(other.getOwnerId());
        authOverride(other.getAuthOverride());
        return this;
      }

      @Override
      public _FinalStage ownerId(UUID ownerId) {
        this.ownerId = ownerId;
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
        return new Request(ownerId, authOverride);
      }
    }
  }
}
