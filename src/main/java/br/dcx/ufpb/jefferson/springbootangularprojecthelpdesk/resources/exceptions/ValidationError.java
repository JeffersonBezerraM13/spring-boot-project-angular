package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.resources.exceptions;

import java.util.List;
import java.util.ArrayList;

public class ValidationError extends StandardError{
    private static final long serialVersionUID = 1L;

    private List<FieldMessage> erros = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    }

    public void addError(String fieldName, String message) {
        this.erros.add(new FieldMessage(fieldName, message));
    }

    public List<FieldMessage> getErros() {
        return erros;
    }
}
