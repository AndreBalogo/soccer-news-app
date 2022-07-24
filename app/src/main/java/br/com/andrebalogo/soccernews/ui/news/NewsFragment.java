package br.com.andrebalogo.soccernews.ui.news;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.loader.content.AsyncTaskLoader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.room.Room;

import br.com.andrebalogo.soccernews.databinding.FragmentNewsBinding;
import br.com.andrebalogo.soccernews.ui.adapters.NewsAdapter;

public class NewsFragment extends Fragment {

    private NewsViewModel newsViewModel;
    private FragmentNewsBinding binding;
    private AppDatabase db;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        binding = FragmentNewsBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        db = Room.databaseBuilder(getContext(), AppDatabase.class, "database-name")
                .allowMainThreadQueries()
                .build();
        //Room.DatabaseBuilder(getContext(), AppDatabase.class, "database-name").build();

        binding.rvNews.setLayoutManager(new LinearLayoutManager(getContext()));
        newsViewModel.getNews().observe(getViewLifecycleOwner(), news -> {
            //Onde recebe a lista de notícias
            binding.rvNews.setAdapter(new NewsAdapter(news, updatedNews -> {
                db.NewsDao().insert(updatedNews);
                Log.d("TAG FAVORITE:", " clicou");
            }));
        });
        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}