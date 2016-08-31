package com.miislyk.fileio;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileOutputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void guardarPreferencia(View v) {

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = miPreferenciaCompartida.edit();

        EditText editTextNombre = (EditText) findViewById(R.id.editTextNombre);
        EditText editTextCorreo = (EditText) findViewById(R.id.editTextCorreo);

        String nombre = editTextNombre.getText().toString();
        String correo = editTextCorreo.getText().toString();

        editor.putString("nombre", nombre);
        editor.putString("correo", correo);

        editor.commit();

        Toast.makeText(MainActivity.this, "Se ha creado la preferencia compartida", Toast.LENGTH_SHORT).show();
        editTextNombre.setText("");
        editTextCorreo.setText("");

    }

    public void mostrarPreferencia(View v){

        SharedPreferences miPreferenciaCompartida = getSharedPreferences("MisDatosPersonales", Context.MODE_PRIVATE);

        String nombre = miPreferenciaCompartida.getString("nombre", "no existe esa variable");
        String correo = miPreferenciaCompartida.getString("correo", "no existe esa variable");

        TextView tvPreferenciaCompartida = (TextView) findViewById(R.id.textViewPreferenciaCompartida);
        String preferencia = "\nNombre: " + nombre + "\nCorreo: " + correo;
        tvPreferenciaCompartida.setText(preferencia);

    }

    public void generarArchivo(View v){

        try {

            EditText edtnombre = (EditText) findViewById(R.id.editTextNombre);
            String nombre = edtnombre.getText().toString();

            FileOutputStream outputStream = null;
            outputStream = openFileOutput("MiArchivo.txt", Context.MODE_PRIVATE);
            outputStream.write(nombre.getBytes());
            outputStream.close();

            Toast.makeText(MainActivity.this, "El archivo se ha creado", Toast.LENGTH_SHORT).show();
            edtnombre.setText("");
        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(MainActivity.this, "Hubo un error en la escritura del archivo", Toast.LENGTH_SHORT).show();
        }

    }
}
