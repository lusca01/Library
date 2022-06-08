package Boundary;
import Control.ExemplarControl;
import Entity.Exemplar;
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

    public class ExemplarBoundary extends CommandProducer implements StrategyBoundary {

        private TextField txfId = new TextField();
        private TextField txfTitulo = new TextField();
        private TextField txfAutor = new TextField();
        private TextField txfNPaginas = new TextField();
        private DatePicker txfAnoPub = new DatePicker();

        private TableView<Exemplar> table = new TableView<>();
        private DateTimeFormatter dataFormat = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        private Button btnAdiciona = new Button("Salvar");
        private Button btnPesquisa = new Button("Pesquisar");

        ExemplarControl control = new ExemplarControl();

        private void criarTabela(){
//            TableColumn<Exemplar,Integer> colunaId = new TableColumn("Id");
//            colunaId.setCellValueFactory(new PropertyValueFactory<>("idexemplar"));

            TableColumn<Exemplar,String> colunaTitulo = new TableColumn<>("Título");
            colunaTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

            TableColumn<Exemplar,String> colunaAutor = new TableColumn<>("Autor");
            colunaAutor.setCellValueFactory(new PropertyValueFactory<>("autor"));

//            TableColumn<Exemplar, Integer> colunaNPaginas = new TableColumn<>("Nº de Páginas");
//            colunaNPaginas.setCellValueFactory(new PropertyValueFactory<>("npaginas"));

            TableColumn<Exemplar, String> colano = new TableColumn<>("Data Lançamento");
            colano.setCellValueFactory( (dataLoc) ->{
                LocalDate dt = dataLoc.getValue().getAnoPublic();
                String data = dt.format(this.dataFormat);
                return new ReadOnlyStringWrapper(data);
            });

            TableColumn<Exemplar, String> colunaAcao = new TableColumn<>("Remover");
            colunaAcao.setCellValueFactory( new PropertyValueFactory<>("DUMMY") );
            colunaAcao.setCellFactory( (tbCol) ->
                    new TableCell<Exemplar, String>() {
                        final Button btn = new Button("Remover");

                        public void updateItem(String item, boolean empty) {
                            if (empty) {
                                setGraphic(null);
                                setText(null);
                            } else {
                                btn.setOnAction( (e) -> {
                                    Exemplar exemplar = getTableView().getItems().get(getIndex());
                                    Alert alert = new Alert(Alert.AlertType.WARNING,
                                            "Você confirma a remoção do Exemplar " +
                                                    exemplar.getTitulo(), ButtonType.OK, ButtonType.CANCEL);
                                    Optional<ButtonType> clicado = alert.showAndWait();
                                    if (clicado.isPresent() &&
                                            clicado.get().equals(ButtonType.OK)) {
                                        control.remover(exemplar.getId());
                                    }
                                });
                                setGraphic(btn);
                                setText(null);
                            }
                        }
                    }
            );
            table.getColumns().addAll(/*colunaId,*/colunaTitulo,colunaAutor/*,colunaNPaginas*/, colano,colunaAcao);

            table.setItems(control.getListaView());

            table.getSelectionModel().selectedItemProperty().addListener(
                    (obs, antigo, novo) -> {
                        if (novo != null) {
                            control.setEntity(novo);
                        }
                    }
            );
        }

        public Pane render(){
            BorderPane principal = new BorderPane();
            GridPane grid = new GridPane();
            Bindings.bindBidirectional(txfId.textProperty(), control.id, new NumberStringConverter());
            Bindings.bindBidirectional(txfTitulo.textProperty(), control.titulo);
            Bindings.bindBidirectional(txfAutor.textProperty(), control.autor);
            Bindings.bindBidirectional(txfNPaginas.textProperty(), control.nPaginas, new NumberStringConverter());
            Bindings.bindBidirectional(txfAnoPub.valueProperty(), control.anopub);

            grid.setVgap(3);
            grid.setHgap(15);

            grid.add(new Label("Id"), 0,0);
            grid.add(txfId, 1, 0);

            grid.add(new Label("Titulo"), 0, 1);
            grid.add(txfTitulo, 1, 1);

            grid.add(new Label("Autor"), 0, 2);
            grid.add(txfAutor, 1, 2);

            grid.add(new Label("n Páginas"), 0, 3);
            grid.add(txfNPaginas, 1, 3);

            grid.add(new Label("Data Lançamento"),0,4);
            grid.add(txfAnoPub, 1,4);

            grid.add(btnAdiciona, 0, 5);
            grid.add(btnPesquisa, 1, 5);

            btnAdiciona.setOnAction( (e) ->{
                control.salvar();
            });

            btnPesquisa.setOnAction( (e) -> {
                control.pesquisarExemplar();
            });

            principal.setTop(grid);
            principal.setCenter(table);
            this.criarTabela();
            return principal;
        }
    }