package com.mycompany.webapp.controller;

import java.util.List;

/**
 * Created by liaoxiang on 2016/4/15.
 */
public class Pagination {
    private int total;
    private List rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List getRows() {
        return rows;
    }

    public void setRows(List rows) {
        this.rows = rows;
    }
}
