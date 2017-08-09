package com.dreamproject.entity;

import com.dreamproject.config.WebConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = WebConfig.PREFIX + "type_object")
public class TypeObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;


    @OneToMany(mappedBy = "typeObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TargetObject> targetObjects;

    public TypeObject() {
    }

    public TypeObject(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<TargetObject> getTargetObjects() {
        return targetObjects;
    }

    public void setTargetObjects(List<TargetObject> targetObjects) {
        this.targetObjects = targetObjects;
    }

    @Override
    public String toString() {
        return "TypeObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", targetObjects=" + targetObjects +
                '}';
    }
}
