package com.capsaicin.sunhan.View.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.bumptech.glide.Glide;
import com.capsaicin.sunhan.Model.CommentItem;
import com.capsaicin.sunhan.Model.CommentResponse;
import com.capsaicin.sunhan.Model.CommunityDetailItem;
import com.capsaicin.sunhan.Model.CommunityDetailResponse;
import com.capsaicin.sunhan.Model.CommunityResponse;
import com.capsaicin.sunhan.Model.CommunityWritingComment;
import com.capsaicin.sunhan.Model.CommunityWritingPost;
import com.capsaicin.sunhan.Model.CommunityWritingResponse;
import com.capsaicin.sunhan.Model.PostDeleteResponse;
import com.capsaicin.sunhan.Model.ResultResponse;
import com.capsaicin.sunhan.Model.Retrofit.RetrofitInstance;
import com.capsaicin.sunhan.Model.Retrofit.RetrofitServiceApi;
import com.capsaicin.sunhan.Model.UserResponse;
import com.capsaicin.sunhan.R;
import com.capsaicin.sunhan.View.adapter.CommunityAdapter;
import com.capsaicin.sunhan.View.adapter.CommunityDetailAdapter;
import com.capsaicin.sunhan.View.adapter.LetterAdapter;
import com.capsaicin.sunhan.View.fragment.CommunityFragment;
import com.capsaicin.sunhan.View.fragment.MyPageFragment;
import com.capsaicin.sunhan.View.interfaceListener.OnClickCommentListener;
import com.capsaicin.sunhan.View.interfaceListener.OnClickCommunityListener;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CommunityDetailActivity extends AppCompatActivity {
    public static CommunityDetailAdapter communityDetailAdapter ;
    RecyclerView commuDetailRecycleView;
    SwipeRefreshLayout swipeRefreshLayout;

    Toolbar toolbar;
    ImageView pop1;
    ImageView pop2;
    ImageView userProfile;
    TextView userId;
    TextView uploadTime;
    TextView content;
    TextView commentNum;

    ImageView addImage;

    //댓글 글쓰기
    EditText commentContent;
    ImageView send;
    CommunityWritingComment communityWritingComment;

    Dialog dilaog01;

    CommunityFragment communityFragment;

    public static String id ;
    public static String user_id ;

    private RetrofitInstance tokenRetrofitInstance ;

    int page;
    ProgressBar progressBar;

    private RetrofitInstance commuDetailRetrofitInstance ;
    private RetrofitServiceApi retrofitServiceApi;//

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);
        toolbar = findViewById(R.id.commu_detail_toolbar);
        setToolbar();

        tokenRetrofitInstance = RetrofitInstance.getRetrofitInstance();
        commuDetailRetrofitInstance = RetrofitInstance.getRetrofitInstance();
        userProfile = findViewById(R.id.detail_userProfile);
        userId = findViewById(R.id.detail_userId);
        uploadTime = findViewById(R.id.detail_uploadTime);
        content = findViewById(R.id.detail_content);
        commentNum = findViewById(R.id.detail_commentNum);

        commentContent = findViewById(R.id.comment_content);
        communityWritingComment = new CommunityWritingComment();
        progressBar = findViewById(R.id.progress_bar);

//        swipeRefreshLayout = findViewById(R.id.swip_comment);

        Intent intent = getIntent();
        id = intent.getStringExtra("_id");

        dilaog01 = new Dialog(CommunityDetailActivity.this);       // Dialog 초기화
        dilaog01.requestWindowFeature(Window.FEATURE_NO_TITLE); // 타이틀 제거
        dilaog01.setContentView(R.layout.dialog);



        page = 1;


//        setList();

        commuDetailRecycleView = findViewById(R.id.recyleView_community_comment_parent);
        RecyclerView.LayoutManager recyclerViewManager = new LinearLayoutManager(this);
        commuDetailRecycleView.setLayoutManager(recyclerViewManager);
        commuDetailRecycleView.setHasFixedSize(true);
        commuDetailRecycleView.setItemAnimator(new DefaultItemAnimator());
//        commuDetailRecycleView.setAdapter(communityDetailAdapter);

        getData();
        initComment(0);

//        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                initComment(0);
//                swipeRefreshLayout.setRefreshing(false);
//            }
//        });

        pop1 = findViewById(R.id.popupMore);
        pop1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog();
            }
        });

