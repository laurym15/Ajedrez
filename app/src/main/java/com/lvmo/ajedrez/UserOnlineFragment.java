package com.lvmo.ajedrez;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.lvmo.ajedrez.myapp.tipoUsuario;

import java.util.ArrayList;
import java.util.List;


public class UserOnlineFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;
    private List<tipoUsuario> userList;
    private FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;
    MyUserOnlineRecyclerViewAdapter adatper;
    RecyclerView recyclerView;

    public UserOnlineFragment() {
    }


    public static UserOnlineFragment newInstance(int columnCount) {
        UserOnlineFragment fragment = new UserOnlineFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        db = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_online, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
             recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }
                db.collection("users")
                        .whereEqualTo("onLine", "on")
                        .addSnapshotListener(new EventListener<QuerySnapshot>() {
                            @Override
                            public void onEvent(@Nullable QuerySnapshot value, @Nullable FirebaseFirestoreException error) {
                                if (error != null) {
                                    Toast.makeText(getContext(),"falla al otern los usuraios", Toast.LENGTH_LONG).show();
                                    return;
                                }

                                userList = new ArrayList<>();
                                for (QueryDocumentSnapshot doc : value) {
                                    if (!doc.get("usuarioId").equals(firebaseAuth.getCurrentUser().getUid())) {
                                        tipoUsuario userItem = doc.toObject(tipoUsuario.class);
                                        userList.add(userItem);
                                    }
                                    adatper= new MyUserOnlineRecyclerViewAdapter(userList,getContext());
                                    recyclerView.setAdapter(adatper);
                            }
                            }
                        });

        }
        return view;
    }
}