package Boundary;

import Control.LocacaoControl;
import Boundary.StrategyBoundary;
import Entity.Locacao;
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

public class LocacaoBoundary extends CommandProducer implements StrategyBoundary {
    private TextField txtIdLocacao = new TextField();
    private TextField txtSalaLocacao = new TextField();
    private DatePicker dataLocacao = new DatePicker();
    private TextField horaLocacao = new TextField();
    private TextField responsavelLocacao = new TextField();

    LocacaoControl control = new LocacaoControl();

    private Button btnSalvar = new Button("Salvar");
    private Button btnPesquisar = new Button("Pesquisar");

    private DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    private TableView<Locacao> table = new TableView<>();

    private void criarTabela(){
        TableColumn<Locacao, Integer> col1 = new TableColumn<>("IdLocacao");
        col1.setCellValueFactory( new PropertyValueFactory<>("idLocacao"));

//        TableColumn<Locacao, String> col2 = new TableColumn<>("NomeSala");
//        col2.setCellValueFactory( new PropertyValueFactory<>("nomeSala"));

        TableColumn<Locacao, String> col3 = new TableColumn<>("DataLocacao");
        col3.setCellValueFactory( (dataLoc) ->{
            LocalDate dt = dataLoc.getValue().getDataLocacao();
            String data = dt.format(this.dataFormat);
            return new ReadOnlyStringWrapper(data);
        });

        TableColumn<Locacao, String> col4 = new TableColumn<>("HoraLocacao");
        col4.setCellValueFactory( new PropertyValueFactory<>("horaLocacao"));

        TableColumn<Locacao, String> col5 = new TableColumn<>("ResponsavelLocacao");
        col5.setCellValueFactory( new PropertyValueFactory<>("responsavelLocacao"));

        TableColumn<Locacao, String> col6 = new TableColumn<>("Remover");
        col6.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
        col6.setCellFactory( (tbCol) ->
                new TableCell<Locacao, String>() {
                    final Button btn = new Button("Remover");

                    public void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction( (e) -> {
                                Locacao loc = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Você confirma a remoção do Evento ID" +
                                                loc.getIdLocacao(), ButtonType.OK, ButtonType.CANCEL);
                                Optional<ButtonType> clicado = alert.showAndWait();
                                if (clicado.isPresent() &&
                                        clicado.get().equals(ButtonType.OK)) {
                                    control.remover(loc.getIdLocacao());
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                }
        );

        table.getColumns().addAll(col1, /*col2,*/ col3, col4, col5, col6);
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
        Bindings.bindBidirectional(txtIdLocacao.textProperty(), control.idLocacao, new NumberStringConverter());
        Bindings.bindBidirectional(txtSalaLocacao.textProperty(), control.salaLocacao);
        Bindings.bindBidirectional(dataLocacao.valueProperty(), control.dataLocacao);
        Bindings.bindBidirectional(horaLocacao.textProperty(), control.horaLocacao);
        Bindings.bindBidirectional(responsavelLocacao.textProperty(), control.responsavelLocacao);

        grid.setVgap(3);
        grid.setHgap(15);

        grid.add(new Label("Id"), 0,0);
        grid.add(txtIdLocacao, 1, 0);

        grid.add(new Label("Sala"), 0, 1);
        grid.add(txtSalaLocacao, 1, 1);

        grid.add(new Label("Data"), 0, 2);
        grid.add(dataLocacao, 1, 2);

        grid.add(new Label("Hora"), 0, 3);
        grid.add(horaLocacao, 1, 3);

        grid.add(new Label("Responsável"),0,4);
        grid.add(responsavelLocacao, 1,4);

        grid.add(btnSalvar, 0, 5);
        grid.add(btnPesquisar, 1, 5);

        btnSalvar.setOnAction( (e) ->{
            control.salvar();
        });

        btnPesquisar.setOnAction( (e) -> {
            control.pesquisarLocacao();
        });

        principal.setTop(grid);
        principal.setCenter(table);
        this.criarTabela();
        return principal;
    }
}
