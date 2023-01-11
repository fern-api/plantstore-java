package com.plant.store.api.core;

import java.lang.String;

public final class Environment {
  public static final Environment PRODUCTION = new Environment("https://api.plantstore.com");

  public static final Environment SANDBOX = new Environment("https://sandbox.plantstore.com");

  private final String url;

  private Environment(String url) {
    this.url = url;
  }

  public String getUrl() {
    return this.url;
  }

  public static Environment custom(String url) {
    return new Environment(url);
  }
}
