package com.a71.dendi.contactslist;

/**
 * Created by Dendi on 2016/11/12.
 */

public class ContactsBean {
    private char mAbbreviation;
    private String mContactName;
    private String mPhoneNum;
    private long mId;
    private long mPortraitId;


    public ContactsBean(String mContactName, String mPhoneNum, long mId, long mPortraitId) {
        this.mContactName = mContactName;
        this.mPhoneNum = mPhoneNum;
        this.mId = mId;
        this.mPortraitId = mPortraitId;

        mAbbreviation = ContactsUtils.getAbbreviation(mContactName);
    }


    public String getContactName() {
        return mContactName;
    }


    public char getAbbreviation() {
        return mAbbreviation;
    }


    public String getPhoneNum() {
        return mPhoneNum;
    }


    public long getId() {
        return mId;
    }


    public long getPortraitId() {
        return mPortraitId;
    }
}
