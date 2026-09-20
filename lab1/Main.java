package superhero;

import javax.swing.SwingUtilities;
import superhero.controller.SuperheroController;
import superhero.model.SuperheroModel;
import superhero.view.MainView;

/** Entry point of the Swing application. */
public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            SuperheroModel model = new SuperheroModel();
            SuperheroController controller = new SuperheroController(model);
            MainView view = new MainView(model, controller);
            view.setVisible(true);
        });
    }
}