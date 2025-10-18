import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.9.20"
    `maven-publish`
    signing
}

group = "ai.useblackman"
version = "0.0.5"

repositories {
    mavenCentral()
}

dependencies {
    implementation("com.squareup.okhttp3:okhttp:4.12.0")
    implementation("com.squareup.moshi:moshi-kotlin:1.15.0")
    implementation("com.squareup.moshi:moshi-adapters:1.15.0")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.7.3")

    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        jvmTarget = "1.8"
        freeCompilerArgs = listOf("-Xjsr305=strict")
    }
}

java {
    withSourcesJar()
    withJavadocJar()
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])

            pom {
                name.set("Blackman AI Kotlin SDK")
                description.set("Official Kotlin client for Blackman AI - The AI API proxy that optimizes token usage")
                url.set("https://www.useblackman.ai")

                licenses {
                    license {
                        name.set("MIT License")
                        url.set("https://opensource.org/licenses/MIT")
                    }
                }

                developers {
                    developer {
                        name.set("Blackman AI Team")
                        email.set("support@blackman.ai")
                        organization.set("Blackman AI")
                        organizationUrl.set("https://www.useblackman.ai")
                    }
                }

                scm {
                    connection.set("scm:git:git://github.com/blackman-ai/kotlin-sdk.git")
                    developerConnection.set("scm:git:ssh://github.com/blackman-ai/kotlin-sdk.git")
                    url.set("https://github.com/blackman-ai/kotlin-sdk")
                }
            }
        }
    }

    repositories {
        maven {
            name = "ossrh"
            url = uri("https://s01.oss.sonatype.org/service/local/staging/deploy/maven2/")
            credentials {
                username = System.getenv("MAVEN_CENTRAL_USERNAME") ?: ""
                password = System.getenv("MAVEN_CENTRAL_PASSWORD") ?: ""
            }
        }
    }

}

signing {
    val signingKey = System.getenv("GPG_PRIVATE_KEY")
    val signingPassword = System.getenv("GPG_PASSPHRASE")
    if (signingKey != null && signingPassword != null) {
        useInMemoryPgpKeys(signingKey, signingPassword)
        sign(publishing.publications["maven"])
    }
}
