package com.elaticsearch.demo.repository.es;


import com.elaticsearch.demo.entity.es.EsArticle;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EsArticleRepository extends ElasticsearchRepository<EsArticle, Integer> {
}
