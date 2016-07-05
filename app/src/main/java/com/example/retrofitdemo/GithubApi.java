package com.example.retrofitdemo;

import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * 编写ａｐｉ接口
 * https://api.github.com/repos/squre/retrofit/contributors
 */
public interface GithubApi {
    // BASE_URL  https://api.github.com/

    //GET 方式
    @GET("repos/squre/retrofit/contributors")
    Call<ResponseBody>getRetrofitcontributors();

    @GET("repos/squre/okhttp/contributors")
   Call <ResponseBody> getOkhttpcontributors();

    @GET("repos/{owner}/{repo}/contributors")//{}里面可以穿参数
   // Call<ResponseBody>getContributors(@Path("owner")String owner,@Path("repo")String repo);
    //添加gson 转换器响应体 自动转换为 实体
     Call<List<Country>>getContributors(@Path("owner")String owner, @Path("repo")String repo);

}
