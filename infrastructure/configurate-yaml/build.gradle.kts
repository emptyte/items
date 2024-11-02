plugins {
  id("project.common-conventions")
}

dependencies {
  api(project(":${rootProject.name}-infrastructure-configurate"))
  api(libs.configurate.yaml)
//  api(libs.caffeine)
}
