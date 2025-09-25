package br.com.hc.model.atendimento;

public class Exame {
    private String resultadosexame;
    private String fazerexame;
    private String tipo;

    public Exame(String resultadosexame, String fazerexame) {
        this.resultadosexame = resultadosexame;
        this.fazerexame = fazerexame;
    }

    public String getResultadosexame() {
        return resultadosexame;
    }

    public void setResultadosexame(String resultadosexame) {
        this.resultadosexame = resultadosexame;
    }

    public String getFazerexame() {
        return fazerexame;
    }

    public void setFazerexame(String fazerexame) {
        this.fazerexame = fazerexame;
    }


    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo() {
        return tipo;
    }

    //rn 4
    public boolean ExameDeRisco() {
        return tipo != null && (
                tipo.equalsIgnoreCase("Ressonancia") ||
                        tipo.equalsIgnoreCase("Tomografia") ||
                        tipo.equalsIgnoreCase("Cirurgia")
        );
    }
}