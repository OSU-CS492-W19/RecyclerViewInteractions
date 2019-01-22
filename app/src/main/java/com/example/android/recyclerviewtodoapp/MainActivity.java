package com.example.android.recyclerviewtodoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mTodoListRV;
    private EditText mTodoEntryET;
    private TodoAdapter mTodoAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoListRV = findViewById(R.id.rv_todo_list);
        mTodoEntryET = findViewById(R.id.et_todo_entry);

        mTodoListRV.setLayoutManager(new LinearLayoutManager(this));
        mTodoListRV.setHasFixedSize(true);

        mTodoAdapter = new TodoAdapter();
        mTodoListRV.setAdapter(mTodoAdapter);

        Button addTodoButton = findViewById(R.id.btn_add_todo);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoText = mTodoEntryET.getText().toString();
                if (!TextUtils.isEmpty(todoText)) {
                    mTodoAdapter.addTodo(todoText);
                    mTodoEntryET.setText("");
                }
            }
        });
    }
}
