package com.javan.camunda;

import com.javan.camunda.api.ApiClient;
import com.javan.camunda.api.IApiService;
import com.javan.camunda.data.PenolakanBody;
import com.javan.camunda.data.ReviewBody;
import com.javan.camunda.data.StartResponse;
import com.javan.camunda.data.TaskResponse;
import com.javan.camunda.data.startbody.*;
import com.javan.camunda.data.taskVariable.TaskVariableResponse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import java.util.List;

@Controller
public class ReimburseController {
    ApiClient apiClient = new ApiClient();
    IApiService iApiService = apiClient.getRetrofit().create(IApiService.class);
    String responses;
    List<TaskResponse> hasil;
    List<TaskResponse> hasil2;
    TaskVariableResponse taskVariableResponse;

    @GetMapping("/index")
    public String getDashboard(){
        return "index";
    }

    @GetMapping("/pengajuan")
    public String getstartTask(){
        return "pengajuan";
    }

    @GetMapping("/list")
    public String getListTask(Model model){
        getListReimburse();
        model.addAttribute("employeeList", hasil);
        return "list";
    }

    public void getListReimburse(){
        iApiService.getListTask()
                .enqueue(new Callback<List<TaskResponse>>() {
                    @Override
                    public void onResponse(Call<List<TaskResponse>> call, Response<List<TaskResponse>> response) {
                        hasil =  response.body();
                    }

                    @Override
                    public void onFailure(Call<List<TaskResponse>> call, Throwable throwable) {
                        responses = throwable.toString();
                    }
                });

        if(hasil == null)
            getListReimburse();
    }

    @GetMapping("/review")
    public String getReviewTask(Model model,
                                @RequestParam(value = "buttonList") String taskId){
        getTaskVariabel(taskId);
        model.addAttribute("variable", taskVariableResponse);
        model.addAttribute("taskId", taskId);
        return "review";
    }

    private void getTaskVariabel(String taskId){
        iApiService.getVariableTask("http://localhost:8081/engine-rest/task/"+ taskId + "/variables")
                .enqueue(new Callback<TaskVariableResponse>() {
                    @Override
                    public void onResponse(Call<TaskVariableResponse> call, Response<TaskVariableResponse> response) {
                        taskVariableResponse =  response.body();
                    }

                    @Override
                    public void onFailure(Call<TaskVariableResponse> call, Throwable throwable) {
                        responses = throwable.toString();
                    }
                });

        if(taskVariableResponse == null)
            getTaskVariabel(taskId);

    }

    @GetMapping("/penolakan")
    public String getPenolakan(Model model, @RequestParam(value = "buttonList") String taskId){
        model.addAttribute("taskId", taskId);
        return "penolakan";
    }

    @PostMapping("/pengajuan")
    public String postStartTask(@RequestParam(value = "nama", defaultValue = "") String nama,
                                 @RequestParam(value = "jumlah", defaultValue = "0") int jumlah,
                                 @RequestParam(value = "keterangan", defaultValue = "") String keterangan){

        if(!nama.equals("") && jumlah != 0 && !keterangan.equals("")){
            Variables variables = new Variables(new Name("String", nama),
                    new Jumlah("Integer", jumlah), new Keterangan("String", keterangan));
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
        }

        return "pengajuan";
    }

    @PostMapping("/list")
    public String postReviewTask(@RequestParam(value = "konfirmasi", defaultValue = "") String konfirmasi,
                                  @RequestParam(value = "taskId", defaultValue = "") String taskId,
                                 @RequestParam(value = "taskName", defaultValue = "") String taskName,
                                  Model model){

        if(!taskId.equals("") && !konfirmasi.equals("")){
            switch (taskName){
                case "review":
                    submitReview(konfirmasi, taskId);
                    break;
                case "penolakan":
                    submitPenolakan(konfirmasi, taskId);
                    break;
                default:
                    break;
            }
        }
        getListReimburse2();
        model.addAttribute("employeeList", hasil2);

        return "index";
    }

    private void submitReview(String konfirmasi, String taskId){
        boolean value;
        value = konfirmasi.equals("ya");
        VariablesReview variablesReview = new VariablesReview(new IsApproved("Boolean", value));
        ReviewBody reviewBody = new ReviewBody(variablesReview);
        iApiService.postReview("http://localhost:8081/engine-rest/task/"+ taskId + "/submit-form", reviewBody)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        //Success

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        responses = throwable.toString();
                    }
                });
    }

    private void submitPenolakan(String keterangan, String taskId){
        VariablesPenolakan variablesPenolakan = new VariablesPenolakan(new Keterangan("String", keterangan));
        PenolakanBody penolakanBody = new PenolakanBody(variablesPenolakan);
        iApiService.postPenolakan("http://localhost:8081/engine-rest/task/"+ taskId + "/submit-form", penolakanBody)
                .enqueue(new Callback<String>() {
                    @Override
                    public void onResponse(Call<String> call, Response<String> response) {
                        //Success

                    }

                    @Override
                    public void onFailure(Call<String> call, Throwable throwable) {
                        responses = throwable.toString();
                    }
                });
    }

    private void getListReimburse2(){
        iApiService.getListTask()
                .enqueue(new Callback<List<TaskResponse>>() {
                    @Override
                    public void onResponse(Call<List<TaskResponse>> call, Response<List<TaskResponse>> response) {
                        hasil2 =  response.body();
                    }

                    @Override
                    public void onFailure(Call<List<TaskResponse>> call, Throwable throwable) {
                        responses = throwable.toString();
                    }
                });

        if(hasil2 == null)
            getListReimburse2();
    }
}
