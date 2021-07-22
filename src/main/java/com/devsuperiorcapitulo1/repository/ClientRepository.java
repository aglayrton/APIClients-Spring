package com.devsuperiorcapitulo1.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.devsuperiorcapitulo1.entities.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
