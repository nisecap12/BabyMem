package com.usoosoft.babymem.view;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.usoosoft.babymem.R;
import com.usoosoft.babymem.model.DiaryItem;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by 조수한 on 2016-08-22.
 */
public class DiaryItemAdapter extends BaseAdapter {
    private ArrayList<DiaryItem> item_list;
    private ArrayList<DiaryItem> reverse_list;
    private DiaryItem temp_item;
    private String itemStartTime;
    private String itemEndTime;
    private String itemMemo;
    private String itemType;
    private int itemID = 0;

    public DiaryItemAdapter(){
        item_list = new ArrayList<DiaryItem>();
        reverse_list = new ArrayList<DiaryItem>();
    }

    @Override
    public int getCount() {
        return item_list.size();
    }

    @Override
    public Object getItem(int i) {
        return item_list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View convertView, ViewGroup parent) {
        final int pos = i;
        final Context context = parent.getContext();

        if ( convertView == null ) {
            // view가 null일 경우 커스텀 레이아웃을 얻어 옴
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.main_record_item, parent, false);

            TextView record_time = (TextView)convertView.findViewById(R.id.record_item_time_txt);
            TextView record_type = (TextView)convertView.findViewById(R.id.record_item_type_txt);
            ImageView record_icon = (ImageView)convertView.findViewById(R.id.record_item_type_icon);

            record_type.setText(itemType);
            record_time.setText(itemStartTime + " ~ " +itemEndTime);
            if(itemType == "모유"){
                record_icon.setImageResource(R.drawable.ic_milk);
            }else if(itemType == "유축"){
                record_icon.setImageResource(R.drawable.ic_gather);
            }else{
                record_icon.setImageResource(R.drawable.ic_sleep);
            }

            // 리스트 아이템을 터치 했을 때 이벤트 발생
            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 클릭 : "+item_list.get(pos), Toast.LENGTH_SHORT).show();
                }
            });

            // 리스트 아이템을 길게 터치 했을 떄 이벤트 발생
            convertView.setOnLongClickListener(new View.OnLongClickListener() {

                @Override
                public boolean onLongClick(View v) {
                    // 터치 시 해당 아이템 이름 출력
                    Toast.makeText(context, "리스트 롱 클릭 : "+item_list.get(pos), Toast.LENGTH_SHORT).show();
                    return true;
                }
            });
        }

        return convertView;
    }

    public void add(String start_time, String end_time ,String type){
        temp_item = new DiaryItem();
        temp_item.setType(type);
        temp_item.setStart_time(start_time);
        temp_item.setEnd_time(end_time);
        temp_item.setId(itemID++);

        itemType = type;
        itemStartTime = start_time;
        itemEndTime = end_time;

        reverse_list.add(temp_item);
        Collections.reverse(reverse_list);
        System.out.println(reverse_list);
        item_list.clear();
        item_list.addAll(reverse_list);
        System.out.println("item_list : "+item_list);
        Collections.reverse(reverse_list);
    }

    public void remove(int position){
        item_list.remove(position);
    }
}
