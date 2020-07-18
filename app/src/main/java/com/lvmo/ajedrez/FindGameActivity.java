package com.lvmo.ajedrez;

import android.app.NotificationManager;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.lvmo.ajedrez.myapp.Constantes;

public class FindGameActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_game);
        TextView Txt =findViewById(R.id.tex1);
        Intent i = getIntent();
        if(i!=null) {
            String tipoPartida = i.getStringExtra(Constantes.EXTRA_TIPO_PARTIDA);
            String partidaID=i.getStringExtra(Constantes.EXTRA_JUGADA_ID);
            NotificationManager notificationManager = (NotificationManager) getSystemService(FindGameActivity.NOTIFICATION_SERVICE);
            notificationManager.cancel(Constantes.EXTRA_NOTIFICACION_ID);
            Txt.setText(partidaID);
        }
    }
}