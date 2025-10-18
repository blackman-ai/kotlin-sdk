# CompletionsApi

All URIs are relative to *https://app.useblackman.ai/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**completions**](CompletionsApi.md#completions) | **POST** /v1/completions |  |


<a id="completions"></a>
# **completions**
> CompletionResponse completions(completionRequest, xProviderApiKey)



### Example
```kotlin
// Import classes:
//import ai.useblackman.client.infrastructure.*
//import ai.useblackman.client.models.*

val apiInstance = CompletionsApi()
val completionRequest : CompletionRequest =  // CompletionRequest | 
val xProviderApiKey : kotlin.String = xProviderApiKey_example // kotlin.String | Optional provider API key to override stored or system keys
try {
    val result : CompletionResponse = apiInstance.completions(completionRequest, xProviderApiKey)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling CompletionsApi#completions")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling CompletionsApi#completions")
    e.printStackTrace()
}
```

### Parameters
| **completionRequest** | [**CompletionRequest**](CompletionRequest.md)|  | |
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **xProviderApiKey** | **kotlin.String**| Optional provider API key to override stored or system keys | [optional] |

### Return type

[**CompletionResponse**](CompletionResponse.md)

### Authorization


Configure BearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

