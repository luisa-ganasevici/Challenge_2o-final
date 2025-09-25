package br.com.hc.model.atendimento;

public class Triagem {
    private String sintomasPaciente;
    private String diasSentidos;
    private String sintomas;
    private int nivelUrgencia;

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

    public void setNivelUrgencia(int nivelUrgencia) {
        this.nivelUrgencia = nivelUrgencia;
    }

    public int getNivelUrgencia() {
        return nivelUrgencia;
    }

    //rn 3
    public String definirPrioridade() {
        if (nivelUrgencia <= 2) {
            return "Emergência";
        } else if (nivelUrgencia == 3) {
            return "Urgência media";
        } else {
            return "Atendimento comum";
        }
    }
}