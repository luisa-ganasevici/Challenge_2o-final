package br.com.hc.model.usuario;



public class ServicoPaciente {
    private boolean convenio;

    public ServicoPaciente(boolean convenio) {
        this.convenio = convenio;
    }

    public boolean isConvenio() {
        return convenio;
    }

    public void setConvenio(boolean convenio) {
        this.convenio = convenio;
    }
}


