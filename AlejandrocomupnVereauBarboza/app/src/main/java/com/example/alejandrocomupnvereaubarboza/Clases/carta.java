package com.example.alejandrocomupnvereaubarboza.Clases;


@Entity(tableName = "Carta")
public class carta {

    @PrimaryKey()
    public int id;

    public String nombre;

    @ColumnInfo(name = "synced")
    private boolean synced;

    public Carta() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isSynced() {
        return synced;
    }

    public void setSynced(boolean synced) {
        this.synced = synced;
    }

}
