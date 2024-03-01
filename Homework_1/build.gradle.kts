plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // Spring Core
    implementation ("org.springframework:spring-core:6.1.3")

    // Spring Context
    implementation ("org.springframework:spring-context:6.1.3")

    // Spring Beans
    implementation ("org.springframework:spring-beans:6.0.16")

    // Spring Context Support
    implementation ("org.springframework:spring-context-support:5.3.4")

    implementation ("org.projectlombok:lombok:1.18.30")
    annotationProcessor ("org.projectlombok:lombok:1.18.30") // this will fix the error

    testImplementation (platform("org.junit:junit-bom:5.9.1"))
    testImplementation ("org.junit.jupiter:junit-jupiter")

    // https://mvnrepository.com/artifact/org.springframework.data/spring-data-jpa
    implementation ("org.springframework.data:spring-data-jpa:3.2.2")
    // https://mvnrepository.com/artifact/org.hibernate/hibernate-core
    implementation ("org.hibernate:hibernate-core:6.4.4.Final")
// https://mvnrepository.com/artifact/org.postgresql/postgresql
    implementation ("org.postgresql:postgresql:42.6.0")
    // https://mvnrepository.com/artifact/org.mapstruct/mapstruct
    implementation ("org.mapstruct:mapstruct:1.5.5.Final")
    implementation ("org.yaml:snakeyaml:2.2") // Текущая версия SnakeYAML

    implementation ("com.zaxxer:HikariCP:3.4.5")
    implementation ("org.springframework:spring-jdbc:5.2.9.RELEASE")


}

tasks.test {
    useJUnitPlatform()
}