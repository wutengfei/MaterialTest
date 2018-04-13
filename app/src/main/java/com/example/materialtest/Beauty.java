package com.example.materialtest;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/4/9.
 */

public class Beauty {
    private String name;
    private String url;

    public Beauty(String name, String url) {
        this.url = url;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
