package com.example.lab05.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.lab05.MainActivity;
import com.example.lab05.R;

public class MyAdapter extends BaseAdapter {
    LayoutInflater inflater;
    TextView textView;
    Context context;

    public MyAdapter(Context context) {
        inflater = LayoutInflater.from(context);
        this.context = context;
    }

    @Override
    public int getCount() {
        return MainActivity.studentArrayList.size();
    }

    @Override
    public Object getItem(int i) {
        return MainActivity.studentArrayList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return MainActivity.studentArrayList.get(i).get_id();
    }

    @Override
    public View getView(final int i, View view, ViewGroup viewGroup) {
        View currentView = inflater.inflate(R.layout.item_listview,null);

        textView = (TextView) currentView.findViewById(R.id.txt_item_name);
        textView.setText(MainActivity.studentArrayList.get(i).getStudentName());
        textView = (TextView) currentView.findViewById(R.id.txt_item_class);
        textView.setText(MainActivity.studentArrayList.get(i).getStudentClass());
        ((ImageView) currentView.findViewById(R.id.img_delete))
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        MainActivity.database.deleteStudent(
                                MainActivity.studentArrayList.get(i)
                        );
                        MainActivity.studentArrayList.remove(i);
                        notifyDataSetChanged();
                    }
                });
        return currentView;
    }
}
