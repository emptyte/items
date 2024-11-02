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
package team.emptyte.template.aggregate.domain;

import org.jetbrains.annotations.NotNull;

/**
 * This class is the base for all the aggregate roots, it contains the essential methods to
 * interact with the database, cache, or whatever you want to use to store your data.
 *
 * @since 0.0.1
 */
public abstract class AggregateRoot {
  private final String id;

  /**
   * Creates a new {@link AggregateRoot} with the specified id.
   *
   * @param id The id of the {@link AggregateRoot}.
   * @since 0.0.1
   */
  protected AggregateRoot(final @NotNull String id) {
    this.id = id;
  }

  /**
   * Returns the id of the {@link AggregateRoot}.
   *
   * @return The id of the {@link AggregateRoot}.
   * @since 0.0.1
   */
  public final @NotNull String id() {
    return this.id;
  }
}
