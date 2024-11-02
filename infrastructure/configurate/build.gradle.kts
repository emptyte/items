plugins {
  id("project.common-conventions")
}

dependencies {
  compileOnlyApi(project(":${rootProject.name}-api"))
  compileOnlyApi(libs.configurate.core)
}
