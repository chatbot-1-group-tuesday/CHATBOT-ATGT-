package com.linearlayout.chatbot20182.model;

public class Sign {
    private int mId;
    private String mName;
    private String mDescription;
    private byte[] mImage;
    private String mActivate;

    public Sign(int mId) {
        this.mId = mId;
    }
    public Sign(String mName, String mDescription,byte[] mImage, String mActivate) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mActivate = mActivate;
        this.mImage = mImage;
    }

    public Sign() {

    }

    public Sign(int mId, String mName, String mDescription, byte[] mImage, String mActivate) {
        this.mId = mId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mActivate = mActivate;
        this.mImage = mImage;
    }


    public byte[] getmImage() {
        return mImage;
    }

    public void setmImage(byte[] mImage) {
        this.mImage = mImage;
    }

    public int getmId() {
        return mId;
    }

    public void setmId(int mId) {
        this.mId = mId;
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

    public String getmActivate() {
        return mActivate;
    }

    public void setmActivate(String mActivate) {
        this.mActivate = mActivate;
    }


}
