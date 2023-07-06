package com.example.alejandrocomupnvereaubarboza.Utilities;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.alejandrocomupnvereaubarboza.Adapters.CartaAdapter;
import com.example.alejandrocomupnvereaubarboza.R;

import java.util.ArrayList;
import java.util.List;

public class ListaCartaActivity extends AppCompatActivity {

    Retrofit mRetrofit;
    RecyclerView mRvLista;
    boolean mIsLoading = false;
    int mPage = 1;
    List<Carta> cdata = new ArrayList<>();
    CartaAdapter mAdapter = new CartaAdapter(cdata, this);
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_cuentas);

        mRetrofit = RetrofitU.build();
        Button btnRegistro = findViewById(R.id.bttnRegistro);
        Button btnActualizar = findViewById(R.id.bttnActualizar);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        mRvLista =  findViewById(R.id.rvListaCuentas);
        mRvLista.setLayoutManager(layoutManager);
        mRvLista.setAdapter(mAdapter);

        mRvLista.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                LinearLayoutManager linearLayoutManager = (LinearLayoutManager) recyclerView.getLayoutManager();

                if (!mIsLoading) {

                    if (linearLayoutManager != null && linearLayoutManager.findLastCompletelyVisibleItemPosition() == cdata.size() - 1) {
                        mPage++;
                        loadMore(mPage);
                    }
                }

            }
        });

}
