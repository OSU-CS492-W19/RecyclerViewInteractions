package com.example.android.recyclerviewtodoapp;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements TodoAdapter.OnTodoCheckedChangeListener {

    private RecyclerView mTodoListRV;
    private EditText mTodoEntryET;
    private TodoAdapter mTodoAdapter;
    private Toast mToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTodoListRV = findViewById(R.id.rv_todo_list);
        mTodoEntryET = findViewById(R.id.et_todo_entry);

        mTodoListRV.setLayoutManager(new LinearLayoutManager(this));
        mTodoListRV.setHasFixedSize(true);

        mTodoAdapter = new TodoAdapter(this);
        mTodoListRV.setAdapter(mTodoAdapter);

        mTodoListRV.setItemAnimator(new DefaultItemAnimator());

        mToast = null;

        Button addTodoButton = findViewById(R.id.btn_add_todo);
        addTodoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String todoText = mTodoEntryET.getText().toString();
                if (!TextUtils.isEmpty(todoText)) {
                    mTodoListRV.scrollToPosition(0);
                    mTodoAdapter.addTodo(todoText);
                    mTodoEntryET.setText("");
                }
            }
        });

        ItemTouchHelper.SimpleCallback itemTouchCallback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder viewHolder1) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {
                ((TodoAdapter.TodoViewHolder)viewHolder).removeFromList();
            }
        };

        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(itemTouchCallback);
        itemTouchHelper.attachToRecyclerView(mTodoListRV);
    }

    @Override
    public void onTodoCheckedChanged(String todo, boolean b) {
        if (mToast != null) {
            mToast.cancel();
        }
        String completedState = b ? "COMPLETED" : "MARKED INCOMPLETE";
        String toastText = completedState + ": " + todo;
        mToast = Toast.makeText(this, toastText, Toast.LENGTH_LONG);
        mToast.show();
    }
}
