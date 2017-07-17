package com.dreamproject.entity;

import com.dreamproject.config.WebConfig;

import javax.persistence.*;

@Entity
@Table(name = WebConfig.PREFIX + "image_object")
public class ImageObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;

    @ManyToOne
    @JoinColumn(name = "target_object_id")
    private TargetObject targetObject;

    public ImageObject(String name, TargetObject targetObject) {
        this.name = name;
        this.targetObject = targetObject;
    }

    public ImageObject() {
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

    public TargetObject getTargetObject() {
        return targetObject;
    }

    public void setTargetObject(TargetObject targetObject) {
        this.targetObject = targetObject;
    }

    @Override
    public String toString() {
        return "ImageObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", targetObject=" + targetObject +
                '}';
    }
}
