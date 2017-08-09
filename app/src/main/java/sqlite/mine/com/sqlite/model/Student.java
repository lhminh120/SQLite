package sqlite.mine.com.sqlite.model;

/**
 * Created by MyPC on 30/07/2017.
 */

public class Student {
    private int mID;
    private  String mName;
    private  String mAddress;
    private  String mPhonenumber;
    private  String mMail;

    public Student() {
    }

    public Student(String mName, String mAddress, String mPhonenumber, String mMail) {
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhonenumber = mPhonenumber;
        this.mMail = mMail;
    }

    public Student(int mID, String mName, String mAddress, String mPhonenumber, String mMail) {
        this.mID = mID;
        this.mName = mName;
        this.mAddress = mAddress;
        this.mPhonenumber = mPhonenumber;
        this.mMail = mMail;
    }

    public int getmID() {
        return mID;
    }

    public void setmID(int mID) {
        this.mID = mID;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmAddress() {
        return mAddress;
    }

    public void setmAddress(String mAddress) {
        this.mAddress = mAddress;
    }

    public String getmPhonenumber() {
        return mPhonenumber;
    }

    public void setmPhonenumber(String mPhonenumber) {
        this.mPhonenumber = mPhonenumber;
    }

    public String getmMail() {
        return mMail;
    }

    public void setmMail(String mMail) {
        this.mMail = mMail;
    }
}
