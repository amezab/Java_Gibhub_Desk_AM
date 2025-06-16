package com.medialist.model;

public class Image extends Media{
    private String imgDimensions;
    private String fileFormat;

    public Image(String name, String imgDimensions, String imgFormat) {
        super(name);
        this.imgDimensions = imgDimensions;
        this.fileFormat = imgFormat;
    }

//    public String getImgDimensions() {
//        return imgDimensions;
//    }
//
//    public void setImgDimensions(String imgDimensions) {
//        this.imgDimensions = imgDimensions;
//    }
//
//    public String getImgFormat() {
//        return fileFormat;
//    }
//
//    public void setImgFormat(String imgFormat) {
//        this.fileFormat = imgFormat;
//    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public void play() {

    }

    @Override
    public String getDescription() {
        return "Image '" + getName() + "' - dimensions " + imgDimensions + " file type " + fileFormat;
    }
}