package sqlite.mine.com.sqlite.data;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import sqlite.mine.com.sqlite.MainActivity;
import sqlite.mine.com.sqlite.model.Student;

/**
 * Created by MyPC on 30/07/2017.
 */

public class DBManager extends SQLiteOpenHelper {
    private final String TAG = "DBManager";
    private static final String DATABASE_NAME ="students_manager";
    private static final String TABLE_NAME ="students";
    private static final String ID ="id";
    private static final String NAME ="name";
    private static final String EMAIL ="email";
    private static final String PHONE_NUMBER ="phone";
    private static final String ADDRESS ="address";
    private static int VERSON = 1;
    private Context context;

    private  String SQLQuery = "CREATE TABLE " + TABLE_NAME + " (" +
            ID + " integer primary key, " +
            NAME + " TEXT, " +
            EMAIL + " TEXT, " +
            PHONE_NUMBER + " TEXT, " +
            ADDRESS + " TEXT)";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null, VERSON);
        this.context = context;
        Log.d(TAG, "DBManager: ");
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(SQLQuery);
        Log.d(TAG, "onCreate: ");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");
    }
    public void addStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, student.getmName());
        values.put(PHONE_NUMBER, student.getmPhonenumber());
        values.put(EMAIL, student.getmMail());
        values.put(ADDRESS, student.getmAddress());
        db.insert(TABLE_NAME, null, values);
        db.close();
        Log.d(TAG, "addStudent: ");
    }
    public List<Student> getAllStudent(){
        List<Student> listStudent = new ArrayList<>();
        String selectQuery = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null); //selectQuery là lấy cái bảng, còn null là lấy hết trong bảng, trong 1 số trường hợp có thể đặt 1 điều kiện để nó trả về
        if(cursor.moveToFirst()){
            do{
                Student student = new Student();
                student.setmID(cursor.getInt(0)); //Phải đúng thứ tự của bảng TABLE_NAME
                student.setmName(cursor.getString(1));
                student.setmMail(cursor.getString(2));
                student.setmPhonenumber(cursor.getString(3));
                student.setmAddress(cursor.getString(4));
            }while(cursor.moveToNext());
        }
        db.close();
        return listStudent;
    }
    public int updateStudent(Student student){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NAME,student.getmName());
        contentValues.put(PHONE_NUMBER,student.getmPhonenumber());
        contentValues.put(ADDRESS,student.getmAddress());
        contentValues.put(EMAIL,student.getmMail());
        return db.update(TABLE_NAME,contentValues, ID + "=?" //+ " and " + NAME + "=?"//
                ,new String[]{String.valueOf(student.getmID())}); //Nếu set cứng ID mà không phải là dấu hỏi thì cái string[] phía sau sẽ là null
    }
    public int deleteStudent (int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME,ID + "=?", new String[]{String.valueOf(id)});
    }
}
