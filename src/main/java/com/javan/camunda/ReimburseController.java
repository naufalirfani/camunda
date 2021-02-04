package com.javan.camunda;

import com.javan.camunda.api.ApiClient;
import com.javan.camunda.api.IApiService;
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
import javax.swing.JOptionPane;

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
    private String getDashboard(){
        return "index";
    }

    @GetMapping("/pengajuan")
    private String getstartTask(){
        return "pengajuan";
    }

    @RequestMapping("/list")
    private String getListTask(Model model){
        getListReimburse();
        model.addAttribute("employeeList", hasil);
        return "list";
    }

    private void getListReimburse(){
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
    private String getReviewTask(Model model, @RequestParam(value = "buttonList") String taskId){
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

    @PostMapping("/pengajuan")
    private String postStartTask(@RequestParam(value = "nama", defaultValue = "") String nama,
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
    private String postReviewTask(@RequestParam(value = "konfirmasi") String konfirmasi,
                                  @RequestParam(value = "taskId") String taskId,
                                  Model model){

        if(!taskId.equals("")){
            boolean value;
            value = konfirmasi.equals("ya");
            VariablesReview variablesReview = new VariablesReview(new IsApproved("Boolean", value));
            ReviewBody reviewBody = new ReviewBody(variablesReview);
            iApiService.postReview("http://localhost:8081/engine-rest/task/"+ taskId + "/submit-form", reviewBody)
                    .enqueue(new Callback<String>() {
                        @Override
                        public void onResponse(Call<String> call, Response<String> response) {

                        }

                        @Override
                        public void onFailure(Call<String> call, Throwable throwable) {
                            responses = throwable.toString();
                        }
                    });
        }
        else{
            JOptionPane.showMessageDialog(null,
                    "ALERT MESSAGE",
                    "TITLE",
                    JOptionPane.WARNING_MESSAGE);
        }
        getListReimburse2();
        model.addAttribute("employeeList", hasil2);

        return "list";
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
