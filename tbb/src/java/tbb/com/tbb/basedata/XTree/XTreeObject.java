package com.tbb.basedata.XTree;

/**
 * 简单树结点类：通过维护数结点的父（parent）子（children）而进行树操作的类，
 */
public class XTreeObject {
    /** The composite primary key value. */
    private java.lang.String code;

    /** The value of the simple xh property. */
    private java.lang.Integer xh;

    /** The value of the simple levels property. */
    private java.lang.Integer levels;

    /** The value of the simple name property. */
    private java.lang.String name;

    /** The value of the simple len property. */
    private java.lang.Integer len;

    /** The value of the simple farthercode property. */
    private java.lang.String farthercode;

    private boolean checked;

    public String url;

    public String target;

    public boolean checkbox;

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public XTreeObject(Object object) {

    }

    public XTreeObject() {

    }

    /**
     * Return the simple primary key value that identifies this object.
     * @return java.lang.String
     */
    public java.lang.String getCode() {
        return code;
    }

    /**
     * Set the simple primary key value that identifies this object.
     * @param code
     */
    public void setCode(java.lang.String code) {
        this.code = code;
    }

    /**
     * Return the value of the xh column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getXh() {
        return this.xh;
    }

    /**
     * Set the value of the xh column.
     * @param xh
     */
    public void setXh(java.lang.Integer xh) {
        this.xh = xh;
    }

    /**
     * Return the value of the levels column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getLevels() {
        return this.levels;
    }

    /**
     * Set the value of the levels column.
     * @param levels
     */
    public void setLevels(java.lang.Integer levels) {
        this.levels = levels;
    }

    /**
     * Return the value of the name column.
     * @return java.lang.String
     */
    public java.lang.String getName() {
        return this.name;
    }

    /**
     * Set the value of the name column.
     * @param name
     */
    public void setName(java.lang.String name) {
        this.name = name;
    }

    /**
     * Return the value of the len column.
     * @return java.lang.Integer
     */
    public java.lang.Integer getLen() {
        return this.len;
    }

    /**
     * Set the value of the len column.
     * @param len
     */
    public void setLen(java.lang.Integer len) {
        this.len = len;
    }

    /**
     * Return the value of the farthercode column.
     * @return java.lang.String
     */
    public java.lang.String getFarthercode() {
        return this.farthercode;
    }

    /**
     * Set the value of the farthercode column.
     * @param farthercode
     */
    public void setFarthercode(java.lang.String farthercode) {
        this.farthercode = farthercode;
    }

}
