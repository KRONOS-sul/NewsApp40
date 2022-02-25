package kg.geektech.newsapp40.ui.news;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.ItemNewsBinding;
import kg.geektech.newsapp40.models.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.MyViewHolder> {
    private ItemNewsBinding binding;
    private ArrayList<News> list;

    public NewsAdapter(){
        list = new ArrayList<>();
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ItemNewsBinding.inflate
                (LayoutInflater.from(parent.getContext()), parent, false);
        return new MyViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(list.get(position));
    }

    @Override
    public int getItemCount() {
        return list.size()-1;
    }

    public void addItem(News news) {
        list.add(0, news);
        notifyItemInserted(0);
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        ItemNewsBinding binding;

        public MyViewHolder(@NonNull ItemNewsBinding itemView) {
            super(itemView.getRoot());
            binding = itemView;
        }

        public void bind(News news) {
            Date currentTime = Calendar.getInstance().getTime();
            String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
            binding.tv1.setText(news.getTitle());
            binding.data.setText((CharSequence) currentTime);
            if (getAdapterPosition() %2 == 1){
                itemView.setBackgroundResource(R.color.red);
            }else{
                itemView.setBackgroundResource(R.color.purple_200);
            }
        }
    }
}
