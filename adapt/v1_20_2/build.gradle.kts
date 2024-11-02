plugins {
  id("project.common-conventions")
  id("io.papermc.paperweight.userdev")
}

tasks {
  assemble {
    dependsOn(reobfJar)
  }
}

dependencies {
  paperweight.paperDevBundle("1.20.2-R0.1-SNAPSHOT")

  compileOnly(project(":${rootProject.name}-adapt"))
}
