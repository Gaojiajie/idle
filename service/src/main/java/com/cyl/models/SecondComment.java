package com.cyl.models;

import java.util.List;

/**
 * Created by cyl on 2019-03-06.
 */
public class SecondComment extends Comment {
    public SecondComment() {
        super();
    }

    private List<Comment> list;

    public List<Comment> getList() {
        return list;
    }

    public void setList(List<Comment> list) {
        this.list = list;
    }
}
