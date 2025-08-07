package br.dcx.ufpb.jefferson.springbootprojecthelpbridge.services.exceptions;

public class DataIntegrityViolationException extends RuntimeException {
    private static final long serialVersionUID = 1L; //talvez não precise disso
    public DataIntegrityViolationException(String message) {
        super(message);
    }

    public DataIntegrityViolationException(String message, Throwable cause) {
        super(message, cause);
    }
}
