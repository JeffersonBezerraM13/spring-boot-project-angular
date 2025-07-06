package br.dcx.ufpb.jefferson.springbootangularprojecthelpdesk.entities.enums;

public enum Status {
    OPEN(0,"LOW"),
    IN_PROGRESS(1,"MEDIUM"),
    CLOSED(2,"HIGH");

    private Integer code;
    private String description;

    Status(Integer valor, String role) {
        this.code = valor;
        this.description = role;
    }

    public Integer getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public static Status toEnum(Integer code){
        if(code == null){
            return null;
        }
        for(Status p : Status.values()){
            if(p.getCode().equals(code)){
                return p;
            }
        }
        throw new IllegalArgumentException("No enum constant with value: " + code);
    }
}
