# InternalOrganizationsApi

All URIs are relative to *http://localhost*

Method | HTTP request | Description
------------- | ------------- | -------------
[**addOrgsToSyncList**](InternalOrganizationsApi.md#addOrgsToSyncList) | **POST** /internal/organizations-sync-list | Add a given list of organizations to the database sync list.
[**getInventoryForOrg**](InternalOrganizationsApi.md#getInventoryForOrg) | **GET** /internal/organizations/{org_id}/inventory | See conduit representation of an org&#39;s systems from RHSM.
[**hasOrgInSyncList**](InternalOrganizationsApi.md#hasOrgInSyncList) | **GET** /internal/organizations-sync-list/{org_id} | Check if given Org ID is in the database sync list.
[**removeOrgsFromSyncList**](InternalOrganizationsApi.md#removeOrgsFromSyncList) | **DELETE** /internal/organizations-sync-list | Remove a given list of organizations from the database sync list.
[**syncFullOrgList**](InternalOrganizationsApi.md#syncFullOrgList) | **POST** /internal/rpc/syncAllOrgs | Sync all organizations.
[**syncOrg**](InternalOrganizationsApi.md#syncOrg) | **POST** /internal/rpc/syncOrg | Sync organization for given org_id.


<a name="addOrgsToSyncList"></a>
# **addOrgsToSyncList**
> DefaultResponse addOrgsToSyncList(orgIds)

Add a given list of organizations to the database sync list.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalOrganizationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: PskIdentity
    ApiKeyAuth PskIdentity = (ApiKeyAuth) defaultClient.getAuthentication("PskIdentity");
    PskIdentity.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //PskIdentity.setApiKeyPrefix("Token");

    InternalOrganizationsApi apiInstance = new InternalOrganizationsApi(defaultClient);
    List<String> orgIds = Arrays.asList(); // List<String> | Comma separated list of org_ids. (Ex. 123,456,789)
    try {
      DefaultResponse result = apiInstance.addOrgsToSyncList(orgIds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalOrganizationsApi#addOrgsToSyncList");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgIds** | [**List&lt;String&gt;**](String.md)| Comma separated list of org_ids. (Ex. 123,456,789) |

### Return type

[**DefaultResponse**](DefaultResponse.md)

### Authorization

[PskIdentity](../README.md#PskIdentity)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/vnd.api+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The request for adding organizations to the sync list has completed successfully. |  -  |
**400** | The server could could not process the current request. |  -  |
**403** | The request was valid, but the request was refused by the server. |  -  |
**500** | An internal server error has occurred and is not recoverable. |  -  |

<a name="getInventoryForOrg"></a>
# **getInventoryForOrg**
> OrgInventory getInventoryForOrg(orgId, limit, offset)

See conduit representation of an org&#39;s systems from RHSM.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalOrganizationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: PskIdentity
    ApiKeyAuth PskIdentity = (ApiKeyAuth) defaultClient.getAuthentication("PskIdentity");
    PskIdentity.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //PskIdentity.setApiKeyPrefix("Token");

    InternalOrganizationsApi apiInstance = new InternalOrganizationsApi(defaultClient);
    String orgId = "orgId_example"; // String | 
    Integer limit = 56; // Integer | The numbers of items to return
    Integer offset = 56; // Integer | The number of items to skip before starting to collect the result set
    try {
      OrgInventory result = apiInstance.getInventoryForOrg(orgId, limit, offset);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalOrganizationsApi#getInventoryForOrg");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgId** | **String**|  |
 **limit** | **Integer**| The numbers of items to return |
 **offset** | **Integer**| The number of items to skip before starting to collect the result set | [optional]

### Return type

[**OrgInventory**](OrgInventory.md)

### Authorization

[PskIdentity](../README.md#PskIdentity)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/vnd.api+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** |  |  -  |
**400** | The server could could not process the current request. |  -  |
**403** | The request was valid, but the request was refused by the server. |  -  |
**404** | The requested resource was not found. |  -  |
**500** | An internal server error has occurred and is not recoverable. |  -  |

<a name="hasOrgInSyncList"></a>
# **hasOrgInSyncList**
> OrgExistsResponse hasOrgInSyncList(orgId)

Check if given Org ID is in the database sync list.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalOrganizationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: PskIdentity
    ApiKeyAuth PskIdentity = (ApiKeyAuth) defaultClient.getAuthentication("PskIdentity");
    PskIdentity.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //PskIdentity.setApiKeyPrefix("Token");

    InternalOrganizationsApi apiInstance = new InternalOrganizationsApi(defaultClient);
    String orgId = "orgId_example"; // String | 
    try {
      OrgExistsResponse result = apiInstance.hasOrgInSyncList(orgId);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalOrganizationsApi#hasOrgInSyncList");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgId** | **String**|  |

### Return type

[**OrgExistsResponse**](OrgExistsResponse.md)

### Authorization

[PskIdentity](../README.md#PskIdentity)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/vnd.api+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The request to check the sync list for given org ID has successfully completed. |  -  |
**400** | The server could could not process the current request. |  -  |
**403** | The request was valid, but the request was refused by the server. |  -  |
**404** | The requested resource was not found. |  -  |
**500** | An internal server error has occurred and is not recoverable. |  -  |

<a name="removeOrgsFromSyncList"></a>
# **removeOrgsFromSyncList**
> DefaultResponse removeOrgsFromSyncList(orgIds)

Remove a given list of organizations from the database sync list.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalOrganizationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: PskIdentity
    ApiKeyAuth PskIdentity = (ApiKeyAuth) defaultClient.getAuthentication("PskIdentity");
    PskIdentity.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //PskIdentity.setApiKeyPrefix("Token");

    InternalOrganizationsApi apiInstance = new InternalOrganizationsApi(defaultClient);
    List<String> orgIds = Arrays.asList(); // List<String> | Comma separated list of org_ids. (Ex. 123,456,789)
    try {
      DefaultResponse result = apiInstance.removeOrgsFromSyncList(orgIds);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalOrganizationsApi#removeOrgsFromSyncList");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgIds** | [**List&lt;String&gt;**](String.md)| Comma separated list of org_ids. (Ex. 123,456,789) |

### Return type

[**DefaultResponse**](DefaultResponse.md)

### Authorization

[PskIdentity](../README.md#PskIdentity)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/vnd.api+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The request for removing organizations from the sync list has completed successfully. |  -  |
**400** | The server could could not process the current request. |  -  |
**403** | The request was valid, but the request was refused by the server. |  -  |
**404** | The requested resource was not found. |  -  |
**500** | An internal server error has occurred and is not recoverable. |  -  |

<a name="syncFullOrgList"></a>
# **syncFullOrgList**
> DefaultResponse syncFullOrgList()

Sync all organizations.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalOrganizationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: PskIdentity
    ApiKeyAuth PskIdentity = (ApiKeyAuth) defaultClient.getAuthentication("PskIdentity");
    PskIdentity.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //PskIdentity.setApiKeyPrefix("Token");

    InternalOrganizationsApi apiInstance = new InternalOrganizationsApi(defaultClient);
    try {
      DefaultResponse result = apiInstance.syncFullOrgList();
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalOrganizationsApi#syncFullOrgList");
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

[**DefaultResponse**](DefaultResponse.md)

### Authorization

[PskIdentity](../README.md#PskIdentity)

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/vnd.api+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**202** | The request for syncing all organizations is successfully running. |  -  |
**403** | The request was valid, but the request was refused by the server. |  -  |
**500** | An internal server error has occurred and is not recoverable. |  -  |

<a name="syncOrg"></a>
# **syncOrg**
> DefaultResponse syncOrg(orgSyncRequest)

Sync organization for given org_id.

### Example
```java
// Import classes:
import org.openapitools.client.ApiClient;
import org.openapitools.client.ApiException;
import org.openapitools.client.Configuration;
import org.openapitools.client.auth.*;
import org.openapitools.client.models.*;
import org.openapitools.client.api.InternalOrganizationsApi;

public class Example {
  public static void main(String[] args) {
    ApiClient defaultClient = Configuration.getDefaultApiClient();
    defaultClient.setBasePath("http://localhost");
    
    // Configure API key authorization: PskIdentity
    ApiKeyAuth PskIdentity = (ApiKeyAuth) defaultClient.getAuthentication("PskIdentity");
    PskIdentity.setApiKey("YOUR API KEY");
    // Uncomment the following line to set a prefix for the API key, e.g. "Token" (defaults to null)
    //PskIdentity.setApiKeyPrefix("Token");

    InternalOrganizationsApi apiInstance = new InternalOrganizationsApi(defaultClient);
    OrgSyncRequest orgSyncRequest = new OrgSyncRequest(); // OrgSyncRequest | ID of Organization to sync
    try {
      DefaultResponse result = apiInstance.syncOrg(orgSyncRequest);
      System.out.println(result);
    } catch (ApiException e) {
      System.err.println("Exception when calling InternalOrganizationsApi#syncOrg");
      System.err.println("Status code: " + e.getCode());
      System.err.println("Reason: " + e.getResponseBody());
      System.err.println("Response headers: " + e.getResponseHeaders());
      e.printStackTrace();
    }
  }
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **orgSyncRequest** | [**OrgSyncRequest**](OrgSyncRequest.md)| ID of Organization to sync |

### Return type

[**DefaultResponse**](DefaultResponse.md)

### Authorization

[PskIdentity](../README.md#PskIdentity)

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/vnd.api+json

### HTTP response details
| Status code | Description | Response headers |
|-------------|-------------|------------------|
**200** | The request for syncing organization has completed successfully. |  -  |
**400** | The server could could not process the current request. |  -  |
**403** | The request was valid, but the request was refused by the server. |  -  |
**404** | The requested resource was not found. |  -  |
**500** | An internal server error has occurred and is not recoverable. |  -  |

