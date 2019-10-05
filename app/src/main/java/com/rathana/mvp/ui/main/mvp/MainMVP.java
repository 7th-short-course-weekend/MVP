package com.rathana.mvp.ui.main.mvp;

import com.rathana.mvp.model.Article;

import java.util.List;

public interface MainMVP {

    interface View{
        void showLoading();
        void hideLoading();
        void onSuccess(List<Article> articles);
        void onError(String msg);

    }
    interface Presenter{

        void setView(View view);
        void getArticles(int page,int limit);
        void onDestroy();
    }
    interface Interactor{

        void getArticles(int page,int limit,InteractorResponse response);
        void onDestroy();
        interface InteractorResponse{
            void onSuccess(List<Article> articles);
            void onError(String msg);
        }
    }

}
