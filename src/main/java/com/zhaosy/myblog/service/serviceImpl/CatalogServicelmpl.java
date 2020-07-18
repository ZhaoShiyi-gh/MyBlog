package com.zhaosy.myblog.service.serviceImpl;

import com.zhaosy.myblog.dao.CatalogDao;
import com.zhaosy.myblog.dao.DaoImpl.CatalogDaoImpl;
import com.zhaosy.myblog.domain.Catalog;
import com.zhaosy.myblog.service.CatalogService;

import java.util.ArrayList;
import java.util.List;

public class CatalogServicelmpl implements CatalogService {
    @Override
    public int findCatalogId(String catalogNmae) {
        int catalogId = 0;

        CatalogDao cd = new CatalogDaoImpl();
        catalogId = cd.findCatalogId(catalogNmae);

        return catalogId;
    }

    @Override
    public List<Catalog> findCatalog() {
        List<Catalog> catalogs = new ArrayList<>();
        CatalogDao cd = new CatalogDaoImpl();
        catalogs = cd.findCatalog();
        return catalogs;

    }

    @Override
    public Boolean insertCatalog(String catalogName, String catalogCase) {
        Boolean flag = false;
        CatalogDao cd = new CatalogDaoImpl();
        flag = cd.insertCatalog(catalogName, catalogCase);
        return flag;
    }
}
