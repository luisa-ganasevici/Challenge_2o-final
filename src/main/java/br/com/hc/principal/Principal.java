package br.com.hc.principal;

import br.com.hc.model.agendamento.AgendarConsulta;
import br.com.hc.model.atendimento.Central;
import br.com.hc.model.atendimento.Triagem;
import br.com.hc.model.usuario.Paciente;
import br.com.hc.model.usuario.Medico;
import br.com.hc.model.usuario.LoginSenha;
import br.com.hc.util.FAQ;
import br.com.hc.dao.PacienteDao;
import br.com.hc.model.usuario.ServicoPaciente;
import br.com.hc.model.atendimento.Exame;

import java.util.Scanner;
import java.util.List;

public class Principal {

    static PacienteDao pacienteDao = new PacienteDao();
    static Scanner ler = new Scanner(System.in);

    public static void main(String[] args) {


        LoginSenha login = new LoginSenha("admin", "123456");

        boolean autenticado = false;

        System.out.println("**** Sistema HC - Hospital ****");

        System.out.println("\n**** Login ****");

        do {
            System.out.print("Digite o login: ");
            String inputLogin = ler.nextLine();

            System.out.print("Digite a senha: ");
            String inputSenha = ler.nextLine();

            try {
                if (login.autenticar(inputLogin, inputSenha)) {
                    System.out.println("Login bem-sucedido!");
                    autenticado = true;

                    exibirMenuPrincipal(login);

                } else {
                    System.out.println("Login ou senha incorretos! Tente novamente.");
                }
            } catch (Exception e) {
                System.out.println("Erro inesperado: " + e.getMessage());
            }

        } while (!autenticado);

        ler.close();
    }

    private static void exibirMenuPrincipal(LoginSenha login) {
        boolean sair = false;

        while (!sair) {
            String menuPrincipal = """
                    \n***** menu principal* ****
                    digitee a opção desejada:
                    
                    1- cadastrar paciente
                    2- listar pacientes
                    3- agendar consulta
                    4- informações sobre médicos
                    5- dúvidas frequentes - FAQ
                    6- exames
                    7- central de atendimento
                    8- alterar senha
                    9- meus dados de login
                    10- serviços do paciente
                    11- triagem online
                    12- gerenciar pacientes
                    0- sair
                    
                    opção:""";

            System.out.print(menuPrincipal);

            try {
                int opcao = ler.nextInt();
                ler.nextLine();

                switch (opcao) {
                    case 1:
                        cadastrarPaciente();
                        break;

                    case 2:
                        listarPacientes();
                        break;

                    case 3:
                        agendarConsulta();
                        break;

                    case 4:
                        informacoesMedicos();
                        break;

                    case 5:
                        duvidasFrequentes();
                        break;

                    case 6:
                        informacoesExames();
                        break;

                    case 7:
                        centralAtendimento();
                        break;

                    case 8:
                        alterarSenha(login);
                        break;

                    case 9:
                        meusDadosLogin(login);
                        break;

                    case 10:
                        servicosPaciente();
                        break;

                    case 11:
                        triagemOnline();
                        break;

                    case 12:
                        menuGerenciarPacientes();
                        break;

                    case 0:
                        System.out.println("saindo");
                        sair = true;
                        break;

                    default:
                        System.out.println("opção invalida");
                        break;
                }
            } catch (Exception e) {
                System.out.println("digite um numero valido");
                ler.nextLine();
            }
        }
    }

