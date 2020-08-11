package com.teamorebro.diaryteamorebro.controllers;

import com.teamorebro.diaryteamorebro.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

}
