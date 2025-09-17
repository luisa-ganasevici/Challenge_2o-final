package br.com.hc.agendamento;




public class AgendarConsulta {
    private String paciente;
    private String medico;
    private String dataHora;
    private String motivo;

    public AgendarConsulta(String paciente, String medico, String dataHora, String motivo) {
        this.paciente = paciente;
        this.medico = medico;
        this.dataHora = dataHora;
        this.motivo = motivo;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getMedico() {
        return medico;
    }

    public void setMedico(String medico) {
        this.medico = medico;
    }

    public String getDataHora() {
        return dataHora;
    }

    public void setDataHora(String dataHora) {
        this.dataHora = dataHora;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }


}