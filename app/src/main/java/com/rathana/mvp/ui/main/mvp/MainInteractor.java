package com.rathana.mvp.ui.main.mvp;

import android.util.Log;

import com.rathana.mvp.data.ApiService;
import com.rathana.mvp.data.ServiceGenerator;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

public class MainInteractor implements MainMVP.Interactor {

    //network layer
    private ApiService apiService;
    private CompositeDisposable disposable=new CompositeDisposable();
    final static  String TAG=MainInteractor.class.getSimpleName();
    public MainInteractor() {
        apiService= ServiceGenerator.createService(ApiService.class);
    }

    @Override
    public void getArticles(int page, int limit, InteractorResponse response) {
        disposable.add(
                apiService.getArticles(page,limit)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        data->{
                            response.onSuccess(data.getArticle());
                        },
                        error->{
                            response.onError(error.toString());
                        },
                        ()->{
                            Log.e(TAG, "getArticles: completed" );
                        }
                )
        );

    }

    @Override
    public void onDestroy() {
        disposable.clear();
    }
}
