package com.app.timetrack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.timetrack.entity.ClientTable;

public interface ClientRepository extends JpaRepository<ClientTable, Long> {

}
