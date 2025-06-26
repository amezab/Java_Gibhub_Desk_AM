package com.medialist.service;
import java.util.ArrayList;
import java.util.List;
import com.medialist.model.Media;

public class MediaService {
    private ArrayList<Media> mediaArrayList;

    public MediaService() {
        this.mediaArrayList = new ArrayList<>();//constructor for the arraylist
    }

    public void addMedia(Media media){
        //Using the add method adds the media object to the list
       this.mediaArrayList.add(media);

    }

    public boolean removeMedia(String name){
        return false;
    }

    public Media findMediaByName(String name){
       return null;

    }

    public List<Media> getAllMedia(){
        return new ArrayList<>(this.mediaArrayList);
    }

    public int getMediacount(){
        return 0;
    }

    public boolean isEmpty(){
        return false;

    }
}
