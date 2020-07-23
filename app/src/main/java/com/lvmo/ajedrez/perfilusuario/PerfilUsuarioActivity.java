package com.lvmo.ajedrez.perfilusuario;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.lvmo.ajedrez.MainActivity;
import com.lvmo.ajedrez.R;
import com.lvmo.ajedrez.myapp.Constantes;
import com.lvmo.ajedrez.myapp.tipoUsuario;

public class PerfilUsuarioActivity extends AppCompatActivity {
    private ScrollView layoutPprogressBar, layoutMenuJuego;
    private TextView tvCargando;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore db;
     String TAG;
    private String verPerfil="",nombreJuno="",uid;
    private String puntosUid="0",uVerificado="no";
    private User userjUno;
    Button btSalir,btCpermanete;
    TextView tvnombre,tvpuntos;
    CheckBox checkBox;
    EditText actNombre;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        layoutPprogressBar =findViewById(R.id.LayoutProgressBar);
        layoutMenuJuego= findViewById(R.id.menuJuegoTrans);
        tvCargando=findViewById(R.id.textViewCargando);
        ProgressBar progresBar = findViewById(R.id.progressBarCargando);
        progresBar.setIndeterminate(true);
        tvCargando.setText(R.string.TBienvenido);
        changeMenuVisibility(false);

        tvnombre=findViewById(R.id.tvNombre);
        tvpuntos=findViewById(R.id.tvpuntos);
        checkBox= findViewById(R.id.checkBox);
        actNombre=findViewById(R.id.editTextTextPersonName);

        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        btCpermanete=findViewById(R.id.cPermanente);
        btSalir=findViewById(R.id.btsalir);

        Intent i = getIntent();
        if(i!=null) {
            uid=firebaseAuth.getUid();
            verPerfil = i.getStringExtra(Constantes.EXTRA_VER_PERFIL);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_jugada, new JugadasFragment())
                    .commit();

        }

    }

    private void craerUsuario() {
        firebaseAuth.signInAnonymously()
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInAnonymously:success");
                            FirebaseUser user = firebaseAuth.getCurrentUser();
                            actilizarDB(user);
                            updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInAnonymously:failure", task.getException());
                            Toast.makeText(PerfilUsuarioActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
    }

    private void actilizarDB(FirebaseUser user) {
        String uid= user.getUid();
        tipoUsuario nuevoUsuario= new tipoUsuario(0,"",uid,"on","no");
        db.collection("users")
                .document(uid)
                .set(nuevoUsuario)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Toast.makeText(PerfilUsuarioActivity.this, R.string.TususarioCreado, Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(PerfilUsuarioActivity.this, R.string.TfalloLawea, Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = firebaseAuth.getCurrentUser();
        updateUI(currentUser);
        if(uid!=null){
        actulizarDBuser("onLine","on");}
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(uid!=null){

            actulizarDBuser("onLine","off");}
    }

    private void updateUI(final FirebaseUser user) {
        //changeLoginFormVisibility(false);

        if (user != null) {
            if(verPerfil.equals("crear")){
           finish();
            Intent i = new Intent(PerfilUsuarioActivity.this, MainActivity.class);
            startActivity(i);
            Toast.makeText(getApplicationContext(), R.string.TBienvenido, Toast.LENGTH_LONG).show();
            }
            else{
                eventoClic();
            }
        } else {
            changeMenuVisibility(true);
            craerUsuario();
        }
    }

    private void eventoClic() {
        changeMenuVisibility(true);

            getnombrePuntos(uid);
        checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkBox.isChecked()) {
                        checkBox.setChecked(false);

                        actulizarDBuser("name", actNombre.getText().toString());
                        getnombrePuntos(uid);
                        Toast.makeText(getApplicationContext(), "nombre actulizado", Toast.LENGTH_LONG).show();

                    }}
            });


        btSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                db.collection("users")
                        .document(uid)
                        .delete()
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                             //   Toast.makeText(getApplicationContext(),"se borraron tus datos", Toast.LENGTH_LONG).show();
                            }
                        });
            }
        });

        if(uVerificado.equals("no")){
            btCpermanete.setVisibility(View.GONE);
        }
        else {
            btCpermanete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AuthCredential credential = EmailAuthProvider.getCredential("laury@gmail.com", "123456");
                    //AuthCredential credential = FacebookAuthProvider.getCredential(token.getToken());
                    vincularCredencticales(credential);
                    actulizarDBuser("usuarioVerificado", "verificado") ;
                    //TODO: iniciar sesion con otras cuentas esto solo vincual las cuentas
                    }
            });
        }
    }

    private void vincularCredencticales(AuthCredential credential) {
        firebaseAuth.getCurrentUser().linkWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            Log.d(TAG, "linkWithCredential:success");
                            FirebaseUser user = task.getResult().getUser();
                            updateUI(user);
                        } else {
                            Log.w(TAG, "linkWithCredential:failure", task.getException());
                            Toast.makeText(PerfilUsuarioActivity.this, "Authentication failed.", Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });
    }
    private void changeMenuVisibility(boolean ShowMenu) {
        layoutPprogressBar.setVisibility(ShowMenu ? View.GONE: View.VISIBLE);
        layoutMenuJuego.setVisibility(ShowMenu ? View.VISIBLE: View.GONE);
    }
    private void getnombrePuntos(String idUsuario){ //regresa nombreJuno,puntosUid, foto perfil
        db.collection("users")
                .document(uid)
                .get()
                .addOnSuccessListener(PerfilUsuarioActivity.this, new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        nombreJuno=documentSnapshot.get("name").toString();
                        puntosUid=documentSnapshot.get("points").toString();
                        tvnombre.setText("nombre:"+nombreJuno);
                        tvpuntos.setText("puntos"+puntosUid);
                    }
                }).addOnFailureListener(PerfilUsuarioActivity.this, new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {

            }
        })
        ;

    }
    private void actulizarDBuser(String campo, String contenidoActu) {
        db.collection("users")
                .document(uid)
                .update(campo, contenidoActu)
                .addOnSuccessListener(PerfilUsuarioActivity.this, new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(PerfilUsuarioActivity.this, new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                    }
                });
    }
}