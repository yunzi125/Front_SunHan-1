package com.capsaicin.sunhan.View.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.capsaicin.sunhan.Model.StoreItem;
import com.capsaicin.sunhan.R;
import com.capsaicin.sunhan.View.adapter.SunhanStoreAdapter;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;


public class SunhanstMainFragment extends Fragment {
    public static SunhanStoreAdapter storeAdapter;
    ArrayList<StoreItem> storeList=new ArrayList<StoreItem>();
    RecyclerView sunhanStoreRecyclerView;

    SunhanstCardFragment sunhanstCardFragment;
    SunhanstSunhanFragment sunhanstSunhanFragment;
    ImageView addImage;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@Nullable LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sunhanst_main, null);

        addImage = view.findViewById(R.id.img_sunhan_donate);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://xn--o39akkz01az4ip7f4xzwoa.com/"));
                startActivity(intent);
            }
        });

        sunhanstCardFragment = new SunhanstCardFragment();
        sunhanstSunhanFragment = new SunhanstSunhanFragment();

        getChildFragmentManager().beginTransaction().replace(R.id.tabs_store_container, sunhanstCardFragment).commit();
        TabLayout tabs1 = view.findViewById(R.id.store_tapLayout);
        tabs1.addTab(tabs1.newTab().setText("아동급식가맹점"));
        tabs1.addTab(tabs1.newTab().setText("선한영향력가게"));

        //getChildFragmentManager().beginTransaction().replace(R.id.tabs_food_container, sunhanstSunhanFragment).commit();
        TabLayout tabs2 = view.findViewById(R.id.food_tapLayout);
        tabs2.addTab(tabs2.newTab().setText("한식"));
        tabs2.addTab(tabs2.newTab().setText("중식"));
        tabs2.addTab(tabs2.newTab().setText("일식"));
        tabs2.addTab(tabs2.newTab().setText("양식"));
        tabs2.addTab(tabs2.newTab().setText("디저트"));


        tabs1.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab1) {
                int position = tab1.getPosition();

                Fragment selected = null;
                if(position == 0){
                    selected = sunhanstCardFragment;
                    LinearLayout tabStrip = ((LinearLayout)tabs2.getChildAt(0));
                    tabStrip.setEnabled(false);
                    for(int i = 0; i < tabStrip.getChildCount(); i++) {
                        tabStrip.getChildAt(i).setClickable(false);
                    }
                }
                else {
                    selected = sunhanstSunhanFragment;
                    LinearLayout tabStrip = ((LinearLayout)tabs2.getChildAt(0));
                    tabStrip.setEnabled(true);
                    for(int i = 0; i < tabStrip.getChildCount(); i++) {
                        tabStrip.getChildAt(i).setClickable(true);
                    }
                }
                getChildFragmentManager().beginTransaction().replace(R.id.tabs_store_container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab1) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab1) {

            }
        });


        tabs2.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab2) {
                int position = tab2.getPosition();

                Fragment selected = null;
                if(position == 0){
                    selected = sunhanstCardFragment;
                }
                else if(position==1){
                    selected = sunhanstSunhanFragment;
                }
                else if(position==2){
                    selected = sunhanstCardFragment;
                }
                else if(position==3){
                    selected = sunhanstSunhanFragment;
                }
                else {
                    selected = sunhanstCardFragment;
                }
                getChildFragmentManager().beginTransaction().replace(R.id.tabs_store_container, selected).commit();
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab2) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab2) {

            }
        });

        return view;
    }
    /*
    void setRecyclerview(View view){
        sunhanStoreRecyclerView = view.findViewById(R.id.recyclerview_store);
        sunhanStoreRecyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager recyclerViewManager = new LinearLayoutManager(getActivity());
        sunhanStoreRecyclerView.setLayoutManager(recyclerViewManager);
        sunhanStoreRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sunhanStoreRecyclerView.setAdapter(storeAdapter);

    }


    void setList(){
        storeAdapter = new SunhanStoreAdapter(getContext(), storeList);
        setData();
    }

    void setData(){

        storeAdapter.addItem(new StoreItem("돈애랑장터순대국감자탕", "경기 수원시 영통구 동문3길 10",
                "0314299444","10:00-21:00"));
        storeAdapter.addItem(new StoreItem("낙원갈비집", "경기 수원시 영통구 1243 1층",
                "0314291234","11:30-22:00"));
        storeAdapter.addItem(new StoreItem("서브웨이", "경기 수원시 영통구 광교산로 22",
                "0314295687","7:00-22:00"));
        storeAdapter.addItem(new StoreItem("맘스터치", "경기 수원시 영통구 광교산로 154",
                "0314293333","9:00-21:30"));
    }

 */

}
