package com.elaticsearch.demo;

import com.elaticsearch.demo.entity.es.EsArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Iterator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ElasticsearchDemoApplicationTests {

    @Autowired
    ElasticsearchRepository elasticsearchRepository;

    @Test
    public void contextLoads() {
    }

    @Test
    public void testEs() {
        Iterable<EsArticle> all = elasticsearchRepository.findAll();
        Iterator<EsArticle> iterator = all.iterator();
        EsArticle next = iterator.next();
        System.out.println("-----------------------" + next.getAuthor());
    }

}
