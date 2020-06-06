package com.elaticsearch.demo.entity.mysql;


import lombok.Data;

import javax.persistence.*;
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
@Table(name="tb_article")
@Entity
public class MysqlArticle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer articleId;
    private String author;
    private Date createDate;
    private Date modificationDate;
    private String problemDesp;
    private Integer workStationId;
    private String divideType;
    @Column(columnDefinition = "longtext")
    private String articleBody;
    private String cloudId;
}