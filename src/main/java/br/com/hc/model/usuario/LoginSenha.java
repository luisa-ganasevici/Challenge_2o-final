package br.com.hc.model.usuario;

public class LoginSenha {

        private String login;
        private String senha;


        public LoginSenha(String login, String senha) {
            this.login = login;
            this.senha = senha;
        }


        public String getLogin() {
            return login;
        }

        public void setLogin(String login) {
            if (login == null || login.trim().isEmpty()) {
                throw new IllegalArgumentException("Login não pode ser vazio");
            }
            this.login = login;
        }

        public String getSenha() {
            return senha;
        }

        public void setSenha(String senha) {
            if (senha == null || senha.length() < 6) {
                throw new IllegalArgumentException("Senha deve ter pelo menos 6 caracteres");
            }
            this.senha = senha;
        }


        public boolean autenticar(String login, String senha) {
            return this.login.equals(login) && this.senha.equals(senha);
        }

        public void alterarSenha(String novaSenha) {
            setSenha(novaSenha);
        }
    }

