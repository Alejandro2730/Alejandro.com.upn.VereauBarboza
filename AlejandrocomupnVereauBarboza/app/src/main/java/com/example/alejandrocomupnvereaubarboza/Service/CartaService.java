package com.example.alejandrocomupnvereaubarboza.Service;

import java.util.List;

public interface CartaService {

    @Query("SELECT * FROM Carta")
    List<Carta> getAll();

    @Insert
    void create(Carta carta);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Carta> carta);

    @Query("SELECT * FROM carta WHERE synced = 0")
    List<Carta> getUnsyncedCarta();

    @Update
    void updateCuenta(Carta carta);

    @Query("SELECT MAX(id) FROM carta")
    int getLastId();

    @Query("SELECT * FROM Cuentas WHERE id = :cuentaId")
    Carta findCuentaById(int cartaId);

}
