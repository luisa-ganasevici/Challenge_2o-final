package br.com.hc.atendimento;


import br.com.hc.usuario.Paciente;
import br.com.hc.usuario.Medico;
import br.com.hc.agendamento.AgendarConsulta;
import br.com.hc.atendimento.Exame;
import br.com.hc.atendimento.Triagem;

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
