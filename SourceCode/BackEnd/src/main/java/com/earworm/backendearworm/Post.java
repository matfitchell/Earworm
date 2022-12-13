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

    public void setDisplayName(String input) {
        this.originalPoster = input;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String input) {
        this.caption = input;
    }

    public void displayComments() {
        for (int i = 0; i < comments.length; i++) {
            System.out.println(comments[i]);
        }
    }
    
    public int getYearPosted() {
        return yearPosted;
    }

    public void setYearPosted(int input) {
        this.yearPosted = input;
    }

    public int getMonthPosted() {
        return monthPosted;
    }

    public void setMonthPosted(int input) {
        this.monthPosted = input;
    }

    public int getdayPosted() {
        return dayPosted;
    }

    public void setDayPosted(int input) {
        this.dayPosted = input;
    }
}
