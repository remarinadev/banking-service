package com.example.banking.test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import com.example.banking.exception.DuplicityException;
import com.example.banking.model.Account;

import com.example.banking.model.CompanyClient;
import com.example.banking.model.Currency;
import com.example.banking.model.PersonalClient;
import com.example.banking.repository.AccountRepository;
import com.example.banking.repository.ClientRepository;
import com.example.banking.service.AccountService;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@Transactional
public class BankingServiceTest {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private ClientRepository clientRepository;

    private PersonalClient personClient1;
    private CompanyClient compaClient1;
    private Account account1;
    private Account account2;

    @BeforeEach
    public void setup() {
        // Inicializar clientes y cuentas de ejemplo
        personClient1 = new PersonalClient("jose"," perez", "mitre 4543", "67876545","111411");


        compaClient1 = new CompanyClient("jose","IVA", "modere  4543", "3432232","111413");
;
        compaClient1.setNroDocumento("87654321");
        compaClient1.setName("ABC Corp.");

        account1 = new Account();
        account1.setNroCuenta("ACC123");
        account1.setCurrency(Currency.DOLLARS);
        account1.setClient(personClient1);

        account2 = new Account();
        account2.setNroCuenta("ACC456");
        account2.setCurrency(Currency.PESOS);
        account2.setClient(compaClient1);
    }

    @Test
    public void testCreateAndFindAccount() {
        // Guardar cuentas y clientes
        Account savedAccount1 = accountService.saveAccount(account1);
        Account savedAccount2 = accountService.saveAccount(account2);

        // Verificar que las cuentas se guardaron correctamente
        assertNotNull(savedAccount1.getId());
        assertNotNull(savedAccount2.getId());

        // Verificar que las cuentas pueden ser encontradas por ID
        Optional<Account> foundAccount1 = accountService.findById(savedAccount1.getId());
        Optional<Account> foundAccount2 = accountService.findById(savedAccount2.getId());

        assertTrue(foundAccount1.isPresent());
        assertTrue(foundAccount2.isPresent());
    }

    @Test
    public void testDuplicateAccountThrowsException() {
        // Guardar la primera cuenta
        accountService.saveAccount(account1);

        // Intentar guardar una cuenta duplicada
        Account duplicateAccount = new Account();
        duplicateAccount.setNroCuenta("ACC123");
        duplicateAccount.setCurrency(Currency.DOLLARS);
        duplicateAccount.setClient(personClient1);

        assertThrows(DuplicityException.class, () -> {
            accountService.saveAccount(duplicateAccount);
        });
    }

    @Test
    public void testFindAllAccounts() {
        // Guardar varias cuentas
        accountService.saveAccount(account1);
        accountService.saveAccount(account2);

        // Encontrar todas las cuentas
        List<Account> accounts = accountService.findAll();
        assertEquals(2, accounts.size());
    }

    @Test
    public void testUpdateAccount() {
        // Guardar la cuenta inicial
        Account savedAccount = accountService.saveAccount(account1);

        // Actualizar la cuenta
        savedAccount.setCurrency(Currency.DOLLARS);
        Account updatedAccount = accountService.update(savedAccount.getId(), savedAccount);

        // Verificar que la cuenta se actualizó correctamente
        assertEquals(Currency.DOLLARS, updatedAccount.getCurrency());
    }

    @Test
    public void testDeleteAccount() {
        // Guardar la cuenta inicial
        Account savedAccount = accountService.saveAccount(account1);

        // Eliminar la cuenta
        accountService.delete(savedAccount.getId());

        // Verificar que la cuenta fue eliminada
        Optional<Account> deletedAccount = accountService.findById(savedAccount.getId());
        assertFalse(deletedAccount.isPresent());
    }
}