    private static void menuGerenciarPacientes() {
        boolean voltar = false;

        while (!voltar) {
            String menuCRUD = """
                    \n***** gerenciar pacientes *****
                    Digite a opção:
                    
                    1- buscar paciente por CPF
                    2- atualizar paciente
                    3- excluir paciente
                    4- verificar se paciente esta no sistema
                    5- contar pacientes
                    6- buscar por idade
                    0- voltar ao menu principal
                    
                    Opção:""";

            System.out.print(menuCRUD);

            try {
                int opcao = ler.nextInt();
                ler.nextLine();

                switch (opcao) {
                    case 1:
                        buscarPacientePorCpf();
                        break;

                    case 2:
                        atualizarPaciente();
                        break;

                    case 3:
                        excluirPaciente();
                        break;

                    case 4:
                        verificarPacienteExiste();
                        break;

                    case 5:
                        contarPacientes();
                        break;

                    case 6:
                        buscarPorFaixaEtaria();
                        break;

                    case 0:
                        System.out.println("Voltando no menu principal");
                        voltar = true;
                        break;

                    default:
                        System.out.println("opção inválida");
                        break;
                }
            } catch (Exception e) {
                System.out.println("digite um numero valido");
                ler.nextLine();
            }
        }
    }


    private static void buscarPacientePorCpf() {
        System.out.println("\n**** Buscar Paciente por CPF ****");
        System.out.print("Digite o CPF do paciente: ");
        String cpf = ler.nextLine();

        try {
            Paciente paciente = pacienteDao.buscarPorCpf(cpf);

            if (paciente != null) {
                System.out.println("\n*** paciente encontrado ***");
                System.out.println("nome: " + paciente.getNome());
                System.out.println("CPF: " + paciente.getCpf());
                System.out.println("idade: " + paciente.getIdade());
                System.out.println("RG: " + paciente.getRg());
                System.out.println("convênio: " + (paciente.isConvenio() ? "Sim" : "Não"));
                System.out.println("ID convênio: " + paciente.getIdConvenio());
                System.out.println("maior de idade: " + (paciente.ehMaiorDeIdade() ? "Sim" : "Não"));
                System.out.println("CPF válido: " + (paciente.validarCpf() ? "Sim" : "Não"));
            } else {
                System.out.println("paciente não encontrado com o CPF: " + cpf);
            }
        } catch (Exception e) {
            System.out.println("erro ao encontrAr paciente: " + e.getMessage());
        }
    }


    private static void atualizarPaciente() {
        System.out.println("\n**** Atualizar Paciente ****");
        System.out.print("Digite o CPF do paciente que deseja atualizar: ");
        String cpf = ler.nextLine();

        try {

            Paciente paciente = pacienteDao.buscarPorCpf(cpf);

            if (paciente == null) {
                System.out.println("paciente não encontrado!");
                return;
            }

            System.out.println("\npaciente atual:");
            System.out.println("nome: " + paciente.getNome());
            System.out.println("idade: " + paciente.getIdade());
            System.out.println("RG: " + paciente.getRg());
            System.out.println("convênio: " + paciente.isConvenio());
            System.out.println("ID convênio: " + paciente.getIdConvenio());

            System.out.println("\ndigite os novos dados (pressione Enter para manter o valor atual):");

            System.out.print("novo nome [" + paciente.getNome() + "]: ");
            String novoNome = ler.nextLine();
            if (!novoNome.trim().isEmpty()) {
                paciente.setNome(novoNome);
            }

            System.out.print("nova idade [" + paciente.getIdade() + "]: ");
            String novaIdadeStr = ler.nextLine();
            if (!novaIdadeStr.trim().isEmpty()) {
                try {
                    int novaIdade = Integer.parseInt(novaIdadeStr);
                    paciente.setIdade(novaIdade);
                } catch (NumberFormatException e) {
                    System.out.println("idade inválida, mantendo idade atual.");
                }
            }

            System.out.print("Novo RG [" + paciente.getRg() + "]: ");
            String novoRg = ler.nextLine();
            if (!novoRg.trim().isEmpty()) {
                paciente.setRg(novoRg);
            }

            System.out.print("tem convênio? [true/false, atual: " + paciente.isConvenio() + "]: ");
            String novoConvenioStr = ler.nextLine();
            if (!novoConvenioStr.trim().isEmpty()) {
                paciente.setConvenio(Boolean.parseBoolean(novoConvenioStr));
            }

            System.out.print("novo ID Convênio [" + paciente.getIdConvenio() + "]: ");
            String novoIdConvenioStr = ler.nextLine();
            if (!novoIdConvenioStr.trim().isEmpty()) {
                try {
                    int novoIdConvenio = Integer.parseInt(novoIdConvenioStr);
                    paciente.setIdConvenio(novoIdConvenio);
                } catch (NumberFormatException e) {
                    System.out.println("ID convênio inválido! mantendo valor atual.");
                }
            }


            pacienteDao.atualizarPaciente(paciente);
            System.out.println("paciente atualizado com sucesso!");

        } catch (Exception e) {
            System.out.println("eerro ao atualizar paciente: " + e.getMessage());
        }
    }

