package com.example.quizapplication;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ProgrammingAdapter extends RecyclerView.Adapter<ProgrammingAdapter.ProgramViewHolder> {

    private List<Model_Class> itemList;


    public  ProgrammingAdapter(List<Model_Class>itemList){
            this.itemList = itemList;
    }
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.item_list_layout,parent,false);
        return new ProgramViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProgramViewHolder holder, int position) {
        int quiz_image1 = itemList.get(position).getQuiz_icon();
        String quiz_title1 = itemList.get(position).getQuiz_title();
        holder.setData(quiz_image1,quiz_title1);
    }

    @Override
    public int getItemCount() {
        return itemList.size();
    }

    public class ProgramViewHolder extends RecyclerView.ViewHolder {
            ImageView imgicon;
            TextView txtTitle;
            public ProgramViewHolder(@NonNull View itemView) {
                super(itemView);
                imgicon = (ImageView) itemView.findViewById(R.id.quiz_icon);
                txtTitle = (TextView) itemView.findViewById(R.id.quiz_title);

            }

        public void setData(int quiz_image1, String quiz_title1) {
                imgicon.setImageResource(quiz_image1);
                txtTitle.setText(quiz_title1);
        }
    }
}
