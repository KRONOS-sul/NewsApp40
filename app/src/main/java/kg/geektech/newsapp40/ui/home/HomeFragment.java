package kg.geektech.newsapp40.ui.home;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.DateFormat;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;

import kg.geektech.newsapp40.R;
import kg.geektech.newsapp40.databinding.FragmentHomeBinding;
import kg.geektech.newsapp40.models.News;
import kg.geektech.newsapp40.ui.news.NewsAdapter;

public class HomeFragment extends Fragment {

    private NewsAdapter adapter;
    private FragmentHomeBinding binding;
    private Object NewsAdapter;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        adapter = new NewsAdapter();
    }

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        binding.recycler.setAdapter(adapter);

        Date currentTime = Calendar.getInstance().getTime();// Здесь получаю время
        String formattedDate = DateFormat.getDateInstance(DateFormat.FULL).format(currentTime);
        Log.e("ggggg", "GG: " + formattedDate);
        

        binding.fab.setOnClickListener(view1 -> {
            openFragment();
        });
        binding.recycler.setAdapter((RecyclerView.Adapter) NewsAdapter);
        getParentFragmentManager().setFragmentResultListener("rk_news",
                getViewLifecycleOwner(), new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                News news = (News) result.getSerializable("news");
                adapter.addItem(news);
                Log.e("ggggg", "GG: " + news.getTitle());
            }
        });
    }

    private void openFragment() {
        NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
        navController.navigate(R.id.newsFragment);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}