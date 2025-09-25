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


    public Paciente buscarPorCpf(String cpf) {
        String sql = "SELECT * FROM pacientes WHERE cpf = ?";
        Paciente paciente = null;

        Connection conn = null;
        try {
            conn = ConexaoBanco.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                paciente = new Paciente(
                        rs.getString("nome"),
                        rs.getString("cpf"),
                        rs.getInt("idade"),
                        rs.getString("rg"),
                        rs.getBoolean("convenio"),
                        rs.getInt("id_convenio")
                );
            }

        } catch (SQLException e) {
            throw new HcException("Erro ao buscar paciente por CPF: " + e.getMessage(), e);
        } finally {
            ConexaoBanco.fecharConexao(conn);
        }

        return paciente;
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


    public void atualizarPaciente(Paciente paciente) {
        String sql = "UPDATE pacientes SET nome = ?, idade = ?, rg = ?, convenio = ?, id_convenio = ? WHERE cpf = ?";

        Connection conn = null;
        try {
            conn = ConexaoBanco.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.setString(1, paciente.getNome());
            stmt.setInt(2, paciente.getIdade());
            stmt.setString(3, paciente.getRg());
            stmt.setBoolean(4, paciente.isConvenio());
            stmt.setInt(5, paciente.getIdConvenio());
            stmt.setString(6, paciente.getCpf());

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Paciente atualizado com sucesso!");
            } else {
                System.out.println("Nenhum paciente encontrado com o CPF informado.");
            }

        } catch (SQLException e) {
            throw new HcException("Erro ao atualizar paciente: " + e.getMessage(), e);
        } finally {
            ConexaoBanco.fecharConexao(conn);
        }
    }


    public void deletarPaciente(String cpf) {
        String sql = "DELETE FROM pacientes WHERE cpf = ?";

        Connection conn = null;
        try {
            conn = ConexaoBanco.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setString(1, cpf);

            int linhasAfetadas = stmt.executeUpdate();

            if (linhasAfetadas > 0) {
                System.out.println("Paciente deletado com sucesso!");
            } else {
                System.out.println("Nenhum paciente encontrado com o CPF informado.");
            }

        } catch (SQLException e) {
            throw new HcException("Erro ao deletar paciente: " + e.getMessage(), e);
        } finally {
            ConexaoBanco.fecharConexao(conn);
        }
    }


    public boolean pacienteExiste(String cpf) {
        return buscarPorCpf(cpf) != null;
    }


    public int contarPacientes() {
        String sql = "SELECT COUNT(*) AS total FROM pacientes";
        int total = 0;

        Connection conn = null;
        try {
            conn = ConexaoBanco.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                total = rs.getInt("total");
            }

        } catch (SQLException e) {
            throw new HcException("Erro ao contar pacientes: " + e.getMessage(), e);
        } finally {
            ConexaoBanco.fecharConexao(conn);
        }

        return total;
    }

    public List<Paciente> buscarPorFaixaEtaria(int idadeMinima, int idadeMaxima) {
        String sql = "SELECT * FROM pacientes WHERE idade BETWEEN ? AND ? ORDER BY idade";
        List<Paciente> pacientes = new ArrayList<>();

        Connection conn = null;
        try {
            conn = ConexaoBanco.obterConexao();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setInt(1, idadeMinima);
            stmt.setInt(2, idadeMaxima);
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
            throw new HcException("Erro ao buscar pacientes por faixa etária: " + e.getMessage(), e);
        } finally {
            ConexaoBanco.fecharConexao(conn);
        }

        return pacientes;
    }
}