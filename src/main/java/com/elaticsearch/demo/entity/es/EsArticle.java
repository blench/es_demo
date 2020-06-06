package com.elaticsearch.demo.entity.es;


import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.Date;

/**
 * CREATE TABLE `tb_article` (
 *   `article_id` int(255) NOT NULL AUTO_INCREMENT COMMENT '文章id\r\n',
 *   `author` varchar(255) DEFAULT NULL COMMENT '作者',
 *   `create_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
 *   `modification_date` datetime DEFAULT NULL COMMENT '修改时间',
 *   `problem_desp` varchar(255) DEFAULT NULL COMMENT '问题描述/标题',
 *   `work_station_id` int(11) DEFAULT NULL COMMENT '工站id',
 *   `divide_type` varchar(255) DEFAULT NULL COMMENT '设备类型',
 *   `articlebody` longtext COMMENT '文章内容',
 *   `cloud_id` varchar(255) DEFAULT NULL COMMENT '专业云ID',
 *   PRIMARY KEY (`article_id`) USING BTREE
 * ) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;
 */
@Data
@Document(indexName = "blog", type = "_doc", useServerConfiguration = true, createIndex = false)
public class EsArticle {
    @Id
    private Integer articleId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String author;
    @Field(type = FieldType.Date, format = DateFormat.custom,
        pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date createDate;
    @Field(type = FieldType.Date, format = DateFormat.custom,
            pattern = "yyyy-MM-dd HH:mm:ss||yyyy-MM-dd||epoch_millis")
    private Date modificationDate;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String problemDesp;
    @Field(type = FieldType.Integer, analyzer = "ik_max_word")
    private Integer workStationId;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String divideType;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String articleBody;
    @Field(type = FieldType.Text, analyzer = "ik_max_word")
    private String cloudId;
}