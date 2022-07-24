package br.com.andrebalogo.soccernews.ui.adapters;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

import br.com.andrebalogo.soccernews.databinding.NewsItemBinding;
import br.com.andrebalogo.soccernews.domain.News;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder> {

    //Lista de notícias
    private List<News> news;


    //Recebido pelo construtor pega lista de notícias
    public NewsAdapter(List<News> news) {
        this.news = news;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //capturou layout inflater
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        //recuperou por ele o binding do news
        NewsItemBinding binding = NewsItemBinding.inflate(layoutInflater, parent, false);
        // retornou o binding para pegar instância
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        News news = this.news.get(position);
        holder.binding.tvTitle.setText(news.getTitle());
        holder.binding.tvDescription.setText(news.getDescription());
        Picasso.get().load(news.getImage())
                .fit()
                .into(holder.binding.ivThumbnail);
        holder.binding.btOpenLink.setOnClickListener( view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.getLink()));
            holder.itemView.getContext().startActivity(i);
        });
    }

    @Override
    public int getItemCount() {

        return this.news.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

         private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

}
