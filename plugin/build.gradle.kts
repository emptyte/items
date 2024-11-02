import org.gradle.configurationcache.extensions.capitalized
import java.nio.file.Files
import java.util.*

plugins {
  id("project.common-conventions")
  id("com.github.johnrengelman.shadow") version "8.1.1"
  id("net.minecrell.plugin-yml.bukkit") version "0.6.0"
}

dependencies {
  implementation(project(":${rootProject.name}-adapt"))
  implementation(project(":${rootProject.name}-infrastructure-configurate-yaml"))
}

bukkit {
  val projectName = rootProject.name.split("-").joinToString("") { it.capitalized() }
  val pluginName = "Emptyte$projectName"
  name = pluginName
  version = project.version.toString()  // This is the version of the plugin
  description = "Emptyte $projectName plugin."
  author = "SrVenient"

  main = "team.emptyte.${projectName.lowercase(Locale.ROOT)}.${pluginName}Plugin"
  apiVersion = "1.20"

  commands {
    register("template") {
      description = "Main command."
    }
  }
}

tasks {
  shadowJar {
    archiveBaseName.set(rootProject.name)
    archiveClassifier.set("")
  }

  Files.walk(rootDir.toPath().resolve("adapt"), 1).forEach {
    val name = it.fileName.toString()
    if (Files.isDirectory(it) && name.startsWith("v")) {
      // replace shadowJar with reobfJar if you use reobfuscation
      val buildTask = project(":${rootProject.name}-adapt-$name").tasks.named("reobfJar")
      dependsOn(buildTask)
      from(zipTree(buildTask.map { out -> out.outputs.files.singleFile }))
    }
  }
}
