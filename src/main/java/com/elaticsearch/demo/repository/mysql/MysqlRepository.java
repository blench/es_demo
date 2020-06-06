package com.elaticsearch.demo.repository.mysql;

import com.elaticsearch.demo.entity.mysql.MysqlArticle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MysqlRepository extends JpaRepository<MysqlArticle, Integer> {

    @Query("select e from MysqlArticle e order by  e.createDate desc")
    List<MysqlArticle> queryAll();

    @Query("select e from MysqlArticle e where e.problemDesp " +
            "like concat('%',:keyword,'%') or e.cloudId like concat('%',:keyword,'%') order by  e.createDate desc")
    List<MysqlArticle> queryMysqlArticles(@Param("keyword")String keyword);
}
