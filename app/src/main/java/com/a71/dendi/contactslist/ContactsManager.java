package com.a71.dendi.contactslist;

import android.content.ContentResolver;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.text.TextUtils;
import java.util.ArrayList;

/**
 * Created by Dendi on 2016/11/12.
 */

public class ContactsManager {

    private static final String[] PHONES_PROJECTION = new String[] {
        ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME,
        ContactsContract.CommonDataKinds.Phone.NUMBER,
        ContactsContract.Contacts.Photo.PHOTO_ID,
        ContactsContract.CommonDataKinds.Phone.CONTACT_ID };

    /** 联系人显示名称 **/
    private static final int PHONES_DISPLAY_NAME_INDEX = 0;

    /** 电话号码 **/
    private static final int PHONES_NUMBER_INDEX = 1;

    /** 头像ID **/
    private static final int PHONES_PHOTO_ID_INDEX = 2;

    /** 联系人的ID **/
    private static final int PHONES_CONTACT_ID_INDEX = 3;


    public static ArrayList<ContactsBean> getPhoneContacts(Context mContext) {
        ArrayList<ContactsBean> result = new ArrayList<>(0);
        ContentResolver resolver = mContext.getContentResolver();
        // 获取手机联系人
        Cursor phoneCursor = resolver.query(ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            PHONES_PROJECTION, null, null, null);

        if (phoneCursor != null) {
            while (phoneCursor.moveToNext()) {
                String phoneNumber = phoneCursor.getString(PHONES_NUMBER_INDEX);
                //当手机号码为空的或者为空字段 跳过当前循环
                if (TextUtils.isEmpty(phoneNumber)) {
                    continue;
                }
                String contactName = phoneCursor.getString(PHONES_DISPLAY_NAME_INDEX);
                Long contactid = phoneCursor.getLong(PHONES_CONTACT_ID_INDEX);
                Long photoid = phoneCursor.getLong(PHONES_PHOTO_ID_INDEX);
                result.add(new ContactsBean(contactName, phoneNumber, contactid, photoid));
            }
            phoneCursor.close();
        }
        return result;
    }

}
