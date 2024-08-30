package com.example.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.banking.model.Account;
import com.example.banking.model.Client;
import com.example.banking.model.Currency;

/**
 * Repositorio para las cuentas bancarias, que extiende JpaRepository
 * para proporcionar métodos CRUD básicos.
 */
public interface AccountRepository extends JpaRepository<Account, Long> {

    /**
     * Verifica si ya existe una cuenta bancaria para un cliente y una moneda específica.
     * @param nroCuenta El numero de la cuenta.
     * @param currency La moneda de la cuenta.
     * @return true si ya existe una cuenta, de lo contrario false.
     */
    boolean existsByNroCuentaAndCurrency(String nroCuenta, Currency currency);
}
