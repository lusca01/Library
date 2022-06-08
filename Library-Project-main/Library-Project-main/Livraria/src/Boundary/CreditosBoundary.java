package Boundary;

import Observer.CommandProducer;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;

public class CreditosBoundary extends CommandProducer implements StrategyBoundary {
    @Override
    public Pane render() {
        VBox pane = new VBox();
        pane.getChildren().addAll(
                new Label("Fatec Zona Leste"),
                new Label("Professor: Marcus V. de Castro"),
                new Label("Desenvolvedores:"),
                new Label("Carlos Henrique Mayer da Silva"),
                new Label("Gabriel Evangelista Guarrieiro"),
                new Label("Lucas de Lima Silva"),
                new Label("Marcello Lima Bento"),
                new Label("Projeto desenvolvido na disciplina Laboratório de Engenharia de Software - 06/2022")
        );

        return pane;
    }
}
