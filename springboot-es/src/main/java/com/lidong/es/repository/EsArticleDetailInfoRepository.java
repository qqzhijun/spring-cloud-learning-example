package com.lidong.es.repository;


import com.lidong.es.entity.EsArticleDetailInfoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;


public interface  EsArticleDetailInfoRepository extends ElasticsearchRepository<EsArticleDetailInfoEntity, Integer> {

    Page<EsArticleDetailInfoEntity> findEsArticleDetailInfoEntityByTitle(String title, Pageable pageable);
}
