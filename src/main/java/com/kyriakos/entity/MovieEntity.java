package com.kyriakos.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Movie")
public class MovieEntity {

    @Id
    @Column
    private int id;
    @Column
    private String name;
    @Column
    private String type;
    @Column
    private Integer price;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getType() {
        return type;
    }
    public void setType(String type) {
        this.type = type;
    }
    public Integer getPrice() {return price;}
    public void setPrice(Integer price) {this.price = price;}

}