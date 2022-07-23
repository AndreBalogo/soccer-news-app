package br.com.andrebalogo.soccernews.ui.news;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

import br.com.andrebalogo.soccernews.domain.News;

public class NewsViewModel extends ViewModel {

    private final  MutableLiveData<List<News>> news;

    public NewsViewModel() {
        this.news = new MutableLiveData<>();

        //TODO Remover mock de notícias depois remover.
        List<News> news = new ArrayList<>();
        news.add(new News("Ferroviária tem desfalque importante.","O departamento médico da equipe da Ferroviária alerta sobre duas jogadoras com problemas musculares sofridos no treino da última semana."));
        news.add(new News("Palmeiras contrata Marta como conselheira.","A intenção da presidente Leila Pereira é dominar o futebol feminino no Brasil e na América, Leila diz que está ansiosa para novas contratações."));
        news.add(new News("Copa do Mundo feminina tem recorde de público.","Após diversas campanhas pró evolução do futebol feminino, houve reflexo de público nas arenas que aumentou cerca de 380% em relação as edições anteriores."));

        this.news.setValue(news);

    }

    public LiveData<List<News>> getNews() {
        return news;
    }
}