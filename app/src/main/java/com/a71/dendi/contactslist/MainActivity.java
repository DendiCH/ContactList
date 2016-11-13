package com.a71.dendi.contactslist;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

public class MainActivity extends Activity {

    private RecyclerView mRecycleView;
    private LetterView mLetterView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecycleView = (RecyclerView) findViewById(R.id.contacts_recycle_view);
        mRecycleView.setLayoutManager(new LinearLayoutManager(this));
        mRecycleView.addItemDecoration(
            new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
       mRecycleView.setAdapter(
            new ContactsListAdapter(LayoutInflater.from(this),
                ContactsManager.getPhoneContacts(this)));

        mLetterView = (LetterView) findViewById(R.id.contacts_letter_view);
        mLetterView.setCharacterListener(new LetterView.CharacterClickListener() {
            @Override public void clickCharacter(String character) {
                Toast.makeText(MainActivity.this, character + " clicked!", Toast.LENGTH_SHORT)
                    .show();
            }


            @Override public void clickArrow() {

            }
        });
    }

}
