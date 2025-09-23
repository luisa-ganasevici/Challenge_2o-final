package br.com.hc.principal;

import br.com.hc.model.agendamento.AgendarConsulta;
import br.com.hc.model.atendimento.Central;
import br.com.hc.model.usuario.Paciente;
import br.com.hc.model.usuario.Medico;
import br.com.hc.model.usuario.LoginSenha;
import br.com.hc.util.FAQ;
import br.com.hc.dao.PacienteDao;

import java.util.Scanner;

import br.com.hc.model.usuario.ServicoPaciente;
import br.com.hc.model.atendimento.Exame;
import br.com.hc.model.atendimento.Triagem;

public class Principal {

    public static void main(String[] args) {

        Scanner ler = new Scanner(System.in);
        ServicoPaciente pacienteService = new ServicoPaciente(true);

        System.out.println("**** Cadastro Paciente *****");
        Paciente paciente = new Paciente("Luisa", "12345678901", 18, "MG1234567", true, 123);

        System.out.println("***** login ******");
        LoginSenha login = new LoginSenha("login", "123");

        boolean autenticado = false;
        do {
            System.out.println("Digite o login:");
            String inputLogin = ler.nextLine();

            System.out.println("Digite a senha:");
            String inputSenha = ler.nextLine();

            try {
                if (login.autenticar(inputLogin, inputSenha)) {
                    System.out.println("Login bem-sucedido!");
                    autenticado = true;

                    String menuPrincipal = """
                            Digite a opção desejada!
                            
                            1- Alterar senha
                            2- Agendar Consulta
                            3- Mais info sobre os medicos
                            4- Duvidas frequentes - FAQ
                            5- Exames
                            6- Central
                            7- Login e Senha
                            8- Servico Paciente 
                            9- Triagem online
                            
                        
                            """;

                    System.out.println(menuPrincipal);

                    int opcao = ler.nextInt();
                    ler.nextLine();

                    Medico medico = new Medico("11111", "Leo", "neuro");
                    AgendarConsulta agendarConsulta = new AgendarConsulta("thiago", "leandro", "15h", "nao indicado");
                    Medico medico1 = new Medico("1212", "mauro", "neuro");
                    FAQ faq = new FAQ("como faço para usar o menu?", "apenas digitar o numero da opção desejada");
                    Exame exame = new Exame("retirar os resultados presencialmente ou pelo email", "ir a uma unidade do HC");
                    Central central = new Central("11333333", "hc@gmail.com");
                    ServicoPaciente servicoPaciente = new ServicoPaciente(true);
                    Triagem triagem = new Triagem(null, null);

                    try {
                        switch (opcao) {
                            case 1:
                                System.out.println("**** Alterar senha *****");
                                System.out.println("Digite a nova senha:");
                                String novaSenha = ler.nextLine();
                                login.setSenha(novaSenha);
                                System.out.println("Senha alterada com sucesso!");
                                break;

                            case 2:
                                System.out.println("digite a especialidade que deseja:");
                                String escolha = ler.nextLine();

                                System.out.println("existe motivo especial?");
                                String motivo = ler.nextLine();

                                System.out.println("digite o melhor dia e melhor horario pra você: ");
                                String diaHora = ler.nextLine();

                                System.out.println("revisando as info: ");
                                System.out.println("você estará lá : " + diaHora);
                                System.out.println("com a escolha da especialidade: " + escolha);

                                System.out.println("consulta agendada! esperamos você lá!");
                                break;

                            case 3:
                                System.out.println("segue as informações sobre o medico" + medico1);
                                break;

                            case 4:
                                System.out.println("perguntas frequentes:" + faq.getPerguntas());
                                System.out.println("resposta: " + faq.getRespostas());
                                break;

                            case 5:
                                System.out.println("sobre realizar exames: " + exame.getFazerexame());
                                System.out.println("sobre retirar exames: " + exame.getResultadosexame());
                                System.out.println("para o email/telefone do HC, volte ao menu e vá na opção de central");
                                break;

                            case 6:
                                System.out.println("email do HC: " + central.getEmailHc());
                                System.out.println("numero do HC" + central.getNumeroHc());
                                break;

                            case 7:
                                System.out.println("seu login é:" + login.getLogin());
                                System.out.println("sua senha é: " + login.getSenha());
                                System.out.println("se deseja altera-los, vá na opção 1 do menu");
                                break;

                            case 8:
                                System.out.println("seus dados são:" + servicoPaciente.isConvenio());
                                System.out.println("se seus dados, foram respondidos como true, você tem convenio, e se responderam como false, dirija-se a um hospital Hc para acertar o financeiro.");
                                break;

                            case 9:
                                System.out.println("esta é a opção de triagem online. Pode nos dizer o que você está sentindo?");
                                String sintomas = ler.nextLine();

                                sintomas = triagem.getSintomasPaciente();

                                System.out.println("entendi. A quantos dias você está sentindo isso?");
                                String dias = ler.nextLine();

                                dias = triagem.getDiasSentidos();

                                System.out.println("recomendamos que você procure um hospital proximo a você, sua triagem online foi encaminhada para o sistemas da HC. Melhoras! ");
                                break;

                            default:
                                System.out.println("Opção inválida!");
                                break;
                        }
                    } catch (Exception e) {
                        System.out.println("Erro ao processar a opção: " + e.getMessage());
                    }

                } else {
                    System.out.println("Login ou senha incorretos!");
                }
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }

        } while (!autenticado);

    }
}
