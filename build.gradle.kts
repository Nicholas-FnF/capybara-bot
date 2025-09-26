plugins {
    application
    id("com.gradleup.shadow") version "8.3.1"
}

group = "org.example"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.cdimascio:dotenv-java:3.0.0")
    implementation("net.dv8tion:JDA:5.0.0-beta.24")
    implementation("org.json:json:20240303")
    implementation("org.json:json:20240303")
}

application {
    mainClass.set("io.notnick.Bot")
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
    options.isIncremental = true
    sourceCompatibility = JavaVersion.VERSION_11.toString()
    targetCompatibility = JavaVersion.VERSION_11.toString()
}
