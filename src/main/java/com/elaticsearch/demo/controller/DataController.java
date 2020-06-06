package com.elaticsearch.demo.controller;

import com.elaticsearch.demo.entity.es.EsArticle;
import com.elaticsearch.demo.entity.mysql.MysqlArticle;
import com.elaticsearch.demo.repository.es.EsArticleRepository;
import com.elaticsearch.demo.repository.mysql.MysqlRepository;
import lombok.Data;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.util.StopWatch;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class DataController {

    private final EsArticleRepository esArticleRepository;

    private final MysqlRepository mysqlRepository;

    @Autowired
    public DataController(EsArticleRepository esArticleRepository, MysqlRepository mysqlRepository) {
        this.esArticleRepository = esArticleRepository;
        this.mysqlRepository = mysqlRepository;
    }
    @GetMapping("/list")
    public Object list() {
        List<MysqlArticle> mysqlArticles = mysqlRepository.queryAll();
        return mysqlArticles;
    }

    @GetMapping("/blog/{id}")
    public Object getArticleById(@PathVariable("id")Integer id) {
        MysqlArticle one = mysqlRepository.getOne(id);
        return one;
    }

    @PostMapping("/search")
    public Object search(@RequestBody Param param) {
        Map<String, Object> map = new HashMap<>();
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        String type = param.getType();
        if("mysql".equals(type)) {
            // mysql
            List<MysqlArticle> list = mysqlRepository.queryMysqlArticles(param.getKeyword());
            map.put("list", list);
        } else if("es".equals(type)) {
            // es
//            POST blog/_search
//            {
//                "query": {
//                "bool": {
//                    "should": [
//                    {
//                        "match_phrase": {
//                        "problem_desp": " "
//                    }
//                    },
//                    {
//                        "match_phrase": {
//                        "cloud_id": "abcd"
//                    }
//                    }
//      ]
//                }
//            }
//            }
            BoolQueryBuilder builder = QueryBuilders.boolQuery();
            builder.should(QueryBuilders.matchPhraseQuery("problem_desp", param.getKeyword()));
            builder.should(QueryBuilders.matchPhraseQuery("cloud_id", param.getKeyword()));
            String s = builder.toString();
            Page<EsArticle> search = (Page<EsArticle>) esArticleRepository.search(builder);
            List<EsArticle> content = search.getContent();
            map.put("list", content);

        } else {
            return "i don't understand";
        }

        stopWatch.stop();
        long totalTimeMillis = stopWatch.getTotalTimeMillis();
        map.put("duration", totalTimeMillis);
        return map;
    }


    @Data
    public static class  Param{
        private String type;
        private String keyword;
    }
}
