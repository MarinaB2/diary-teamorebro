package com.teamorebro.diaryteamorebro.services;

import com.teamorebro.diaryteamorebro.models.Entry;
import com.teamorebro.diaryteamorebro.repository.EntryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.util.*;

@Service
public class EntryService {

    @Autowired
    DataSource dataSource;

    @Autowired
    EntryRepository entryRepository;

    public EntryService() {

    }

    public Entry getEntry(int id) {
        return entryRepository.findById(id).get();
    }

    public List<Entry> getAllEntries() {
        return entryRepository.findAll(Sort.by(Sort.Direction.DESC, "published"));
    }

    public void deleteEntry(int id) {
        entryRepository.deleteById(id);
    }
}
