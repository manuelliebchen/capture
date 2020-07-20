plugins {
    `java-library`
    `java-library-distribution`
	
     id("org.openjfx.javafxplugin") version "0.0.8"

    application
}

repositories {
    mavenCentral()
}

dependencies {	
	implementation(project(":apcf"))

	implementation("org.apache.logging.log4j:log4j-core:2.13.0")
	implementation("org.apache.logging.log4j:log4j-api:2.13.0")
}

javafx {
    version = "11"
    modules("javafx.controls", "javafx.fxml")
//    configuration = "compileOnly"
}

java {
    group = "de.acagamics"
	version = "1.8.1"

    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    
    withJavadocJar()
    withSourcesJar()
}

application{
    mainClassName = "Main"
}
