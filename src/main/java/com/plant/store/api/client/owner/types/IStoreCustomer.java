package com.plant.store.api.client.owner.types;

import com.plant.store.api.client.plant.types.Plant;
import java.lang.Integer;
import java.lang.String;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface IStoreCustomer {
  String getName();

  Optional<Integer> getAge();

  List<Plant> getPlants();

  double getLifetimeSpend();

  UUID getId();
}
