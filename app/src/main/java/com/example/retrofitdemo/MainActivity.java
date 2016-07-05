package com.example.retrofitdemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;

import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayAdapter<Country> adapter;

    private Retrofit retrofit;
    private GithubApi githubApi;

    private Call<List<Country>> call;

    //初始化
    private void initRetrofit() {
        retrofit = new Retrofit
                .Builder()
                .baseUrl("https://api.github.com")
                .addConverterFactory(GsonConverterFactory.create())//转换器（Gson)
                .build();

        //创建接口
        githubApi = retrofit.create(GithubApi.class);

        call = githubApi.getContributors("square", "retrofit");//参数传过来得到 Call 模型
        call.enqueue(callback);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //列表视图
        listView = (ListView) findViewById(R.id.list_item);
        adapter = new ArrayAdapter<Country>(this, android.R.layout.simple_list_item_1);
        listView.setAdapter(adapter);

        initRetrofit();


    }


    //    private Callback<ResponseBody> callback=new Callback<ResponseBody>() {
//        @Override
//        public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
//
//            String body=response.body().string();
//        }
//
//        @Override
//        public void onFailure(Call<ResponseBody> call, Throwable t) {
//
//            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//        }
//    };
    private Callback<List<Country>> callback = new Callback<List<Country>>() {
        @Override
        public void onResponse(Call<List<Country>> call, Response<List<Country>> response) {

            List<Country> country = response.body();
            adapter.addAll(country);
            adapter.notifyDataSetChanged();
        }

        @Override
        public void onFailure(Call<List<Country>> call, Throwable t) {
            Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
        }
    };
}
