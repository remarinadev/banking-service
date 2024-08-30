package com.example.banking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entidad que representa un cliente común.
 */
@Entity

public class PersonalClient extends Client {


    private String apellido;

    public PersonalClient() {}

    public PersonalClient(String name, String apellido, String direccion, String telefono,String nroDoc) {
        super(name,direccion,telefono, nroDoc);
        this.apellido = apellido;
    }





	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
		
		
	}

	@Override
	public String toString() {
		return getName() +" "+ apellido ;
	}
}
