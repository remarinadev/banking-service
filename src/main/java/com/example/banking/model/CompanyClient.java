package com.example.banking.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entidad que representa un cliente empresarial.
 */
@Entity

public class CompanyClient extends Client {

    private String taxId;

    public CompanyClient() {}
/**
 * Nombre del cliente y taxId 
 * @param name
 * @param taxId
 */
    public CompanyClient(String name, String taxId, String direccion, String telefono,String nroDoc) {
        super(name,direccion,telefono,nroDoc);
        this.taxId = taxId;
    }

    public String getTaxId() {
        return taxId;
    }

    public void setTaxId(String taxId) {
        this.taxId = taxId;
    }
}
