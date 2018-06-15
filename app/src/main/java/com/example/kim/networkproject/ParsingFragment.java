package com.example.kim.networkproject;

import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParsingFragment extends Fragment {
    RecyclerView recyclerView;
    EditText editSearch;
    Button searchBtn;
    BookItems bookItems;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        recyclerView = (RecyclerView)inflater.inflate(R.layout.activity_parsing_fragment,container,false);
        //recyclerView = container.findViewById(R.id.recyclerView);


        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_parsing_fragment,container,false);
        editSearch = (EditText)linearLayout.findViewById(R.id.editSearch);
        searchBtn = (Button)linearLayout.findViewById(R.id.searchBtn);

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String key = editSearch.getText().toString();

               getBooklist(key);
            }
        });

        recyclerView = (RecyclerView)linearLayout.findViewById(R.id.recyclerView);



        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        recyclerView.addItemDecoration(new MyItemDecoration());

        return linearLayout;
    }

    private void getBooklist(String key){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        NaverApiService apiService = retrofit.create(NaverApiService.class);
        Call<BookItems> call = apiService.getSearchItems(key,"20","1");
        Log.d("callprev","pr");
        call.enqueue(new Callback<BookItems>() {
            @Override
            public void onResponse(Call<BookItems> call, Response<BookItems> response) {
                Log.d("callin","pr");
                if(response.isSuccessful()){
                    //데이터를 받아서 recyclerAdapter에 설정
                    bookItems = response.body();
                    if(!bookItems.getItems().isEmpty()){

                        recyclerView.setAdapter(new MyAdapter(bookItems));

                    }
                }
            }

            @Override
            public void onFailure(Call<BookItems> call, Throwable t) {
                call.cancel();
                t.printStackTrace();
            }
        });

    }


    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


        private BookItems bookItems;



        public MyAdapter(BookItems bookItems){
            this.bookItems = bookItems;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.main_list_item,parent,false
            );
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {
            String text = bookItems.getItems().get(position).getTitle();
            String pubdate = bookItems.getItems().get(position).getPubdate();
            holder.title.setText(text);
            holder.pubdate.setText(pubdate);
        }

        @Override
        public int getItemCount() {
            return bookItems.getItems().size();
        }
    }//end RecyclerAdapter

    private class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title;
        public TextView pubdate;

        public MyViewHolder(View itemView) {
            super(itemView);
            pubdate = (TextView)itemView.findViewById(R.id.txt_pubdate);
            title = (TextView)itemView.findViewById(R.id.txt_title);
        }
    }//end viewHolder

    private class MyItemDecoration extends RecyclerView.ItemDecoration {

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            outRect.set(20,20,20,20); // 바깥여백 지정
            view.setBackgroundColor(0xFFECE9E9);
            ViewCompat.setElevation(view,10.0f); //떠있는 효과

        }
    }
}
