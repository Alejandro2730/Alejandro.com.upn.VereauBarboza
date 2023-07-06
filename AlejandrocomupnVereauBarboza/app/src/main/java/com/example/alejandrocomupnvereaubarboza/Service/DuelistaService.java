package com.example.alejandrocomupnvereaubarboza.Service;

import java.util.List;

public interface DuelistaService {

    @GET("Carta")
    Call<List<carta>> getAllUser(@Query("limit") int limit, @Query("page") int page);

    @GET("Carta/{id}")
    Call<Cuenta> findUser(@Path("id") int id);

    @POST("Carta")
    Call<Cuenta> create(@Body Cuenta cuenta);

    @PUT("Carta/{id}")
    Call<Cuenta> update(@Path("id") int id, @Body Cuenta cuenta);

    @DELETE("Carta/{id}")
    Call<Void> delete(@Path("id") int id);

    @POST("Carta/upload")
    Call<Void> uploadCuentas(@Body List<Cuenta> cuentas);

}
