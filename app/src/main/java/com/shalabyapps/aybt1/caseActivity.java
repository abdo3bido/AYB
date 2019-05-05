package com.shalabyapps.aybt1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class caseActivity extends AppCompatActivity {

    String title;
    String type;
    String url;
    String desc;
    TextView titre;
    TextView description;
    ImageView img;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_case);
        Bundle extras = getIntent().getExtras();
        title = extras.getString("title");
        type = extras.getString("type");
        url = extras.getString("url");
        desc = extras.getString("desc");
        titre = (TextView) findViewById(R.id.titre);
        description = (TextView) findViewById(R.id.body);
        img = (ImageView)findViewById(R.id.imageView4);
        titre.setText(title);
        description.setText(desc);
        Picasso.get().load(url).into(img);
    }
}
