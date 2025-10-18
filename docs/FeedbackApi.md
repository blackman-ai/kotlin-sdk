# FeedbackApi

All URIs are relative to *https://app.useblackman.ai/v1*

| Method | HTTP request | Description |
| ------------- | ------------- | ------------- |
| [**submitFeedback**](FeedbackApi.md#submitFeedback) | **POST** /v1/feedback | Submit feedback for a completion response |


<a id="submitFeedback"></a>
# **submitFeedback**
> SubmitFeedbackResponse submitFeedback(submitFeedbackRequest)

Submit feedback for a completion response

### Example
```kotlin
// Import classes:
//import ai.useblackman.client.infrastructure.*
//import ai.useblackman.client.models.*

val apiInstance = FeedbackApi()
val submitFeedbackRequest : SubmitFeedbackRequest =  // SubmitFeedbackRequest | 
try {
    val result : SubmitFeedbackResponse = apiInstance.submitFeedback(submitFeedbackRequest)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FeedbackApi#submitFeedback")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FeedbackApi#submitFeedback")
    e.printStackTrace()
}
```

### Parameters
| Name | Type | Description  | Notes |
| ------------- | ------------- | ------------- | ------------- |
| **submitFeedbackRequest** | [**SubmitFeedbackRequest**](SubmitFeedbackRequest.md)|  | |

### Return type

[**SubmitFeedbackResponse**](SubmitFeedbackResponse.md)

### Authorization


Configure BearerAuth:
    ApiClient.accessToken = ""

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

