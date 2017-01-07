package com.desarrollomovil.angel.ut3downloadurlsevicemejorado;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class DownloadActivity extends AppCompatActivity {

    private Button btnDescarga;
    private EditText url;
    private ProgressBar progressBar;
    private ImageView img;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_download);

        btnDescarga = (Button)findViewById(R.id.button);
        url = (EditText) findViewById(R.id.textURL);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        img = (ImageView)findViewById(R.id.imageView);
        progressBar.setVisibility(View.INVISIBLE); //Ocultamos el preogressbar de la activity
    }

    public void descargaAsincrona(View v){
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnected()){ //Comprobamos que hay conexión a internet
            DownloadURLTask task = new DownloadURLTask(this);
            String urlTexto = url.getText().toString();
            task.execute(urlTexto);//Le pasamos la url
        }else{
            Toast.makeText(this, "No hay conexión de red", Toast.LENGTH_LONG).show();
        }
    }
}