//        pop2 = commuDetailRecycleView.findViewById(R.id.comment_More);
//        pop2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                commentDialog();
//            }
//        });

        addImage = findViewById(R.id.sunhan_add);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://xn--o39akkz01az4ip7f4xzwoa.com/"));
                startActivity(intent);
            }

        });

        send = findViewById(R.id.send_comment);
        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                communityWritingComment.setContent(commentContent.getText().toString());
                communityWritingComment.setPostId(id);
                if(communityWritingComment.getContent().isEmpty()){
                    commentContent.setError("내용을 입력해주세요");
                } else {
                    saveComment(communityWritingComment);
                }
            }
        });

        commuDetailRecycleView.addOnScrollListener(new RecyclerView.OnScrollListener() {

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                int lastVisibleItemPosition = ((LinearLayoutManager) commuDetailRecycleView.getLayoutManager()).
                        findLastCompletelyVisibleItemPosition();
                int itemTotalCount = commuDetailRecycleView.getAdapter().getItemCount() - 1;
                if(lastVisibleItemPosition == itemTotalCount) {
//                    progressBar.setVisibility(View.VISIBLE);
                    getComment(page);
                    page++;
                }
            }
        });

    }

    private void initComment(int page)
    {
        if(commuDetailRetrofitInstance!=null){
            Log.d("댓글", "토큰인스턴스이후 콜백 전");
            Call<CommentResponse> call = RetrofitInstance.getRetrofitService().getCommunityCommentList("Bearer "+LoginActivity.userAccessToken,id,page); //error

            call.enqueue(new Callback<CommentResponse>() {
                @Override
                public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                    if (response.isSuccessful()) {
//                        progressBar.setVisibility(View.GONE);
                        CommentResponse result = response.body();
                        communityDetailAdapter = new CommunityDetailAdapter(getApplicationContext(),result.getData());
                        commuDetailRecycleView.setAdapter(communityDetailAdapter);

                        communityDetailAdapter.setOnClickCommentListner(new OnClickCommentListener() {
                            @Override
                            public void onItemClick(CommunityDetailAdapter.ViewHolder holder, View view, int position) {
                                if (position != RecyclerView.NO_POSITION) {
                                    holder.itemView.findViewById(R.id.comment_More).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            commentDialog();
                                        }
                                    });
                                }
                            }
                        });

                        Log.d("성공", new Gson().toJson(response.body()));
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Log.d("REST FAILED MESSAGE", response.message());
                    }
                }

                @Override
                public void onFailure(Call<CommentResponse> call, Throwable t) {
//                    progressBar.setVisibility(View.GONE);
                    Log.d("REST ERROR!", t.getMessage());
                }
            });
        } else {
            Log.d("인스턴스 널임","null");
        }
    }

    private void getComment(int page)
    {
//        if(LoginActivity.userAccessToken!=null){
        if(commuDetailRetrofitInstance!=null){
            Log.d("커뮤니티 댓글", "토큰인스턴스이후 콜백 전");
            Call<CommentResponse> call = RetrofitInstance.getRetrofitService().getCommunityCommentList("Bearer "+LoginActivity.userAccessToken,id,page);
            call.enqueue(new Callback<CommentResponse>() {
                @Override
                public void onResponse(Call<CommentResponse> call, Response<CommentResponse> response) {
                    if (response.isSuccessful()) {
//                        progressBar.setVisibility(View.GONE);
                        CommentResponse result = response.body();
                        communityDetailAdapter.addList(result.getData());

                        communityDetailAdapter.setOnClickCommentListner(new OnClickCommentListener() {
                            @Override
                            public void onItemClick(CommunityDetailAdapter.ViewHolder holder, View view, int position) {
                                int num = position;
                                if (position != RecyclerView.NO_POSITION) {
                                    holder.itemView.findViewById(R.id.comment_More).setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            commentDialog();
                                        }
                                    });
                                }
                            }
                        });
                        Log.d("성공", new Gson().toJson(response.body()));
                    } else {
                        progressBar.setVisibility(View.GONE);
                        Log.d("커뮤니티 댓글 실패", response.message());
                    }
                }

                @Override
                public void onFailure(Call<CommentResponse> call, Throwable t) {
                    progressBar.setVisibility(View.GONE);
                    Log.d("REST ERROR!", t.getMessage());
                }
            });
        }