    private static void excluirPaciente() {
        System.out.println("\n**** excluir paciente ****");
        System.out.print("digite o CPF do paciente para excluir: ");
        String cpf = ler.nextLine();

        try {
            Paciente paciente = pacienteDao.buscarPorCpf(cpf);

            if (paciente == null) {
                System.out.println("paciente não encontrado!");
                return;
            }

            System.out.println("\npaciente a ser excluído:");
            System.out.println("nome: " + paciente.getNome());
            System.out.println("CPF: " + paciente.getCpf());
            System.out.println("idade: " + paciente.getIdade());

            System.out.print("\ntem certeza que deseja excluir este paciente? (s/n): ");
            String confirmacao = ler.nextLine();

            if (confirmacao.equalsIgnoreCase("s")) {
                pacienteDao.deletarPaciente(cpf);
                System.out.println("paciente excluído");
            } else {
                System.out.println("cancelado.");
            }

        } catch (Exception e) {
            System.out.println("erro ao excluir paciente: " + e.getMessage());
        }
    }


    private static void verificarPacienteExiste() {
        System.out.println("\n**** verificar existência de paciente ****");
        System.out.print("digite o CPF para verificar: ");
        String cpf = ler.nextLine(

        );

        try {
            boolean existe = pacienteDao.pacienteExiste(cpf);

            if (existe) {
                System.out.println("paciente com CPF " + cpf + " está cadastrado no sistema.");
            } else {
                System.out.println("paciente com CPF " + cpf + " não encontrado no sistema.");
            }
        } catch (Exception e) {
            System.out.println("erro ao verificar paciente: " + e.getMessage());
        }
    }

    private static void contarPacientes() {
        System.out.println("\n**** contagem de pacientes ****");

        try {
            int total = pacienteDao.contarPacientes();
            System.out.println("total de pacientes cadastrados no sistema: " + total);


            List<Paciente> todosPacientes = pacienteDao.listarTodos();
            if (!todosPacientes.isEmpty()) {
                long comConvenio = todosPacientes.stream().filter(Paciente::isConvenio).count();
                long maioresIdade = todosPacientes.stream().filter(Paciente::ehMaiorDeIdade).count();

                System.out.println("pacientes com convênio: " + comConvenio);
                System.out.println("pacientes maiores de idade: " + maioresIdade);
                System.out.println("pacientes menores de idade: " + (total - maioresIdade));
            }
        } catch (Exception e) {
            System.out.println("erro ao contar pacientes: " + e.getMessage());
        }
    }

