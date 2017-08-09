package sqlite.mine.com.sqlite;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

import sqlite.mine.com.sqlite.adapter.CustomAdapter;
import sqlite.mine.com.sqlite.data.DBManager;
import sqlite.mine.com.sqlite.model.Student;

public class MainActivity extends AppCompatActivity {
    private EditText edtName;
    private EditText edtNumber;
    private EditText edtAddress;
    private EditText edtMail;
    private EditText edtID;
    private Button btnSave;
    private Button btnUpdate;
    private ListView lvStudent;
    private DBManager dbManager;
    private CustomAdapter customAdapter;
    private List<Student> students;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dbManager = new DBManager(this);
        initWidget();
        students = dbManager.getAllStudent();
        setAdap();
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dbManager.addStudent(createStudent());
                Student student = createStudent();
                if(student != null){
                    //Cái này al2 không cần thiết nhưng check cho có với tạo thành thói quen
                    dbManager.addStudent(student);
                }
                updateListStudent();
                setAdap();
            }
        });
        lvStudent.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = students.get(position);
                edtID.setText(student.getmID()+"");
                edtName.setText(student.getmName());
                edtAddress.setText(student.getmAddress());
                edtNumber.setText(student.getmPhonenumber());
                edtMail.setText(student.getmMail());
                btnSave.setEnabled(false);
                btnUpdate.setEnabled(true);
            }
        });
        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Student student = new Student();
                student.setmID(Integer.parseInt(String.valueOf(edtID.getText())));
                student.setmName(edtName.getText().toString());
                student.setmAddress(edtAddress.getText().toString());
                student.setmPhonenumber(edtNumber.getText().toString());
                student.setmMail(edtMail.getText().toString());
                int result = dbManager.updateStudent(student);
                if(result>0){
                    updateListStudent();
                }
                btnSave.setEnabled(true);
                btnUpdate.setEnabled(false);
            }
        });
        lvStudent.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Student student = students.get(position);
                int result = dbManager.deleteStudent(student.getmID());
                if(result>0){
                    Toast.makeText(MainActivity.this, "Delete sucessfully", Toast.LENGTH_SHORT).show();
                    updateListStudent();
                }else{
                    Toast.makeText(MainActivity.this, "Delete fail", Toast.LENGTH_SHORT).show();
                }
                return false;
            }
        });
    }
    private Student createStudent(){
        String name = edtName.getText().toString();
        String number = String.valueOf(edtNumber.getText());
        String address = edtAddress.getText() + "";
        String mail = edtMail.getText().toString();

        Student student = new Student(name, address, number, mail);
        return student;
    }
    private void initWidget(){
        edtName = (EditText) findViewById(R.id.edt_name);
        edtNumber = (EditText) findViewById(R.id.edt_phonenumber);
        edtAddress = (EditText) findViewById(R.id.edt_address);
        edtMail = (EditText) findViewById(R.id.edt_email);
        edtID = (EditText) findViewById(R.id.edt_ID);
        btnSave = (Button) findViewById(R.id.btnSave);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        lvStudent = (ListView) findViewById(R.id.lv_student);
    }
    private void setAdap(){
        if(customAdapter == null){
            customAdapter = new CustomAdapter(this, R.layout.item_list_student, students);
            lvStudent.setAdapter(customAdapter);
        }else{
            customAdapter.notifyDataSetChanged();
            lvStudent.setSelection(customAdapter.getCount()-1);
        }

    }
    private void updateListStudent(){
        students.clear();
        students.addAll(dbManager.getAllStudent());
        if(customAdapter != null){
            customAdapter.notifyDataSetChanged();
        }

    }
}
