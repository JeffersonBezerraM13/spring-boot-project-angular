package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities.enums.Priorit;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities.enums.Status;

import java.time.LocalDate;
import java.util.Objects;

public class Call {
    private Integer id;
    private LocalDate openingDate = LocalDate.now();
    private LocalDate closingDate;
    private Priorit priorit;
    private Status status;
    private String title;
    private String observations;

    private Technical technical;
    private Client client;

    public Call() {
    }

    public Call(Priorit priorit, Integer id, Status status, String title, String observations, Technical technical, Client client) {
        this.priorit = priorit;
        this.id = id;
        this.status = status;
        this.title = title;
        this.observations = observations;
        this.technical = technical;
        this.client = client;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDate getOpeningDate() {
        return openingDate;
    }

    public void setOpeningDate(LocalDate openingDate) {
        this.openingDate = openingDate;
    }

    public LocalDate getClosingDate() {
        return closingDate;
    }

    public void setClosingDate(LocalDate closingDate) {
        this.closingDate = closingDate;
    }

    public Priorit getPriorit() {
        return priorit;
    }

    public void setPriorit(Priorit priorit) {
        this.priorit = priorit;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getObservations() {
        return observations;
    }

    public void setObservations(String observations) {
        this.observations = observations;
    }

    public Technical getTechnical() {
        return technical;
    }

    public void setTechnical(Technical technical) {
        this.technical = technical;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Call call)) return false;
        return Objects.equals(getId(), call.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }
}
