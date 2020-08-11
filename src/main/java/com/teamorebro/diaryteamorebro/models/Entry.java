package com.teamorebro.diaryteamorebro.models;


import javax.persistence.*;
import java.util.Date;

@Entity
public class Entry {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    public Integer id;

    @Column(nullable = false)
    public String content;

    @Column(nullable = false)
    public Date published;

    @Column
    public byte[] image;

}

