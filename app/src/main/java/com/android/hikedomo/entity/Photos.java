package com.android.hikedomo.entity;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Photos implements Serializable {
    @SerializedName("page")
    private int page;
    @SerializedName("pages")
    private int pages;
    @SerializedName("perpage")
    private int perpage;
    @SerializedName("total")
    private String total;
    @SerializedName("photo")
    private List<Photo> imageList;

    public void setPage(int page) {
        this.page = page;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public void setPerpage(int perpage) {
        this.perpage = perpage;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public void setImageList(List<Photo> imageList) {
        this.imageList = imageList;
    }

    public int getPage() {
        return page;
    }

    public int getPages() {
        return pages;
    }

    public int getPerpage() {
        return perpage;
    }

    public String getTotal() {
        return total;
    }

    public List<Photo> getImageList() {
        return imageList;
    }
}

