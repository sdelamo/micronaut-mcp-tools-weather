plugins {
    id("io.micronaut.application") version "4.5.4"
    id("com.gradleup.shadow") version "8.3.7"
}
version = "0.1"
group = "example.micronaut"
repositories {
    mavenCentral()
}
dependencies {
    annotationProcessor("io.micronaut:micronaut-graal")
    annotationProcessor("io.micronaut:micronaut-http-validation")
    implementation("io.micronaut:micronaut-jackson-databind")
    implementation("io.micronaut.mcp:micronaut-mcp-server-java-sdk:0.0.2")
    annotationProcessor("io.micronaut.jsonschema:micronaut-json-schema-processor:1.7.0")
    implementation("io.micronaut.jsonschema:micronaut-json-schema-annotations:1.7.0")
    implementation("io.micronaut:micronaut-http-client-jdk")
    runtimeOnly("ch.qos.logback:logback-classic")
    testImplementation("org.skyscreamer:jsonassert:1.5.3")
}
application {
    mainClass = "example.micronaut.Application"
}
java {
    sourceCompatibility = JavaVersion.toVersion("21")
    targetCompatibility = JavaVersion.toVersion("21")
}
graalvmNative.toolchainDetection = false
micronaut {
    runtime("netty")
    testRuntime("junit5")
    processing {
        incremental(true)
        annotations("example.micronaut.*")
    }
}
tasks.named<io.micronaut.gradle.docker.NativeImageDockerfile>("dockerfileNative") {
    jdkVersion = "21"
}
