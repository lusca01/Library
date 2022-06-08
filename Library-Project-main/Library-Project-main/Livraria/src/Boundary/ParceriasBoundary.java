package Boundary;
import Control.ParceriasControl;
import Entity.Parcerias;
import Observer.CommandProducer;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.util.converter.NumberStringConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

public class ParceriasBoundary extends CommandProducer implements StrategyBoundary {
    private TextField txtIdParcerias = new TextField();
    private ComboBox<String> comboTipo = new ComboBox<>();
    private TextField txtNomeParceria = new TextField();
    private DatePicker dataParceria = new DatePicker();
    private TextField txtFornece = new TextField();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private ParceriasControl control = new ParceriasControl();

    private DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private TableView<Parcerias> table = new TableView<>();

    private void criarTabela(){
        ObservableList<String> tipo = FXCollections.observableArrayList("Editora","Biblioteca", "Patrocínio");
        comboTipo.setItems(tipo);

        TableColumn<Parcerias, Integer> col1 = new TableColumn<>("ID Parceria");
        col1.setCellValueFactory(new PropertyValueFactory<>("idParceria"));

        TableColumn<Parcerias, String> col2 = new TableColumn<>("Tipo Parceria");
        col2.setCellValueFactory( new PropertyValueFactory<>("tipoParceria"));

        TableColumn<Parcerias, String> col3 = new TableColumn<>("Nome Parceiro(a)");
        col3.setCellValueFactory( new PropertyValueFactory<>("nomeParceria"));

        TableColumn<Parcerias, String> col4 = new TableColumn<>("Data Parceria");
        col4.setCellValueFactory( (par) ->{
            LocalDate dt = par.getValue().getDataParceria();
            String data = dt.format(this.dataFormat);
            return new ReadOnlyStringWrapper(data);
        });

        TableColumn<Parcerias, String> col5 = new TableColumn<>("Fornece");
        col5.setCellValueFactory( new PropertyValueFactory<>("fornece"));

        TableColumn<Parcerias, String> col6 = new TableColumn<>("Remover");
        col6.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
        col6.setCellFactory( (tb) ->
            new TableCell<Parcerias, String>() {
                final Button btn = new Button("Remover");

                public void updateItem(String item, boolean empty) {
                    if (empty) {
                        setGraphic(null);
                        setText(null);
                    } else {
                        btn.setOnAction( (e) -> {
                            Parcerias par = getTableView().getItems().get(getIndex());
                            Alert alert = new Alert(Alert.AlertType.WARNING,
                                    "Você confirma a remoção da Parceria " +
                                            par.getNomeParceria(), ButtonType.OK, ButtonType.CANCEL);
                            Optional<ButtonType> clicado = alert.showAndWait();
                            if (clicado.isPresent() &&
                                    clicado.get().equals(ButtonType.OK)) {
                                control.remover(par.getIdParceria());
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
        Bindings.bindBidirectional(txtIdParcerias.textProperty(), control.idParceria, new NumberStringConverter());
        Bindings.bindBidirectional(comboTipo.valueProperty(), control.tipoParceria);
        Bindings.bindBidirectional(txtNomeParceria.textProperty(), control.nomeParceria);
        Bindings.bindBidirectional(dataParceria.valueProperty(), control.dataParceria);
        Bindings.bindBidirectional(txtFornece.textProperty(), control.fornece);

        grid.setVgap(3);
        grid.setHgap(15);

        grid.add(new Label("ID Parceria"), 0,0);
        grid.add(txtIdParcerias, 1,0);

        grid.add(new Label("Tipo de Evento"), 0, 1);
        grid.add(comboTipo, 1,1);

        grid.add(new Label("Nome Parceria"), 0,2);
        grid.add(txtNomeParceria, 1,2);

        grid.add(new Label("Data parceria"),0,3);
        grid.add(dataParceria, 1,3);

        grid.add(new Label("Fornece"),0,4);
        grid.add(txtFornece,1,4);

        grid.add(btnSalvar, 0,5);
        grid.add(btnPesquisar,1,5);

        btnSalvar.setOnAction((e)->{
            control.salvar();
        });

        btnPesquisar.setOnAction((e) ->{
            control.pesquisarParcerias();
        });

        principal.setTop(grid);
        principal.setCenter(table);
        this.criarTabela();
        return principal;
    }
}
