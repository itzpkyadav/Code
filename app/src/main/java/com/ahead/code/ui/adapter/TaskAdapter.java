package com.ahead.code.ui.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ahead.code.R;
import com.ahead.code.data.network.model.Task;

import java.util.ArrayList;
import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> list = new ArrayList<>();

    public void addTask(Task user) {
        list.add(user);
    }

    public void setList(List<Task> list) {
        this.list.removeAll(this.list);
        this.list.addAll(list);
    }

    public List<Task> getList() {
        return list;
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    @Override
    public int getItemViewType(int position) {
        return super.getItemViewType(position);
    }

    @NonNull
    @Override
    public TaskHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_task, parent, false);
        return new TaskHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskHolder holder, int position) {
        holder.editTime.setText(
                String.format("%s - %s",
                        list.get(position).getStart_time(),
                        list.get(position).getEnd_time())
        );
        holder.editDescription.setText(list.get(position).getDescription());
    }

    class TaskHolder extends RecyclerView.ViewHolder {

        private TextView editTime, editDescription;

        TaskHolder(@NonNull View itemView) {
            super(itemView);

            editTime = itemView.findViewById(R.id.editTime);
            editDescription = itemView.findViewById(R.id.editDescription);
        }
    }
}
