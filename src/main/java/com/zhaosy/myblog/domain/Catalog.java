package com.zhaosy.myblog.domain;

public class Catalog {
    private int catalogId;
    private String catalogName;
    private String catalogCase;

    public int getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(int catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public String getCatalogCase() {
        return catalogCase;
    }

    public void setCatalogCase(String catalogCase) {
        this.catalogCase = catalogCase;
    }
}
