package com.a71.dendi.contactslist;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * Created by Dendi on 2016/11/13.
 */

public class ContactsListAdapter extends RecyclerView.Adapter {
    private ArrayList<ContactsBean> mList;
    private Context mContext;
    private LayoutInflater mLayoutInflater;


    public enum ITEM_TYPE {
        CHARACTER,
        CONTACT
    }


    public ContactsListAdapter(Context context, ArrayList<ContactsBean> list) {
        this.mContext = mContext;
        this.mList = list;
        Collections.sort(list, new Comparator<ContactsBean>() {
            @Override public int compare(ContactsBean l, ContactsBean r) {
                if (l.getAbbreviation() == r.getAbbreviation()) {
                    return l.getContactName().compareTo(r.getContactName());
                }
                return l.getAbbreviation() - r.getAbbreviation();
            }
        });
        mLayoutInflater = LayoutInflater.from(this.mContext);
    }

    public ContactsListAdapter(LayoutInflater layoutInflater,ArrayList<ContactsBean> list) {
        this.mList = list;
        Collections.sort(list, new Comparator<ContactsBean>() {
            @Override public int compare(ContactsBean l, ContactsBean r) {
                if (l.getAbbreviation() == r.getAbbreviation()) {
                    return l.getContactName().compareTo(r.getContactName());
                }
                return l.getAbbreviation() - r.getAbbreviation();
            }
        });
        mLayoutInflater = layoutInflater;
    }


    @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == ITEM_TYPE.CHARACTER.ordinal()) {
            return new CharacterSectionViewHolder(
                mLayoutInflater.inflate(R.layout.listitem_character_section, null));
        } else if (viewType == ITEM_TYPE.CONTACT.ordinal()) {
            return new ContactsViewHolder(
                mLayoutInflater.inflate(R.layout.listitem_contact_info, null));
        }
        throw new IllegalStateException("Illegal Exception");
    }


    @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof CharacterSectionViewHolder) {
            ((CharacterSectionViewHolder) holder).charTv.setText("asdfasdfsdf");
        } else if (holder instanceof ContactsViewHolder) {
            ((ContactsViewHolder) holder).headPortIv.setImageBitmap(null);
            ((ContactsViewHolder) holder).nameTx.setText("ddf");
            ((ContactsViewHolder) holder).phoneTv.setText("23232");
        } else {
            throw new IllegalStateException("Illegal state Exception onBindviewHolder");
        }
    }


    @Override public int getItemViewType(int position) {
        // TODO: 2016/11/13
        return ITEM_TYPE.CONTACT.ordinal();
    }


    @Override public int getItemCount() {
        // TODO: 2016/11/13
        return mList.size();
    }


    private class CharacterSectionViewHolder extends RecyclerView.ViewHolder {
        TextView charTv;


        public CharacterSectionViewHolder(View itemView) {
            super(itemView);
            charTv = (TextView) itemView.findViewById(R.id.listitem_character_section_header);
        }
    }


    private class ContactsViewHolder extends RecyclerView.ViewHolder {
        ImageView headPortIv;
        TextView nameTx;
        TextView phoneTv;


        public ContactsViewHolder(View itemView) {
            super(itemView);
            headPortIv = (ImageView) itemView.findViewById(R.id.listitem_head_portrait);
            nameTx = (TextView) itemView.findViewById(R.id.listitem_contacts_name);
            phoneTv = (TextView) itemView.findViewById(R.id.listitem_contacts_num);
        }
    }
}
