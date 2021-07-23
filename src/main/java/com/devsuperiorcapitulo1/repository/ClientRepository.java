package com.devsuperiorcapitulo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.devsuperiorcapitulo1.entities.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

}
