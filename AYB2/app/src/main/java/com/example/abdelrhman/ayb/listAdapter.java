package com.example.abdelrhman.ayb;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.abdelrhman.ayb.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class listAdapter extends RecyclerView.Adapter<listAdapter.lAViewHolder> {

    private List<cases> casesList;

    public listAdapter(List<cases> casesList) {
        this.casesList = casesList;
    }
    LayoutInflater inflater;

    @NonNull
    @Override
    public lAViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        Context context  = viewGroup.getContext();
         inflater = LayoutInflater.from(context);
        View itemView = inflater.inflate(R.layout.activity_list_item,viewGroup,false);
        return new lAViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull lAViewHolder _lAViewHolder, final int i) {
        _lAViewHolder.cTitle.setText(casesList.get(i).getTitle());
//        _lAViewHolder.cDesc.setText(casesList.get(i).getDescription());
        _lAViewHolder.cType.setText(casesList.get(i).getCaseType());
//        _lAViewHolder.cImg.setImageURI(Uri.parse(casesList.get(i).getURLs().get(0)));
        Picasso.get().load(casesList.get(i).getURLs().get(0)).into(_lAViewHolder.cImg);
        _lAViewHolder.cImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(inflater.getContext(),Case.class);
                intent.putExtra("title",casesList.get(i).getTitle());
                intent.putExtra("desc",casesList.get(i).getDescription());
                intent.putExtra("type",casesList.get(i).getCaseType());
                intent.putExtra("url",casesList.get(i).getURLs().get(0));
                inflater.getContext().startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return casesList.size();
    }

    public class lAViewHolder extends RecyclerView.ViewHolder{
        TextView cTitle;
        TextView cDesc;
        TextView cType;
        ImageView cImg;

        public lAViewHolder(@NonNull View itemView) {
            super(itemView);
            cTitle = (TextView) itemView.findViewById(R.id.cTitle);
            cDesc = (TextView) itemView.findViewById(R.id.cDescription);
            cType = (TextView) itemView.findViewById(R.id.cType);
            cImg = (ImageView) itemView.findViewById(R.id.cImg);
        }
    }
}