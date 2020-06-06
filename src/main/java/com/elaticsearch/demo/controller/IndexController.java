package com.elaticsearch.demo.controller;

import com.elaticsearch.demo.entity.mysql.MysqlArticle;
import com.elaticsearch.demo.repository.mysql.MysqlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class IndexController {

    private final MysqlRepository mysqlRepository;

    @Autowired
    public IndexController(MysqlRepository mysqlRepository) {
        this.mysqlRepository = mysqlRepository;
    }

    @RequestMapping("/")
    public String index() {
        List<MysqlArticle> all = mysqlRepository.findAll();
        return "index.html";
    }
}
