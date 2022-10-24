package com.example.exag2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.exag2.model.Libro;
import com.example.exag2.service.LibroService;
import com.example.exag2.service.apis;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LibroActivity extends AppCompatActivity {
    LibroService libroService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_libro);


        EditText txtnombre = (EditText)findViewById(R.id.txtnombre);
        TextView nombre = (TextView) findViewById(R.id.nombre);
        EditText txtautor = (EditText)findViewById(R.id.txtAutorp);
        TextView autor = (TextView) findViewById(R.id.Autorp);
        EditText txtpaginas = (EditText)findViewById(R.id.txtPaginasp);
        TextView paginas = (TextView) findViewById(R.id.Paginasp);



        Button btnSave=(Button)findViewById(R.id.btnSave);
        Button btnVolver=(Button)findViewById(R.id.btnVolver);
        Button btnEliminar=(Button)findViewById(R.id.btnEliminar);

        Bundle bundle=getIntent().getExtras();
        String ide = bundle.getString("idlibro");
        String nom= bundle.getString("Titulo");
        String aut = bundle.getString("Autor");
        String pag = bundle.getString("Paginas");
        String idedit = bundle.getString("IdEditorial");
        String nombreedit = bundle.getString("Nombre");


        txtnombre.setText(nom);
        txtautor.setText(aut);
        txtpaginas.setText(pag);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Libro p=new Libro();
                p.setNombre(txtnombre.getText().toString());
                p.setAutor(txtautor.getText().toString());
                p.setPaginas(Integer.valueOf(txtautor.getText().toString()));
                if(ide.trim().length()==0||ide.equals("")){
                    addLibro(p);
                    Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                    startActivity(intent);
                }else{
                    updateLibro(p,Integer.valueOf(ide));
                    Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                    startActivity(intent);
                }

            }
        });
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteLibro(Integer.valueOf(ide));
                Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        btnVolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent =new Intent(LibroActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }
    public void deleteLibro(int id){
        libroService = apis.getLibroService();
        Call<Libro> call=libroService.deleteLibro(id);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LibroActivity.this,"Se Elimino el registro",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });

    }
    public void updateLibro(Libro p,int id){
        libroService= apis.getLibroService();
        Call<Libro>call=libroService.updateLibro(p,id);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LibroActivity.this,"Se Actualizó conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(LibroActivity.this,MainActivity.class);
        startActivity(intent);
    }

    public void addLibro(Libro p){
        libroService= apis.getLibroService();
        Call<Libro> call=libroService.addLibro(p);
        call.enqueue(new Callback<Libro>() {
            @Override
            public void onResponse(Call<Libro> call, Response<Libro> response) {
                if(response.isSuccessful()){
                    Toast.makeText(LibroActivity.this,"Se agrego conéxito",Toast.LENGTH_LONG).show();
                }
            }
            @Override
            public void onFailure(Call<Libro> call, Throwable t) {
                Log.e("Error:",t.getMessage());
            }
        });
        Intent intent =new Intent(LibroActivity.this,MainActivity.class);
        startActivity(intent);
    }
}