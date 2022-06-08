package Boundary;
import Observer.CommandExecution;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class PrincipalBoundary extends Application implements CommandExecution {
    private EventosBoundary eventosBoundary = new EventosBoundary();
    private ParceriasBoundary parceriasBoundary = new ParceriasBoundary();
    private SalaBoundary salaBoundary = new SalaBoundary();
    private LocacaoBoundary locacaoBoundary = new LocacaoBoundary();
    private FuncionarioBoundary funcionarioBoundary = new FuncionarioBoundary();
    private ClientesBoundary clientesBoundary = new ClientesBoundary();
    private ExemplarBoundary exemplarBoundary = new ExemplarBoundary();
    private CreditosBoundary creditosBoundary = new CreditosBoundary();

    private BorderPane principal = new BorderPane();

    public PrincipalBoundary(){
        eventosBoundary.addExecution(this);
        parceriasBoundary.addExecution(this);
        salaBoundary.addExecution(this);
        locacaoBoundary.addExecution(this);
        funcionarioBoundary.addExecution(this);
        clientesBoundary.addExecution(this);
        exemplarBoundary.addExecution(this);
        creditosBoundary.addExecution(this);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Scene scn = new Scene(principal, 565,400);

        MenuBar MenuPrincipal = new MenuBar();

        Menu MenuCadastro = new Menu("Cadastros");
        Menu MenuInicio = new Menu("Home");
        Menu MenuSobre = new Menu("Sobre");
        Menu MenuAjuda = new Menu("Ajuda");

        MenuItem itemEvento = new MenuItem("Cadastro/Pesquisa Eventos");
        MenuItem itemParceria = new MenuItem("Cadastro/Pesquisa Parceria");
        MenuItem itemSala = new MenuItem("Cadastro/Pesquisa Sala");
        MenuItem itemAlocacao = new MenuItem("Cadastro/Pesquisa Alocação");
        MenuItem itemFuncionario = new MenuItem("Cadastro/Pesquisa Funcionários");
        MenuItem itemClientes = new MenuItem("Cadastro/Pesquisa Clientes");
        MenuItem itemExemplar = new MenuItem("Cadastro/Pesquisa Exemplar");

        MenuItem itemSobre = new MenuItem("Sobre");

        MenuItem itemHome = new MenuItem("Home");

        MenuItem itemAjuda = new MenuItem("Ajuda");

        itemEvento.setOnAction( (e) ->{
            execute("EVENTOS-BOUNDARY");
        });

        itemParceria.setOnAction( event -> {
            execute("PARCERIAS-BOUNDARY");
        });

        itemSala.setOnAction( (e) -> {
            execute("SALA-BOUNDARY");
        });

        itemAlocacao.setOnAction( (e) ->{
            execute("ALOCACAO-BOUNDARY");
        });

        itemFuncionario.setOnAction( (e) ->{
            execute("FUNCIONARIO-BOUNDARY");
        });

        itemClientes.setOnAction( (e) -> {
            execute("CLIENTES-BOUNDARY");
        });

        itemExemplar.setOnAction( (e) ->{
            execute("EXEMPLAR-BOUNDARY");
        });

        itemSobre.setOnAction((event -> {
            execute("CREDITOS-BOUNDARY");
        }));

        itemHome.setOnAction(event -> {
            principal.setCenter(null);
        });

        itemAjuda.setOnAction( (event -> getHostServices().showDocument("www.google.com.br")));

        MenuInicio.getItems().addAll(itemHome);
        MenuSobre.getItems().addAll(itemSobre);
        MenuCadastro.getItems().addAll(itemAlocacao, itemEvento, itemSala, itemParceria, itemFuncionario, itemClientes, itemExemplar);
        MenuAjuda.getItems().addAll(itemAjuda);

        MenuPrincipal.getMenus().addAll(MenuInicio, MenuCadastro, MenuSobre, MenuAjuda);
        principal.setTop(MenuPrincipal);

        stage.setScene(scn);
        stage.setTitle("Livraria da Programação não orientada a objetos");
        stage.show();
    }

    @Override
    public void execute(String command) {
        switch (command){
            case "EVENTOS-BOUNDARY":
               principal.setCenter(eventosBoundary.render());
               break;
            case "PARCERIAS-BOUNDARY":
                principal.setCenter(parceriasBoundary.render());
                break;
            case  "SALA-BOUNDARY":
                principal.setCenter(salaBoundary.render());
                break;
            case "ALOCACAO-BOUNDARY":
                principal.setCenter(locacaoBoundary.render());
                break;
            case "FUNCIONARIO-BOUNDARY":
                principal.setCenter(funcionarioBoundary.render());
                break;
            case "CLIENTES-BOUNDARY":
                principal.setCenter(clientesBoundary.render());
                break;
            case "EXEMPLAR-BOUNDARY":
                principal.setCenter(exemplarBoundary.render());
                break;
            case "CREDITOS-BOUNDARY":
                principal.setCenter(creditosBoundary.render());
                break;
        }
    }

    public static void main(String[] args) {
        Application.launch(PrincipalBoundary.class, args);
    }

}
