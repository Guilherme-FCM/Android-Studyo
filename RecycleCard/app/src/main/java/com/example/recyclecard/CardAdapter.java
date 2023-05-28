package com.example.recyclecard;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CardAdapter extends RecyclerView.Adapter<CardAdapter.ViewHolder> implements AdapterView.OnItemClickListener {
    private Context context;
    private ArrayList<CardModel> cards;
    private ItemClickListener itemClickListener;

    public CardAdapter(Context context, ArrayList<CardModel> cards) {
        this.context = context;
        this.cards = cards;
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
        Toast.makeText(context, cards.get(i).getTitulo(), Toast.LENGTH_SHORT).show();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imageView;
        private TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.img);
            textView = itemView.findViewById(R.id.textView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (itemClickListener != null) {
                itemClickListener.onItemClick(view, getAdapterPosition());
            }
        }
    }

    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void setClickListener(ItemClickListener i) {
        itemClickListener = i;
    }

    public CardModel getItem(int id) {
        return cards.get(id);
    }

    @NonNull
    @Override
    public CardAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_view, parent, false);
        return new ViewHolder(view); // Wrapper
    }

    @Override
    public void onBindViewHolder(@NonNull CardAdapter.ViewHolder holder, int position) {
        CardModel card = cards.get(position);
        holder.textView.setText(card.getTitulo());
        holder.imageView.setImageResource(card.getImagem());
    }

    @Override
    public int getItemCount() {
        return cards.size();
    }
}
