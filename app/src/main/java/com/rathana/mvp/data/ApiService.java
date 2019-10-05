package com.rathana.mvp.data;

import com.rathana.mvp.model.ArticleResponse;

import io.reactivex.Flowable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("/v1/api/articles")
    Flowable<ArticleResponse> getArticles(
            @Query("page") int page,
            @Query("limit") int limit);

}
