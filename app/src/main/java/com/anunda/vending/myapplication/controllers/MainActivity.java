package com.anunda.vending.myapplication.controllers;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.anunda.vending.myapplication.R;
import com.anunda.vending.myapplication.adapter.TestAdapter;
import com.anunda.vending.myapplication.model.ProductModel;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends AppCompatActivity {

    RecyclerView testRecyclerview;

    private TestAdapter adapter;
    private static final String TAG = "test_project_bas";
    List<ProductModel> productModels = new ArrayList<>();
    ProductModel productModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        testRecyclerview = findViewById(R.id.testRecyclerview);
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("products");
        readDataFromFirebase(myRef);
    }

    private void readDataFromFirebase(DatabaseReference myRef) {
        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    productModel = new ProductModel();
                    productModel.setColumn(ds.child("column").getValue(String.class));
                    productModel.setDescription(ds.child("description").getValue(String.class));
                    productModel.setName(ds.child("name").getValue(String.class));
                    productModel.setPicture(ds.child("picture").getValue(String.class));
                    productModel.setPrice(ds.child("price").getValue(String.class));
                    productModel.setProductX(ds.child("productX").getValue(String.class));
                    productModel.setProductY(ds.child("productY").getValue(String.class));
                    productModel.setRow(ds.child("row").getValue(String.class));
                    productModel.setShelf(ds.child("shelf").getValue(String.class));
                    productModel.setType(ds.child("shelf").getValue(String.class));
                    productModel.setWeight(ds.child("weigth").getValue(String.class));
                    productModels.add(productModel);
                }
                LinearLayoutManager manager = new LinearLayoutManager(getApplicationContext());
                testRecyclerview.setLayoutManager(manager);
                adapter = new TestAdapter(getApplicationContext(),productModels);
                testRecyclerview.setAdapter(adapter);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }
}
