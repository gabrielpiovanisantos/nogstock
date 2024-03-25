import org.gradle.api.JavaVersion.VERSION_17
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

//Properties
val slf4jVersion: String = "2.0.9"
val ogmVersion: String = "5.4.1.Final"
val lombokVersion: String = "1.18.32"

plugins {
    id("org.springframework.boot") version "3.2.4"
    id("io.spring.dependency-management") version "1.1.4"
    id("org.jetbrains.kotlin.jvm") version "1.9.23"
    id("org.jetbrains.kotlin.plugin.spring") version "1.9.23"
}

group = "com.gabriel"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = VERSION_17

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-webflux")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb-reactive")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.projectlombok:lombok:${lombokVersion}")
    implementation("io.projectreactor:reactor-tools")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.hibernate.ogm:hibernate-ogm-bom:${ogmVersion}")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    developmentOnly("org.springframework.boot:spring-boot-devtools")
    testImplementation("org.springframework.boot:spring-boot-starter-test") {
        exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
    }
    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.projectlombok:lombok:${lombokVersion}")
    testImplementation("io.mockk:mockk")
    //LOMBOK Dependencies
    annotationProcessor("org.projectlombok:lombok")
    compileOnly("org.projectlombok:lombok")
    testAnnotationProcessor("org.projectlombok:lombok")
    testCompileOnly("org.projectlombok:lombok")
}

tasks.withType<Test> {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "17"
    }
}
