pluginManagement {
  includeBuild("build-logic")
}

rootProject.name = "template"

sequenceOf(
  "api",
  "plugin",
  "adapt",
  "infrastructure:configurate",
  "infrastructure:configurate-yaml",
  "adapt:v1_21",
  "adapt:v1_20_6",
  "adapt:v1_20_4",
  "adapt:v1_20_2",
).forEach {
  includePrefixed(it)
}

fun includePrefixed(name: String) {
  val kebabName = name.replace(":", "-")
  val path = name.replace(":", "/")
  val baseName = "${rootProject.name}-$kebabName"

  include(baseName)
  project(":$baseName").projectDir = file(path)
}
