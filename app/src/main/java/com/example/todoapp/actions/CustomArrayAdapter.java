package com.example.todoapp.actions;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.todoapp.MyContext;
import com.example.todoapp.R;
import com.example.todoapp.business.Services;
import com.example.todoapp.model.Todo;

import java.util.List;

public class CustomArrayAdapter extends ArrayAdapter<Todo> {
    private Context mContext;
    private CheckBox mIsCompletedCheckBox;
    private Button mDeleteButton;
    private Todo mTodo;
    private Services mServices;
    int resource;
    public CustomArrayAdapter(@NonNull Context context, int resource, @NonNull List<Todo> objects) {
        super(context, resource, objects);
        this.resource=resource;
        mServices = ((MyContext) context.getApplicationContext()).getServices();
        mContext = getContext();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View v, @NonNull ViewGroup parent) {
        View view = v;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(resource, parent, false);

            CheckBox isCompletedCheckBox = view.findViewById(R.id.todoCheckBo);
            Button deleteButton = view.findViewById(R.id.detete_button);

            isCompletedCheckBox.setTag(isCompletedCheckBox);
            isCompletedCheckBox.setOnClickListener(completedTodo);

            deleteButton.setTag(deleteButton);
            deleteButton.setOnClickListener(deleteTodo);
        }

        Todo todo = getItem(position);

        CheckBox isCompletedCheckBox = view.findViewById(R.id.todoCheckBo);
        Button deleteButton = view.findViewById(R.id.detete_button);

        isCompletedCheckBox.setText(todo.getTitle());
        isCompletedCheckBox.setChecked(todo.isCompleted());

        view.setBackgroundColor(mContext.getResources().getColor(R.color.information));

        isCompletedCheckBox.setTag(todo);
        deleteButton.setTag(todo);

        return view;
    }

    private View.OnClickListener completedTodo = v -> {
        CheckBox checkBox = (CheckBox) v;
        Todo todo_ = (Todo) v.getTag();
        todo_.setCompleted(checkBox.isChecked());
        mServices.updateTodo(todo_);
        notifyDataSetChanged(); // notify the adapter that the data has changed
    };

    private View.OnClickListener deleteTodo = v -> {
        Button button = (Button) v;
        Todo todo_ = (Todo) v.getTag();
        System.out.println(todo_);
        mServices.deleteTodo(todo_);
        remove(todo_); // remove the todo from the adapter's list
        notifyDataSetChanged(); // notify the adapter that the data has changed
    };
}

