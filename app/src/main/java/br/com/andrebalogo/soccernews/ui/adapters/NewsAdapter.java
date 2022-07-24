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
    private final List<News> news;
    private final favoriteListener favoriteListener;


    //Recebido pelo construtor pega lista de notícias
    public NewsAdapter(List<News> news, favoriteListener favoriteListener) {
        this.news = news;
        this.favoriteListener = favoriteListener;
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
        holder.binding.tvTitle.setText(news.title);
        holder.binding.tvDescription.setText(news.description);
        Picasso.get().load(news.image)
                .fit()
                .into(holder.binding.ivThumbnail);
        //funcionalidade de abrir link.
        holder.binding.btOpenLink.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(news.link));
            holder.itemView.getContext().startActivity(i);
        });
        //funcionalidade de compartilhar
        holder.binding.ivShare.setOnClickListener(view -> {
            Intent i = new Intent(Intent.ACTION_SEND);
            i.setType("Text/plain");
            i.putExtra(Intent.EXTRA_TITLE, news.title);
            i.putExtra(Intent.EXTRA_TEXT, news.link);
            holder.itemView.getContext().startActivity(Intent.createChooser(i, "share via"));
        });
        holder.binding.ivFavorite.setOnClickListener(view -> {
            news.favorite = !news.favorite;
            this.favoriteListener.onFavorite(news);
            //reciclerview enxerga qual posição foi flagada.
            notifyItemChanged(position);
        } );
        if {news.favorite) {
        } else {
        //TODO
        }
    }

    @Override
    public int getItemCount() { return this.news.size(); }

    public static class ViewHolder extends RecyclerView.ViewHolder {

         private final NewsItemBinding binding;

        public ViewHolder(NewsItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;

        }
    }

    public interface favoriteListener{
        void onFavorite(News news);

    }


}
