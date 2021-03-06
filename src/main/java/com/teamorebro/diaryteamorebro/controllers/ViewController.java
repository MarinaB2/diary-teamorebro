package com.teamorebro.diaryteamorebro.controllers;

import com.teamorebro.diaryteamorebro.models.Entry;
import com.teamorebro.diaryteamorebro.services.EntryService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;


@Controller
public class ViewController {

    @Autowired
    private EntryService entryService;

    @GetMapping("/")
    public String listEntries(Model model){
        model.addAttribute("entries", entryService.getAllEntries());
        return "index";
    }

    @GetMapping("/entry/image/{id}")
    public void showProductImage(@PathVariable int id,
                                 HttpServletResponse response) throws IOException {
        response.setContentType("image/png"); // Or whatever format you wanna use
        Entry entry = entryService.getEntry(id);
        if(!(entry.image == null)){
            InputStream is = new ByteArrayInputStream(entry.image);
            IOUtils.copy(is, response.getOutputStream());
        }
    }

    @GetMapping("/newEntry")
    public String getAddNewEntryPage() {
        return "addEntry";
    }

    @RequestMapping(value = "/add", method = {RequestMethod.POST, RequestMethod.GET} , consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addNewEntry(HttpServletResponse response, @RequestParam String title, String content, MultipartFile image) throws IOException {
        Entry entry = new Entry();
        entry.title = title;
        entry.content = content;
        entry.published = new Date();

        if(image != null) {
            entry.image = image.getBytes();
        }

        entryService.addEntry(entry);
        response.sendRedirect("/");
    }

    @GetMapping("/edit")
    public String editEntry(@RequestParam int id, Model model) {
        Entry entry = entryService.getEntry(id);
        model.addAttribute("entry", entry);
        return "editEntry";
    }

    @RequestMapping(value = "/save", method = {RequestMethod.POST}, consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void saveChanges(HttpServletResponse response, @RequestParam int id, String title, String content, MultipartFile image) throws IOException {
        Entry entry = entryService.getEntry(id);
        entry.title = title;
        entry.content = content;

        if(image != null) {
            entry.image = image.getBytes();
        }

        entryService.updateEntry(entry);
        response.sendRedirect("/");
    }

    @RequestMapping(value = "/delete", method = {RequestMethod.DELETE, RequestMethod.GET})
    public void deleteEntry(HttpServletResponse response, @RequestParam int id) throws IOException {
        entryService.deleteEntry(id);
        response.sendRedirect("/");
    }

}
