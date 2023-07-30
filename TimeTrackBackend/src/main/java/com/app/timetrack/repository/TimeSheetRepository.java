package com.app.timetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.timetrack.entity.TimeSheet;

public interface TimeSheetRepository extends JpaRepository<TimeSheet, Long> {

}
