package team.emptyte.template;

import org.bukkit.plugin.java.JavaPlugin;
import org.spongepowered.configurate.yaml.NodeStyle;
import org.spongepowered.configurate.yaml.YamlConfigurationLoader;
import team.emptyte.template.config.infrastructure.configurate.ConfigurateConfigurationContainer;
import team.emptyte.template.config.infrastructure.configurate.ConfigurateConfigurationProperties;

import java.nio.file.Path;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class EmptyteTemplatePlugin extends JavaPlugin {
  @Override
  public void onEnable() {
    final Path dataFolderPath = this.getDataFolder().toPath();

    final ConfigurateConfigurationContainer<ConfigurateLanguageConfigurationProperties> configurateLanguageContainer =
      new ConfigurateConfigurationContainer<>(YamlConfigurationLoader.builder()
        .path(dataFolderPath.resolve("language.yml"))
        .indent(2)
        .nodeStyle(NodeStyle.BLOCK)
        .defaultOptions(configurationOptions -> configurationOptions.shouldCopyDefaults(true))
        .build(), ConfigurateLanguageConfigurationProperties.class);

    final ConfigurateConfigurationContainer<ConfigurateConfigurationProperties> configurateConfigurationContainer =
      new ConfigurateConfigurationContainer<>(YamlConfigurationLoader.builder()
        .path(dataFolderPath.resolve("configuration.yml"))
        .indent(2)
        .nodeStyle(NodeStyle.BLOCK)
        .defaultOptions(configurationOptions -> configurationOptions.shouldCopyDefaults(true))
        .build(), ConfigurateConfigurationProperties.class);

    final Executor executor = Executors.newFixedThreadPool(configurateConfigurationContainer.configuration().executorThreads);
  }

  @Override
  public void onDisable() {
    // Plugin shutdown logic
  }
}
