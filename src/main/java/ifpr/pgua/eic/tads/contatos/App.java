package ifpr.pgua.eic.tads.contatos;

import ifpr.pgua.eic.tads.contatos.controllers.AddController;
import ifpr.pgua.eic.tads.contatos.controllers.AddTarefaController;
import ifpr.pgua.eic.tads.contatos.controllers.IndexController;
import ifpr.pgua.eic.tads.contatos.controllers.ListController;
import ifpr.pgua.eic.tads.contatos.controllers.ListTarefaController;
import ifpr.pgua.eic.tads.contatos.controllers.SearchController;
import ifpr.pgua.eic.tads.contatos.model.Agenda;
import ifpr.pgua.eic.tads.contatos.utils.JavalinUtils;
import io.javalin.Javalin;

public class App {
    public static void main(String[] args) {

        Javalin app = JavalinUtils.makeApp(8080);
        Agenda agenda = new Agenda();
        IndexController indexController = new IndexController();
        AddController addController = new AddController(agenda);
        ListController listController = new ListController(agenda);
        SearchController searchController = new SearchController(agenda);
        AddTarefaController addTarefaController = new AddTarefaController(agenda);
        ListTarefaController listTarefaController = new ListTarefaController(agenda);

        app.get("/", indexController.get);

        app.get("/add", addController.get);
        app.post("/add", addController.post);

        app.get("/list", listController.get);

        app.get("/search", searchController.get);
        app.post("/search", searchController.post);

        app.get("/addTarefa", addTarefaController.get);
        app.post("/addTarefa", addTarefaController.post);

        app.get("/listTarefas", listTarefaController.get);
    }
}
