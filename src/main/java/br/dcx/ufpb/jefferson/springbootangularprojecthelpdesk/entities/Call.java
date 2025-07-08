package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities.enums.Priorit;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Table(name="call_tb")
public class Call implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;


    private Priorit priorit;
    private Status status;
    private String title;
    private String observations;

    @ManyToOne
    @JoinColumn(name = "technical_id")
    private Technical technical;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Call() {
    }

    public Call(Integer id, Priorit priorit, Status status, String title, String observations, Technical technical, Client client) {
        this.id = id;
        this.priorit = priorit;
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
