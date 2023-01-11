package sample;

import com.plantStore.api.client.plant.endpoints.Find;
import com.plantStore.api.client.plant.exceptions.FindException;
import com.plantStore.api.client.plant.types.Plant;
import com.plantStore.api.core.BearerAuth;
import java.lang.String;
import com.plantStore.api.PlantStoreApiClient;
import java.util.UUID;

public final class App {

  public static void main(String[] args) {
    PlantStoreApiClient plantStore = new PlantStoreApiClient(BearerAuth.of("MY-TOKEN"));
    try {
        Plant plant = plantStore.plant().find(Find.Request.builder()
                .plantId(UUID.fromString("ef19fe7b-c4c9-4631-81d7-a83aba610a7d"))
              .build());
        System.out.println("Received plant: " + plant.toString());
    } catch (FindException e) {
        System.out.println("Failed to find plant");
    }
  }
}
