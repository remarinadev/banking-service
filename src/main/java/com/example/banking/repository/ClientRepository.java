package com.example.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking.model.Account;
import com.example.banking.model.Client;
import com.example.banking.model.Currency;

/**
 * Repositorio para las cuentas bancarias, que extiende JpaRepository
 * para proporcionar métodos CRUD básicos.
 */
public interface ClientRepository extends JpaRepository<Client, Long> {

    boolean existsByNroDocumento(String nroDocumento);
    Client findByNroDocumento(String nroDocumento);
}
