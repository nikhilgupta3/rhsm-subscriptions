# RootApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**getOpenApiJson**](RootApi.md#getOpenApiJson) | **GET** /internal-organizations-sync-openapi.json | 
[**getOpenApiYaml**](RootApi.md#getOpenApiYaml) | **GET** /internal-organizations-sync-openapi.yaml | 


<a name="getOpenApiJson"></a>
# **getOpenApiJson**
> String getOpenApiJson()



Get the OpenAPI spec in JSON format.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RootApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: PskIdentity
    ApiKeyAuth PskIdentity = (ApiKeyAuth) defaultClient.getAuthentication("PskIdentity");
    PskIdentity.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //PskIdentity.setApiKeyPrefix("Token");

    RootApi apiInstance = new RootApi(defaultClient);
    try {
      String result = apiInstance.getOpenApiJson();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RootApi#getOpenApiJson");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

[PskIdentity](../README.md#PskIdentity)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The request to get the OpenAPI JSON was successful. |  -  |

<a name="getOpenApiYaml"></a>
# **getOpenApiYaml**
> String getOpenApiYaml()



Get the OpenAPI spec in YAML format.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.RootApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: PskIdentity
    ApiKeyAuth PskIdentity = (ApiKeyAuth) defaultClient.getAuthentication("PskIdentity");
    PskIdentity.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //PskIdentity.setApiKeyPrefix("Token");

    RootApi apiInstance = new RootApi(defaultClient);
    try {
      String result = apiInstance.getOpenApiYaml();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling RootApi#getOpenApiYaml");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

**String**

### Authorization

[PskIdentity](../README.md#PskIdentity)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/x-yaml

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The request to get the OpenAPI YAML was successful. |  -  |

