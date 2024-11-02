/*
 * This file is part of summoning, licensed under the Emptyte Proprietary License.
 *
 * Copyright (c) 2024 Emptyte Team
 *
 * All rights reserved.
 *
 * This software is provided under a proprietary license. You may not use,
 * modify, distribute, or sublicense this software without explicit written
 * permission from the copyright holder.  Unauthorized use is prohibited and
 * may result in legal action.
 *
 * For licensing inquiries, please contact Emptyte Team at contact@emptyte.com
 */
package team.emptyte.template.config.infrastructure.configurate;

import org.jetbrains.annotations.NotNull;
import org.spongepowered.configurate.ConfigurateException;
import org.spongepowered.configurate.ConfigurationNode;
import org.spongepowered.configurate.loader.ConfigurationLoader;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionException;

public final class ConfigurateConfigurationContainer<ConfigType> {
  private ConfigType configuration;
  private final ConfigurationLoader<?> loader;
  private final Class<ConfigType> clazz;

  public ConfigurateConfigurationContainer(final @NotNull ConfigurationLoader<?> loader, final @NotNull Class<ConfigType> clazz) {
    this.loader = loader;
    this.clazz = clazz;

    try {
      this.configuration = this.load();
    } catch (final Throwable t) {
      throw new RuntimeException(t);
    }
  }

  public @NotNull ConfigType configuration() {
    return this.configuration;
  }

  private @NotNull ConfigType load() throws ConfigurateException {
    final ConfigurationNode node = this.loader.load();
    final ConfigType config = node.get(this.clazz);
    if (config == null) {
      throw new ConfigurateException(node, "Failed to load configuration");
    }
    node.set(this.clazz, config);
    this.loader.save(node);
    return config;
  }

  public @NotNull CompletableFuture<Void> reload() {
    return CompletableFuture.runAsync(() -> {
      try {
        this.configuration = this.load();
      } catch (final Exception e) {
        throw new CompletionException(e);
      }
    });
  }

  public @NotNull CompletableFuture<Void> save() {
    return CompletableFuture.runAsync(() -> {
      try {
        final ConfigurationNode node = this.loader.createNode();
        node.set(this.clazz, this.configuration);
        this.loader.save(node);
      } catch (final Exception e) {
        throw new CompletionException(e);
      }
    });
  }
}
