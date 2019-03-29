package com.linearlayout.chatbot20182.model;

public class Law {
    private int mId;
    private String mName;
    private String mDescription;
    private String mImageURL;
    private String mActivate;

    public Law(String mName, String mDescription,String mImageURL, String mActivate) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mActivate = mActivate;
        this.mImageURL=mImageURL;
    }

    public Law() {

    }

    public Law(int mId, String mName, String mDescription,String mImageURL, String mActivate) {
        this.mId = mId;
        this.mName = mName;
        this.mDescription = mDescription;
        this.mActivate = mActivate;
        this.mImageURL=mImageURL;
    }

    public String getmImageURL() {
        return mImageURL;
    }

    public void setmImageURL(String mImageURL) {
        this.mImageURL = mImageURL;
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
