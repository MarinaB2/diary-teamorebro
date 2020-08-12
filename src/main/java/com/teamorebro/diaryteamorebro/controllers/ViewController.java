package com.teamorebro.diaryteamorebro.controllers;

import com.teamorebro.diaryteamorebro.DiaryTeamorebroApplication;
import com.teamorebro.diaryteamorebro.models.Entry;
import com.teamorebro.diaryteamorebro.services.EntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.Optional;

@Controller
public class ViewController {

    @Autowired
    private EntryService entryService;

    @GetMapping("/")
    public String listEntries(Model model){
        model.addAttribute("entries", entryService.getAllEntries());
        return "index";
    }

    @GetMapping("/newEntry")
    public String getAddNewEntryPage() {
        return "addEntry";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET})
    public void addNewEntry(HttpServletResponse response, @RequestParam String title, String content) throws IOException {
        Entry entry = new Entry();
        entry.title=title;
        entry.content = content;
        entry.published = new Date();

        entryService.addEntry(entry);
        response.sendRedirect("/");
    }

    @GetMapping("/edit")
    public String editEntry(@RequestParam int id, Model model) {
        Entry entry = entryService.getEntry(id);
        model.addAttribute("entry", entry);
        return "editEntry";
    }

    @RequestMapping(value = "/save", method = {RequestMethod.PUT, RequestMethod.GET})
    public void saveChanges(HttpServletResponse response, @RequestParam int id, String title, String content) throws IOException {
        Entry entry = entryService.getEntry(id);
        entry.title = title;
        entry.content = content;

        entryService.addEntry(entry);
        response.sendRedirect("/");
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteEntry(HttpServletResponse response, @RequestParam int id) throws IOException {
        entryService.deleteEntry(id);
        response.sendRedirect("/");
    }
}
