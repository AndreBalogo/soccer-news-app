package br.com.andrebalogo.soccernews.data.remote;

import java.util.List;

import br.com.andrebalogo.soccernews.domain.News;
import retrofit2.Call;
import retrofit2.http.GET;


public interface SoccerNewsApi {

    //abstrai a chamada ao json (API do GitHub pages)
    @GET("news.json")
    Call<List<News>> getNews();

}
