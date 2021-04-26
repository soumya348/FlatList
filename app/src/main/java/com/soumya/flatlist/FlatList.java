package com.soumya.flatlist;

public class FlatList {
    String count;
    String title;
    String first;
    String last;
    public FlatList(String count, String title, String first, String last) {
        this.count = count;
        this.title = title;
        this.first = first;
        this.last = last;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public void setFirst(String first) {
        this.first = first;
    }

    public void setLast(String last) {
        this.last = last;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFirst() {
        return first;
    }

    public String getLast() {
        return last;
    }

    public String getTitle() {
        return title;
    }
}
