package com.sprint.gina.sqlitefuns1;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

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

    ContactOpenHelper helper;

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

        helper = new ContactOpenHelper(this);
        Contact contact = new Contact("Spike the Bulldog",
                "509-509-5095");
        helper.insertContact(contact);
        List<Contact> contacts = helper.getSelectAllContacts();
        Log.d(TAG, "onCreate: " + contacts);
        Contact updatedContact = new Contact(1, "SPIKE",
                "208-208-2085", -1);
        helper.updateContactById(updatedContact);
        // TODO: fix this!! after you start deleting records
        // you can't insert again and expect the adapter to
        // work when we call position + 1 (BIG BUG)
        helper.deleteAllContacts();
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
            Contact contact = helper.getSelectContactById(position + 1); // BIG BUG!!!!!!
            // TODO: for PA7 fix this bug where the positions to ids
            // no longer map by a simple +1 because of deletions
            holder.updateView(contact);
        }

        @Override
        public int getItemCount() {
            // TODO: get number of Contacts
            return helper.getSelectAllContacts().size(); // not very efficient
        }
    }
}