package com.example.petssqlite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.petssqlite.data.PetContract;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

public class EditorActivity extends AppCompatActivity {

    private EditText mNameEdittext;
    private EditText mBreedEditText;
    private EditText mWeightEdittext;
    private Spinner mGenderSpinner;
    private int gender = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_editor);
        mNameEdittext = findViewById(R.id.name);
        mBreedEditText = findViewById(R.id.breed);
        mWeightEdittext = findViewById(R.id.weight);
        mGenderSpinner = findViewById(R.id.spinner_gender);

        Objects.requireNonNull(getSupportActionBar()).setTitle("Add a pet");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        setUpSpinner();
    }
    public void setUpSpinner(){
        //spinner logic
        ArrayList<String> spinnerItem = new ArrayList<String>(Arrays.asList("male","female","unknown"));
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerItem);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        mGenderSpinner.setAdapter(adapter);
        mGenderSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selection = (String) adapterView.getItemAtPosition(i);
                if (!TextUtils.isEmpty(selection))
                {
                    if(selection.equals(getString(R.string.gender_male))){
                        gender = 0;
                        Toast.makeText(EditorActivity.this, "male is selected", Toast.LENGTH_SHORT).show();
                    }
                    else if(selection.equals(getString(R.string.gender_female))){
                        gender = 2;
                        Toast.makeText(EditorActivity.this, "Female is Selected", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        gender=0;
                        Toast.makeText(EditorActivity.this, "Nothing is selected", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                gender = 0;
                Toast.makeText(EditorActivity.this, "Nothing is selected", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // R.menu.mymenu is a reference to an xml file named mymenu.xml which should be inside your res/menu directory.
        // If you don't have res/menu, just create a directory named "menu" inside res
        getMenuInflater().inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

}