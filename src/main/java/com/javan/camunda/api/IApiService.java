package com.javan.camunda.api;

import com.javan.camunda.data.ProcessDefinitionResponse;
import com.javan.camunda.data.StartBody;
import com.javan.camunda.data.StartResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface IApiService {

    @GET
    Call<ProcessDefinitionResponse> getProcessDefinition(@Url String url);

    @POST
    Call<StartResponse> startProcessInstance(@Url String url, @Body StartBody startBody);
}
