package com.dreamproject.entity;

import com.dreamproject.config.WebConfig;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.apache.tomcat.jni.Local;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

@Entity
@Table(name = WebConfig.PREFIX + "target_object")
public class TargetObject {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "type_object_id")
    private TypeObject typeObject;

    @OneToMany(mappedBy = "targetObject", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<ImageObject> imageObject;

    private double latitude;
    private double longitude;
    private String comment;
    private Calendar date;
    private boolean draggable = false;
    private boolean positionIsChanged = false;
    private String statusObject = "lost";

    @Column(name = "create_at")
    private Date createdAt;

    @Column(name = "update_at")
    private Date updatedAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonBackReference
    private User user;

    public TargetObject() {
    }

    public TargetObject(TypeObject typeObject, double latitude, double longitude, String comment, User user, Calendar date) {
        this.typeObject = typeObject;
        this.latitude = latitude;
        this.longitude = longitude;
        this.comment = comment;
        this.date = date;
        this.user = user;
    }


    @PrePersist
    public void onPrePersist(){
        createdAt = new Date();
    }

    @PreUpdate
    public void onPreOpdate(){
        updatedAt = new Date();
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TypeObject getTypeObject() {
        return typeObject;
    }

    public void setTypeObject(TypeObject typeObject) {
        this.typeObject = typeObject;
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

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Calendar getDate() {
        return date;
    }

    public void setDate(Calendar date) {
        this.date = date;
    }

    public boolean isDraggable() {
        return draggable;
    }

    public void setDraggable(boolean draggable) {
        this.draggable = draggable;
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public String getStatusObject() {
        return statusObject;
    }

    public void setStatusObject(String statusObject) {
        this.statusObject = statusObject;
    }

    @Override
    public String toString() {
        return "TargetObject{" +
                "id=" + id +
                ", typeObject=" + typeObject +
                ", imageObject=" + imageObject +
                ", latitude=" + latitude +
                ", longitude=" + longitude +
                ", comment='" + comment + '\'' +
                ", date=" + date +
                ", draggable=" + draggable +
                ", positionIsChanged=" + positionIsChanged +
                ", statusObject='" + statusObject + '\'' +
                ", createdAt=" + createdAt +
                ", updatedAt=" + updatedAt +
                ", user=" + user +
                '}';
    }
}
