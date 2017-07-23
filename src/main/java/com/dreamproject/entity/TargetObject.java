package com.dreamproject.entity;

import com.dreamproject.config.WebConfig;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = WebConfig.PREFIX + "target_object")
public class TargetObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @OneToMany(mappedBy = "targetObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageObject> imageObject;

    private double latitude;
    private double longitude;
    private boolean draggable = false;
    private boolean positionIsChanged = false;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    @ManyToOne
    @JoinColumn(name = "type_object_id")
    private TypeObject typeObject;

    public TargetObject() {
    }


    public TargetObject(TypeObject typeObject, double latitude, double longitude, User user) {
        this.typeObject = typeObject;
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

    public List<ImageObject> getImageObject() {
        return imageObject;
    }

    public void setImageObject(List<ImageObject> imageObject) {
        this.imageObject = imageObject;
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
                ", imageObject=" + imageObject +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", draggable=" + draggable +
                ", positionIsChanged=" + positionIsChanged +
                ", user=" + user +
                ", typeObject=" + typeObject +
                '}';
    }
}
