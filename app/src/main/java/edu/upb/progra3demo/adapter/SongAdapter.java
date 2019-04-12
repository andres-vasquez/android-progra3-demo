package edu.upb.progra3demo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

import edu.upb.progra3demo.R;
import edu.upb.progra3demo.model.Song;

public class SongAdapter extends BaseAdapter {

    private Context context;
    private List<Song> items;

    public SongAdapter(Context context, List<Song> items) {
        this.context = context;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Song getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return items.get(position).getId();
    }

    @Override
    public View getView(int position, View vista, ViewGroup parent) {
        ViewHolder viewHolder;
        if (vista == null) { //No se puede reciclar
            viewHolder = new ViewHolder();

            //Inflater nos permite usar un layout dentro de un componente
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            vista = inflater.inflate(R.layout.layout_song, null); //Cual layout y principal o no.

            viewHolder.image = vista.findViewById(R.id.image);
            viewHolder.songArtist = vista.findViewById(R.id.songArtist);
            viewHolder.songName = vista.findViewById(R.id.sontTitle);
            vista.setTag(viewHolder); //Guardar para reciclar
        } else {
            viewHolder = (ViewHolder) vista.getTag(); //Obtener el dato reciclado
        }

        Song cancionActual = items.get(position);
        viewHolder.songName.setText(cancionActual.getSongTitle());
        viewHolder.songArtist.setText(cancionActual.getSongArtist());
        viewHolder.image.setImageResource(cancionActual.getCoverImage());
        return vista;
    }

    static class ViewHolder {
        ImageView image;
        TextView songName;
        TextView songArtist;
    }
}
