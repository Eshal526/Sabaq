package com.example.sabaq;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;


import java.util.ArrayList;
import java.util.List;

public class MainActivity5 extends AppCompatActivity {

    private ListView listView;
    private EditText searchEditText;
    private ArrayAdapter<Student> arrayAdapter;
    Student student;
    private List<Student> studentList;
    private DbHelper dbHelper;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        listView = findViewById(R.id.list2);
        searchEditText = findViewById(R.id.searchEditText);
        dbHelper = new DbHelper(this);
        studentList = new ArrayList<>();

        // Set up the ArrayAdapter with the initial student list
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, studentList);
        listView.setAdapter(arrayAdapter);

        // Set up the text watcher for search functionality
        searchEditText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                // Not needed for this implementation
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                // Perform the search whenever the text changes
                searchStudents(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
                // Not needed for this implementation
            }
        });
    }

    private void searchStudents(String searchQuery) {
        studentList.clear();
        studentList.addAll(dbHelper.searchStudents(searchQuery));
        arrayAdapter.notifyDataSetChanged();
    }
}