package com.teamorebro.diaryteamorebro.controllers;

import com.teamorebro.diaryteamorebro.DiaryTeamorebroApplication;
import com.teamorebro.diaryteamorebro.models.Entry;
import com.teamorebro.diaryteamorebro.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    private EntryService entryService;

    @GetMapping("/")
    public String listEntries(Model model){
        model.addAttribute("entries", entryService.getAllEntries());
        return "/index";
    }

    @GetMapping("/add")
    public String addNewEntry() {
        return "addEntry";
    }

    @GetMapping("/edit")
    public String editEntry(@RequestParam int id, Model model) {
        model.addAttribute("entry", entryService.getEntry(id));
        return "editEntry";
    }
}
