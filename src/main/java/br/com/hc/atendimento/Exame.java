package br.com.hc.atendimento;

import br.com.hc.usuario.Paciente;
import br.com.hc.usuario.Medico;

public class Exame {
    private String resultadosexame;
    private String fazerexame;

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
}
