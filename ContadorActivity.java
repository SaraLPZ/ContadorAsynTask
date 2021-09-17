package com.example.juego;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ContadorActivity extends AppCompatActivity {

    private TextView tvDinero;
    private Button btnAsynTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contador);

        tvDinero=findViewById(R.id.tv_dinero);
        btnAsynTask=findViewById(R.id.btnAsynTask);

        btnAsynTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TareaAsynTask tareaAsynTask = new TareaAsynTask();
                tareaAsynTask.execute();
            }
        });
    }

    private class TareaAsynTask extends AsyncTask<Void, Integer,String>{

        @Override
        protected void onPreExecute() {
            tvDinero.setText("0");
        }

        @Override
        protected String doInBackground(Void... voids) {
            //Pondrá finalizado al llegar al 10, pongo 11 porque sino terminaría en el 9
            for(int i=0; i<11;i++){
                esperarUnSegundo();
                publishProgress(i);
            }
            return "Finalizado  ";
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            tvDinero.setText(values[0].toString());
        }



        @Override
        protected void onPostExecute(String s) {
            tvDinero.setText(s);
        }
    }

    private void esperarUnSegundo() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}