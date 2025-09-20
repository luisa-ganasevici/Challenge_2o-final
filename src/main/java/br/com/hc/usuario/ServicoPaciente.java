package br.com.hc.usuario;



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


