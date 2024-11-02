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
package team.emptyte.template.language.infrastructure.configurate;

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
@SuppressWarnings("ALL")
public final class ConfigurateLanguageConfigurationProperties {
  public Reload reload = new Reload();

  public String processError = "<red>An error occurred while processing your request.";

  @ConfigSerializable
  public static class Reload {
    public String error = "<red>An error occurred while reloading the configuration, check the console.";
    public String config = "<green>The configuration has been reloaded correctly.";
    public String lang = "<green>The language has been reloaded correctly.";
  }
}
