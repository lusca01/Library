package Boundary;
import Control.ClientesControl;
import Entity.Clientes;
import Observer.CommandProducer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ClientesBoundary extends CommandProducer implements StrategyBoundary {
    private TextField txtIdCliente = new TextField();
    private TextField txtNomeCliente = new TextField();
    private TextField rgCliente = new TextField();
    private TextField emailCliente = new TextField();
    private TextField foneCliente = new TextField();
    private DatePicker dtNascimento = new DatePicker();
    private DatePicker dtAfiliacao = new DatePicker();

    private ClientesControl control = new ClientesControl();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private TableView<Clientes> table = new TableView<>();

    private void criarTabela(){
//        TableColumn<Clientes, Integer> col1 = new TableColumn<>("ID");
//        col1.setCellValueFactory( new PropertyValueFactory<>("idCliente"));

        TableColumn<Clientes, String> col2 = new TableColumn<>("NomeCliente");
        col2.setCellValueFactory( new PropertyValueFactory<>("nomeCliente"));

        TableColumn<Clientes, String> col3 = new TableColumn<>("Telefone");
        col3.setCellValueFactory( new PropertyValueFactory<>("telefone"));

        TableColumn<Clientes, String> col8 = new TableColumn<>("Email");
        col8.setCellValueFactory( new PropertyValueFactory<>("email"));

        TableColumn<Clientes, String> col4 = new TableColumn<>("Nascimento");
        col4.setCellValueFactory( (dataLoc) ->{
            LocalDate dt = dataLoc.getValue().getDataNascimento();
            String data = dt.format(this.dataFormat);
            return new ReadOnlyStringWrapper(data);
        });

        TableColumn<Clientes, String> col5 = new TableColumn<>("Afiliação");
        col5.setCellValueFactory( (dataLoc) ->{
            LocalDate dt = dataLoc.getValue().getDataAfiliacao();
            String data = dt.format(this.dataFormat);
            return new ReadOnlyStringWrapper(data);
        });

        TableColumn<Clientes, String> col6 = new TableColumn<>("Remover");
        col6.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
        col6.setCellFactory( (tbCol) ->
                new TableCell<Clientes, String>() {
                    final Button btn = new Button("Remover");

                    public void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction( (e) -> {
                                Clientes cli = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Você confirma a remoção do Cliente" +
                                                cli.getNomeCliente(), ButtonType.OK, ButtonType.CANCEL);
                                Optional<ButtonType> clicado = alert.showAndWait();
                                if (clicado.isPresent() &&
                                        clicado.get().equals(ButtonType.OK)) {
                                    control.remover(cli.getIdClientes());
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                }
        );

        table.getColumns().addAll(/*col1, */col2, col4, col5, col6, col3, col8);
        table.setItems(control.getListaView());

        table.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) ->{
            if (novo != null){
                control.setEntity(novo);
            }
        });
    }

    @Override
    public Pane render() {
        BorderPane principal = new BorderPane();
        GridPane grid = new GridPane();
        Bindings.bindBidirectional(txtIdCliente.textProperty(), control.idCliente, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeCliente.textProperty(), control.nomeCliente);
        Bindings.bindBidirectional(rgCliente.textProperty(), control.docRg);
        Bindings.bindBidirectional(foneCliente.textProperty(), control.telefone);
        Bindings.bindBidirectional(emailCliente.textProperty(), control.email);
        Bindings.bindBidirectional(dtNascimento.valueProperty(), control.dataNascimento);
        Bindings.bindBidirectional(dtAfiliacao.valueProperty(), control.dataAfiliacao);

        grid.setVgap(3);
        grid.setHgap(15);

        grid.add(new Label("Id"), 0,0);
        grid.add(txtIdCliente, 1, 0);

        grid.add(new Label("Nome"), 0, 1);
        grid.add(txtNomeCliente, 1, 1);

        grid.add(new Label("RG"), 0, 2);
        grid.add(rgCliente, 1, 2);

        grid.add(new Label("Nascimento"), 0, 3);
        grid.add(dtNascimento, 1, 3);

        grid.add(new Label("Data Afiliação"),0,4);
        grid.add(dtAfiliacao, 1,4);

        grid.add(new Label("Email"), 0, 5);
        grid.add(emailCliente, 1, 5);

        grid.add(new Label("Telefone"), 0, 6);
        grid.add(foneCliente, 1, 6);

        grid.add(btnSalvar, 0, 7);
        grid.add(btnPesquisar, 1, 7);

        btnSalvar.setOnAction( (e) ->{
            control.salvar();
        });

        btnPesquisar.setOnAction( (e) -> {
            control.pesquisarClientes();
        });

        principal.setTop(grid);
        principal.setCenter(table);
        this.criarTabela();
        return principal;
    }
}
