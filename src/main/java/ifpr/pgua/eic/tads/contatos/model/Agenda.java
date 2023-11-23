package ifpr.pgua.eic.tads.contatos.model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Agenda {
    private ArrayList<Contato> lista;
    private ArrayList<Tarefa> listaTarefas;

    public Agenda() {
        lista = new ArrayList<>();
        listaTarefas = new ArrayList<>();
    }

    public ArrayList<Contato> getLista() {
        lista.clear();
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://wagnerweinert.com.br:3306/tads23_pedrotonial",
                    "tads23_pedrotonial", "tads23_pedrotonial");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_contatos");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String email = rs.getString("email");
                String telefone = rs.getString("telefone");

                Contato contato = new Contato(id, nome, email, telefone);

                lista.add(contato);
            }

        } catch (SQLException e) {
            System.out.println("Problema ao fazer seleção" + e.getMessage());
        }

        return lista;
    }

    public String cadastrar(String nome, String telefone, String email) {

        if (nome.isBlank() || nome.isEmpty()) {
            return "Nome inválido!";
        }

        if (telefone.isBlank() || telefone.isEmpty()) {
            return "Telefone inválido!";
        }

        if (email.isBlank() || email.isEmpty()) {
            return "E-mail inválido!";
        }

        if (buscar(nome) == null) {

            Contato contato = new Contato(nome, telefone, email);

            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://wagnerweinert.com.br:3306/tads23_pedrotonial",
                        "tads23_pedrotonial", "tads23_pedrotonial");

                PreparedStatement pstm = con
                        .prepareStatement("INSERT INTO oo_contatos(nome, email, telefone) VALUES (?,?,?)");

                pstm.setString(1, contato.getNome());
                pstm.setString(2, contato.getEmail());
                pstm.setString(3, contato.getTelefone());

                pstm.executeUpdate();

                lista.add(contato);
                return "Cadastrado!";
            } catch (SQLException e) {
                return "Problema ao conectar" + e.getMessage();
            }

        } else {
            return "Erro! Dados já cadastrados!";
        }

    }

    public Contato buscar(String nome) {

        for (Contato c : lista) {
            if (c.getNome().equals(nome)) {
                return c;
            }
        }
        return null;
    }

    public Contato buscarNomeMaisComprido() {

        Contato maiorNome = lista.get(0);

        for (int i = 1; i < lista.size(); i++) {
            if (lista.get(i).getNome().length() > maiorNome.getNome().length()) {
                maiorNome = lista.get(i);
            }
        }
        return maiorNome;
    }

    public String listar() {
        String texto = "Contatos Cadastrados:<br/>";

        for (Contato c : lista) {
            texto += c.toString() + "<br/>";
        }

        return texto;
    }

    public String cadastrarTarefa(String titulo, String descricao) {

        if (titulo.isBlank() || titulo.isEmpty()) {
            return "Titulo inválido!";
        }

        if (descricao.isBlank() || descricao.isEmpty()) {
            return "Descrição inválida!";
        }

        if (buscar(titulo) == null) {

            Tarefa tarefa = new Tarefa(titulo, descricao);

            try {
                Connection con = DriverManager.getConnection(
                        "jdbc:mysql://wagnerweinert.com.br:3306/tads23_pedrotonial",
                        "tads23_pedrotonial", "tads23_pedrotonial");

                PreparedStatement pstm = con
                        .prepareStatement("INSERT INTO oo_tarefas(titulo, descricao) VALUES (?,?)");

                pstm.setString(1, tarefa.getTitulo());
                pstm.setString(2, tarefa.getDescricao());

                pstm.executeUpdate();

                listaTarefas.add(tarefa);
                return "Tarefa cadastrada!";
            } catch (SQLException e) {
                return "Problema ao conectar" + e.getMessage();
            }

        } else {
            return "Erro! Tarefa já cadastrada!";
        }

    }

    public ArrayList<Tarefa> getListaTarefas() {
        listaTarefas.clear();
        try {
            Connection con = DriverManager.getConnection(
                    "jdbc:mysql://wagnerweinert.com.br:3306/tads23_pedrotonial",
                    "tads23_pedrotonial", "tads23_pedrotonial");
            PreparedStatement pstm = con.prepareStatement("SELECT * FROM oo_tarefas");

            ResultSet rs = pstm.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                String descricao = rs.getString("descricao");

                Tarefa tarefa = new Tarefa(id, titulo, descricao);

                listaTarefas.add(tarefa);
            }

        } catch (SQLException e) {
            System.out.println("Problema ao fazer seleção" + e.getMessage());
        }

        return listaTarefas;
    }

    public Tarefa buscarTarefa(String titulo) {

        for (Tarefa t : listaTarefas) {
            if (t.getTitulo().equals(titulo)) {
                return t;
            }
        }
        return null;
    }
}
