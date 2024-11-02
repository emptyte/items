plugins {
  id("project.common-conventions")
}

dependencies {
  api(project(":${rootProject.name}-api"))

  implementation("io.papermc:paperlib:1.0.7")
}
