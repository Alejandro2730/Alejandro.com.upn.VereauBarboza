package com.example.alejandrocomupnvereaubarboza.Utilities;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.alejandrocomupnvereaubarboza.R;
import com.example.alejandrocomupnvereaubarboza.Service.CartaService;

import java.util.List;

public class DetallesCuentaActivity   extends AppCompatActivity  {

    Carta carta;
    Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles_cuenta);

        mRetrofit = RetrofitU.build();

        float saldoF = 0;

        int position = getIntent().getIntExtra("position", 0);

        AppDatabase db = AppDatabase.getInstance(this);
        CartaRepository repository = db.cartaRepository();
        Cuenta cuenta = repository.findCuentaById(position);

        MovimientoRepository repositoryM = db.movimientoRepository();
        List<Movimiento> movimientos = repositoryM.getMovimientosByCuentaId(position);


        TextView tvNombre = findViewById(R.id.tvNombreCarta);
        TextView tvSaldoFinal = findViewById(R.id.tvSaldoFinal);
        Button bttnRegistrar = findViewById(R.id.DbtnRegistrar);
        Button bttnVerM = findViewById(R.id.DbtnVerMovimiento);
        Button bttnSincro = findViewById(R.id.DbtnSincro);

        tvNombre.setText(cuenta.nombre);

        if(movimientos == null) tvSaldoFinal.setText("S/. 0");
        else {
            for (int i = 0; i< movimientos.size(); i++){
                String aux = movimientos.get(i).tipo.toString();
                if(aux.equals("Ingreso")) saldoF += movimientos.get(i).monto;
                if(aux.equals("Gasto")) saldoF -= movimientos.get(i).monto;
            }
            tvSaldoFinal.setText("S/. " + saldoF);
        }

        bttnSincro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!cuenta.isSynced()) {
                    CartaService service = mRetrofit.create(CartaService.class);
                    Cuenta cuenta = new Cuenta();
                    cuenta.setNombre(tvNombre.getText().toString());
                    cuenta.setSynced(true);

                    Call<Carta> call = service.create(cuenta);

                    call.enqueue(new Callback<Carta>() {
                        @Override
                        public void onResponse(Call<Carta> call, Response<Carta> response) {
                            Log.i("MAIN_APP",  String.valueOf(response.code()));

                            Intent intent =  new Intent(DetallesCuentaActivity.this, ListaCartaActivity.class);
                            startActivity(intent);
                            finish();

                        }

                        @Override
                        public void onFailure(Call<Carta> call, Throwable t) {

                        }
                    });
                }
            }
        });

    }
}
