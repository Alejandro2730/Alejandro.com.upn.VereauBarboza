package com.example.alejandrocomupnvereaubarboza.Utilities;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alejandrocomupnvereaubarboza.R;
import com.example.alejandrocomupnvereaubarboza.Repositeres.CartaRepository;

public class RegistroCartaActivity extends AppCompatActivity  {

    Retrofit mRetrofit;
    Context context = this;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro_carta);

        Button btnRegistrar = findViewById(R.id.bttnRegistrar);
        Button btnVolver = findViewById(R.id.bttnVolver);
        EditText etNombre = findViewById(R.id.etNombre);

        mRetrofit = RetrofitU.build();

        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =  new Intent(RegistroCartaActivity.this, ListaCartaActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // llamar a retrofit
                AppDatabase database = AppDatabase.getInstance(context);
                CartaRepository cuentaRepository = database.cartaRepository();

                // Obtener el último ID registrado en la base de datos
                int lastId = cuentaRepository.getLastId();

                Cuenta cuenta = new Cuenta();
                cuenta.setId(lastId + 1); // Asignar el nuevo ID incrementado en uno
                cuenta.setNombre(etNombre.getText().toString());
                cuenta.setSynced(false);

                cuentaRepository.create(cuenta);
                Log.i("MAIN_APP: DB", new Gson().toJson(cuenta));
                Intent intent = new Intent(RegistroCartaActivity.this, ListaCartaActivity.class);
                startActivity(intent);
                finish();
            }
        });



    }
    private boolean isNetworkConnected() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null && cm.getActiveNetworkInfo().isConnected();
    }
}
