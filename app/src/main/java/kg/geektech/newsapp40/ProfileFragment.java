package kg.geektech.newsapp40;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;

import kg.geektech.newsapp40.databinding.FragmentProfileBinding;

public class ProfileFragment extends Fragment {
    private Prefs prefs;
    private FragmentProfileBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        prefs = new Prefs(requireActivity());

        Glide.with(binding.avatar).load(prefs.isAvatar()).into(binding.avatar); //вызываю имейджку

        binding.avatarName.setText(prefs.isAvatarName());

        setImage();

        binding.avatarName.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void afterTextChanged(Editable editable) {
                prefs.saveAvatarName(binding.avatarName.getText().toString());
            }
        });
    }

    private void setImage() {
        ActivityResultLauncher<String> activityResultLauncher = registerForActivityResult(new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        Glide.with(binding.avatar).load(result).into(binding.avatar); // сохраняю имейджку
                        prefs.saveAvatar(result);
                    }
                });
        binding.avatar.setOnClickListener(view -> {
            activityResultLauncher.launch("image/*"); //означает вызываеваемый тип данных (image,video,document)
        });
    }
}