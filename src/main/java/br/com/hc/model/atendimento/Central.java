package br.com.hc.model.atendimento;



public class Central {
    private String numeroHc;
    private String emailHc;

    public Central(String numeroHc, String emailHc) {
        this.numeroHc = numeroHc;
        this.emailHc = emailHc;
    }

    public String getNumeroHc() {
        return numeroHc;
    }

    public void setNumeroHc(String numeroHc) {
        this.numeroHc = numeroHc;
    }

    public String getEmailHc() {
        return emailHc;
    }

    public void setEmailHc(String emailHc) {
        this.emailHc = emailHc;
    }
}
