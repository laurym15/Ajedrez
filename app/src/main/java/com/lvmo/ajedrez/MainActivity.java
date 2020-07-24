package com.lvmo.ajedrez;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.Intent;
import android.graphics.Color;
import android.media.MediaPlayer;
import android.media.RingtoneManager;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.lvmo.ajedrez.myapp.Constantes;
import com.lvmo.ajedrez.perfilusuario.PerfilUsuarioActivity;
import com.lvmo.ajedrez.perfilusuario.RankingActivity;

public class MainActivity extends AppCompatActivity {
    private CountDownTimer countDownTimerDial;
    private static long millisecondsleft;
    private CountDownTimer countDownTimerSalir;
    private int contSalir=0;
    private String tiponoti="notifica";
    private FirebaseAuth firebaseAuth;
    ExtendedFloatingActionButton fab,fab1;
    private String uid, nombreJuno = "", jugadorDosId, nombreJdos = "";

    private FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        db = FirebaseFirestore.getInstance();

        firebaseAuth = FirebaseAuth.getInstance();
        if(firebaseAuth.getUid()==null)
        { Intent i= new Intent(MainActivity.this, PerfilUsuarioActivity.class);
        i.putExtra(Constantes.EXTRA_VER_PERFIL,"crear");
        startActivity(i);
        }else {

            fab = findViewById(R.id.extended_fab);
            fab1 = findViewById(R.id.extended_fab1);
            fab.shrink();
            fab1.shrink();

            View.OnClickListener clickListener = new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ExtendedFloatingActionButton extFab = (ExtendedFloatingActionButton) v;
                    if (extFab.isExtended()) {
                        extFab.shrink();
                    } else {
                        extFab.extend();
                        jugadorDosId="sIqwR6XmsIZ4ymPyepQhuq42Pls2";
                        db.collection("users")
                                .document(jugadorDosId)
                                .get()
                                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                                    @Override
                                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                                        nombreJdos = documentSnapshot.get("name").toString();
                                        sonidoNoti();
                                        dialogo_NotificacionJugada();
                                    } });
                    }
                }
            };
            fab.setOnClickListener(clickListener);
            fab1.setOnClickListener(clickListener);
        }

        eventoClic();

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.content_usuario_online, new UserOnlineFragment() )
                .commit();
    }
    private void sonidoNoti(){
        MediaPlayer mediaPlayer=MediaPlayer.create(this,RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION));
        mediaPlayer.start();
    }
    private void dialogo_NotificacionJugada() {

        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        LayoutInflater inflater = getLayoutInflater();

        View view = inflater.inflate(R.layout.layout_dialgonotificacion, null);
        builder.setView(view);
        final AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        TextView tvTitulo = view.findViewById(R.id.tvTitulo);
        TextView tvcuerpo = view.findViewById(R.id.tvCuerpo);
        tvcuerpo.setText(nombreJdos+","+getString(R.string.noti_Cuerpo));
        tvTitulo.setText(getString(R.string.noti_Titulo));
        ImageButton aceptar = view.findViewById(R.id.acpetar);
        aceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tiponoti.equals("notifica"))  {
                    Toast.makeText(getApplicationContext(), R.string.TComienzaJu, Toast.LENGTH_LONG).show();
                    Intent i= new Intent(MainActivity.this, PerfilUsuarioActivity.class);
                    i.putExtra(Constantes.EXTRA_VER_PERFIL,"ver");
                    startActivity(i);
                   /* Intent i= new Intent(TransisionActivity.this, FindGameActivity.class);
                    i.putExtra(Constantes.EXTRA_TIPO_PARTIDA,"pinvDirecta");
                    i.putExtra(Constantes.EXTRA_JUGADA_ID,jugadaId);
                    startActivity(i);*/
                }
                countDownTimerDial.cancel();
                dialog.dismiss();
            }
        });
        final Button cancelar = view.findViewById(R.id.cancelar);
        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tiponoti.equals("notifica"))  {
                  //  cancelarJugada();

                }
                countDownTimerDial.cancel();
                dialog.dismiss();
            }
        });
        countDownTimerDial = new CountDownTimer(35000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                millisecondsleft = millisUntilFinished;
                cancelar.setText("(" + String.format("%02d", millisUntilFinished / 1000) + ")");
            }

            @Override
            public void onFinish() {
                if(tiponoti.equals("notifica"))  {
                    //cancelarJugada();
                    }
                dialog.dismiss();
            }
        }.start();
    }

    private void eventoClic() {
         Button btRanking = findViewById(R.id.bRanking);
        btRanking.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), R.string.TvamosRanking, Toast.LENGTH_LONG).show();
                Intent i= new Intent(MainActivity.this, RankingActivity.class);
                startActivity(i);
            }
        });
    }

    private void crearNotificacionInterna(String jugadaId) {
        Notification.Builder mBuilder = new Notification.Builder(MainActivity.this)
                .setContentTitle("Tienes una partida pendiente")
                .setContentText("Apresurate, te esperan")
                .setSmallIcon(R.mipmap.ic_launcher_round)
                .setPriority(Notification.PRIORITY_MAX)
                .setLights(Color.CYAN,1,0)
                .setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
                .setOngoing(false);//sirve para canerla solo cuando netras a laplaicacion.
        // .setLargeIcon(R.drawable.tocate)

        Intent i= new Intent(MainActivity.this, FindGameActivity.class);
        i.putExtra(Constantes.EXTRA_TIPO_PARTIDA,"pinvDirecta");
        i.putExtra(Constantes.EXTRA_JUGADA_ID,jugadaId);

        TaskStackBuilder stackBuilder = TaskStackBuilder.create(MainActivity.this);
        stackBuilder.addParentStack(FindGameActivity.class);
        stackBuilder.addNextIntent(i);

        PendingIntent pendingIntet =stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(pendingIntet);

        NotificationManager notificationManager= (NotificationManager) getSystemService(MainActivity.NOTIFICATION_SERVICE);
        notificationManager.notify(Constantes.EXTRA_NOTIFICACION_ID,mBuilder.build());
    }


    public void onBackPressed() {
        if(contSalir==0){
            Toast.makeText(MainActivity.this, getString(R.string.TpresioneSalir),Toast.LENGTH_SHORT).show();
            contSalir++;
        }
        else
        {
            //actulizarDBuser("off");
            super.onBackPressed();
            finishAffinity();
            //System.exit(0);*/
        }

        countDownTimerSalir = new CountDownTimer(3000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                contSalir=0;
            }
        }.start();
    }

    @Override
    protected void onStop() {
        super.onStop();
        crearNotificacionInterna("funcionafunciona");
    }
}