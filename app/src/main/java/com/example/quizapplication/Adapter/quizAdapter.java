package com.example.quizapplication.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.quizapplication.Modals.Model_Class;
import com.example.quizapplication.R;
import com.example.quizapplication.quiz_dashboard;

import java.util.List;

public class quizAdapter extends RecyclerView.Adapter<quizAdapter.ProgramViewHolder> {

    private List<Model_Class> itemList;
    private  View itemview;
    private Context context;

    public  quizAdapter(List<Model_Class>itemList){
        this.itemList = itemList;
    }
    @Override
    public ProgramViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        context = parent.getContext();
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
        private static final String TAG = "Check";
        ImageView imgicon;
        TextView txtTitle;
        public ProgramViewHolder(@NonNull View itemView) {
            super(itemView);
            itemview = itemView;
            imgicon = (ImageView) itemView.findViewById(R.id.quiz_icon);
            txtTitle = (TextView) itemView.findViewById(R.id.quiz_title);

        }

        public void setData(int quiz_image1, String quiz_title1) {
            imgicon.setImageResource(quiz_image1);
            txtTitle.setText(quiz_title1);
            itemview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, quiz_dashboard.class);
                    intent.putExtra("qu",quiz_title1);
                    context.startActivity(intent);                }
            });
        }
    }
}
