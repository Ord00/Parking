plugins {
    id("org.springframework.boot")
}

springBoot {
    mainClass.set("com.parking.UserGeneratorApplication")
}

val grpcVersion = "1.61.0"
val protobufVersion = "4.28.2"
val protocVersion = protobufVersion

dependencies {
    implementation(project(":Model"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.springframework.boot:spring-boot-starter")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("io.grpc:grpc-netty:${grpcVersion}")
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("com.google.protobuf:protobuf-java:${protobufVersion}")
    implementation("com.google.protobuf:protobuf-java-util:${protobufVersion}")
    compileOnly("org.apache.tomcat:annotations-api:6.0.53")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}