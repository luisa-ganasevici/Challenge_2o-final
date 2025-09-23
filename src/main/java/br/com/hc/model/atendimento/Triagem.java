package br.com.hc.model.atendimento;


public class Triagem {
    private String sintomasPaciente;
    private String diasSentidos;


    public Triagem(String sintomasPaciente, String diasSentidos) {
        this.sintomasPaciente = sintomasPaciente;
        this.diasSentidos = diasSentidos;
    }

    public String getSintomasPaciente() {
        return sintomasPaciente;
    }

    public void setSintomasPaciente(String sintomasPaciente) {
        this.sintomasPaciente = sintomasPaciente;
    }

    public String getDiasSentidos() {
        return diasSentidos;
    }

    public void setDiasSentidos(String diasSentidos) {
        this.diasSentidos = diasSentidos;
    }
}
