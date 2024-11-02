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

import org.spongepowered.configurate.objectmapping.ConfigSerializable;

@ConfigSerializable
@SuppressWarnings("ALL")
public final class ConfigurateConfigurationProperties {
  public int executorThreads = 2;
}
