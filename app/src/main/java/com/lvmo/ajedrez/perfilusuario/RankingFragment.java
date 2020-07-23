package com.lvmo.ajedrez.perfilusuario;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.lvmo.ajedrez.R;
import com.lvmo.ajedrez.myapp.tipoUsuario;

import java.util.ArrayList;
import java.util.List;

public class RankingFragment extends Fragment {

    private static final String ARG_COLUMN_COUNT = "column-count";
    private int mColumnCount = 1;

    List<tipoUsuario> userList;
    MyUserRankingRecyclerViewAdapter adapter;
    RecyclerView recyclerView;

    private FirebaseFirestore db;
    private FirebaseAuth fireBaseAuth;
    public RankingFragment() {
    }

    // TODO: Customize parameter initialization
    @SuppressWarnings("unused")
    public static RankingFragment newInstance(int columnCount) {
        RankingFragment fragment = new RankingFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        db =FirebaseFirestore.getInstance();
        fireBaseAuth = FirebaseAuth.getInstance();

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_ranking_list, container, false);

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
                 .orderBy("points", Query.Direction.DESCENDING)
                 .limit(10)
                 .get()
                 .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                     @Override
                     public void onComplete(@NonNull Task<QuerySnapshot> task) {
                         userList =new ArrayList<>();
                         for(DocumentSnapshot documentSnapshot: task.getResult()) {
                             tipoUsuario userItem=documentSnapshot.toObject(tipoUsuario.class);
                             userList.add(userItem);

                             adapter= new MyUserRankingRecyclerViewAdapter(userList);
                             recyclerView.setAdapter(adapter);
                         }
                     }
                 });
        }
        return view;
    }
}