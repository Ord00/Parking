plugins {
    id("org.springframework.boot")
    id("com.google.protobuf")
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
    compileOnly("org.apache.tomcat:annotations-api:6.0.53")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
}