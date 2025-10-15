plugins {
    id("org.springframework.boot")
    id("com.google.protobuf")
}

springBoot {
    mainClass.set("com.parking.UserRegistrarApplication")
}

val grpcVersion = "1.61.0"
val protobufVersion = "4.28.2"
val protocVersion = protobufVersion

dependencies {
    implementation(project(":Model"))
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("io.grpc:grpc-netty:${grpcVersion}")
    implementation("io.grpc:grpc-protobuf:${grpcVersion}")
    implementation("io.grpc:grpc-stub:${grpcVersion}")
    implementation("com.google.protobuf:protobuf-java:${protobufVersion}")
    implementation("com.google.protobuf:protobuf-java-util:${protobufVersion}")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}