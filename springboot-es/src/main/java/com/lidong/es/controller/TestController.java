package com.lidong.es.controller;

import com.lidong.es.entity.EsArticleDetailInfoEntity;
import com.lidong.es.repository.EsArticleDetailInfoRepository;
import io.swagger.annotations.Api;
import org.elasticsearch.index.query.QueryStringQueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Iterator;

@Api("test")
@RestController
public class TestController {

    @Autowired
    private EsArticleDetailInfoRepository articleSearchRepository;

    @RequestMapping("/add")
    public void testSaveArticleIndex() {
        EsArticleDetailInfoEntity article = new EsArticleDetailInfoEntity();
        articleSearchRepository.save(article);
    }

    @RequestMapping("/query")
    public void testSearch() {
        String queryString = "健康";//搜索关键字
        QueryStringQueryBuilder builder = new QueryStringQueryBuilder(queryString);
        Iterable<EsArticleDetailInfoEntity> searchResult = articleSearchRepository.findEsArticleDetailInfoEntityByTitle("健康", PageRequest.of(1, 10));
        Iterator<EsArticleDetailInfoEntity> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            EsArticleDetailInfoEntity infoEntity = iterator.next();
            System.out.println(infoEntity.getTitle());
        }
    }

    @RequestMapping("/getAll")
    public void getAllEsArticleDetailInfoEntity() {
        Iterable<EsArticleDetailInfoEntity> searchResult = articleSearchRepository.findAll();
        Iterator<EsArticleDetailInfoEntity> iterator = searchResult.iterator();
        while (iterator.hasNext()) {
            EsArticleDetailInfoEntity  entity =   iterator.next();
            System.out.println("标题："+entity.getTitle());
        }
    }
}
