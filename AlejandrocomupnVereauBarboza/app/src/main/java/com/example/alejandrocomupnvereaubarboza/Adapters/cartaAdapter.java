package com.example.alejandrocomupnvereaubarboza.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alejandrocomupnvereaubarboza.R;

import java.util.List;

public class CartaAdapter extends RecyclerView.Adapter {

    private List<Carta> carta;
    private Context context;

    public CartaAdapter(List<Carta> carta, Context context) {
        this.carta = carta;
        this.context =context;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        NameViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        if(viewType == 1) {
            View view = inflater.inflate(R.layout.cuenta_string, parent, false);
            viewHolder = new NameViewHolder(view);
        } else {
            View view = inflater.inflate(R.layout.carta_progressbar, parent, false);
            viewHolder = new NameViewHolder(view);
        }

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Carta item = carta.get(position);

        if(item == null) return;

        View view = holder.itemView;

        TextView tvName = view.findViewById(R.id.tvName);


        tvName.setText(item.getNombre());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(context, DetallesCartaActivity.class);
                intent.putExtra("position", item.getId());
                context.startActivity(intent);
            }
        });

    }
    @Override
    public int getItemCount() {
        return carta.size();
    }

    @Override
    public int getItemViewType(int position) {
        Carta item = carta.get(position);
        return item == null ? 0 : 1;
    }

    public void setCuentas(List<Carta> carta) {
        this.carta = carta;
    }

    public class NameViewHolder extends RecyclerView.ViewHolder {

        public NameViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
