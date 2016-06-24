package com.mycompany.test.velocity;

/**
 * Created by liaoxiang on 2016/6/8.
 */
public class Attribute {
    private String name;
    private String nameUpCase;
    private String type;
    private boolean index;
    private String columnName;
    private boolean columnNullable;
    private boolean columnUnique;
    private boolean fieldValue;

    public Attribute(){}

    public Attribute(String name, String nameUpCase, String type, boolean index, String columnName, boolean columnNullable, boolean columnUnique, boolean fieldValue ){
        this.name = name;
        this.nameUpCase = nameUpCase;
        this.type = type;
        this.index = index;
        this.columnName = columnName;
        this.columnNullable = columnNullable;
        this.columnUnique = columnUnique;
        this.fieldValue = fieldValue;

    }

    public Attribute(String name, String nameUpCase, String type) {
        this.name = name;
        this.nameUpCase = nameUpCase;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameUpCase() {
        return nameUpCase;
    }

    public void setNameUpCase(String nameUpCase) {
        this.nameUpCase = nameUpCase;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isIndex() {
        return index;
    }

    public void setIndex(boolean index) {
        this.index = index;
    }

    public String getColumnName() {
        return columnName;
    }

    public void setColumnName(String columnName) {
        this.columnName = columnName;
    }

    public boolean isColumnNullable() {
        return columnNullable;
    }

    public void setColumnNullable(boolean columnNullable) {
        this.columnNullable = columnNullable;
    }

    public boolean isColumnUnique() {
        return columnUnique;
    }

    public void setColumnUnique(boolean columnUnique) {
        this.columnUnique = columnUnique;
    }

    public boolean isFieldValue() {
        return fieldValue;
    }

    public void setFieldValue(boolean fieldValue) {
        this.fieldValue = fieldValue;
    }
}
