package com.rathana.mvp.ui.main;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.rathana.mvp.R;
import com.rathana.mvp.model.Article;
import com.rathana.mvp.ui.main.mvp.MainMVP;
import com.rathana.mvp.ui.main.mvp.MainPresenter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainMVP.View {

    private RecyclerView rvArticle;
    private List<Article> articles=new ArrayList<>();
    private ArticleAdapter articleAdapter;
    private ProgressBar progressBar;

    private MainMVP.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter=new MainPresenter();
        presenter.setView(this);
        initUI();
        presenter.getArticles(1,50);
    }

    private void initUI(){
        rvArticle=findViewById(R.id.rvArticle);
        progressBar=findViewById(R.id.progressBar);
        articleAdapter=new ArticleAdapter(articles,this);
        rvArticle.setLayoutManager(new LinearLayoutManager(this));
        rvArticle.setAdapter(articleAdapter);

    }
    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void onSuccess(List<Article> articles) {
        //set data to adapter
        articleAdapter.addMoreItems(articles);

    }

    @Override
    public void onError(String msg) {
        Log.e(TAG, "onError: "+msg );
    }

    static final String TAG= MainActivity.class.getSimpleName();

    @Override
    protected void onDestroy() {
        presenter.onDestroy();

        super.onDestroy();
    }
}
