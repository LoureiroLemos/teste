package ifpr.pgua.eic.tads.contatos.controllers;

import java.util.ArrayList;

import ifpr.pgua.eic.tads.contatos.model.Agenda;
import ifpr.pgua.eic.tads.contatos.model.Tarefa;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class ListTarefaController {
    private Agenda agenda;

    public ListTarefaController(Agenda agenda) {
        this.agenda = agenda;
    }

    public Handler get = (Context ctx) -> {

        ArrayList<Tarefa> listaTarefas = agenda.getListaTarefas();

        String html = "<html><head><meta charset=\"UTF-8\"></head><body><h1>Lista de Tarefas</h1><ol>";

        for (Tarefa t : listaTarefas) {
            html += "<li>" + t.toString() + "</li>";
        }

        html += "</ol><a href=\"/\">Voltar</a></body></html>";
        ctx.html(html);
    };

}
