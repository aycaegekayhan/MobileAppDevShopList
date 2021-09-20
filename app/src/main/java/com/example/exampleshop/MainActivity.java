package com.example.exampleshop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<String> items;
    ArrayAdapter<String> adapter;

    EditText input;
    ImageView enter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listView = findViewById(R.id.listview);
        input = findViewById(R.id.input);
        enter = findViewById(R.id.add);

        items = new ArrayList<>();
        items.add("Apple");
        items.add("Orange");
        items.add("Banana");
        items.add("Peach");
        items.add("Kiwi");
        items.add("Pear");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //String name = items.get(i);
                //makeToast(name);
                makeToast("Removed " + items.get(i));
                removeItem(i);
            }
        });

        //listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            //@Override
            //public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                //makeToast("Removed " + items.get(i));
                //removeItem(i);
                //return false;
            //}
        //});

        adapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, items);
        listView.setAdapter(adapter);

        enter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String text = input.getText().toString();
                if(text == null || text.length() == 0){
                    makeToast("Not a valid product");
                }else{
                    addItem(text);
                    input.setText("");
                    makeToast("Your product is added " + text);
                }
            }
        });

    }

    public void removeItem(int remove){
        items.remove(remove);
        adapter.notifyDataSetChanged();
    }

    public void addItem(String item) {
        items.add(item);
        listView.setAdapter(adapter);
    }

    Toast t;
    private void makeToast(String s) {
        if (t != null) t.cancel();
        t = Toast.makeText(getApplicationContext(), s, Toast.LENGTH_SHORT);
        t.show();
    }
}