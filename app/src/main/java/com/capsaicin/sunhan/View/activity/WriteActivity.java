package com.capsaicin.sunhan.View.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.capsaicin.sunhan.Model.ProfileChangeResponse;
import com.capsaicin.sunhan.Model.Retrofit.RetrofitInstance;
import com.capsaicin.sunhan.Model.Retrofit.RetrofitServiceApi;
import com.capsaicin.sunhan.Model.UserResponse;
import com.capsaicin.sunhan.Model.WritepostItem;
import com.capsaicin.sunhan.Model.WritepostResponse;
import com.capsaicin.sunhan.R;
import com.capsaicin.sunhan.View.fragment.CommunityFragment;
import com.capsaicin.sunhan.View.fragment.MyPageFragment;
import com.google.gson.Gson;

import java.io.File;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class WriteActivity extends AppCompatActivity {

    Button finishBtn;
    Toolbar toolbar;
    EditText writeContent;
    CommunityFragment communityFragment;
    WritepostItem writepostItem;

    public static String id ;

    private RetrofitInstance tokenRetrofitInstance ;
    private RetrofitServiceApi retrofitServiceApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wirte_community);
        toolbar = findViewById(R.id.write_toolbar);

        writeContent = findViewById(R.id.write_content);

        communityFragment = new CommunityFragment();
        writepostItem = new WritepostItem();


        Intent intent = getIntent();
        id = "1234";

        setToolbar();
        finishBtn = findViewById(R.id.write_btn);
        finishBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String content = writeContent.getText().toString().trim();
                if(content.isEmpty()){
                    writeContent.setError("내용을 작성해주세요.");
                } else {
                    savePost(content);
                    finish();
                }
            }
        });
    }

    private void savePost(String content){
        Log.d("확인1", id);
        Call<WritepostResponse> call = RetrofitInstance.getRetrofitService().writePost("Bearer "+LoginActivity.userAccessToken, content);
        Log.d("확인2", id);
        if(LoginActivity.userAccessToken!=null){
            if(tokenRetrofitInstance!=null){
                call.enqueue(new Callback<WritepostResponse>() {
                    @Override
                    public void onResponse(Call<WritepostResponse> call, Response<WritepostResponse> response) {
                        if (response.isSuccessful()) {
                            Log.d("확인3", id);
                            WritepostResponse result = response.body();
                            Log.d("글 올리기 성공", new Gson().toJson(response.body()));
                        } else {
                            Log.d("REST FAILED MESSAGE", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<WritepostResponse> call, Throwable t) {
                        Log.d("REST ERROR!", t.getMessage());
                    }
                });
            }
        }
    }

//    void uploadPost(){
//        if(LoginActivity.userAccessToken!=null){
//            if(tokenRetrofitInstance!=null){
//                Call<WritepostResponse> call = RetrofitInstance.getRetrofitService().writePost("Bearer "+LoginActivity.userAccessToken, writepostItem);
//                call.enqueue(new Callback<WritepostResponse>() {
//                    @Override
//                    public void onResponse(Call<WritepostResponse> call, Response<WritepostResponse> response) {
//                        if (response.isSuccessful()) {
//                            WritepostResponse result = response.body();
//                            Log.d("글 올리기 성공", new Gson().toJson(response.body()));
//                        } else {
//                            Log.d("REST FAILED MESSAGE", response.message());
//                        }
//                    }
//
//                    @Override
//                    public void onFailure(Call<WritepostResponse> call, Throwable t) {
//                        Log.d("REST ERROR!", t.getMessage());
//                    }
//                });
//            }
//        }
//
//    }

    void setToolbar(){
        setSupportActionBar (toolbar); //액티비티의 앱바(App Bar)로 지정
        ActionBar actionBar = getSupportActionBar (); //앱바 제어를 위해 툴바 액세스
        actionBar.setDisplayShowTitleEnabled(false);
        actionBar.setTitle("메인툴바");
        actionBar.setDisplayHomeAsUpEnabled (true); // 앱바에 뒤로가기 버튼 만들기
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                //select back button
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
