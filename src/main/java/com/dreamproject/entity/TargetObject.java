package com.dreamproject.entity;

import com.dreamproject.config.WebConfig;

import javax.persistence.*;

@Entity
@Table(name = WebConfig.PREFIX + "search_object")
public class TargetObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String fileName;
    private double latitude;
    private double longitude;
    private boolean draggable = false;
    private boolean positionIsChanged = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

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

    @Override
    public String toString() {
        return "TargetObject{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", fileName='" + fileName + '\'' +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", draggable=" + draggable +
                ", positionIsChanged=" + positionIsChanged +
                ", user=" + user +
                '}';
    }
}
