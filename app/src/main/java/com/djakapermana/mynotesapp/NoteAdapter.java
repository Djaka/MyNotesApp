package com.djakapermana.mynotesapp;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.djakapermana.mynotesapp.entity.Note;

import org.w3c.dom.Text;

import java.util.LinkedList;

import static com.djakapermana.mynotesapp.db.DatabaseContract.CONTENT_URI;

/**
 * Created by Djaka on 29/10/2017.
 */

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.NoteViewHolder> {
//    private LinkedList<Note> listNotes;
    private Cursor listNotes;
    private Activity activity;

    public NoteAdapter(Activity activity){
        this.activity = activity;
    }

    public Cursor getListNotes(){
        return listNotes;
    }

    public void setListNotes(Cursor listNotes){
        this.listNotes = listNotes;
    }

    @Override
    public NoteViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false);
        return new NoteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(NoteViewHolder holder, int position) {
        final Note note = getItem(position);
        holder.tvTitle.setText(note.getTitle());
        holder.tvDescription.setText(note.getDescription());
        holder.tvDate.setText(note.getDate());
        holder.cvNote.setOnClickListener(new CustomOnclickListener(position, new CustomOnclickListener.OnItemClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(activity, FormAddUpdateActivity.class);

                Uri uri = Uri.parse(CONTENT_URI + "/" + note.getId());

                intent.setData(uri);

                Log.d("NoteAdapter: ", "" + position);

//                intent.putExtra(FormAddUpdateActivity.EXTRA_POSITION, position);
//                intent.putExtra(FormAddUpdateActivity.EXTRA_NOTE, getListNotes().get(position));
                activity.startActivityForResult(intent, FormAddUpdateActivity.REQUEST_UPDATE);
            }
        }));
    }

    @Override
    public int getItemCount() {
        if(listNotes == null){
            return 0;
        }
        return getListNotes().getCount();
    }

    private Note getItem(int position){
        if (!listNotes.moveToPosition(position)){
            throw  new IllegalStateException("Position Invalid");
        }
        return new Note(listNotes);
    }

    public class NoteViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle, tvDescription, tvDate;
        CardView cvNote;
        public NoteViewHolder(View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_item_title);
            tvDescription = itemView.findViewById(R.id.tv_item_description);
            tvDate = itemView.findViewById(R.id.tv_item_date);
            cvNote = itemView.findViewById(R.id.cv_item_note);
        }
    }
}
