package com.example.kim.networkproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.TextView;

public class DetailView extends AppCompatActivity {
    TextView title;
    TextView pubdate;
    TextView content;
    TextView link;
    String linkurl;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        content = findViewById(R.id.content);
        pubdate = findViewById(R.id.pubdate);
        title = findViewById(R.id.detailtitle);
        link = findViewById(R.id.link);


        Bundle bundle = getIntent().getExtras();
        linkurl = bundle.getString("link");
        linkurl = "<a href='"+linkurl+"'>"+linkurl+"</a>";
        title.setText(Html.fromHtml(bundle.getString("title")));
        pubdate.setText(bundle.getString("pubDate"));
        content.setText(Html.fromHtml(bundle.getString("content")));
        link.setText(Html.fromHtml(linkurl));




    }
}
