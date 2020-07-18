package com.zhaosy.myblog.service;

import com.zhaosy.myblog.domain.Catalog;

import java.util.List;

public interface CatalogService {
    int findCatalogId(String catalogNmae);

    List<Catalog> findCatalog();

    Boolean insertCatalog(String catalogName, String catalogCase);
}
