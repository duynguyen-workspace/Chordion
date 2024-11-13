/*
* Reference:
* 1. Set margin and padding for TextView dynamically: https://stackoverflow.com/questions/12728255/in-android-how-do-i-set-margins-in-dp-programmatically
* 2. Set font family for TextView dynamically: https://stackoverflow.com/questions/12128331/how-to-change-fontfamily-of-textview-in-android
* 3.
* */


package vn.edu.rmit.chordion;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class ChordsAdapter extends RecyclerView.Adapter<ChordsAdapter.ChordViewHolder> {

    private ArrayList<Chord> chords;
    private Context context;

    public ChordsAdapter(ArrayList<Chord> chords, AppCompatActivity activity) {
        this.chords = chords;
        this.context = activity;
    }

    @Override
    public int getItemCount() {
        return chords.size();
    }

    @NonNull
    @Override
    public ChordViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.chord_row, parent, false);

        return new ChordViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChordViewHolder holder, int position) {
        Chord chordItem = chords.get(position);
        holder.bind(chordItem);
        
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ChordDetailActivity.class);

                intent.putExtra("shortName", chordItem.getShortName());
                intent.putExtra("fullName", chordItem.getFullName());
                intent.putExtra("type", chordItem.getType());
                intent.putExtra("instrument", chordItem.getInstrument());
                intent.putExtra("rating", chordItem.getRating());
                intent.putExtra("imageId", chordItem.getImageId());
                intent.putExtra("difficulty", chordItem.getDifficulty());
                intent.putExtra("description", chordItem.getDescription());
                intent.putExtra("isFavourite", chordItem.isFavourite());
                intent.putStringArrayListExtra("genres", chordItem.getGenres());

                if (chordItem.getCreatedDate() != null) {
                    intent.putExtra("createdDate", chordItem.getCreatedDate().getTime());
                }

                context.startActivity(intent);
            }
        });
    }

    static class ChordViewHolder extends RecyclerView.ViewHolder {

        private CardView chordCard;
        private TextView chordShortName;
        private TextView chordType;
        private ImageView chordThumbnail;
        private LinearLayout genresLayout;
        private ImageButton favouriteBtn;
        private FrameLayout difficultyTag;

        public ChordViewHolder(@NonNull View itemView) {
            super(itemView);
            chordCard = itemView.findViewById(R.id.chordCard); //
            chordShortName = itemView.findViewById(R.id.chordShortName); //
            chordType = itemView.findViewById(R.id.chordType); //
            chordThumbnail = itemView.findViewById(R.id.chordThumbnail); //
            genresLayout = itemView.findViewById(R.id.genresLayout); //
            favouriteBtn = itemView.findViewById(R.id.favouriteBtn); //
            difficultyTag = itemView.findViewById(R.id.difficultyTag);
        }

        public void bind(Chord chord) {
            chordShortName.setText(chord.getShortName());

            String chordTypeTxt = String.format("(%s)",chord.getType());

            chordType.setText(chordTypeTxt);
            chordThumbnail.setImageResource(getThumbnailResource(chord.getInstrument()));
            favouriteBtn.setImageResource(R.drawable.heart_outlined);

            // Dynamically add genres TextView
            genresLayout.removeAllViews(); // Clear any previous views
            for (String genre : chord.getGenres()) {
                TextView genreTextView = new TextView(itemView.getContext());
                genreTextView.setText(genre);
                genreTextView.setBackgroundResource(R.drawable.genre_background);

                ViewGroup.MarginLayoutParams marginLayoutParams = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                marginLayoutParams.setMarginEnd(12);
                genreTextView.setLayoutParams(marginLayoutParams);
                genreTextView.setPadding(30,0, 30,5);

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    Typeface typeface = itemView.getContext().getResources().getFont(R.font.source_sans_pro);
                    genreTextView.setTypeface(typeface);
                }


                genresLayout.addView(genreTextView);
            }

            if (chord.isFavourite()) {
                favouriteBtn.setImageResource(R.drawable.heart_filled);
            }

            difficultyTag.setBackgroundResource(getDifficultyColor(chord.getDifficulty()));

            favouriteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    chord.setFavourite(!chord.isFavourite());

                    if (chord.isFavourite()) {
                        favouriteBtn.setImageResource(R.drawable.heart_filled);
                    } else {
                        favouriteBtn.setImageResource(R.drawable.heart_outlined);
                    }
                }
            });
        }

        private int getThumbnailResource(String instrument) {
            switch (instrument.toLowerCase()) {
                case "piano":
                    return R.drawable.piano;
                case "flute":
                    return R.drawable.flute;
                case "guitar":
                    return R.drawable.guitar;
                case "ukulele":
                    return R.drawable.ukulele;
                case "keyboard":
                    return R.drawable.keyboard;
                default:
                    return R.drawable.ic_launcher_background; // Use a default image if the instrument doesn't match
            }
        }

        private int getDifficultyColor(String difficulty) {
            switch (difficulty.toLowerCase()) {
                case "easy":
                    return R.color.easy;
                case "intermediate":
                    return R.color.intermediate;
                case "hard":
                    return R.color.hard;
                default:
                    return R.color.white;
            }
        }
    }

}
