# Plant Store API Java Library

[![Maven Central](https://img.shields.io/maven-central/v/io.github.fern-api/plantstore)](https://central.sonatype.dev/artifact/io.github.fern-api/plantstore/0.0.7) 
![Sonatype Nexus (Releases)](https://img.shields.io/nexus/r/io.github.fern-api/plantstore?server=https%3A%2F%2Fs01.oss.sonatype.org)

## Usage

Check out the [sample app](.sample-app/app.java) which consumes this SDK!

```java
PlantStoreApiClient plantStore =
        new PlantStoreApiClient(BearerAuth.of("MY-TOKEN"));

try {
    Plant plant = plantStore.plant().find(Find.Request.builder()
        .plantId(UUID.fromString("ef19fe7b-c4c9-4631-81d7-a83aba610a7d"))
        .build());
    System.out.println("Received plant: " + plant.toString());
} catch (FindException e) {
    System.out.println("Failed to find plant");
}
```

## Beta status

This SDK is in beta, and there may be breaking changes between versions without a major version update. Therefore, we recommend pinning the package version to a specific version in your build.gradle file. This way, you can install the same version each time without breaking changes unless you are intentionally looking for the latest version.

## Contributing

While we value open-source contributions to this SDK, this library is generated programmatically. Additions made directly to this library would have to be moved over to our generation code, otherwise they would be overwritten upon the next generated release. Feel free to open a PR as a proof of concept, but know that we will not be able to merge it as-is. We suggest [opening an issue](https://github.com/fern-api/plantstore-java/issues) first to discuss with us!

On the other hand, contributions to the README are always very welcome!