    private static void buscarPorFaixaEtaria() {
        System.out.println("\n**** buscar pacientes por faixa etária ****");

        try {
            System.out.print("idade mínima: ");
            int idadeMinima = ler.nextInt();

            System.out.print("idade máxima: ");
            int idadeMaxima = ler.nextInt();
            ler.nextLine();

            if (idadeMinima < 0 || idadeMaxima < idadeMinima) {
                System.out.println("idades inválida, a idade mínima deve ser menor que a máxima.");
                return;
            }

            List<Paciente> pacientes = pacienteDao.buscarPorFaixaEtaria(idadeMinima, idadeMaxima);

            System.out.println("\n*** paciente com" + idadeMinima + "-" + idadeMaxima + " anos ***");

            if (pacientes.isEmpty()) {
                System.out.println("nenhum paciente encontrado nesta faixa etária.");
            } else {
                System.out.println("total encontrado: " + pacientes.size());
                for (int i = 0; i < pacientes.size(); i++) {
                    Paciente p = pacientes.get(i);
                    System.out.println((i + 1) + ". " + p.getNome() +
                            " - " + p.getIdade() + " anos" +
                            " - CPF: " + p.getCpf() +
                            " - convênio: " + (p.isConvenio() ? "sim" : "não"));
                }
            }
        } catch (Exception e) {
            System.out.println("erro ao buscar por faixa etária: " + e.getMessage());
        }
    }

    private static void cadastrarPaciente() {
        System.out.println("\n**** cadastrar novo paciente ****");

        try {
            System.out.print("nome: ");
            String nome = ler.nextLine();

            System.out.print("CPF (apenas números): ");
            String cpf = ler.nextLine();

            System.out.print("idade: ");
            int idade = ler.nextInt();
            ler.nextLine();

            System.out.print("RG: ");
            String rg = ler.nextLine();

            System.out.print("tem convênio? (true/false): ");
            boolean convenio = ler.nextBoolean();
            ler.nextLine();

            System.out.print("ID do convênio: ");
            int idConvenio = ler.nextInt();
            ler.nextLine();

            Paciente paciente = new Paciente(nome, cpf, idade, rg, convenio, idConvenio);

            if (!paciente.validarCpf()) {
                System.out.println("erro: CPF inválido!");
                return;
            }

            if (!paciente.ehMaiorDeIdade()) {
                System.out.println("atenção: paciente menor de idade!");
            }

            pacienteDao.inserirPaciente(paciente);
            System.out.println("paciente cadastrado");

        } catch (Exception e) {
            System.out.println("erro ao cadastrar paciente: " + e.getMessage());
        }
    }

    private static void listarPacientes() {
        System.out.println("\n**** lista de cadastrados ****");

        try {
            List<Paciente> pacientes = pacienteDao.listarTodos();

            if (pacientes.isEmpty()) {
                System.out.println("nenhum paciente cadastrado.");
            } else {
                System.out.println("total de pacientes: " + pacientes.size());
                for (int i = 0; i < pacientes.size(); i++) {
                    Paciente p = pacientes.get(i);
                    System.out.println((i + 1) + ". " + p.getInfoBasica() +
                            " - convênio: " + (p.isConvenio() ? "sim" : "não") +
                            " - maioridade: " + (p.ehMaiorDeIdade() ? "sim" : "não"));
                }
            }
        } catch (Exception e) {
            System.out.println("erro ao listar pacientes: " + e.getMessage());
        }
    }

    private static void agendarConsulta() {
        System.out.println("\n**** agendar consulta ****");

        try {
            System.out.print("nome do paciente: ");
            String paciente = ler.nextLine();

            System.out.print("nome do médico: ");
            String medico = ler.nextLine();

            System.out.print("data e horário (ex: 25/09/2025 14:30): ");
            String dataHora = ler.nextLine();

            System.out.print("motivo da consulta: ");
            String motivo = ler.nextLine();

            AgendarConsulta consulta = new AgendarConsulta(paciente, medico, dataHora, motivo);

            System.out.println("\n*** consulta agendada ***");
            System.out.println("paciente: " + consulta.getPaciente());
            System.out.println("médico: " + consulta.getMedico());
            System.out.println("data/hora: " + consulta.getDataHora());
            System.out.println("motivo: " + consulta.getMotivo());
            System.out.println("consulta agendada com sucesso!");

        } catch (Exception e) {
            System.out.println("erro ao agendar consulta: " + e.getMessage());
        }
    }

