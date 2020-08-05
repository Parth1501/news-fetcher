package com.example.parth.myapplication;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.zip.Inflater;

public class ExampleAdapter  extends RecyclerView.Adapter<ExampleAdapter.exampleHolder>  {

    private List<String> info;
    HashMap<String, String> map;
    private OnNewsClickListener mListener;
    public interface OnNewsClickListener {
        void onNewsClick(int i);
    }
    public void setOnNewsClickListener(OnNewsClickListener listener) {
        mListener=listener;
    }

    public ExampleAdapter(List<String> info,HashMap<String,String> map) {
        this.info = info;
        this.map=map;

    }

    @NonNull
    @Override
    public exampleHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater=LayoutInflater.from(viewGroup.getContext());
        View view=inflater.inflate(R.layout.example,viewGroup,false);

        return new exampleHolder(view,mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull exampleHolder exampleHolder, int i) {
        String news_info=info.get(i);
        //String news_link=info_link.get(i);
        exampleHolder.text_info.setText(news_info);


    }

    @Override
    public int getItemCount() {
        return info.size();
    }

    public static class exampleHolder extends RecyclerView.ViewHolder  {
        TextView text_info;
        CardView cr;

        public exampleHolder(@NonNull View itemView, final OnNewsClickListener listener) {
            super(itemView);
            text_info=itemView.findViewById(R.id.text);
            cr=itemView.findViewById(R.id.card_view);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(listener!=null) {
                        int i=getAdapterPosition();

                            listener.onNewsClick(i);
                                            }
                }
            });


        }


    }

}
