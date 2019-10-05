package com.rathana.mvp.ui.main.mvp;

import com.rathana.mvp.model.Article;

import java.util.List;

public class MainPresenter implements MainMVP.Presenter {

    private MainMVP.View view;
    private MainMVP.Interactor interactor;

    public MainPresenter() {
        interactor = new  MainInteractor();
    }

    @Override
    public void getArticles(int page, int limit) {
        if(view!=null)
            view.showLoading();
        interactor.getArticles(
                page,
                limit,
                new MainMVP.Interactor.InteractorResponse() {
                    @Override
                    public void onSuccess(List<Article> articles) {
                        if (view!=null){
                            view.hideLoading();
                            view.onSuccess(articles);
                        }
                    }
                    @Override
                    public void onError(String msg) {
                        if (view!=null){
                            view.hideLoading();
                            view.onError(msg);
                        }
                    }
                }
        );
    }

    @Override
    public void setView(MainMVP.View view) {
        this.view=view;
    }

    @Override
    public void onDestroy() {
        interactor.onDestroy();
    }
}
