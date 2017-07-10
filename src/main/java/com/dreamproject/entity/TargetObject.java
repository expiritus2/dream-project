package com.dreamproject.entity;

import com.dreamproject.config.WebConfig;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;

@Entity
@Table(name = WebConfig.PREFIX + "target_object")
public class TargetObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String fileName;
    private double latitude;
    private double longitude;
    private boolean draggable = false;
    private boolean positionIsChanged = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "type_object_id")
    private TypeObject typeObject;

    public TargetObject() {
    }


    public TargetObject(TypeObject typeObject, String fileName, double latitude, double longitude, User user) {
        this.typeObject = typeObject;
        this.fileName = fileName;
        this.latitude = latitude;
        this.longitude = longitude;
        this.user = user;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public boolean isPositionIsChanged() {
        return positionIsChanged;
    }

    public void setPositionIsChanged(boolean positionIsChanged) {
        this.positionIsChanged = positionIsChanged;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
    }

    @Override
    public String toString() {
        return "TargetObject{" +
                "id=" + id +
                ", fileName='" + fileName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", draggable=" + draggable +
                ", positionIsChanged=" + positionIsChanged +
                ", user=" + user +
                ", typeObject=" + typeObject +
                '}';
    }
}
