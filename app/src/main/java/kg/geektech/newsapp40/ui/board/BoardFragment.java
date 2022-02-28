package kg.geektech.newsapp40.ui.board;

import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tbuonomo.viewpagerdotsindicator.DotsIndicator;

import kg.geektech.newsapp40.Prefs;
import kg.geektech.newsapp40.R;

public class BoardFragment extends Fragment {
    private Button skip;
    private Button next;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_board, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        skip = view.findViewById(R.id.skip_btn);
        next = view.findViewById(R.id.next_btn);

        DotsIndicator dotsIndicator = view.findViewById(R.id.dots_indicator);
        ViewPager2 viewPager2 = view.findViewById(R.id.view_pager);
        BoardAdapter adapter = new BoardAdapter();
        viewPager2.setAdapter(adapter);
        dotsIndicator.setViewPager2(viewPager2);
        viewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);

                if (position == 2) {
                    skip.setVisibility(View.GONE);
                    next.setVisibility(View.VISIBLE);
                } else {
                    skip.setVisibility(View.VISIBLE);
                    next.setVisibility(View.GONE);
                }
            }
        });
        skip.setOnClickListener(view1 -> {
            Prefs prefs = new Prefs(requireActivity());
            prefs.saveBoardState();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigateUp();
        });
        next.setOnClickListener(view12 -> {
            Prefs prefs = new Prefs(requireActivity());
            prefs.saveBoardState();
            NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_activity_main);
            navController.navigateUp();
        });

        requireActivity().getOnBackPressedDispatcher().addCallback(getViewLifecycleOwner(), new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                requireActivity().finish();
            }
        });
    }
}