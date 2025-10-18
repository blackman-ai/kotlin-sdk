# SemanticCacheApi

All URIs are relative to *https://app.useblackman.ai/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**getConfig**](SemanticCacheApi.md#getConfig) | **GET** /v1/semantic-cache/config | Get semantic cache configuration for the current account |
| [**getStats**](SemanticCacheApi.md#getStats) | **GET** /v1/semantic-cache/stats | Get semantic cache statistics including hit rate and savings |
| [**invalidateAll**](SemanticCacheApi.md#invalidateAll) | **DELETE** /v1/semantic-cache/invalidate | Invalidate all cache entries for the current account |
| [**updateConfig**](SemanticCacheApi.md#updateConfig) | **PUT** /v1/semantic-cache/config | Update semantic cache configuration for the current account |


<a id="getConfig"></a>
# **getConfig**
> SemanticCacheConfig getConfig()

Get semantic cache configuration for the current account

### Example
```kotlin
// Import classes:
//import ai.useblackman.client.infrastructure.*
//import ai.useblackman.client.models.*

val apiInstance = SemanticCacheApi()
try {
    val result : SemanticCacheConfig = apiInstance.getConfig()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SemanticCacheApi#getConfig")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SemanticCacheApi#getConfig")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SemanticCacheConfig**](SemanticCacheConfig.md)

### Authorization


Configure BearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="getStats"></a>
# **getStats**
> SemanticCacheStats getStats()

Get semantic cache statistics including hit rate and savings

### Example
```kotlin
// Import classes:
//import ai.useblackman.client.infrastructure.*
//import ai.useblackman.client.models.*

val apiInstance = SemanticCacheApi()
try {
    val result : SemanticCacheStats = apiInstance.getStats()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SemanticCacheApi#getStats")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SemanticCacheApi#getStats")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**SemanticCacheStats**](SemanticCacheStats.md)

### Authorization


Configure BearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="invalidateAll"></a>
# **invalidateAll**
> InvalidateResponse invalidateAll()

Invalidate all cache entries for the current account

### Example
```kotlin
// Import classes:
//import ai.useblackman.client.infrastructure.*
//import ai.useblackman.client.models.*

val apiInstance = SemanticCacheApi()
try {
    val result : InvalidateResponse = apiInstance.invalidateAll()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling SemanticCacheApi#invalidateAll")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SemanticCacheApi#invalidateAll")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**InvalidateResponse**](InvalidateResponse.md)

### Authorization


Configure BearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a id="updateConfig"></a>
# **updateConfig**
> updateConfig(semanticCacheConfig)

Update semantic cache configuration for the current account

### Example
```kotlin
// Import classes:
//import ai.useblackman.client.infrastructure.*
//import ai.useblackman.client.models.*

val apiInstance = SemanticCacheApi()
val semanticCacheConfig : SemanticCacheConfig =  // SemanticCacheConfig | 
try {
    apiInstance.updateConfig(semanticCacheConfig)
} catch (e: ClientException) {
    println("4xx response calling SemanticCacheApi#updateConfig")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling SemanticCacheApi#updateConfig")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **semanticCacheConfig** | [**SemanticCacheConfig**](SemanticCacheConfig.md)|  | |

### Return type

null (empty response body)

### Authorization


Configure BearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

