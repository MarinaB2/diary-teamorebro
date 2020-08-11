package com.teamorebro.diaryteamorebro.controllers;

import com.teamorebro.diaryteamorebro.DiaryTeamorebroApplication;
import com.teamorebro.diaryteamorebro.models.Entry;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;

@Controller
public class ViewController {

    @GetMapping("/")
    public String listEntries(Model model){
       // ArrayList<Entry> entries = DiaryTeamorebroApplication.;
       // model.addAttribute("entries", entries);
        return "/index";
    }

    @GetMapping("/add")
    public String addNewEntry() {
        return "addEntry";
    }
    @GetMapping("/edit")
    public String editEntry(@RequestParam int id, Model model) {
     //   Entry entry = EntryController.;
      //  model.addAttribute(entry);
        return "editEntry";
    }
}
