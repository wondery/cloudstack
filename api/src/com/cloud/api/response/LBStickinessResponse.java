// Licensed to the Apache Software Foundation (ASF) under one
// or more contributor license agreements.  See the NOTICE file
// distributed with this work for additional information
// regarding copyright ownership.  The ASF licenses this file
// to you under the Apache License, Version 2.0 (the
// "License"); you may not use this file except in compliance
// with the License.  You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.
package com.cloud.api.response;

import com.cloud.api.ApiConstants;
import com.cloud.utils.IdentityProxy;
import com.cloud.serializer.Param;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class LBStickinessResponse extends BaseResponse {
    @SerializedName("lbruleid")
    @Param(description = "the LB rule ID")
    private IdentityProxy lbRuleId = new IdentityProxy("firewall_rules");

    @SerializedName("name")
    @Param(description = "the name of the Stickiness policy")
    private String name;

    @SerializedName("description")
    @Param(description = "the description of the Stickiness policy")
    private String description;;

    @SerializedName("account")
    @Param(description = "the account of the Stickiness policy")
    private String accountName;
    
    @SerializedName(ApiConstants.DOMAIN_ID)
    @Param(description = "the domain ID of the Stickiness policy")
    private IdentityProxy domainId = new IdentityProxy("domain");

    @SerializedName("domain")
    @Param(description = "the domain of the Stickiness policy")
    private String domainName;

    @SerializedName("state")
    @Param(description = "the state of the policy")
    private String state;
    
    @SerializedName(ApiConstants.ZONE_ID)
    @Param(description = "the id of the zone the Stickiness policy belongs to")
    private IdentityProxy zoneId = new IdentityProxy("data_center");

    @SerializedName("stickinesspolicy")
    @Param(description = "the list of stickinesspolicies", responseObject = LBStickinessPolicyResponse.class)
    private List<LBStickinessPolicyResponse> stickinessPolicies;

    public void setlbRuleId(Long lbRuleId) {
        this.lbRuleId.setValue(lbRuleId);
    }

    public void setRules(List<LBStickinessPolicyResponse> policies) {
        this.stickinessPolicies = policies;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<LBStickinessPolicyResponse> getStickinessPolicies() {
        return stickinessPolicies;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public void setDomainId(Long domainId) {
        this.domainId.setValue(domainId);
    }
    
    public void setZoneId(Long zoneId) {
        this.zoneId.setValue(zoneId);
    }
    
    public String getDomainName() {
        return domainName;
    }

    public void setDomainName(String domainName) {
        this.domainName = domainName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public LBStickinessResponse() {

    }

}