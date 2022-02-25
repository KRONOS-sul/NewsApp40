package kg.geektech.newsapp40.ui.board;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import kg.geektech.newsapp40.R;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {
    private String[] titles = new String[]{"Salam", "HI", "GG"};
    private Integer[] imageOnBoarding = new Integer[]{R.drawable.pngwing_com_1_, R.drawable.pngwing_com_2_, R.drawable.pngwing_com_3_};
    private String[] description = new String[]{"This is the first page, \n in my On Boarding",
            "Wow, it's actually the second page", " THE END"};

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).
                inflate(R.layout.item_board, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return titles.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView textTitlesHolder;
        private ImageView imageOnBoardingHolder;
        private TextView descriptionHolder;


        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            imageOnBoardingHolder = itemView.findViewById(R.id.image_view);
            textTitlesHolder = itemView.findViewById(R.id.text_title);
            descriptionHolder = itemView.findViewById(R.id.text_desc);
        }

        public void bind(int position) {
            textTitlesHolder.setText(titles[position]);
            descriptionHolder.setText(description[position]);
            imageOnBoardingHolder.setImageResource(imageOnBoarding[position]);
        }
    }
}
