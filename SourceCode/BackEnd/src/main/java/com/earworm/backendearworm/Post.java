package com.earworm.backendearworm;
public class Post {
    private String originalPoster = " ";
    private String caption = " ";
    private String comments[] = new String[] {"This", "is", "a", "post", "comment"};
    private int yearPosted = 0;
    private int monthPosted = 0;
    private int dayPosted = 0;

    //Constructor
    public Post(String originalPoster, String caption, int yearPosted, int monthPosted, int dayPosted) {
        this.originalPoster = originalPoster;
        this.caption = caption;
        this.yearPosted = yearPosted;
        this.monthPosted = monthPosted;
        this.dayPosted = dayPosted;
    }

    public String getOriginalPoster() {
        return originalPoster;
    }

    public String setDisplayName() {
        this.originalPoster = originalPoster;
    }

    public String getCaption() {
        return caption;
    }

    public String setCaption() {
        this.caption = caption;
    }

    public void displayComments() {
        for (int i = 0; i < comments.length; i++) {
            System.out.println(comments[i]);
        }
    }
    
    public int getYearPosted() {
        return yearPosted;
    }

    public int setYearPosted() {
        this.yearPosted = yearPosted;
    }

    public int getMonthPosted() {
        return monthPosted;
    }

    public int setMonthPosted() {
        this.monthPosted = monthPosted;
    }

    public int getdayPosted() {
        return dayPosted;
    }

    public int setDayPosted() {
        this.dayPosted = dayPosted;
    }
}
