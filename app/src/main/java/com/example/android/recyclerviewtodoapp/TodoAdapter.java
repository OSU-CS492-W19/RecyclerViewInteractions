package com.example.android.recyclerviewtodoapp;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.ArrayList;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {
    private ArrayList<String> mTodoList;
    private OnTodoCheckedChangeListener mCheckedChangeListener;

    public interface OnTodoCheckedChangeListener {
        void onTodoCheckedChanged(String todo, boolean b);
    }

    public TodoAdapter(OnTodoCheckedChangeListener checkedChangeListener) {
        mTodoList = new ArrayList<>();
        mCheckedChangeListener = checkedChangeListener;
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
        String todo = mTodoList.get(adapterPositionToArrayIndex(i));
        todoViewHolder.bind(todo);
    }

    public int adapterPositionToArrayIndex(int i) {
        return mTodoList.size() - i - 1;
    }

    class TodoViewHolder extends RecyclerView.ViewHolder {
        private TextView mTodoTV;

        public TodoViewHolder(final View itemView) {
            super(itemView);
            mTodoTV = itemView.findViewById(R.id.tv_todo_text);

            CheckBox checkBox = itemView.findViewById(R.id.todo_checkbox);
            checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                    String todo = mTodoList.get(adapterPositionToArrayIndex(getAdapterPosition()));
                    mCheckedChangeListener.onTodoCheckedChanged(todo, b);
                }
            });
        }

        public void bind(String todo) {
            mTodoTV.setText(todo);
        }

        public void removeFromList() {
            int position = getAdapterPosition();
            mTodoList.remove(adapterPositionToArrayIndex(position));
            notifyItemRemoved(position);
        }
    }
}
