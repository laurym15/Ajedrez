package com.lvmo.ajedrez;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lvmo.ajedrez.myapp.Constantes;
import com.lvmo.ajedrez.myapp.tipoUsuario;

import java.util.List;


public class MyUserOnlineRecyclerViewAdapter extends RecyclerView.Adapter<MyUserOnlineRecyclerViewAdapter.ViewHolder> {

    private final List<tipoUsuario> mValues;
    private Context context;
    private int pos;


    public MyUserOnlineRecyclerViewAdapter(List<tipoUsuario> items,Context conText ) {
        mValues = items;
        this.context = this.context;
    }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_item_useronline, parent, false);
        context = parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        holder.mNombre.setText(mValues.get(position).getName());
        holder.mPuntos.setText(String.valueOf(mValues.get(position).getPoints()));
        pos=position;
        holder.mimageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, FindGameActivity.class);
                i.putExtra(Constantes.EXTRA_TIPO_PARTIDA, "Invita?" + mValues.get(pos).getUsuarioId());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView mNombre;
        public final TextView mPuntos;
        public final ImageView mImageView;
        public final ImageButton mimageButton;
        public tipoUsuario mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            mNombre = view.findViewById(R.id.item_nombre);
            mPuntos = view.findViewById(R.id.item_puntos);
            mImageView = view.findViewById(R.id.imageView1);
            mimageButton = view.findViewById(R.id.imageButton);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + mNombre.getText() + "'";
        }
    }
}