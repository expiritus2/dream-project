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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}
