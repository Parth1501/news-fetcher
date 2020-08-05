package com.example.parth.myapplication;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MainActivity extends AppCompatActivity  {
    RecyclerView examplelist;
    List<String> information;
    HashMap<String,String> map;
    ExampleAdapter madapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        examplelist= findViewById(R.id.example);
        examplelist.setLayoutManager(new LinearLayoutManager(this));
        examplelist.setAdapter(madapter);
        information=new ArrayList<String>() ;
        map=new HashMap<>();
        getWebsite();

    }
    private void getWebsite() {


        new Thread(new Runnable() {


            @Override
            public void run() {
                try {
                    Document doc = Jsoup.connect("https://www.livemint.com/latest-news").get();
                    Elements div=doc.select("#mylistView");
                    Elements a=div.select("a");
                    for(Element e : a) {
                        if(!e.text().equals("")) {
                            information.add(e.text());
                            map.put(e.text(), e.attr("href"));
                            System.out.println("------------------------------------------"+e.text()+"----------------------------");
                        }
                    }

                    /*String li_a=li.attr("a");
                    list.add(li_a);*/

                } catch (Exception e) {
                    System.out.println("-------------------------------------CANNOT CONNECT---------------------------------" + e);
                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("-------------------Hello------------------------");
                        madapter=new ExampleAdapter(information,map);
                        examplelist.setAdapter(madapter);
                        madapter.setOnNewsClickListener(new ExampleAdapter.OnNewsClickListener() {
                            @Override
                            public void onNewsClick(int i) {
                                System.out.println("-----------------CLICK----------------------------");
                                String url_name=information.get(i);
                                String url1=map.get(url_name);
                                String appender="https://www.livemint.com";
                                String url=appender+url1;
                                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                                startActivity(browserIntent);

                            }
                        });
                    }
                });


            }
        }).start();



    }


    public void getWebsite(View view) {
        getWebsite();
    }
}
