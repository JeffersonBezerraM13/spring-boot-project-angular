package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.dtos;

import br.dcx.ufpb.jefferson.springbootprojecthelpbridge.domain.entities.Call;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.time.LocalDate;

@Schema(name = "CallDTO", description = "Data transfer object para Chamados")
public class CallDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @Schema(description = "ID do chamado", example = "1")
    private Integer id;

    @Schema(description = "Data de abertura do chamado", example = "25/07/2025", type = "string", format = "date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate openingDate = LocalDate.now();

    @Schema(description = "Data de fechamento do chamado", example = "30/07/2025", type = "string", format = "date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate closingDate;
    /**
     * Integer nessas referencias de priorit, status, technical e client pois o chamadaDto não quer pegar todos essas informações para criar um chamado e sim uma referencia a eles
     */
    @Schema(description = "Prioridade do chamado (referência por código)", example = "2", required = true)
    @NotNull(message = "O campo PRIORIDADE é requerido")
    private Integer priorit;

    @Schema(description = "Status do chamado (referência por código)", example = "1", required = true)
    @NotNull(message = "O campo STATUS é requerido")
    private Integer status;

    @Schema(description = "Título do chamado", example = "Computador não liga", required = true)
    @NotNull(message = "O campo TÍTULO é requerido")
    @NotBlank(message = "O campo TÍTULO não pode ser vazio")
    private String title;

    @Schema(description = "Observações do chamado", example = "Cliente relatou que o computador fica sem energia", required = true)
    @NotNull(message = "O campo OBSERVAÇÕES é requerido")
    @NotBlank(message = "O campo OBSERVAÇÕES não pode ser vazio")
    private String observations;

    @Schema(description = "ID do técnico responsável", example = "5", required = true)
    @NotNull(message = "O campo TÉCNICO é requerido")
    private Integer technical;

    @Schema(description = "ID do cliente solicitante", example = "10", required = true)
    @NotNull(message = "O campo CLIENTE é requerido")
    private Integer client;

    //atributo já pensando no front
    @Schema(description = "Nome do técnico (somente para exibição no front)", example = "João Silva")
    private String technicalName;

    @Schema(description = "Nome do cliente (somente para exibição no front)", example = "Maria Souza")
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
