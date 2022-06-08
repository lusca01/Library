package Boundary;
import Control.EventosControl;
import Entity.Eventos;
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


public class EventosBoundary extends CommandProducer implements StrategyBoundary {
   private TextField txtIdEvento = new TextField();
   private ComboBox<String> comboTipo = new ComboBox<>();
   private DatePicker dataEvento = new DatePicker();
   private TextField txtRepresentante = new TextField();
   private TextField txtParticipacao = new TextField();
   private ComboBox<String> comboPublico = new ComboBox<>();

   private Button btnSalvar = new Button("Salvar");
   private Button btnPesquisar = new Button("Pesquisar");

   private EventosControl control = new EventosControl();

   private DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

   private TableView<Eventos> table = new TableView<>();

    private void criarTabela(){
        ObservableList<String> tipos = FXCollections.observableArrayList("Lançamento de livro", "Grupo de leitura", "Palestra", "Outros");
        comboTipo.setItems(tipos);
        ObservableList<String> publicos = FXCollections.observableArrayList("Infantil", "Jovem", "Adulto", "Todos", "Indefinido");
        comboPublico.setItems(publicos);

        TableColumn<Eventos, Integer> col1 = new TableColumn<>("IdEvento");
        col1.setCellValueFactory( new PropertyValueFactory<>("idEvento"));

        TableColumn<Eventos, String> col2 = new TableColumn<>("Tipo de Evento");
        col2.setCellValueFactory( new PropertyValueFactory<>("tipoEvento"));

        TableColumn<Eventos, String> col3 = new TableColumn<>("Data Evento");
        col3.setCellValueFactory( (eventData) ->{
            LocalDate dt = eventData.getValue().getDataEvento();
            String data = dt.format(this.dataFormat);
            return new ReadOnlyStringWrapper(data);
        });

        TableColumn<Eventos, String> col4 = new TableColumn<>("Representante");
        col4.setCellValueFactory( new PropertyValueFactory<>("representante"));

        TableColumn<Eventos, String> col5 = new TableColumn<>("Participação");
        col5.setCellValueFactory( new PropertyValueFactory<>("participacao"));

        TableColumn<Eventos, String> col6 = new TableColumn<>("Público Alvo");
        col6.setCellValueFactory( new PropertyValueFactory<>("publicoAlvo"));

        TableColumn<Eventos, String> col7 = new TableColumn<>("Remover");
        col7.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
        col7.setCellFactory( (tbCol) ->
                new TableCell<Eventos, String>() {
                    final Button btn = new Button("Remover");

                    public void updateItem(String item, boolean empty) {
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            btn.setOnAction( (e) -> {
                                Eventos eve = getTableView().getItems().get(getIndex());
                                Alert alert = new Alert(Alert.AlertType.WARNING,
                                        "Você confirma a remoção do Evento ID" +
                                                eve.getIdEvento(), ButtonType.OK, ButtonType.CANCEL);
                                Optional<ButtonType> clicado = alert.showAndWait();
                                if (clicado.isPresent() &&
                                        clicado.get().equals(ButtonType.OK)) {
                                    control.remover(eve.getIdEvento());
                                }
                            });
                            setGraphic(btn);
                            setText(null);
                        }
                    }
                }
        );

        table.getColumns().addAll(col1, col2, col3, col4, col5, col6, col7);

        table.setItems(control.getListaView());

        table.getSelectionModel().selectedItemProperty().addListener((obs, antigo, novo) ->{
            if(novo != null){
                control.setEntity(novo);
            }
        });


    }

    @Override
    public Pane render() {
        BorderPane principal = new BorderPane();
        GridPane grid = new GridPane();
        Bindings.bindBidirectional(txtIdEvento.textProperty(), control.idEvento, new NumberStringConverter());
        Bindings.bindBidirectional(comboTipo.valueProperty(), control.tipoEvento);
        Bindings.bindBidirectional(dataEvento.valueProperty(), control.dataEvento);
        Bindings.bindBidirectional(txtRepresentante.textProperty(), control.representante);
        Bindings.bindBidirectional(txtParticipacao.textProperty(), control.participacao);
        Bindings.bindBidirectional(comboPublico.valueProperty(), control.publicoAlvo);

        grid.setVgap(3);
        grid.setHgap(15);

        grid.add(new Label("Id"), 0,0);
        grid.add(txtIdEvento, 1, 0);

        grid.add(new Label("Tipo"), 0, 1);
        grid.add(comboTipo, 1, 1);

        grid.add(new Label("Data"), 0, 2);
        grid.add(dataEvento, 1, 2);

        grid.add(new Label("Representante"), 0, 3);
        grid.add(txtRepresentante, 1, 3);

        grid.add(new Label("Participação"),0,4);
        grid.add(txtParticipacao, 1,4);

        grid.add(new Label("Público Alvo"),0, 5);
        grid.add(comboPublico, 1, 5);

        grid.add(btnSalvar, 0, 6);
        grid.add(btnPesquisar, 1, 6);

        btnSalvar.setOnAction( (e) ->{
            control.salvar();
        });

        btnPesquisar.setOnAction( (e) -> {
            control.pesquisarEventos();
        });

        principal.setTop(grid);
        principal.setCenter(table);
        this.criarTabela();
        return principal;
    }
}
