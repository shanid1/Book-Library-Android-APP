package com.example.myapplication;

public class Book {
    private int id,pages;
    private String name, author, shortDesc, longDesc;
    private int imageResId;
    private boolean isExpanded;

    public void setExpanded(boolean expanded) {
        isExpanded = expanded;
    }

    public boolean isExpanded() {
        return isExpanded;
    }

    public Book(int id, int pages, String name, String author, int imageResId, String shortDesc, String longDesc) {
        this.id = id;
        this.pages = pages;
        this.name = name;
        this.author = author;
        this.imageResId = imageResId;
        this.shortDesc = shortDesc;
        this.longDesc = longDesc;
        isExpanded = false;
    }

    public int getId() {
        return id;
    }

    public int getPages() {
        return pages;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public int getImageResId() {
        return imageResId;
    }

    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", pages=" + pages +
                ", name='" + name + '\'' +
                ", author='" + author + '\'' +
                ", shortDesc='" + shortDesc + '\'' +
                ", longDesc='" + longDesc + '\'' +
                ", imageResId=" + imageResId +
                '}';
    }

    public void setImageResId(int imageResId) {
        this.imageResId = imageResId;
    }

    public String getShortDesc() {
        return shortDesc;
    }

    public String getLongDesc() {
        return longDesc;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAuthor(String author) {
        this.author = author;
    }



    public void setShortDesc(String shortDesc) {
        this.shortDesc = shortDesc;
    }

    public void setLongDesc(String longDesc) {
        this.longDesc = longDesc;
    }


}
