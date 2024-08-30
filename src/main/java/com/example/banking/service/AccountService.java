package com.example.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.exception.DuplicityException;
import com.example.banking.model.Account;
import com.example.banking.model.Client;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.ClientRepository;

/**
 * Servicio que proporciona la lógica de negocio para manejar cuentas bancarias.
 */
@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;
@Autowired
private ClientService clientservice;


    /**
     * Encuentra todas las cuentas bancarias.
     * @return Una lista de todas las cuentas.
     */
    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    /**
     * Encuentra una cuenta bancaria por su ID.
     * @param id El ID de la cuenta.
     * @return Un Optional con la cuenta bancaria si se encuentra.
     */
    public Optional<Account> findById(Long id) {
        return accountRepository.findById(id);
    }

    /**
     * Guarda una nueva cuenta bancaria, asegurándose de que no exista duplicidad, y creo el cliente por nro doc si no existia .
     * @param account La cuenta bancaria a guardar.
     * @return La cuenta bancaria guardada.
     * @throws DuplicityException Si ya existe una cuenta similar.
     */
    public Account saveAccount(Account account) {
    	
       if (accountRepository.existsByNroCuentaAndCurrency( account.getNroCuenta(), account.getCurrency())) {
            throw new DuplicityException("Ya existe una cuenta en " +account.getCurrency()+" para el cliente " +account.getClient());
        }
       account.setClient(clientservice.save(account.getClient()));
     
        return accountRepository.save(account);
    }
    
    /**
     * Actualiza una cuenta bancaria existente.
     * @param id El ID de la cuenta a actualizar.
     * @param account La nueva información de la cuenta.
     * @return La cuenta bancaria actualizada.
     */
    public Account update(Long id, Account account) {
        account.setId(id);
        return accountRepository.save(account);
    }

    /**
     * Elimina una cuenta bancaria por su ID.
     * @param id El ID de la cuenta a eliminar.
     */
    public void delete(Long id) {
        accountRepository.deleteById(id);
    }
    

}
