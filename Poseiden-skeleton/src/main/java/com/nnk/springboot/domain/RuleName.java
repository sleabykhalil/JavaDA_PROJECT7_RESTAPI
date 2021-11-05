package com.nnk.springboot.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "Rulename")
public class RuleName {//fixme ask if i need to change name to Rule
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id")
    Integer id;
    @NotBlank(message = "name is mandatory")
    String name;
    @NotBlank(message = "description is mandatory")
    String description;
    @NotBlank(message = "json is mandatory")
    String json;
    @NotBlank(message = "template is mandatory")
    String template;
    @NotBlank(message = "sqlStr is mandatory")
    String sqlStr;
    @NotBlank(message = "sqlPart is mandatory")
    String sqlPart;

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
