package com.example.alejandrocomupnvereaubarboza.DateBase;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.alejandrocomupnvereaubarboza.Repositeres.CartaRepository;

public class AppDateBase {

    @Database(entities = {Cuenta.class, Movimiento.class}, version = 1)
    public abstract class AppDatabase extends RoomDatabase {

        public abstract CartaRepository cartaRepository();
        public abstract MovimientoRepository movimientoRepository();

        public static AppDatabase getInstance(Context context) {
            return Room.databaseBuilder(context, AppDatabase.class, "CuentaBD")
                    .allowMainThreadQueries()
                    .build();
        }
}
