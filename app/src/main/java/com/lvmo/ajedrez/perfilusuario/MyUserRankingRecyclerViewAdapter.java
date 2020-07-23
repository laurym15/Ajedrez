package com.lvmo.ajedrez.perfilusuario;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.lvmo.ajedrez.R;
import com.lvmo.ajedrez.myapp.tipoUsuario;

import java.util.List;

public class MyUserRankingRecyclerViewAdapter extends RecyclerView.Adapter<MyUserRankingRecyclerViewAdapter.ViewHolder> {

    private final List<tipoUsuario> mValues;

    public MyUserRankingRecyclerViewAdapter(List<tipoUsuario> items) {
        mValues = items;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.fragment_ranking, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        holder.mItem = mValues.get(position);
        int pos = position +1;
        holder.posicion.setText(pos +"°");
        holder.item_nombre.setText(mValues.get(position).getName());
        holder.puntos.setText(String.valueOf(mValues.get(position).getPoints()));
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;
        public final TextView item_nombre;
        public final TextView puntos;
        public final TextView posicion;

        public tipoUsuario mItem;

        public ViewHolder(View view) {
            super(view);
            mView = view;
            item_nombre = (TextView) view.findViewById(R.id.item_nombre);
            puntos = (TextView) view.findViewById(R.id.puntos);
            posicion = (TextView) view.findViewById(R.id.posicion);
        }

        @Override
        public String toString() {
            return super.toString() + " '" + item_nombre.getText() + "'";
        }
    }
}