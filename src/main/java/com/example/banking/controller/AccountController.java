package com.example.banking.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.banking.model.Account;
import com.example.banking.model.Client;
import com.example.banking.service.AccountService;

/**
 * Controlador REST para manejar las operaciones CRUD de las cuentas bancarias.
 */
@RestController
@RequestMapping("/api/accounts")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping
    public List<Account> getAllAccounts() {
        return accountService.findAll();
    }

    @GetMapping("/{id}")
    public Account getAccountById(@PathVariable Long id) {
        return accountService.findById(id)
                .orElseThrow(() -> new RuntimeException("Cuenta no encontrada"));
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    public Account createAccount(@RequestBody Account account) {
        return accountService.saveAccount(account);
    }


    @PutMapping("/{id}")
    public Account updateAccount(@PathVariable Long id, @RequestBody Account account) {
        return accountService.update(id, account);
    }

    @DeleteMapping("/{id}")
    public void deleteAccount(@PathVariable Long id) {
        accountService.delete(id);
    }

    /**
     * @param id
     * @return
     */
    @GetMapping("/getAccount/{id}")
    public Account viewAccountById(@PathVariable Long id) {
        // Este endpoint consume el mismo endpoint de consulta
        return this.getAccountById(id);
    }
}
