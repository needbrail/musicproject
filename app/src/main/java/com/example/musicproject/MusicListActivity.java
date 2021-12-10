package com.example.musicproject;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Map;

public class MusicListActivity {
    private View.OnClickListener onClickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) view.getTag();
            int position = viewHolder.getAdapterPosition();
            Intent intent = new Intent(MusicListActivity.this, MainActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            intent.putExtra("position", position);
            startActivity(intent);
        }
    };

    private class SortByName implements Comparator<Music> {

        private boolean isAscending;

        public SortByName(boolean isAscending) {
            this.isAscending = isAscending;
        }

        @Override
        public int compare(Music music, Music t1) {
            if (isAscending)
                return music.getSongName().compareTo(t1.getSongName());
            else
                return t1.getSongName().compareTo(Music.getName());
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactlist);

        initAddButton();
        initFavoritesButton();

        MusicDataSource ds = new MusicDataSource(this);
        ArrayList<Contact> contacts;
        try {
            ds.open();
            contacts = ds.getContacts();
            ds.close();
            SharedPreferences sharedPref = getSharedPreferences("MyContactListPreferennces", Context.MODE_PRIVATE);
            String sortBy = sharedPref.getString("sortfield", "name");
            String sortOrder = sharedPref.getString("sortorder", "ascending");
            if(sortBy.equals("name"))
                if(sortOrder.equals("ascending"))
                    contacts.sort(new SortByName(true));
                else
                    contacts.sort(new SortByName(false));
            RecyclerView contactlist = findViewById(R.id.rvContacts);
            RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
            contactlist.setLayoutManager(layoutManager);
            MusicAdapter contactAdapter = new MusicAdapter(contacts);
            contactAdapter.setOnClickListener(onClickListener);
            contactlist.setAdapter(contactAdapter);
        } catch (Exception e) {
            Toast.makeText(this, "Error retrieving contacts", Toast.LENGTH_LONG).show();
        }
    }

    private void initAddButton() {
        Button addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MusicListActivity.this, MusicListActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
    private void initFavoritesButton() {
        Button favoriteButton = fd
            }
        });
    }
}
