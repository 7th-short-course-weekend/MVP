package com.rathana.mvp.ui.main;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.rathana.mvp.R;
import com.rathana.mvp.model.Article;
import com.rathana.mvp.model.Author;
import com.rathana.mvp.model.Category;
import com.rathana.mvp.utils.DateFormatter;

import java.util.List;

public class ArticleAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Article> articles;
    private Context context;

    final static String TAG=ArticleAdapter.class.getSimpleName();
    public ArticleAdapter(List<Article> articles, Context context) {
        this.articles = articles;
        this.context = context;
    }
    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        if (viewHolder instanceof ViewHolder) {
            Article article = articles.get(i);
            Category category = article.getCategory();
            Author author = article.getAuthor();
            ((ViewHolder) viewHolder).binding(article, category, author);
        }

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int itemViewType) {
        View view = LayoutInflater.from(context)
                    .inflate(R.layout.article_item_layout, viewGroup, false);
        return new ViewHolder(view);

    }

    public void addMoreItems(List<Article> articles) {
        int previousSize = this.articles.size();
        this.articles.addAll(articles);
        notifyItemRangeInserted(previousSize, articles.size());
        Log.e(TAG, "addMoreItems: "+getItemCount() );
    }

    public void remove(Article article, int pos) {
        this.articles.remove(article);
        notifyItemRemoved(pos);
    }

    public void update(Article article, int pos) {
        this.articles.set(pos, article);
        notifyItemChanged(pos);
    }

    public void addItem(Article article) {
        this.articles.add(0, article);
        notifyItemInserted(0);
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        TextView title, date, author, category;
        ImageView thumb, btnFavorite, btnDel, btnEdit;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            date = itemView.findViewById(R.id.date);
            author = itemView.findViewById(R.id.author);
            category = itemView.findViewById(R.id.category);
            thumb = itemView.findViewById(R.id.thumb);
            btnFavorite = itemView.findViewById(R.id.favorite);

            btnDel = itemView.findViewById(R.id.btnDel);
            btnEdit = itemView.findViewById(R.id.btnedit);
        }

        public void binding(Article article, Category cat, Author user) {
            title.setText(article.getTitle() != null ? article.getTitle() : "");
            author.setText(user.getName() != null ? user.getName() : "");
            date.setText(DateFormatter.format(article.getCreatedDate()));
            category.setText(cat.getName() != null ? cat.getName() : "");
            //image
            //todo bind image to image View
            if (article.getImage() != null) {
                Glide.with(context)
                        .load(article.getImage())
                        .override(250, 160)
                        .into(thumb);
            } else {
                thumb.setImageResource(R.mipmap.ic_launcher);
            }

            //setEvent
            btnDel.setOnClickListener(v -> {
                if (callback != null)
                    callback.onDelete(article, getAdapterPosition());
            });

            btnEdit.setOnClickListener(v -> {
                callback.onEdit(article, getAdapterPosition());
            });

        }
    }

    class LoadingViewHolder extends RecyclerView.ViewHolder {
        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    private ArticleCallback callback;

    public void setCallback(ArticleCallback callback) {
        this.callback = callback;
    }

    public interface ArticleCallback {
        void onDelete(Article article, int pos);

        void onEdit(Article article, int pos);
    }
}