//        }
    }

    private void getData(){ //커뮤니티 상세페이지 데이터 가져옴
        if(tokenRetrofitInstance!=null){
            Log.d("상세정보 id", id);
            Call<CommunityDetailResponse> call = RetrofitInstance.getRetrofitService().getCommunityDetail(id);
            call.enqueue(new Callback<CommunityDetailResponse>() {
                @Override
                public void onResponse(Call<CommunityDetailResponse> call, Response<CommunityDetailResponse> response) {
                    if (response.isSuccessful()) {
                        CommunityDetailResponse result = response.body();

//                        CommunityFragment.commuId.setText(result.getCommunityDetailItem().getCommuId());
                        Glide.with(getApplicationContext()).load("https://sunhan.s3.ap-northeast-2.amazonaws.com/raw/"+result.getCommunityItem().getWriterItem().getAvatarUrl()).error(R.drawable.profile).circleCrop().into(userProfile);
                        userId.setText(result.getCommunityItem().getWriterItem().getNickname());
                        uploadTime.setText(result.getCommunityItem().getCommuIsCreateAt());
                        content.setText(result.getCommunityItem().getCommuContent());
                        commentNum.setText(result.getCommunityItem().getCommuIsCommentCount());

                        user_id = result.getCommunityItem().getWriterItem().get_id(); // 글쓴이 id정보 저장
                        Log.d("글쓴사람 id",user_id);
                        Log.d("유저 id",MyPageFragment.user_id);
                        Log.d("성공", new Gson().toJson(response.body()));
                    } else {

                        Log.d("커뮤니티 상세정보실패", response.message());
                    }
                }

                @Override
                public void onFailure(Call<CommunityDetailResponse> call, Throwable t) {
                    Log.d("REST ERROR!", t.getMessage());
                }
            });


        }
    }

    private void saveComment(CommunityWritingComment content){
        if(LoginActivity.userAccessToken!=null){
            if(tokenRetrofitInstance!=null){
                Call<CommunityWritingResponse> call = RetrofitInstance.getRetrofitService().writeComment("Bearer "+LoginActivity.userAccessToken, content);
                call.enqueue(new Callback<CommunityWritingResponse>() {
                    @Override
                    public void onResponse(Call<CommunityWritingResponse> call, Response<CommunityWritingResponse> response) {
                        if (response.isSuccessful()) {
                            CommunityWritingResponse result = response.body();
                            Log.d("글 올리기 성공", new Gson().toJson(response.body()));
                        } else {
                            Log.d("REST FAILED MESSAGE", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<CommunityWritingResponse> call, Throwable t) {
                        Log.d("REST ERROR!", t.getMessage());
                    }
                });
            }
        }
    }

    public void dialog() { //post 다이얼로그
        dilaog01.show();

        Button modify_btn = dilaog01.findViewById(R.id.modify_btn);
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(LoginActivity.userAccessToken!=null && user_id.equals(MyPageFragment.user_id)){
                    Intent intent = new Intent(getApplicationContext(), EditPostActivity.class);
                    intent.putExtra("_id",id);
                    intent.putExtra("content",content.getText());
                    startActivity(intent);
                }else{
                    showDialog();
                }

                dilaog01.dismiss();
            }
        });

        Button delete_btn = dilaog01.findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                int num = position;
                if(LoginActivity.userAccessToken!=null && user_id.equals(MyPageFragment.user_id)){
                    if(tokenRetrofitInstance!=null){
                        Call<PostDeleteResponse> call = RetrofitInstance.getRetrofitService().deletePost("Bearer "+LoginActivity.userAccessToken, id);
                        call.enqueue(new Callback<PostDeleteResponse>() {
                            @Override
                            public void onResponse(Call<PostDeleteResponse> call, Response<PostDeleteResponse> response) {
                                if (response.isSuccessful()) {
                                    PostDeleteResponse result = response.body();
//                                    communityDetailAdapter.removeItem(position);
                                    AlertDialog.Builder dlg = new AlertDialog.Builder(CommunityDetailActivity.this);
                                    dlg.setMessage("글이 삭제되었습니다. "); // 메시지
                                    dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                                        public void onClick(DialogInterface dialog, int which) {
                                            //토스트 메시지
                                            finish();
                                        }
                                    });
                                    dlg.show();
                                    Log.d("삭제성공", result.getMessage());
                                } else {
                                    Log.d("REST FAILED MESSAGE", response.message());
                                }
                            }

                            @Override
                            public void onFailure(Call<PostDeleteResponse> call, Throwable t) {
                                Log.d("REST ERROR!", t.getMessage());
                            }
                        });
                    }
                } else {
                    AlertDialog.Builder dlg = new AlertDialog.Builder(CommunityDetailActivity.this);
                    dlg.setMessage("본인이 작성한 글이 아닙니다. "); // 메시지
                    dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
                        public void onClick(DialogInterface dialog, int which) {
                            //토스트 메시지
                        }
                    });
                    dlg.show();
                }
                dilaog01.dismiss();
            }
        });

        Button report_btn = dilaog01.findViewById(R.id.report_btn);
        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResultResponse> call = RetrofitInstance.getRetrofitService().blockPost("Bearer "+LoginActivity.userAccessToken, id);
                call.enqueue(new Callback<ResultResponse>() {
                    @Override
                    public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                        if (response.isSuccessful()) {
                            ResultResponse result = response.body();
                            Toast toast = Toast.makeText(getApplicationContext(), "글 신고되었습니다",Toast.LENGTH_SHORT);
                            toast.show();
                            Log.d("신고성공", new Gson().toJson(response.body()));
                        } else {

                            Log.d("ERROR", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultResponse> call, Throwable t) {
                        Log.d("REST ERROR!", t.getMessage());
                    }
                });
                dilaog01.dismiss();
            }
        });

        Button block_btn = dilaog01.findViewById(R.id.block_btn);
        block_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResultResponse> call = RetrofitInstance.getRetrofitService().blockCommuUser(id);
                call.enqueue(new Callback<ResultResponse>() {
                    @Override
                    public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                        if (response.isSuccessful()) {
                            ResultResponse result = response.body();
                            Toast toast = Toast.makeText(getApplicationContext(), "차단되었습니다",Toast.LENGTH_SHORT);
                            toast.show();
                            Log.d("신고성공", new Gson().toJson(response.body()));
                        } else {

                            Log.d("ERROR", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultResponse> call, Throwable t) {
                        Log.d("REST ERROR!", t.getMessage());
                    }
                });
                dilaog01.dismiss();
            }
        });

        Button cancle_btn = dilaog01.findViewById(R.id.cancle_btn);
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilaog01.dismiss();
            }
        });
    }

    public void commentDialog() { //commment 다이얼로그
        dilaog01.show();

        Button modify_btn = dilaog01.findViewById(R.id.modify_btn);
        modify_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dilaog01.dismiss();
            }
        });

        Button delete_btn = dilaog01.findViewById(R.id.delete_btn);
        delete_btn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                dilaog01.dismiss();
            }
        });

        Button report_btn = dilaog01.findViewById(R.id.report_btn);
        report_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Call<ResultResponse> call = RetrofitInstance.getRetrofitService().blockComment("Bearer "+LoginActivity.userAccessToken, id);
                call.enqueue(new Callback<ResultResponse>() {
                    @Override
                    public void onResponse(Call<ResultResponse> call, Response<ResultResponse> response) {
                        if (response.isSuccessful()) {
                            ResultResponse result = response.body();
                            Toast toast = Toast.makeText(getApplicationContext(), "댓글 신고되었습니다",Toast.LENGTH_SHORT);
                            toast.show();
                            Log.d("신고성공", new Gson().toJson(response.body()));
                        } else {

                            Log.d("ERROR", response.message());
                        }
                    }

                    @Override
                    public void onFailure(Call<ResultResponse> call, Throwable t) {
                        Log.d("REST ERROR!", t.getMessage());
                    }
                });
                dilaog01.dismiss();
            }
        });

        Button block_btn = dilaog01.findViewById(R.id.block_btn);
        block_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dilaog01.dismiss();
            }
        });

        Button cancle_btn = dilaog01.findViewById(R.id.cancle_btn);
        cancle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dilaog01.dismiss();
            }
        });
    }

    void showDialog() {
        AlertDialog.Builder dlg = new AlertDialog.Builder(CommunityDetailActivity.this);
        dlg.setMessage("본인 글이 아닙니다."); // 메시지
        dlg.setPositiveButton("확인",new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int which) {
                //토스트 메시지
                finish();
            }
        });
        dlg.show();
    }

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
