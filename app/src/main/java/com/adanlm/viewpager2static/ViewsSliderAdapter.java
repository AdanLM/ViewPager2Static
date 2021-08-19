package com.adanlm.viewpager2static;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * ViewPager esta construido sobre RecyclerView, lo cual podemos aprovechar para utilizar sus metodos
 * y hacer un ViewPager estatico
 */

public class ViewsSliderAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private int[] layouts;

    //Le pasamos los id de los layouts a utilizar
    public ViewsSliderAdapter(int[] layouts) {
        this.layouts = layouts;
    }

    //No necesitamos pasarle un vista item ya que no necesitas rellenar ese item con información.
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false);
        return new SliderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

    }

    //Este metodo es que hace la magia, aqui es donde le decimos que layout debe mostrar
    @Override
    public int getItemViewType(int position) {
        return layouts[position];
    }

    @Override
    public int getItemCount() {
        return layouts.length;
    }

    public static class SliderViewHolder extends RecyclerView.ViewHolder {

        public SliderViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
