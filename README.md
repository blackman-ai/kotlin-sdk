# Blackman AI Kotlin SDK

Official Kotlin client for [Blackman AI](https://www.useblackman.ai) - The AI API proxy that optimizes token usage to reduce costs.

## Features

- 🚀 Drop-in replacement for OpenAI, Anthropic, and other LLM APIs
- 💰 Automatic token optimization (save 20-40% on costs)
- 📊 Built-in analytics and cost tracking
- 🔒 Enterprise-grade security with SSO support
- ⚡ Low latency overhead (<50ms)
- 🎯 Semantic caching for repeated queries
- ⚙️ Coroutines support
- 📱 Android compatible

## Installation

### Gradle (Kotlin DSL)

```kotlin
dependencies {
    implementation("ai.useblackman:client-kotlin:0.0.5")
}
```

### Gradle (Groovy)

```groovy
dependencies {
    implementation 'ai.useblackman:client-kotlin:0.0.5'
}
```

### Maven

```xml
<dependency>
    <groupId>ai.useblackman</groupId>
    <artifactId>client-kotlin</artifactId>
    <version>0.0.5</version>
</dependency>
```

## Quick Start

```kotlin
import ai.useblackman.client.apis.CompletionsApi
import ai.useblackman.client.models.CompletionRequest
import ai.useblackman.client.models.Message

suspend fun main() {
    // Configure client
    val api = CompletionsApi(basePath = "https://app.useblackman.ai")
    api.setApiKey("Bearer sk_your_blackman_api_key")

    // Create completion request
    val request = CompletionRequest(
        provider = "OpenAI",
        model = "gpt-4o",
        messages = listOf(
            Message(
                role = "user",
                content = "Explain quantum computing in simple terms"
            )
        )
    )

    // Send request
    try {
        val response = api.completions(request)
        println(response.choices[0].message.content)
        println("Tokens used: ${response.usage.totalTokens}")
    } catch (e: Exception) {
        println("Error: ${e.message}")
    }
}
```

## Authentication

Get your API key from the [Blackman AI Dashboard](https://app.useblackman.ai/settings/api-keys).

```kotlin
val api = CompletionsApi(basePath = "https://app.useblackman.ai")
api.setApiKey("Bearer sk_your_blackman_api_key")
```

## Framework Integration

### Ktor Server

```kotlin
import io.ktor.server.application.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import ai.useblackman.client.apis.CompletionsApi
import ai.useblackman.client.models.CompletionRequest
import ai.useblackman.client.models.Message
import kotlinx.serialization.Serializable

@Serializable
data class ChatRequest(val message: String)

@Serializable
data class ChatResponse(val response: String, val tokens: Int)

fun main() {
    embeddedServer(Netty, port = 8080) {
        val api = CompletionsApi(basePath = "https://app.useblackman.ai")
        api.setApiKey("Bearer ${System.getenv("BLACKMAN_API_KEY")}")

        routing {
            post("/chat") {
                val request = call.receive<ChatRequest>()

                val completionRequest = CompletionRequest(
                    provider = "OpenAI",
                    model = "gpt-4o",
                    messages = listOf(
                        Message(role = "user", content = request.message)
                    )
                )

                try {
                    val response = api.completions(completionRequest)
                    call.respond(
                        ChatResponse(
                            response = response.choices[0].message.content,
                            tokens = response.usage.totalTokens
                        )
                    )
                } catch (e: Exception) {
                    call.respond(mapOf("error" to e.message))
                }
            }
        }
    }.start(wait = true)
}
```

### Spring Boot

```kotlin
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.bind.annotation.*
import ai.useblackman.client.apis.CompletionsApi
import ai.useblackman.client.models.CompletionRequest
import ai.useblackman.client.models.Message

@SpringBootApplication
class Application

fun main(args: Array<String>) {
    runApplication<Application>(*args)
}

@Configuration
class BlackmanConfig {
    @Bean
    fun completionsApi(): CompletionsApi {
        val api = CompletionsApi(basePath = "https://app.useblackman.ai")
        api.setApiKey("Bearer ${System.getenv("BLACKMAN_API_KEY")}")
        return api
    }
}

data class ChatRequest(val message: String)
data class ChatResponse(val response: String, val tokens: Int)

@RestController
@RequestMapping("/api")
class ChatController(private val completionsApi: CompletionsApi) {

    @PostMapping("/chat")
    suspend fun chat(@RequestBody request: ChatRequest): ChatResponse {
        val completionRequest = CompletionRequest(
            provider = "OpenAI",
            model = "gpt-4o",
            messages = listOf(
                Message(role = "user", content = request.message)
            )
        )

        val response = completionsApi.completions(completionRequest)
        return ChatResponse(
            response = response.choices[0].message.content,
            tokens = response.usage.totalTokens
        )
    }
}
```

### Android (Jetpack Compose)

```kotlin
import androidx.compose.runtime.*
import androidx.compose.material3.*
import androidx.compose.foundation.layout.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import ai.useblackman.client.apis.CompletionsApi
import ai.useblackman.client.models.CompletionRequest
import ai.useblackman.client.models.Message
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {
    private val api = CompletionsApi(basePath = "https://app.useblackman.ai").apply {
        setApiKey("Bearer YOUR_API_KEY")
    }

    var response by mutableStateOf("")
        private set

    var isLoading by mutableStateOf(false)
        private set

    fun sendMessage(message: String) {
        viewModelScope.launch {
            isLoading = true
            try {
                val request = CompletionRequest(
                    provider = "OpenAI",
                    model = "gpt-4o",
                    messages = listOf(Message(role = "user", content = message))
                )
                val result = api.completions(request)
                response = result.choices[0].message.content
            } catch (e: Exception) {
                response = "Error: ${e.message}"
            } finally {
                isLoading = false
            }
        }
    }
}

@Composable
fun ChatScreen(viewModel: ChatViewModel) {
    var userMessage by remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        TextField(
            value = userMessage,
            onValueChange = { userMessage = it },
            label = { Text("Enter your message") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = { viewModel.sendMessage(userMessage) },
            enabled = !viewModel.isLoading && userMessage.isNotEmpty()
        ) {
            Text("Send")
        }

        if (viewModel.isLoading) {
            CircularProgressIndicator()
        }

        Text(viewModel.response)
    }
}
```

## Advanced Usage

### Custom Timeout

```kotlin
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

val client = OkHttpClient.Builder()
    .connectTimeout(30, TimeUnit.SECONDS)
    .readTimeout(60, TimeUnit.SECONDS)
    .writeTimeout(60, TimeUnit.SECONDS)
    .build()

val api = CompletionsApi(basePath = "https://app.useblackman.ai", client = client)
```

### Error Handling

```kotlin
import ai.useblackman.client.infrastructure.ClientException
import ai.useblackman.client.infrastructure.ServerException

try {
    val response = api.completions(request)
    println(response.choices[0].message.content)
} catch (e: ClientException) {
    println("Client error (4xx): ${e.statusCode} - ${e.message}")
} catch (e: ServerException) {
    println("Server error (5xx): ${e.statusCode} - ${e.message}")
} catch (e: Exception) {
    println("Unexpected error: ${e.message}")
}
```

### Retry Logic with Coroutines

```kotlin
import kotlinx.coroutines.delay
import kotlin.math.pow

suspend fun completionsWithRetry(
    api: CompletionsApi,
    request: CompletionRequest,
    maxRetries: Int = 3
): CompletionResponse {
    var lastException: Exception? = null

    repeat(maxRetries) { attempt ->
        try {
            return api.completions(request)
        } catch (e: Exception) {
            lastException = e
            if (attempt < maxRetries - 1) {
                val delayMs = (2.0.pow(attempt) * 100).toLong()
                delay(delayMs)
            }
        }
    }

    throw lastException ?: Exception("Retry failed")
}
```

### Concurrent Requests

```kotlin
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.coroutineScope

suspend fun sendMultipleRequests(messages: List<String>) = coroutineScope {
    val api = CompletionsApi(basePath = "https://app.useblackman.ai")

    messages.map { message ->
        async {
            val request = CompletionRequest(
                provider = "OpenAI",
                model = "gpt-4o",
                messages = listOf(Message(role = "user", content = message))
            )
            api.completions(request)
        }
    }.awaitAll()
}
```

### Flow-based Streaming

```kotlin
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

fun completionsFlow(api: CompletionsApi, request: CompletionRequest): Flow<String> = flow {
    try {
        val response = api.completions(request)
        emit(response.choices[0].message.content)
    } catch (e: Exception) {
        emit("Error: ${e.message}")
    }
}

// Usage
completionsFlow(api, request).collect { content ->
    println(content)
}
```

## Documentation

- [Full API Reference](https://app.useblackman.ai/docs)
- [Getting Started Guide](https://app.useblackman.ai/docs/getting-started)
- [Kotlin Examples](https://github.com/blackman-ai/kotlin-sdk/tree/main/examples)

## Requirements

- Kotlin 1.9+
- JVM 8+
- Android 5.0+ (API 21+) for Android projects

## Support

- 📧 Email: [support@blackman.ai](mailto:support@blackman.ai)
- 💬 Discord: [Join our community](https://discord.gg/blackman-ai)
- 🐛 Issues: [GitHub Issues](https://github.com/blackman-ai/kotlin-sdk/issues)

## License

MIT © Blackman AI
