package com.example.kim.networkproject;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class DetailView extends AppCompatActivity {
    //변수 선언
    TextView title;
    TextView pubdate;
    TextView content;

    Button linkBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);

        content = findViewById(R.id.content);
        pubdate = findViewById(R.id.pubdate);
        title = findViewById(R.id.detailtitle);

        linkBtn = findViewById(R.id.linkBtn);


        final Bundle bundle = getIntent().getExtras();
        //번들로 받아오고

        //url 조립후
        title.setText(Html.fromHtml(bundle.getString("title")));
        pubdate.setText(bundle.getString("pubDate"));
        content.setText(Html.fromHtml(bundle.getString("content")));
        linkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(bundle.getString("link")));
                startActivity(intent);
            }
        });
        //내용물들을 뿌려준다.




    }
}
