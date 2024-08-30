package com.example.banking.controller;

import com.example.banking.model.Client;
import com.example.banking.model.CompanyClient;
import com.example.banking.service.AccountService;
import com.example.banking.service.ClientService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 * Controlador REST para gestionar las operaciones relacionadas con los clientes.
 */
@RestController
@RequestMapping("/api/clients")
public class ClientController {

	@Autowired
	private ClientService clientservice;

    /**
     * Obtiene una lista de todos los clientes.
     * @return Una lista de todos los clientes.
     */
    @GetMapping
    public List<Client> getAllClients() {
        return clientservice.findAll();
    }

    /**
     * Obtiene un cliente por su ID.
     * @param id El ID del cliente a buscar.
     * @return El cliente correspondiente al ID proporcionado.
     */
    @GetMapping("/{id}")
    public Client getClientById(@PathVariable Long id) {
        return clientservice.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado"));
    }

    /**
     * Crea un nuevo cliente.
     * @param client Los detalles del cliente a crear.
     * @return El cliente creado.
     */
    @PostMapping
    public CompanyClient createClient(@RequestBody CompanyClient client) {
        return clientservice.save(client);
     
    }

    /**
     * Actualiza un cliente existente.
     * @param id El ID del cliente a actualizar.
     * @param client Los nuevos detalles del cliente.
     * @return El cliente actualizado.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id, @RequestBody Client client) {
        Optional<Client> existingClient = clientservice.findById(id);
        if (existingClient.isPresent()) {
            client.setId(id); // Asegúrate de que el ID coincide con el del cliente existente
            Client updatedClient = clientservice.save(client);
            return ResponseEntity.ok(updatedClient);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Elimina un cliente por su ID.
     * @param id El ID del cliente a eliminar.
     * @return Una respuesta sin contenido si se eliminó exitosamente.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id) {
        if (clientservice.findById(id).isPresent()) {
        	clientservice.delete(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}


