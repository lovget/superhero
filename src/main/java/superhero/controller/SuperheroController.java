package superhero.controller;

import java.awt.Frame;
import javax.swing.JOptionPane;
import superhero.model.SuperheroModel;
import superhero.view.InputDialog;

public class SuperheroController {

    private final SuperheroModel model;

    public SuperheroController(SuperheroModel model) {
        this.model = model;
    }

    public void openInputDialog(Frame owner) {
        InputDialog dialog = new InputDialog(owner, this);
        if (model.hasData()) {
            dialog.setValues(model.getHeight(), model.getWeight(), model.getAge(),
                    model.getPullUps(), model.getSleepHours());
        }
        dialog.setVisible(true);
    }

    public void processInput(InputDialog dialog, String heightText, String weightText,
                             String ageText, String pullUpsText, String sleepHoursText) {
        try {
            int height = Integer.parseInt(heightText.trim());
            double weight = Double.parseDouble(weightText.trim());
            int age = Integer.parseInt(ageText.trim());
            int pullUps = Integer.parseInt(pullUpsText.trim());
            double sleepHours = Double.parseDouble(sleepHoursText.trim());

            model.setData(height, weight, age, pullUps, sleepHours);
            dialog.dispose();
        } catch (NumberFormatException exception) {
            showError(dialog, "Ошибка: введите числовые значения.");
        } catch (IllegalArgumentException exception) {
            showError(dialog, exception.getMessage());
        }
    }

    private void showError(InputDialog dialog, String message) {
        JOptionPane.showMessageDialog(dialog, message, "Ошибка ввода", JOptionPane.ERROR_MESSAGE);
    }
}