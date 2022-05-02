package com.capsaicin.sunhan.View.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.capsaicin.sunhan.Model.CommentItem;
import com.capsaicin.sunhan.Model.CommunityDetailItem;
import com.capsaicin.sunhan.R;

import java.util.ArrayList;

public class CommunityDetailAdapter extends RecyclerView.Adapter<CommunityDetailAdapter.ViewHolder> {
    private Context context;
    ArrayList<CommunityDetailItem> CommunityDetailItemList;
    public static CommunityDetailCommentAdapter communityDetailCommentAdapter ;

    public CommunityDetailAdapter(Context context, ArrayList<CommunityDetailItem> arrayList){
        this.context = context ;
        this.CommunityDetailItemList= arrayList;
    }

    @NonNull
    @Override
    public CommunityDetailAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView = layoutInflater.inflate(R.layout.community_detail_item, parent, false);
        return new CommunityDetailAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull CommunityDetailAdapter.ViewHolder holder, int position) {
        CommunityDetailItem item =CommunityDetailItemList.get(position);

        holder.userProfile.setImageResource(CommunityDetailItemList.get(position).getUserProfile());
        holder.userId.setText(CommunityDetailItemList.get(position).getUserId());
        holder.content.setText(CommunityDetailItemList.get(position).getContent());
        holder.commentDate.setText(CommunityDetailItemList.get(position).getCommentDate());
        holder.commentTime.setText(CommunityDetailItemList.get(position).getCommentTime());

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setHasFixedSize(true);

        ArrayList<CommentItem> arrayList = new ArrayList<>();

        if (CommunityDetailItemList.get(position).getUserId().equals("선한2")) {
            arrayList.add(new CommentItem(R.drawable.profile,"익명","와우","03/17 14:12"));
            arrayList.add(new CommentItem(R.drawable.profile,"익명","와우","03/17 14:12"));
            arrayList.add(new CommentItem(R.drawable.profile,"익명","와우","03/17 14:12"));
            arrayList.add(new CommentItem(R.drawable.profile,"익명","와우","03/17 14:12"));
            arrayList.add(new CommentItem(R.drawable.profile,"익명","와우","03/17 14:12"));
            arrayList.add(new CommentItem(R.drawable.profile,"익명","와우","03/17 14:12"));
        }

        communityDetailCommentAdapter = new CommunityDetailCommentAdapter(holder.recyclerView.getContext(), arrayList); //이코드 쓰면 오류
        holder.recyclerView.setAdapter(communityDetailCommentAdapter);

    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        public ImageView userProfile;
        public TextView userId;
        public TextView content;
        public TextView commentDate;
        public TextView commentTime;
        public RecyclerView recyclerView; //

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            userProfile = itemView.findViewById(R.id.userProfile);
            userId = itemView.findViewById(R.id.userId);
            content = itemView.findViewById(R.id.content);
            commentDate = itemView.findViewById(R.id.commentDate);
            commentTime = itemView.findViewById(R.id.commentTime);
            recyclerView = itemView.findViewById(R.id.recylerView_community_comment); //

        }

    }

    @Override
    public int getItemCount() {
        return CommunityDetailItemList.size();
    }
    public void addItem(CommunityDetailItem item){ CommunityDetailItemList.add(item); }
    public void setarrayList(ArrayList<CommunityDetailItem> arrayList) { this.CommunityDetailItemList = arrayList; }
    public CommunityDetailItem getItem(int position) { return CommunityDetailItemList.get(position); }
    public void setItem(int position, CommunityDetailItem item) { CommunityDetailItemList.set(position, item); }
}