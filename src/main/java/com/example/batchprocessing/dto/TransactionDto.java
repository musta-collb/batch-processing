package com.example.batchprocessing.dto;

import com.example.batchprocessing.entities.Compte;

import java.util.Date;

public class TransactionDto {
    private String idTransaction;
    private String montant;
    private String dateTransaction;
    private String dateDebit;
    private String compteId;

    public TransactionDto() {
    }

    public String getIdTransaction() {
        return idTransaction;
    }

    public void setIdTransaction(String idTransaction) {
        this.idTransaction = idTransaction;
    }

    public String getMontant() {
        return montant;
    }

    public void setMontant(String montant) {
        this.montant = montant;
    }

    public String getDateTransaction() {
        return dateTransaction;
    }

    public void setDateTransaction(String dateTransaction) {
        this.dateTransaction = dateTransaction;
    }

    public String getDateDebit() {
        return dateDebit;
    }

    public void setDateDebit(String dateDebit) {
        this.dateDebit = dateDebit;
    }

    public String getCompteId() {
        return compteId;
    }

    public void setCompteId(String compteId) {
        this.compteId = compteId;
    }

    @Override
    public String toString() {
        return "TransactionDto{" +
                "idTransaction='" + idTransaction + '\'' +
                ", montant='" + montant + '\'' +
                ", dateTransaction='" + dateTransaction + '\'' +
                ", dateDebit='" + dateDebit + '\'' +
                ", compteId='" + compteId + '\'' +
                '}';
    }
}
