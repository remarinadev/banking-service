package com.example.banking.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.banking.exception.DuplicityException;
import com.example.banking.model.Client;
import com.example.banking.repository.ClientRepository;
@Service
public class ClientService {
	
   
    @Autowired
    private ClientRepository clientRepository;
	
	/**
     * Guarda una nueva cuenta bancaria, asegurándose de que no exista duplicidad, y creo el cliente por nro doc si no existia .
     * @param account La cuenta bancaria a guardar.
     * @return La cuenta bancaria guardada.
     * @throws DuplicityException Si ya existe una cuenta similar.
     */
    public Client save(Client client) {
    	
    	if (clientRepository.existsByNroDocumento(client.getNroDocumento())) 
    	   return  clientRepository.findByNroDocumento(client.getNroDocumento());
        	
    	else 
     return clientRepository.save(client);
    }

    
    
   


    /**
     * Encuentra todos los clientes.
     * @return Una lista de todos los clientes.
     */
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    /**
     * Encuentra un cliente por su ID.
     * @param id El ID del cliente.
     * @return Un Optional con el cliente si se encuentra.
     */
    public Optional<Client> findById(Long id) {
        return clientRepository.findById(id);
    }



    /**
     * Elimina un cliente por su ID.
     * @param id El ID del cliente a eliminar.
     */
    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

}
