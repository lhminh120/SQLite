package sqlite.mine.com.sqlite.adapter;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import sqlite.mine.com.sqlite.R;
import sqlite.mine.com.sqlite.model.Student;

/**
 * Created by MyPC on 31/07/2017.
 */

public class CustomAdapter extends ArrayAdapter<Student> {
    private Context context;
    private int resource;
    private List<Student> listStudent;
    public CustomAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Student> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.listStudent = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            convertView = LayoutInflater.from(context).inflate(R.layout.item_list_student,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.tvID = (TextView) convertView.findViewById(R.id.tv_id);
            viewHolder.tvName = (TextView) convertView.findViewById(R.id.tv_name);
            viewHolder.tvAddress = (TextView) convertView.findViewById(R.id.tv_address);
            viewHolder.tvNumber = (TextView) convertView.findViewById(R.id.tv_number);
            viewHolder.tvMail = (TextView) convertView.findViewById(R.id.tv_mail);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }
        Student student = listStudent.get(position);
        viewHolder.tvID.setText(String.valueOf(student.getmID()));
        viewHolder.tvName.setText(student.getmName());
        viewHolder.tvNumber.setText(student.getmPhonenumber());
        viewHolder.tvAddress.setText(student.getmAddress());
        viewHolder.tvMail.setText(student.getmMail());
        return convertView;
    }
    public class ViewHolder{
        private TextView tvID; //Ctrl+D để copy nhanh
        private TextView tvName;
        private TextView tvNumber;
        private TextView tvMail;
        private TextView tvAddress;
    }
}
