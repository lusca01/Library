package Boundary;

import Boundary.StrategyBoundary;
import Observer.CommandProducer;
import Control.SalaControl;
import Entity.Sala;
import javafx.beans.binding.Bindings;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;
import java.util.Optional;

public class SalaBoundary extends CommandProducer implements StrategyBoundary {
    private TextField txtIdSala = new TextField();
    private TextField txtNomeSala = new TextField();
    private ComboBox<String> cmbTamanhoSala = new ComboBox<>();
    private TextField txtTipoSala = new TextField();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private SalaControl control = new SalaControl();

    private TableView<Sala> table = new TableView<>();

    private void criarTabela() {
        ObservableList<String> items =
                FXCollections.observableArrayList("Pequena", "Média", "Grande");
        cmbTamanhoSala.setItems(items);

        TableColumn<Sala, Long> col1 = new TableColumn<>("Id");
        col1.setCellValueFactory( new PropertyValueFactory<>("idSala") );

        TableColumn<Sala, String> col2 = new TableColumn<>("Nome");
        col2.setCellValueFactory( new PropertyValueFactory<>("nomeSala") );

        TableColumn<Sala, String> col3 = new TableColumn<>("Tamanho");
        col3.setCellValueFactory( new PropertyValueFactory<>("tamanhoSala") );

        TableColumn<Sala, String> col4 = new TableColumn<>("Tipo");
        col4.setCellValueFactory( new PropertyValueFactory<>("tipoSala") );

        TableColumn<Sala, String> col5 = new TableColumn<>("Remover");
        col5.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
        col5.setCellFactory( (tbCol) ->
        new TableCell<Sala, String>() {
            final Button btn = new Button("Remover");

            public void updateItem(String item, boolean empty) {
                if (empty) {
                    setGraphic(null);
                    setText(null);
                } else {
                    btn.setOnAction( (e) -> {
                        Sala sal = getTableView().getItems().get(getIndex());
                        Alert alert = new Alert(Alert.AlertType.WARNING,
                                "Você confirma a remoção da Sala " +
                                        sal.getNomeSala(), ButtonType.OK, ButtonType.CANCEL);
                        Optional<ButtonType> clicado = alert.showAndWait();
                        if (clicado.isPresent() &&
                                clicado.get().equals(ButtonType.OK)) {
                            control.remover(sal.getIdSala());
                        }
                    });
                    setGraphic(btn);
                    setText(null);
                    }
                }
            }
        );

        table.getColumns().addAll(col1, col2, col3, col4, col5);

        table.setItems(control.getListaView());

        table.getSelectionModel().selectedItemProperty().addListener( (obs, antigo, novo) -> {
                    if (novo != null) {
                        control.setEntity(novo);
                    }
                });
    }

    @Override
    public Pane render() {
        BorderPane panPrincipal = new BorderPane();
        GridPane panCampos = new GridPane();
        Bindings.bindBidirectional(txtIdSala.textProperty(), control.idSala, new NumberStringConverter());
        Bindings.bindBidirectional(txtNomeSala.textProperty(), control.nomeSala);
        Bindings.bindBidirectional(cmbTamanhoSala.valueProperty(), control.tamanhoSala);
        Bindings.bindBidirectional(txtTipoSala.textProperty(), control.tipoSala);

        panCampos.setVgap(3);
        panCampos.setHgap(15);

        panCampos.add(new Label("Id"), 0, 0);
        panCampos.add(txtIdSala, 1, 0);

        panCampos.add(new Label("Nome"), 0, 1);
        panCampos.add(txtNomeSala, 1, 1);

        panCampos.add(new Label("Tamanho"), 0, 2);
        panCampos.add(cmbTamanhoSala, 1, 2);

        panCampos.add(new Label("Tipo"), 0, 3);
        panCampos.add(txtTipoSala, 1, 3);


        panCampos.add(btnSalvar, 0, 5);
        panCampos.add(btnPesquisar, 1, 5);

        btnSalvar.setOnAction(e -> {
            control.salvar();
        });

        btnPesquisar.setOnAction( e -> {
           control.pesquisarSala();
        });

        panPrincipal.setTop(panCampos);
        panPrincipal.setCenter(table);
        this.criarTabela();
        return panPrincipal;
    }
}