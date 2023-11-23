package ifpr.pgua.eic.tads.contatos.controllers;

import ifpr.pgua.eic.tads.contatos.model.Agenda;
import ifpr.pgua.eic.tads.contatos.model.Contato;
import io.javalin.http.Context;
import io.javalin.http.Handler;

public class SearchController {

    private Agenda agenda;

    public SearchController(Agenda agenda) {
        this.agenda = agenda;
    }

    public Handler get = (Context ctx) -> {
        ctx.render("templates/search.html");
    };

    public Handler post = (Context ctx) -> {
        String nome = ctx.formParam("nome");

        Contato resultado = agenda.buscar(nome);
        ctx.html(resultado + "<br/><a href=\"/\">Voltar</a>");
    };

}
