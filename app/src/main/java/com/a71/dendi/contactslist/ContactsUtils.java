package com.a71.dendi.contactslist;

import android.content.ContentUris;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.ContactsContract;
import java.io.InputStream;

/**
 * Created by Dendi on 2016/11/12.
 */

public class ContactsUtils {

    public static Bitmap getHeadPortrait(ContactsBean bean, Context context, int defaultDrawableId) {
        Bitmap contactPhoto;
        if (bean.getPortraitId() > 0) {
            Uri uri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI,
                bean.getId());
            InputStream input = ContactsContract.Contacts.openContactPhotoInputStream(
                context.getContentResolver(), uri);
            contactPhoto = BitmapFactory.decodeStream(input);
        } else {
            contactPhoto = BitmapFactory.decodeResource(context.getResources(),
                defaultDrawableId);
        }
        return contactPhoto;
    }


    // 简体中文的编码范围从B0A1（45217）一直到F7FE（63486）
    private static int BEGIN = 45217;
    private static int END = 63486;

    // 按照声 母表示，这个表是在GB2312中的出现的第一个汉字，也就是说“啊”是代表首字母a的第一个汉字。
    // i, u, v都不做声母, 自定规则跟随前面的字母
    private static char[] chartable = { '啊', '芭', '擦', '搭', '蛾', '发', '噶', '哈', '击', '喀', '垃',
        '妈', '拿', '哦', '啪', '期', '然', '撒', '塌', '挖', '昔', '压', '匝' };

    // 二十六个字母区间对应二十七个端点
    // GB2312码汉字区间十进制表示
    private static int[] table = new int[chartable.length + 1];

    // 对应首字母区间表
    private static char[] initialtable = { 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'j', 'k',
        'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'w', 'x', 'y', 'z' };


    // 初始化
    static {
        for (int i = 0; i < chartable.length; i++) {
            table[i] = gbValue(chartable[i]);// 得到GB2312码的首字母区间端点表，十进制。
        }
        table[chartable.length] = END;// 区间表结尾
    }


    public static char getAbbreviation(String string) {
        return getAbbreviation(string, '#');
    }


    public static char getAbbreviation(String string, char defaultAbbreviation) {
        return string == null || string.length() == 0
               ? defaultAbbreviation
               : Char2Initial(string.charAt(0), defaultAbbreviation);
    }


    /**
     * 输入字符,得到他的声母,英文字母返回对应的大写字母,其他非简体汉字返回 '0' 　　*
     */
    private static char Char2Initial(char ch, char defaultAbbreviation) {
        // 对英文字母的处理：小写字母转换为大写，大写的直接返回
        if (ch >= 'a' && ch <= 'z') {
            return (char) (ch - 'a' + 'A');
        }
        if (ch >= 'A' && ch <= 'Z') {
            return ch;
        }
        // 对非英文字母的处理：转化为首字母，然后判断是否在码表范围内，
        // 若不是，则直接返回。
        // 若是，则在码表内的进行判断。
        int gb = gbValue(ch);// 汉字转换首字母
        if ((gb < BEGIN) || (gb > END))// 在码表区间之前，直接返回
        {
            return defaultAbbreviation;
        }

        int i = 1;
        while (i < table.length) {
            if (gb < table[i]) {
                i--;
                break;
            }
            i++;
        }
        return initialtable[i];
    }


    /**
     * 取出汉字的编码 cn 汉字
     */
    private static int gbValue(char ch) {// 将一个汉字（GB2312）转换为十进制表示。
        String str = new String();
        str += ch;
        try {
            byte[] bytes = str.getBytes("GB2312");
            if (bytes.length < 2) {
                return 0;
            }
            return (bytes[0] << 8 & 0xff00) + (bytes[1] & 0xff);
        } catch (Exception e) {
            return 0;
        }
    }

}
