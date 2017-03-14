package com.example.yq.newapplication;

import android.graphics.Rect;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    RecyclerView recycler_view;
    List<String> lists = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        lists.add("0xxxxxx\nxxxx\nxxx\nxxxxxx");
        for (int i = 0;i<20;i++){
            lists.add(i+"");
        }
        lists.add("21yyyyyyyyyyyyyyyyyyyyyyyyyyyyy");

        setContentView(R.layout.activity_main);//recyclerview,把listview中findviewbyid,优化，
        recycler_view = (RecyclerView) findViewById(R.id.recycler_view);
        //listview,gridview,stragged交叉布局----布局管理器
        recycler_view.setLayoutManager(new LinearLayoutManager(this));
//        recycler_view.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL, false));
//        recycler_view.setLayoutManager(new GridLayoutManager(this, 2));
//        recycler_view.setLayoutManager(new GridLayoutManager(this, 2,GridLayoutManager.HORIZONTAL,false));
//        recycler_view.setLayoutManager(new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));
        //item间距-添加item的修饰Decoration
        recycler_view.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL){
            @Override//item视图的left,top,right,bottom边距
            public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
//                super.getItemOffsets(outRect, view, parent, state);
                outRect.left = 3;
                outRect.top = 3;
                outRect.right = 3;
                outRect.bottom = 10;
            }
        });


        //adapter
        recycler_view.setAdapter(new RecyclerView.Adapter<MyViewHolder>() {//0加载布局(重复使用的item)
            @Override//getview 给一个布局给getview
            public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                View view = LayoutInflater.from(MainActivity.this).inflate(R.layout.item,parent,false);
                return new MyViewHolder(view);
            }

            @Override//1绑定数据
            public void onBindViewHolder(final MyViewHolder holder, int position) {
                holder.textView.setText(lists.get(position));
                //itemview布局item的整个内容,,2设置点击事件
                holder.itemView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Toast.makeText(MainActivity.this, ""+holder.getAdapterPosition(), Toast.LENGTH_SHORT).show();
                    }
                });
            }

            @Override
            public int getItemCount() {
                return lists.size();
            }
        });
    }
    class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView textView;
        public MyViewHolder(View itemView) {
            super(itemView);
            textView = (TextView) itemView.findViewById(R.id.tv);
        }
    }
}