    private static void alterarSenha(LoginSenha login) {
        System.out.println("\n**** alterar senha ****");
        System.out.print("digite a nova senha (mínimo 6 digitos): ");
        String novaSenha = ler.nextLine();

        try {
            login.setSenha(novaSenha);
            System.out.println("senha alterada");
        } catch (IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }
    }

    private static void informacoesMedicos() {
        System.out.println("\n**** informações sobre médicos ****");
        Medico medico1 = new Medico("12345", "Dr. Leonardo Silva", "Neurologia");
        Medico medico2 = new Medico("67890", "Dra. Maria Santos", "Cardiologia");

        System.out.println("médicos disponíveis:");
        System.out.println("1. " + medico1);
        System.out.println("2. " + medico2);
    }

    private static void duvidasFrequentes() {
        System.out.println("\n**** dúvidas frequentes - FAQ ****");
        FAQ faq = new FAQ("como agendar uma consulta?",
                "acesse o menu principal e selecione a opção 'Agendar Consulta'.");

        System.out.println("P: " + faq.getPerguntas());
        System.out.println("R: " + faq.getRespostas());
    }

    private static void informacoesExames() {
        System.out.println("\n**** informações sobre exames ****");
        Exame exame = new Exame("os resultados podem ser retirados presencialmente ou enviados por email.",
                "dirija-se a uma unidade do HC para realizar os exames.");

        System.out.println("como realizar exames: " + exame.getFazerexame());
        System.out.println("resultados: " + exame.getResultadosexame());

        exame.setTipo("tomografia");
        System.out.println("exame de risco: " + (exame.ExameDeRisco() ? "Sim" : "Não"));
    }

    private static void centralAtendimento() {
        System.out.println("\n**** central de atendimento ****");
        Central central = new Central("(11) 3333-3333", "hc.hospital@gmail.com");

        System.out.println("telefone: " + central.getNumeroHc());
        System.out.println("email: " + central.getEmailHc());
        System.out.println("horário de atendimento: Segunda a Sexta, 8h às 18h");
    }

    private static void meusDadosLogin(LoginSenha login) {
        System.out.println("\n**** Dados de Login ****");
        System.out.println("login: " + login.getLogin());
        System.out.println("senha: ********");
        System.out.println("para alterar sua senha, use a opção 8 do menu.");
    }

    private static void servicosPaciente() {
        System.out.println("\n**** serviços do paciente ****");

        try {
            List<Paciente> pacientes = pacienteDao.listarTodos();
            if (!pacientes.isEmpty()) {
                Paciente paciente = pacientes.get(0);
                ServicoPaciente servico = new ServicoPaciente(paciente.isConvenio());

                System.out.println("paciente: " + paciente.getNome());
                System.out.println("convênio: " + (servico.isConvenio() ? "Sim" : "Não"));
                System.out.println("maior de idade: " + (paciente.ehMaiorDeIdade() ? "Sim" : "Não"));
                System.out.println("CPF válido: " + (paciente.validarCpf() ? "Sim" : "Não"));
            } else {
                System.out.println("nenhum paciente cadastrado. Cadastre um paciente primeiro.");
            }
        } catch (Exception e) {
            System.out.println("erro ao acessar serviços: " + e.getMessage());
        }
    }

    private static void triagemOnline() {
        System.out.println("\n**** triagem online ****");

        System.out.print("quais sintomas você está sentindo? ");
        String sintomas = ler.nextLine();

        System.out.print("quantos dias? ");
        String dias = ler.nextLine();

        Triagem triagem = new Triagem(sintomas, dias);


        triagem.setNivelUrgencia(2);
        String prioridade = triagem.definirPrioridade();

        System.out.println("\n--- informações registradas ---");
        System.out.println("sintomas: " + sintomas);
        System.out.println("dias: " + dias);
        System.out.println("prioridade: " + prioridade);
        System.out.println("\nrecomendamos que procure um hospital próximo.");
        System.out.println("sua triagem foi encaminhada para o sistema do HC");
    }
}