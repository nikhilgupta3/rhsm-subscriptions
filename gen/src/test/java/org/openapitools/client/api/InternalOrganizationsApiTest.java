/*
 * internal-organizations-sync-api
 * REST interface for the internal-organization-sync service. Please note any deprecated APIs. Our current deprecation policy is to keep deprecated APIs around for at least 6 months.
 *
 * The version of the OpenAPI document: 1.0.0
 * 
 *
 * NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
 * https://openapi-generator.tech
 * Do not edit the class manually.
 */


package org.openapitools.client.api;

import org.openapitools.client.ApiException;
import org.openapitools.client.model.DefaultResponse;
import org.openapitools.client.model.Errors;
import org.openapitools.client.model.OrgExistsResponse;
import org.openapitools.client.model.OrgInventory;
import org.openapitools.client.model.OrgSyncRequest;
import org.junit.Test;
import org.junit.Ignore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * API tests for InternalOrganizationsApi
 */
@Ignore
public class InternalOrganizationsApiTest {

    private final InternalOrganizationsApi api = new InternalOrganizationsApi();

    
    /**
     * Add a given list of organizations to the database sync list.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void addOrgsToSyncListTest() throws ApiException {
        List<String> orgIds = null;
        DefaultResponse response = api.addOrgsToSyncList(orgIds);

        // TODO: test validations
    }
    
    /**
     * See conduit representation of an org&#39;s systems from RHSM.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void getInventoryForOrgTest() throws ApiException {
        String orgId = null;
        Integer limit = null;
        Integer offset = null;
        OrgInventory response = api.getInventoryForOrg(orgId, limit, offset);

        // TODO: test validations
    }
    
    /**
     * Check if given Org ID is in the database sync list.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void hasOrgInSyncListTest() throws ApiException {
        String orgId = null;
        OrgExistsResponse response = api.hasOrgInSyncList(orgId);

        // TODO: test validations
    }
    
    /**
     * Remove a given list of organizations from the database sync list.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void removeOrgsFromSyncListTest() throws ApiException {
        List<String> orgIds = null;
        DefaultResponse response = api.removeOrgsFromSyncList(orgIds);

        // TODO: test validations
    }
    
    /**
     * Sync all organizations.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void syncFullOrgListTest() throws ApiException {
        DefaultResponse response = api.syncFullOrgList();

        // TODO: test validations
    }
    
    /**
     * Sync organization for given org_id.
     *
     * 
     *
     * @throws ApiException
     *          if the Api call fails
     */
    @Test
    public void syncOrgTest() throws ApiException {
        OrgSyncRequest orgSyncRequest = null;
        DefaultResponse response = api.syncOrg(orgSyncRequest);

        // TODO: test validations
    }
    
}
