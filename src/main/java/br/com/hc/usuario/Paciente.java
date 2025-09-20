package br.com.hc.usuario;



public class Paciente {

        private String nome;
        private String cpf;
        private int idade;
        private String rg;
        private boolean convenio;
        private int idConvenio;


        public Paciente(String nome, String cpf, int idade, String rg, boolean convenio, int idConvenio) {
            this.nome = nome;
            this.cpf = cpf;
            this.idade = idade;
            this.rg = rg;
            this.convenio = convenio;
            this.idConvenio = idConvenio;
        }

        public Paciente() {

        }
        public String getNome() {
            return nome;
        }

        public void setNome(String nome) {
            if (nome == null || nome.trim().isEmpty()) {
                throw new IllegalArgumentException("Nome não pode ser vazio");
            }
            this.nome = nome;
        }

        public String getCpf() {
            return cpf;
        }

        public void setCpf(String cpf) {
            if (!validarCPF(cpf)) {
                throw new IllegalArgumentException("CPF inválido");
            }
            this.cpf = cpf;
        }

        public int getIdade() {
            return idade;
        }

        public void setIdade(int idade) {
            if (idade < 0 || idade > 120) {
                throw new IllegalArgumentException("Idade inválida");
            }
            this.idade = idade;
        }

        public String getRg() {
            return rg;
        }

        public void setRg(String rg) {
            this.rg = rg;
        }

        public boolean isConvenio() {
            return convenio;
        }

        public void setConvenio(boolean convenio) {
            this.convenio = convenio;
        }

        public int getIdConvenio() {
            return idConvenio;
        }

        public void setIdConvenio(int idConvenio) {
            this.idConvenio = idConvenio;
        }


        public boolean ehMaiorDeIdade() {
            return idade >= 18;
        }

        public String getInfoBasica() {
            return nome + " (CPF: " + cpf + ", Idade: " + idade + ")";
        }

        private boolean validarCPF(String cpf) {

            return cpf != null && cpf.matches("\\d{11}");
        }
    }

