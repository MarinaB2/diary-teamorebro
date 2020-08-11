package com.teamorebro.diaryteamorebro.repository;

import com.teamorebro.diaryteamorebro.models.Entry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryRepository extends JpaRepository<Entry, Integer> {

    Entry getById(String id);
}
