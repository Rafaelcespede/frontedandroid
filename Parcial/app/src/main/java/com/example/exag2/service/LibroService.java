package com.example.exag2.service;

import com.example.exag2.model.Libro;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LibroService {
    @GET("listar")
    Call<List<Libro>> getProductos();

    @GET("buscarcat/{id}")
    Call<List<Libro>> getproductosCat(@Path("id") int id);

    @GET("listar/listadonombreedit")
    Call<List<Libro>> getlibrosconeditorial();

    @POST("agregar")
    Call<Libro>addLibro(@Body Libro libro);

    @POST("actualizar/{id}")
    Call<Libro>updateLibro(@Body Libro libro, @Path("id") int id);

    @DELETE("eliminar/{id}")
    Call<Libro>deleteLibro(@Path("id") int id);

}
