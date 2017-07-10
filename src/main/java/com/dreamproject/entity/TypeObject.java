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

    @Column(name = "name_en")
    private String nameEn;

    @Column(name = "name_ru")
    private String nameRu;

    @OneToMany(mappedBy = "typeObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<TargetObject> targetObjects;

    public TypeObject() {
    }

    public TypeObject(String nameEn) {
        this.nameEn = nameEn;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNameEn() {
        return nameEn;
    }

    public void setNameEn(String nameEn) {
        this.nameEn = nameEn;
    }

    public String getNameRu() {
        return nameRu;
    }

    public void setNameRu(String nameRu) {
        this.nameRu = nameRu;
    }

    public Long getId() {
        return id;
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
                ", nameEn='" + nameEn + '\'' +
                ", nameRu='" + nameRu + '\'' +
                ", targetObjects=" + targetObjects +
                '}';
    }
}
