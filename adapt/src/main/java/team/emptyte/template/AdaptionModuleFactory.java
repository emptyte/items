package team.emptyte.template;

import java.util.regex.Pattern;
import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

public final class AdaptionModuleFactory {
  private AdaptionModuleFactory() {
    throw new UnsupportedOperationException("This class cannot be instantiated");
  }

  public static @NotNull AdaptionModule create() {
    final String className = "team.emptyte.template.AdaptionModule" + ServerVersion.newInstance();
    try {
      final Class<?> clazz = Class.forName(className);
      final Object module = clazz.getConstructor().newInstance();
      if (!(module instanceof AdaptionModule)) {
        throw new IllegalStateException("Invalid adaption module: '"
          + className + "'. It doesn't implement " + AdaptionModule.class);
      }
      return (AdaptionModule) module;
    } catch (final ClassNotFoundException e) {
      throw new IllegalStateException("Adaption module not found: '" + className + '.');
    } catch (final ReflectiveOperationException e) {
      throw new IllegalStateException("Failed to instantiate adaption module", e);
    }
  }

  private record ServerVersion(int major, int minor, int releases) {
    public static @NotNull ServerVersion newInstance() {
      final String[] parts = Bukkit.getMinecraftVersion().split(Pattern.quote("."));
      if (parts.length != 2 && parts.length != 3) {
        throw new IllegalStateException("Failed to determine minecraft version!");
      }
      return new ServerVersion(
        Integer.parseInt(parts[0]),
        Integer.parseInt(parts[1]),
        parts.length == 3 ? Integer.parseInt(parts[2]) : -1
      );
    }

    @Override
    public @NotNull String toString() {
      if (this.releases == -1) {
        return this.major + "_" + this.minor;
      }

      return this.major + "_" + this.minor + "_" + this.releases;
    }
  }
}
