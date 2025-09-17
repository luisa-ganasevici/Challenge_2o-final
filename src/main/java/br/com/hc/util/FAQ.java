package br.com.hc.util;


public class FAQ {
    private String perguntas;
    private String respostas;

    public FAQ(String perguntas, String respostas) {
        this.perguntas = perguntas;
        this.respostas = respostas;
    }

    public String getPerguntas() {
        return perguntas;
    }

    public void setPerguntas(String perguntas) {
        this.perguntas = perguntas;
    }

    public String getRespostas() {
        return respostas;
    }

    public void setRespostas(String respostas) {
        this.respostas = respostas;
    }
}
