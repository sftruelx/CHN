package com.mycompany.util;

import java.io.Serializable;
import java.util.List;

/**
 * Created by liaoxiang on 2016/4/15.
 */
public class Pagination  implements Serializable {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Pagination that = (Pagination) o;

        if (total != that.total) return false;
        return rows != null ? rows.equals(that.rows) : that.rows == null;

    }

    @Override
    public int hashCode() {
        int result = total;
        result = 31 * result + (rows != null ? rows.hashCode() : 0);
        return result;
    }
}
