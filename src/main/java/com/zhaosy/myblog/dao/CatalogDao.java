package com.zhaosy.myblog.dao;

import com.zhaosy.myblog.domain.Catalog;
import com.zhaosy.myblog.domain.Setting;

import java.util.List;
import java.util.Map;

public interface CatalogDao {
    Catalog findCatalog(int catalogId);

    List<Catalog> findUserCatalog(String username);

    int findCatalogId(String catalogName);

    List<Catalog> findCatalog();

    Boolean insertCatalog(String catalogName, String catalogCase);
}
