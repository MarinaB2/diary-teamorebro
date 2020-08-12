package com.teamorebro.diaryteamorebro.controllers;

import com.teamorebro.diaryteamorebro.models.CommonResponse;
import com.teamorebro.diaryteamorebro.models.Entry;
import com.teamorebro.diaryteamorebro.repository.EntryRepository;
import com.teamorebro.diaryteamorebro.utils.Command;
import com.teamorebro.diaryteamorebro.utils.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Optional;

@RestController
public class EntryController {

    @Autowired
    private EntryRepository entryRepository;

    @GetMapping("/entry")
    public ResponseEntity<CommonResponse> entryRoot(HttpServletRequest request) {
        Command cmd = new Command(request);

        //process
        CommonResponse cr = new CommonResponse();
        cr.data = null;
        cr.message = "Not implemented";

        HttpStatus resp = HttpStatus.NOT_IMPLEMENTED;

        //log and return
        cmd.setResult(resp);
        Logger.getInstance().logCommand(cmd);
        return new ResponseEntity<>(cr, resp);
    }

    @GetMapping("/entry/all")
    public ResponseEntity<CommonResponse> getAllEntries(HttpServletRequest request) {
        Command cmd = new Command(request);

        //process
        CommonResponse cr = new CommonResponse();
        cr.data = entryRepository.findAll(Sort.by(Sort.Direction.DESC, "published"));
        cr.message = "All entries";

        HttpStatus resp = HttpStatus.OK;

        //log and return
        cmd.setResult(resp);
        Logger.getInstance().logCommand(cmd);
        return new ResponseEntity<>(cr, resp);
    }

    @GetMapping("/entry/{id}")
    public ResponseEntity<CommonResponse> getEntryById(HttpServletRequest request, @PathVariable Integer id) {
        Command cmd = new Command(request);

        //process
        CommonResponse cr = new CommonResponse();
        HttpStatus resp;

        if (entryRepository.existsById(id)) {
            cr.data = entryRepository.findById(id);
            cr.message = "Entry with id: " + id;
            resp = HttpStatus.OK;
        } else {
            cr.data = null;
            cr.message = "Entry not found";
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        cmd.setResult(resp);
        Logger.getInstance().logCommand(cmd);
        return new ResponseEntity<>(cr, resp);
    }

    @PostMapping("/entry")
    public ResponseEntity<CommonResponse> addEntry(HttpServletRequest request, @RequestBody Entry entry) {
        Command cmd = new Command(request);

        //process
        entry = entryRepository.save(entry);

        CommonResponse cr = new CommonResponse();
        cr.data = entry;
        cr.message = "New entry with id: " + entry.id;

        HttpStatus resp = HttpStatus.CREATED;

        //log and return
        cmd.setResult(resp);
        Logger.getInstance().logCommand(cmd);
        return new ResponseEntity<>(cr, resp);
    }

    @PatchMapping("/entry/{id}")
    public ResponseEntity<CommonResponse> updateEntry(HttpServletRequest request, @RequestBody Entry newEntry, @PathVariable Integer id) {
        Command cmd = new Command(request);

        //process
        CommonResponse cr = new CommonResponse();
        HttpStatus resp;

        if (entryRepository.existsById(id)) {
            Optional<Entry> entryRepo = entryRepository.findById(id);
            Entry entry = entryRepo.get();

            if (newEntry.title != null) {
                entry.title = newEntry.title;
            }
            if (newEntry.content != null) {
                entry.content = newEntry.content;
            }
            if (newEntry.published != null) {
                entry.published = newEntry.published;
            }
            if (newEntry.image != null) {
                entry.image = newEntry.image;
            }

            entryRepository.save(entry);

            cr.data = entry;
            cr.message = "Updated entry with id: " + entry.id;
            resp = HttpStatus.OK;
        } else {
            cr.message = "Entry not found with id: " + id;
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        cmd.setResult(resp);
        Logger.getInstance().

                logCommand(cmd);
        return new ResponseEntity<>(cr, resp);
    }

    @PutMapping("/entry/{id}")
    public ResponseEntity<CommonResponse> replaceEntry(HttpServletRequest request, @RequestBody Entry newEntry, @PathVariable Integer id) {
        Command cmd = new Command(request);

        //process
        CommonResponse cr = new CommonResponse();
        HttpStatus resp;

        if (entryRepository.existsById(id)) {
            Optional<Entry> entryRepo = entryRepository.findById(id);
            Entry entry = entryRepo.get();

            entry.title = newEntry.title;
            entry.content = newEntry.content;
            entry.published = newEntry.published;
            entry.image = newEntry.image;

            entryRepository.save(entry);

            cr.data = entry;
            cr.message = "Replaced entry with id: " + entry.id;
            resp = HttpStatus.OK;
        } else {
            cr.message = "Entry not found with id: " + id;
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        cmd.setResult(resp);
        Logger.getInstance().logCommand(cmd);
        return new ResponseEntity<>(cr, resp);
    }

    @DeleteMapping("/entry/{id}")
    public ResponseEntity<CommonResponse> deleteEntry(HttpServletRequest request, @PathVariable Integer id) {
        Command cmd = new Command(request);

        //process
        CommonResponse cr = new CommonResponse();
        HttpStatus resp;

        if (entryRepository.existsById(id)) {
            entryRepository.deleteById(id);
            cr.message = "Deleted entry with id: " + id;
            resp = HttpStatus.OK;
        } else {
            cr.message = "entry not found with id: " + id;
            resp = HttpStatus.NOT_FOUND;
        }

        //log and return
        cmd.setResult(resp);
        Logger.getInstance().logCommand(cmd);
        return new ResponseEntity<>(cr, resp);
    }

}
