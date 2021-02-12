package com.javan.camunda;

import ch.qos.logback.core.joran.util.beans.BeanDescriptionFactory;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.javan.camunda.api.ApiClient;
import com.javan.camunda.api.IApiService;
import com.javan.camunda.data.*;
import com.javan.camunda.data.startbody.*;
import com.javan.camunda.data.taskVariable.TaskVariableResponse;
import eu.dattri.jsonbodyhandler.JsonBodyHandler;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Scanner;

@Controller
public class ReimburseController {
    ApiClient apiClient = new ApiClient();
    IApiService iApiService = apiClient.getRetrofit().create(IApiService.class);
    String responses;
    List<TaskResponse> hasil;
    List<TaskResponse> hasil2;
    TaskVariableResponse taskVariableResponse;
    CloseableHttpClient httpclient = HttpClients.createDefault();
    HttpGet httpget = new HttpGet("http://localhost:8081/engine-rest/task");

    @GetMapping("/index")
    public String getDashboard(){
        return "index";
    }

    @GetMapping("/pengajuan")
    public String getstartTask(){
        return "pengajuan";
    }

    @GetMapping("/list")
    public String getListTask(Model model) throws IOException {
        getListReimburse();
        model.addAttribute("employeeList", hasil);
        return "list";
    }

    public void getListReimburse() throws IOException {
        
        //Executing the Get request
        HttpResponse httpresponse = httpclient.execute(httpget);

        Scanner sc = new Scanner(httpresponse.getEntity().getContent());

        ObjectMapper mapper = new ObjectMapper();
        List<TaskResponse> taskResponseList = mapper.readValue(sc.nextLine(), new TypeReference<List<TaskResponse>>(){});
        hasil = taskResponseList;
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

    @GetMapping("/bukti")
    public String getBukti(Model model, @RequestParam(value = "buttonList") String taskId){
        model.addAttribute("taskId", taskId);
        return "bukti";
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
                                  Model model) throws IOException {

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

    private void getListReimburse2() throws IOException {
        //Executing the Get request
        HttpResponse httpresponse = httpclient.execute(httpget);

        Scanner sc = new Scanner(httpresponse.getEntity().getContent());

        ObjectMapper mapper = new ObjectMapper();
        List<TaskResponse> taskResponseList = mapper.readValue(sc.nextLine(), new TypeReference<>() {
        });
        hasil2 = taskResponseList;
    }

    @PostMapping("/imageUpload")
    public String image(@RequestParam("image") MultipartFile imagefile,
                        @RequestParam(value = "taskId", defaultValue = "") String taskId){

        String path = "D:\\Naufal\\Dokumen\\KP\\Javan\\camunda\\Bukti\\";
        var fileName = imagefile.getOriginalFilename();
        try {
            var is = imagefile.getInputStream();

            Files.copy(is, Paths.get(path + fileName),
                    StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            System.out.println(e);
        }
        String imageUrl = path + fileName;
        submitBukti(imageUrl, taskId);
        return "index";
    }

    private void submitBukti(String imageUrl, String taskId){
        VariablesBukti variablesBukti = new VariablesBukti(new ImageUrl("String", imageUrl));
        BuktiBody buktiBody = new BuktiBody(variablesBukti);
        iApiService.uploadBukti("http://localhost:8081/engine-rest/task/"+ taskId + "/submit-form", buktiBody)
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
}
