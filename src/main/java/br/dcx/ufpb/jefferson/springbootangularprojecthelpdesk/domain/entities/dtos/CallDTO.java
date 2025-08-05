package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Call;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Client;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Technical;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Priorit;
import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.enums.Status;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.time.LocalDate;

public class CallDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;
    private Priorit priorit;
    private Status status;
    private String title;
    private String observations;
    private Technical technical;
    private Client client;
    //atributo já pensando no front
    private String technicalName;
    private String clientName;

    public CallDTO() {
    }

    public CallDTO(Call obj) {
        this.id = obj.getId();
        this.openingDate = obj.getOpeningDate();
        this.closingDate = obj.getClosingDate();
        this.priorit = obj.getPriorit();
        this.status = obj.getStatus();
        this.title = obj.getTitle();
        this.observations = obj.getObservations();
        this.technical = obj.getTechnical();
        this.client = obj.getClient();
        this.technicalName = obj.getClient().getName();
        this.clientName = obj.getClient().getName();
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

    public String getTechnicalName() {
        return technicalName;
    }

    public void setTechnicalName(String technicalName) {
        this.technicalName = technicalName;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }
}
