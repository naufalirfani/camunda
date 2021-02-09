package com.javan.camunda.api;

import com.javan.camunda.data.*;
import com.javan.camunda.data.startbody.StartBody;
import com.javan.camunda.data.taskVariable.TaskVariableResponse;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IApiService {

    @GET
    Call<ProcessDefinitionResponse> getProcessDefinition(@Url String url);

    @POST
    Call<StartResponse> startProcessInstance(@Url String url, @Body StartBody startBody);

    @GET("http://localhost:8081/engine-rest/task")
    Call<List<TaskResponse>> getListTask();

    @GET()
    Call<TaskVariableResponse> getVariableTask(@Url String url);

    @POST
    Call<String> postReview(@Url String url, @Body ReviewBody reviewBody);

    @POST
    Call<String> postPenolakan(@Url String url, @Body PenolakanBody penolakanBody);

    @POST
    Call<String> uploadBukti(@Url String url, @Body BuktiBody buktiBody);
}
