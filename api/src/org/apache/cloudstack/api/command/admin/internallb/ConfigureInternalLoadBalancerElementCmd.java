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

package org.apache.cloudstack.api.command.admin.internallb;

import java.util.List;

import javax.inject.Inject;

import org.apache.cloudstack.api.APICommand;
import org.apache.cloudstack.api.ApiConstants;
import org.apache.cloudstack.api.ApiErrorCode;
import org.apache.cloudstack.api.BaseAsyncCmd;
import org.apache.cloudstack.api.Parameter;
import org.apache.cloudstack.api.ServerApiException;
import org.apache.cloudstack.api.response.VirtualRouterProviderResponse;
import org.apache.cloudstack.network.element.InternalLoadBalancerElementService;
import org.apache.log4j.Logger;

import com.cloud.async.AsyncJob;
import com.cloud.event.EventTypes;
import com.cloud.exception.ConcurrentOperationException;
import com.cloud.exception.InsufficientCapacityException;
import com.cloud.exception.ResourceUnavailableException;
import com.cloud.network.VirtualRouterProvider;
import com.cloud.user.Account;
import com.cloud.user.UserContext;

@APICommand(name = "configureInternalLoadBalancerElement", responseObject=VirtualRouterProviderResponse.class,
            description="Configures an internal load balancer element.", since="4.2.0")
public class ConfigureInternalLoadBalancerElementCmd extends BaseAsyncCmd {
    public static final Logger s_logger = Logger.getLogger(ConfigureInternalLoadBalancerElementCmd.class.getName());
    private static final String s_name = "configureinternalloadbalancerelementresponse";

    @Inject
    private List<InternalLoadBalancerElementService> _service;

    /////////////////////////////////////////////////////
    //////////////// API parameters /////////////////////
    /////////////////////////////////////////////////////

    @Parameter(name=ApiConstants.ID, type=CommandType.UUID, entityType = VirtualRouterProviderResponse.class,
            required=true, description="the ID of the internal lb provider")
    private Long id;

    @Parameter(name=ApiConstants.ENABLED, type=CommandType.BOOLEAN, required=true, description="Enables/Disables the Internal load balancer element")
    private Boolean enabled;

    /////////////////////////////////////////////////////
    /////////////////// Accessors ///////////////////////
    /////////////////////////////////////////////////////

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public Boolean getEnabled() {
        return enabled;
    }

    /////////////////////////////////////////////////////
    /////////////// API Implementation///////////////////
    /////////////////////////////////////////////////////

    @Override
    public String getCommandName() {
        return s_name;
    }

    public static String getResultObjectName() {
        return "boolean";
    }

    @Override
    public long getEntityOwnerId() {
        return Account.ACCOUNT_ID_SYSTEM;
    }

    @Override
    public String getEventType() {
        return EventTypes.EVENT_NETWORK_ELEMENT_CONFIGURE;
    }

    @Override
    public String getEventDescription() {
        return  "configuring internal load balancer element: " + id;
    }

    public AsyncJob.Type getInstanceType() {
        return AsyncJob.Type.None;
    }

    public Long getInstanceId() {
        return id;
    }

    @Override
    public void execute() throws ConcurrentOperationException, ResourceUnavailableException, InsufficientCapacityException{
        s_logger.debug("hello alena");
        UserContext.current().setEventDetails("Internal load balancer element: " + id);
        s_logger.debug("hello alena");
        VirtualRouterProvider result = _service.get(0).configure(this);
        s_logger.debug("hello alena");
        if (result != null){
            VirtualRouterProviderResponse routerResponse = _responseGenerator.createVirtualRouterProviderResponse(result);
            routerResponse.setResponseName(getCommandName());
            this.setResponseObject(routerResponse);
        } else {
            throw new ServerApiException(ApiErrorCode.INTERNAL_ERROR, "Failed to configure the internal load balancer element");
        }
    }
}