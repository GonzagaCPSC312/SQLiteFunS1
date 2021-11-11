package com.sprint.gina.sqlitefuns1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

// a database is like an excel workbook
// a database table is like a workbook sheet
// the table has rows and columns (just like an excel spreadsheet)
// rows are uniquely identified by ids
// columns are uniquely identfied by fields (name + type)

// we want a contactsDatabase with a single table, contactsTable
// contactsTable has 4 fields (_id, name, phoneNumber, imageResourceId)
// we will insert a new row into the contactsTable for each contact

// 2 classes to know
// 1. SQLiteOpenHelper: we will subclass this class and add some SQL methods
// CRUD: create, read, update, delete
// 2. SQLiteDatabase: a reference to the database (read or write)

public class MainActivity extends AppCompatActivity {
    static final String TAG = "MainActivityTag";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // setContentView(R.layout.activity_main);

        // set up GUI programmatically
        RecyclerView recyclerView = new RecyclerView(this);
        recyclerView.setBackgroundColor(getResources().getColor(R.color.purple_200));
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        CustomAdapter adapter = new CustomAdapter();
        recyclerView.setAdapter(adapter);
        setContentView(recyclerView);
    }

    class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.CustomViewHolder> {
        class CustomViewHolder extends RecyclerView.ViewHolder {
            TextView text1;

            public CustomViewHolder(@NonNull View itemView) {
                super(itemView);
                text1 = itemView.findViewById(android.R.id.text1);
            }

            public void updateView(Contact c) {
                text1.setText(c.toString());
            }
        }

        @NonNull
        @Override
        public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
           View view = LayoutInflater.from(MainActivity.this)
                    .inflate(android.R.layout.simple_list_item_1, parent, false);
            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull CustomViewHolder holder, int position) {
            // TODO: get the Contact at position
            // holder.updateView(contact);
        }

        @Override
        public int getItemCount() {
            // TODO: get number of Contacts
            return 0;
        }
    }
}