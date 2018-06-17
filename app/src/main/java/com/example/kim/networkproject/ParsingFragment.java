package com.example.kim.networkproject;

import android.content.Intent;
import android.graphics.Rect;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ParsingFragment extends Fragment {
    RecyclerView recyclerView;
    EditText editSearch;
    Button searchBtn;
    NewsItems newsItems;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        recyclerView = (RecyclerView)inflater.inflate(R.layout.activity_parsing_fragment,container,false);
        //recyclerView = container.findViewById(R.id.recyclerView);


        LinearLayout linearLayout = (LinearLayout) inflater.inflate(R.layout.activity_parsing_fragment,container,false);
        //LinearLayout 변수
        editSearch = (EditText)linearLayout.findViewById(R.id.editSearch);
        searchBtn = (Button)linearLayout.findViewById(R.id.searchBtn);

        //검색 버튼을 누르면
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               String key = editSearch.getText().toString();
               //key는 검색할 때 쓰이는 key이다.

               getBooklist(key);
            }
        });

        recyclerView = (RecyclerView)linearLayout.findViewById(R.id.recyclerView);
        //recyclerView 를 linearLayout에서 찾아온다

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        //setLayoutManager

        recyclerView.addItemDecoration(new MyItemDecoration());
        //색깔 떠있는 여부 등을 지정한다

        return linearLayout;
        //리턴
    }

    private void getBooklist(String key){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://openapi.naver.com/")  //요청할 api 의 baseUrl
                .addConverterFactory(GsonConverterFactory.create()) //Gson은 json객체를 자바 객체로 변환 해준다.
                .build();

        NaverApiService apiService = retrofit.create(NaverApiService.class);
        Call<NewsItems> call = apiService.getSearchItems(key,"20","1");
        //key는 검색할때 쓰이는 key 이고, display는 화면에 보여질 개수 , start는 검색 시작 위치로 최대 1000까지 지정가능

        call.enqueue(new Callback<NewsItems>() {
            //호출
            @Override
            public void onResponse(Call<NewsItems> call, Response<NewsItems> response) {
                //응답
                if(response.isSuccessful()){
                    //정상적으로 응답이 오면
                    newsItems = response.body();
                    //데이터를 받아서 recyclerAdapter에 설정 이때 api의 반환 변수명과 vo의 변수명이 같아야 한다.
                    if(!newsItems.getItems().isEmpty()){
                        //비어있는지 체크를 한후 recyclerView에다가 Adpter를 단다.
                        recyclerView.setAdapter(new MyAdapter(newsItems));

                    }
                }
            }

            @Override
            public void onFailure(Call<NewsItems> call, Throwable t) {
                //실패
                call.cancel();
                t.printStackTrace();
            }
        });

    }


    private class MyAdapter extends RecyclerView.Adapter<MyViewHolder> {


        private NewsItems newsItems;



        public MyAdapter(NewsItems newsItems){
            this.newsItems = newsItems;
        }

        @Override
        public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.main_list_item,parent,false
            );
            //View 객체를 생성
            //main_list_item은 cardView
            return new MyViewHolder(view);
            //MyViewHolder는 Holder클래스 역할을 한다
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, final int position) {
            //Binding
            final String title = newsItems.getItems().get(position).getTitle();
            final String pubDate = newsItems.getItems().get(position).getPubDate();
            final String content = newsItems.getItems().get(position).getDescription();
            final String link = newsItems.getItems().get(position).getLink();
            holder.title.setText(Html.fromHtml(title));
            holder.pubdate.setText(Html.fromHtml(pubDate));



            //CardView의 Linear 자체가 클릭되었을때
            holder.linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),DetailView.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title",title);
                    bundle.putString("pubDate",pubDate);
                    bundle.putString("content",content);
                    bundle.putString("link",link);

                    intent.putExtras(bundle);
                    startActivity(intent);
                    Log.d("pubDate",pubDate);
                    Log.d("linear click", newsItems.getItems().get(position).getDescription());
                }
            });

            //제목이 클릭되었을때
            holder.title.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(),DetailView.class);
                    Bundle bundle = new Bundle();
                    bundle.putString("title",title);
                    bundle.putString("pubDate",pubDate);
                    bundle.putString("content",content);
                    bundle.putString("link",link);

                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        }

        @Override
        public int getItemCount() {
            return newsItems.getItems().size();
        }
        // 아이템 개수를 리턴해준다
    }//end RecyclerAdapter

    private class MyViewHolder extends RecyclerView.ViewHolder{
        //Holder 클래스
        public TextView title;
        public TextView pubdate;
        public LinearLayout linearLayout;

        public MyViewHolder(View itemView) {
            super(itemView);
            linearLayout = (LinearLayout)itemView.findViewById(R.id.linear);
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
