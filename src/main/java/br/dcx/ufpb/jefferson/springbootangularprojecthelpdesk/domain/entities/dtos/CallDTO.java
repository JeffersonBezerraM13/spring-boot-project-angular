package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.dtos;

import br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.domain.entities.Call;
import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

public class CallDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Integer id;
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;
    /**
     * Integer nessas referencias de priorit, status, technical e client
     * pois o chamadaDto não quer pegar todos essas informações para criar um chamado
     * e sim uma referencia a eles
     */
    @NotNull(message = "O campo PRIORIDADE é requerido")
    private Integer priorit;
    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;
    @NotNull(message = "O campo TÍTULO é requerido")
    @NotBlank(message = "O campo TÍTULO não pode ser vazio")
    private String title;
    @NotNull(message = "O campo OBSERVAÇÕES é requerido")
    @NotBlank(message = "O campo OBSERVAÇÕES não pode ser vazio")
    private String observations;
    @NotNull(message = "O campo TÉCNICO é requerido")
    private Integer technical;
    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer client;
    //atributo já pensando no front
    private String technicalName;
    private String clientName;

    public CallDTO() {
    }

    public CallDTO(Call obj) {
        this.id = obj.getId();
        this.openingDate = obj.getOpeningDate();
        this.closingDate = obj.getClosingDate();
        this.priorit = obj.getPriorit().getCode();
        this.status = obj.getStatus().getCode();
        this.title = obj.getTitle();
        this.observations = obj.getObservations();
        this.technical = obj.getTechnical().getId();
        this.client = obj.getClient().getId();
        this.technicalName = obj.getTechnical().getName();
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

    public Integer getPriorit() {
        return priorit;
    }

    public void setPriorit(Integer priorit) {
        this.priorit = priorit;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
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

    public Integer getTechnical() {
        return technical;
    }

    public void setTechnical(Integer technical) {
        this.technical = technical;
    }

    public Integer getClient() {
        return client;
    }

    public void setClient(Integer client) {
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
