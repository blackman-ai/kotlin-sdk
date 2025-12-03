
# CompletionRequest

## Properties
| Name | Type | Description | Notes |
| ------------ | ------------- | ------------- | ------------- |
| **messages** | [**kotlin.collections.List&lt;Message&gt;**](Message.md) |  |  |
| **model** | **kotlin.String** |  |  |
| **provider** | [**Provider**](Provider.md) |  |  |
| **maxTokens** | **kotlin.Int** |  |  [optional] |
| **stop** | **kotlin.collections.List&lt;kotlin.String&gt;** |  |  [optional] |
| **stream** | **kotlin.Boolean** |  |  [optional] |
| **temperature** | **kotlin.Float** |  |  [optional] |
| **topP** | **kotlin.Float** |  |  [optional] |
| **metadata** | [**kotlin.Any**](.md) | Optional metadata for tracking, analytics, and conditional processing. Can include session IDs, user context, feature flags, or any custom data. This metadata is logged with the request and can be used for filtering/analysis. |  [optional] |



