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
        View layout = v;
        if(layout == null) {
            layout = LayoutInflater.from(mContext).inflate(resource,parent,false);

            CheckBox isCompletedCheckBox = layout.findViewById(R.id.todoCheckBo);
            Button deleteButton = layout.findViewById(R.id.detete_button);

            Todo todo = getItem(position);

            isCompletedCheckBox.setText(todo.getTitle());
            isCompletedCheckBox.setChecked(todo.isCompleted());

            layout.setBackgroundColor(
                    mContext.getResources().getColor(R.color.information)
            );

            deleteButton.setTag(todo);
            deleteButton.setOnClickListener(deleteTodo);

            isCompletedCheckBox.setTag(todo);
            isCompletedCheckBox.setOnClickListener(completedTodo);
        } else {
            Todo todo = (Todo) layout.findViewById(R.id.detete_button).getTag();
            layout.findViewById(R.id.todoCheckBo).setTag(todo);
            layout.findViewById(R.id.detete_button).setTag(todo);
        }

        return layout;
    }

    private View.OnClickListener completedTodo = v -> {
        CheckBox checkBox = (CheckBox) v;
        Todo todo = (Todo) v.getTag();
        todo.setCompleted(checkBox.isChecked());
        mServices.updateTodo(todo);
        notifyDataSetChanged(); // notify the adapter that the data has changed
    };

    private View.OnClickListener deleteTodo = v -> {
        Button button = (Button) v;
        Todo todo = (Todo) v.getTag();
        mServices.deleteTodo(todo);
        remove(todo); // remove the todo from the adapter's list
        notifyDataSetChanged(); // notify the adapter that the data has changed
    };
}

