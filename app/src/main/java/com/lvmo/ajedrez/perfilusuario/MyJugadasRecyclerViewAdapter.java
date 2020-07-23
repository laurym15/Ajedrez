package com.lvmo.ajedrez.perfilusuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.lvmo.ajedrez.R;
import com.lvmo.ajedrez.myapp.jugada;

import java.util.List;

public class MyJugadasRecyclerViewAdapter extends RecyclerView.Adapter<MyJugadasRecyclerViewAdapter.ViewHolder> {
    private StorageReference mStorageRef;
    private FirebaseAuth fireBaseAuth;
    private final List<jugada> mValues;

    public MyJugadasRecyclerViewAdapter(List<jugada> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_juagdas, parent, false);
        mStorageRef = FirebaseStorage.getInstance().getReference();
        fireBaseAuth = FirebaseAuth.getInstance();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        String uid =fireBaseAuth.getUid();
        holder.mItem = mValues.get(position);
        if(uid.equals(mValues.get(position).getjBlancasId())){
        holder.item_nombre.setText(mValues.get(position).getjBlancasId());
        }
        else{        holder.item_nombre.setText(mValues.get(position).getjNegrasId());
        }
        if(uid.equals(mValues.get(position).getGanadorId()))
        {holder.imGano.setVisibility(View.VISIBLE);
            holder.imPerdio.setVisibility(View.INVISIBLE);
        }
        else{
            holder.imGano.setVisibility(View.INVISIBLE);
            holder.imPerdio.setVisibility(View.VISIBLE);
        }
        holder.editTextDate.setText(mValues.get(position).getFecha().toString());
        if(uid.equals(mValues.get(position).getpBlancas()))
        { holder.puntos.setText(mValues.get(position).getpBlancas());}
        else{
            { holder.puntos.setText(mValues.get(position).getpNegras());}
        }
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView item_nombre;
        public final ImageView imGano;
        public final ImageView imPerdio;
        public final TextView editTextDate;
        public final TextView puntos;
        public jugada mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            item_nombre = (TextView) view.findViewById(R.id.item_nombre);
            editTextDate = (TextView) view.findViewById(R.id.editTextDate);
            puntos = (TextView) view.findViewById(R.id.puntos);
            imGano = (ImageView) view.findViewById(R.id.imGano);
            imPerdio = (ImageView) view.findViewById(R.id.imPerdio);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + item_nombre.getText() + "'";
        }
    }
}