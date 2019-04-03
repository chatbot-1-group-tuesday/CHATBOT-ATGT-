package com.linearlayout.chatbot20182.Model;

public class Law
{
    private int mID;
    private String mName;
    private String mDescription;


    public Law(int mID, String mName, String mDescription) {
        this.mID = mID;
        this.mName = mName;
        this.mDescription = mDescription;
    }

    public Law(String mName, String mDescription) {
        this.mName = mName;
        this.mDescription = mDescription;
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

    public String getmDescription() {
        return mDescription;
    }

    public void setmDescription(String mDescription) {
        this.mDescription = mDescription;
    }
}
