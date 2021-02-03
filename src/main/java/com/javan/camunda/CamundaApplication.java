package com.javan.camunda;

import com.javan.camunda.api.ApiClient;
import com.javan.camunda.api.IApiService;
import com.javan.camunda.data.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@SpringBootApplication
@RestController
public class CamundaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamundaApplication.class, args);
	}

	ApiClient apiClient = new ApiClient();
	IApiService iApiService = apiClient.getRetrofit().create(IApiService.class);
	String responses;
	@GetMapping("/coba")
	private String getTask(){
		iApiService.getProcessDefinition("process-definition/key/process_reimburse_kel4")
				.enqueue(new Callback<ProcessDefinitionResponse>() {
					@Override
					public void onResponse(Call<ProcessDefinitionResponse> call, Response<ProcessDefinitionResponse> response) {
						responses = response.body().getId();
					}

					@Override
					public void onFailure(Call<ProcessDefinitionResponse> call, Throwable throwable) {
						responses = throwable.toString();
					}
				});
		return responses;
	}


	@GetMapping("/starttask")
	private String startTask(){
		ApiClient apiClient = new ApiClient();
		IApiService iApiService = apiClient.getRetrofit().create(IApiService.class);
		Variables variables = new Variables(new Name("String", "Naufal Irfani"),
				new Jumlah("Integer", 150000), new Keterangan("String", "Bayar cicilan motor"));
		StartBody startBody = new StartBody(variables,"reimburse");
		iApiService.startProcessInstance(
				"http://localhost:8081/engine-rest/process-definition/key/process_reimburse_wisnu/start", startBody)
				.enqueue(new Callback<StartResponse>() {
					@Override
					public void onResponse(Call<StartResponse> call, Response<StartResponse> response) {
						responses = response.body().getBusinessKey();
					}

					@Override
					public void onFailure(Call<StartResponse> call, Throwable throwable) {
						responses = throwable.toString();
					}
				});
		return responses;
	}

}
