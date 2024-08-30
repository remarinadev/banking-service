package com.example.banking.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

/**
 * Entidad base que representa un cliente bancario.
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;
    private String nroDocumento;
    private String name;
    private String direccion;
    private String telefono;

    @OneToMany(mappedBy = "client")
    private List<Account> accounts;

    public Client() {}



	public Client(String name, String direccion, String telefono, String nroDoc) {
		super();
		this.name = name;
		this.direccion = direccion;
		this.telefono = telefono;
		this.nroDocumento=nroDoc;
	}



	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

	@Override
	public String toString() {
		return name;
	}
    public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}



	public String getNroDocumento() {
		return nroDocumento;
	}



	public void setNroDocumento(String nroDocumento) {
		this.nroDocumento = nroDocumento;
	}


	
	
}
