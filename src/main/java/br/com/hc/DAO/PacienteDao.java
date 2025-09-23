package br.com.hc.dao;

import br.com.hc.model.usuario.Paciente;
import br.com.hc.exception.HcException;
import br.com.hc.conexao.ConexaoBanco;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

    public class PacienteDao {

        public void inserirPaciente(Paciente paciente) {
            String sql = "INSERT INTO pacientes (nome, cpf, idade, rg, convenio, id_convenio) VALUES (?, ?, ?, ?, ?, ?)";

            Connection conn = null;
            try {
                conn = ConexaoBanco.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);

                stmt.setString(1, paciente.getNome());
                stmt.setString(2, paciente.getCpf());
                stmt.setInt(3, paciente.getIdade());
                stmt.setString(4, paciente.getRg());
                stmt.setBoolean(5, paciente.isConvenio());
                stmt.setInt(6, paciente.getIdConvenio());

                stmt.executeUpdate();
                System.out.println("Paciente cadastrado com sucesso!");

            } catch (SQLException e) {
                throw new HcException("Erro ao inserir paciente: " + e.getMessage(), e);
            } finally {
                ConexaoBanco.fecharConexao(conn);
            }
        }

        public List<Paciente> listarTodos() {
            String sql = "SELECT * FROM pacientes";
            List<Paciente> pacientes = new ArrayList<>();

            Connection conn = null;
            try {
                conn = ConexaoBanco.obterConexao();
                PreparedStatement stmt = conn.prepareStatement(sql);
                ResultSet rs = stmt.executeQuery();

                while (rs.next()) {
                    Paciente paciente = new Paciente(
                            rs.getString("nome"),
                            rs.getString("cpf"),
                            rs.getInt("idade"),
                            rs.getString("rg"),
                            rs.getBoolean("convenio"),
                            rs.getInt("id_convenio")
                    );
                    pacientes.add(paciente);
                }

            } catch (SQLException e) {
                throw new HcException("Erro ao listar pacientes: " + e.getMessage(), e);
            } finally {
                ConexaoBanco.fecharConexao(conn);
            }

            return pacientes;
        }

    }



