/*
  RMIT University Vietnam
  Course: COSC2657 - Android Development
  Semester: 2024C
  Assessment: Assignment 1
  Author: Nguyen Anh Duy
  ID: s3878141
  Created  date: 12/11/2024
  Last modified: 17/11/2024
*/

package vn.edu.rmit.chordion;

import java.util.ArrayList;
import java.util.Date;

public class Chord {
    private String shortName;
    private String fullName;
    private String type;
    private String instrument;
    private int rating;
    private int imageId;
    private String difficulty;
    private String description;
    private ArrayList<String> genres;
    private boolean isFavourite;

    public ArrayList<String> getGenres() {
        return genres;
    }

    public void setGenres(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }



    public Chord() {
    }

    ;

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getInstrument() {
        return instrument;
    }

    public void setInstrument(String instrument) {
        this.instrument = instrument;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getImageId() {
        return imageId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isFavourite() {
        return isFavourite;
    }

    public void setFavourite(boolean favourite) {
        isFavourite = favourite;
    }
}
