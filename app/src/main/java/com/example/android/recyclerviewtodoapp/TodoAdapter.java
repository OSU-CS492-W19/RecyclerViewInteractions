package com.example.android.recyclerviewtodoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<String> mTodoList;

    public TodoAdapter() {
        mTodoList = new ArrayList<>();
    }

    public void addTodo(String todo) {
        mTodoList.add(todo);
        notifyItemInserted(0);
    }

    @Override
    public int getItemCount() {
        return mTodoList.size();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View itemView = inflater.inflate(R.layout.todo_list_item, viewGroup, false);
        return new TodoViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder todoViewHolder, int i) {
        String todo = mTodoList.get(mTodoList.size() - i - 1);
        todoViewHolder.bind(todo);
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView mTodoTV;

        public TodoViewHolder(View itemView) {
            super(itemView);
            mTodoTV = itemView.findViewById(R.id.tv_todo_text);
        }

        public void bind(String todo) {
            mTodoTV.setText(todo);
        }
    }
}
