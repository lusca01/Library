package Boundary;
import Control.FuncionarioControl;
import Entity.Funcionario;
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

public class FuncionarioBoundary extends CommandProducer implements StrategyBoundary {
    private TextField txtIdFuncionario = new TextField();
    private TextField txtNomeFuncionario = new TextField();
    private TextField txtFuncaoFuncionario = new TextField();
    private DatePicker dataAdmissao = new DatePicker();
    private TextField salarioFuncionario = new TextField();

    FuncionarioControl control = new FuncionarioControl();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private TableView<Funcionario> table = new TableView<>();

    private void criarTabela(){
        TableColumn<Funcionario, Integer> col1 = new TableColumn<>("IdFuncionario");
        col1.setCellValueFactory( new PropertyValueFactory<>("idFuncionario"));

        TableColumn<Funcionario, String> col2 = new TableColumn<>("NomeFuncionario");
        col2.setCellValueFactory( new PropertyValueFactory<>("nomeFuncionario"));

        TableColumn<Funcionario, String> col3 = new TableColumn<>("Função Funcionário");
        col3.setCellValueFactory( new PropertyValueFactory<>("funcaoFuncionario"));

        TableColumn<Funcionario, String> col4 = new TableColumn<>("DataAdmissão");
        col4.setCellValueFactory( (dateAd) ->{
            LocalDate dt = dateAd.getValue().getDataAdmissao();
            String data = dt.format(this.dataFormat);
            return new ReadOnlyStringWrapper(data);
        });

        TableColumn<Funcionario, Double> col5 = new TableColumn<>("Salário Funcionário");
        col5.setCellValueFactory( new PropertyValueFactory<>("salarioFuncionario"));

        TableColumn<Funcionario, String> col6 = new TableColumn<>("Remover");
        col6.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
        col6.setCellFactory( (tbCol) ->
                new TableCell<Funcionario, String>() {
                    final Button btn = new Button("Remover");

                    public void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction( (e) -> {
                                Funcionario fun = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Você confirma a remoção do Funcionário" +
                                                fun.getNomeFuncionario(), ButtonType.OK, ButtonType.CANCEL);
                                Optional<ButtonType> clicado = alert.showAndWait();
                                if (clicado.isPresent() &&
                                        clicado.get().equals(ButtonType.OK)) {
                                    control.remover(fun.getIdFuncionario());
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                }
        );

        table.getColumns().addAll(col1, col2, col3, col4, col5, col6);
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

        Bindings.bindBidirectional(txtIdFuncionario.textProperty(), control.idFuncionario, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeFuncionario.textProperty(), control.nomeFuncionario);
        Bindings.bindBidirectional(txtFuncaoFuncionario.textProperty(), control.funcaoFuncionario);
        Bindings.bindBidirectional(dataAdmissao.valueProperty(), control.dataAdmissao);
        Bindings.bindBidirectional(salarioFuncionario.textProperty(), control.salarioFuncionario, new NumberStringConverter());

        grid.setVgap(3);
        grid.setHgap(15);

        grid.add(new Label("Id"), 0,0);
        grid.add(txtIdFuncionario, 1, 0);

        grid.add(new Label("Nome"), 0, 1);
        grid.add(txtNomeFuncionario, 1, 1);

        grid.add(new Label("Função"), 0, 2);
        grid.add(txtFuncaoFuncionario, 1, 2);

        grid.add(new Label("Data Admissão"), 0, 3);
        grid.add(dataAdmissao, 1, 3);

        grid.add(new Label("Salário"),0,4);
        grid.add(salarioFuncionario, 1,4);

        grid.add(btnSalvar, 0, 5);
        grid.add(btnPesquisar, 1, 5);

        btnSalvar.setOnAction( (e) ->{
            control.salvar();
        });

        btnPesquisar.setOnAction( (e) -> {
            control.pesquisarFuncionario();
        });

        principal.setTop(grid);
        principal.setCenter(table);
        this.criarTabela();
        return principal;
    }
}
