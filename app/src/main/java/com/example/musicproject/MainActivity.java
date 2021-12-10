package com.example.musicproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private com.example.musicproject.Music

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initToggleButton();
        initAddButton();
        initFavoritesButton();
        initSaveButton();
        setForEditing(false);
    }

    public void onResume() {
        super.onResume();
        Intent intent = getIntent();
        int position = intent.getIntExtra("position", -1);
        if (position != -1) {
            MusicDataSource ds = new MusicDataSource(this);
            try {
                ds.open();
                currentMusic = ds.getMusics(position + 1);
                EditText nameEdit = findViewById(R.id.editContact);
                nameEdit.setText(currentContact.getName());
                EditText addressEdit = findViewById(R.id.editAddress);
                addressEdit.setText(currentContact.getAddress());
                EditText cityEdit = findViewById(R.id.editCity);
                cityEdit.setText(currentContact.getCity());
            } catch (Exception e) {
                Toast.makeText(this, "Error accessing contact", Toast.LENGTH_LONG).show();
            }
        }
    }

    private void initToggleButton() {
        ToggleButton toggleButton = findViewById(R.id.toggleButton);
        toggleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { setForEditing(toggleButton.isChecked()); }
        });
    }

    private void initAddButton() {
        Button addButton = findViewById(R.id.buttonAdd);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, List.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initFavoritesButton() {
        Button favoritesButton = findViewById(R.id.buttonFavorite);
        favoritesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, Map.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }

    private void initSaveButton() {
        Button savebutton = findViewById(R.id.buttonSave);
        savebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                boolean wasSuccessful;
                MusicDataSource ds = new MusicDataSource(MainActivity.this);
                try {
                    ds.open();
                    if (currentContact.getContactID() == -1) {
                        wasSuccessful = ds.insertContact(currentContact);
                        if(wasSuccessful) {
                            int newId = ds.getLastContactID();
                            currentContact.setContactID(newId);
                        }
                    } else {
                        wasSuccessful = ds.updateContact(currentContact);
                    }
                    ds.close();
                } catch (Exception e) {
                    wasSuccessful = false;
                }
                if (wasSuccessful) {
                    ToggleButton editToggle = findViewById(R.id.toggleButton);
                    editToggle.toggle();
                    setForEditing(false);
                }
            }
        });
    }

    private void setForEditing(boolean enabled) {
        EditText editSongName = findViewById(R.id.editSongName);
        EditText editYearReleased =  findViewById(R.id.editYearReleased);
        EditText editAristName = findViewById(R.id.editArtistName);
        Button buttonsave = findViewById(R.id.buttonSave);

        editSongName.setEnabled(enabled);
        editYearReleased.setEnabled(enabled);
        editAristName.setEnabled(enabled);
        buttonsave.setEnabled(enabled);
    }
}