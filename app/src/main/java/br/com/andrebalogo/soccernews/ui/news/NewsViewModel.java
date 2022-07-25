package br.com.andrebalogo.soccernews.ui.news;

import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.List;

import br.com.andrebalogo.soccernews.data.SoccerNewsRepository;
import br.com.andrebalogo.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class NewsViewModel extends ViewModel {

    public enum State {
        DOING, DONE, ERROR;
    }

    private final MutableLiveData<List<News>> news = new MutableLiveData<>();
    private final MutableLiveData<State> state = new MutableLiveData<State>();
    //private final SoccerNewsApi api;

    public NewsViewModel() {
        this.findNews();
    }

    public void findNews() {
        //Status DOING, trazendo as informações e processando.
        state.setValue(State.DOING);
        SoccerNewsRepository.getInstance().getRemoteApi().getNews().enqueue(new Callback<List<News>>() {
            @Override
            public void onResponse(@NonNull Call<List<News>> call, @NonNull Response<List<News>> response) {
                if (response.isSuccessful()) {
                    news.setValue(response.body());
                    //Checagem de OK!
                    state.setValue(State.DONE);
                } else {
                    //DEU RUIM!
                    state.setValue(State.ERROR);
                }
            }

            @Override
            public void onFailure(@NonNull Call<List<News>> call, Throwable error) {
                //FIXME Tirar o printStackTrace quando formos para produção!
                //Gerenciar Crashes via Crashlytics
                error.printStackTrace();
                state.setValue(State.ERROR);
            }
        });
    }

    //public LiveData<Void> saveNews(News news) {
    public void saveNews(News news) {
        AsyncTask.execute(() -> SoccerNewsRepository.getInstance().getLocalDb().newsDao().save(news);
    }

    public LiveData<List<News>> getNews() {
        return this.news;
    }

    public MutableLiveData<State> getState() {
        return this.state;
    }
}