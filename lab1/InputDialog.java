package superhero.view;

import java.awt.BorderLayout;
import java.awt.Dialog;
import java.awt.Frame;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import superhero.controller.SuperheroController;

/** Modal dialog that collects text values and sends them to the controller. */
public class InputDialog extends JDialog {

    private final JTextField heightField = new JTextField(12);
    private final JTextField weightField = new JTextField(12);
    private final JTextField ageField = new JTextField(12);
    private final JTextField pullUpsField = new JTextField(12);
    private final JTextField sleepHoursField = new JTextField(12);

    public InputDialog(Frame owner, SuperheroController controller) {
        super(owner, "Ввод данных супергероя", Dialog.ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel fields = new JPanel(new GridLayout(5, 2, 8, 8));
        fields.setBorder(BorderFactory.createEmptyBorder(15, 15, 10, 15));
        addField(fields, "Рост (см):", heightField);
        addField(fields, "Вес (кг):", weightField);
        addField(fields, "Возраст (лет):", ageField);
        addField(fields, "Подтягивания:", pullUpsField);
        addField(fields, "Сон (часов):", sleepHoursField);

        JButton calculateButton = new JButton("Рассчитать");
        calculateButton.addActionListener(event -> controller.processInput(this,
                heightField.getText(), weightField.getText(), ageField.getText(),
                pullUpsField.getText(), sleepHoursField.getText()));
        JButton cancelButton = new JButton("Отмена");
        cancelButton.addActionListener(event -> dispose());
        JPanel buttons = new JPanel();
        buttons.add(calculateButton);
        buttons.add(cancelButton);

        add(fields, BorderLayout.CENTER);
        add(buttons, BorderLayout.SOUTH);
        pack();
        setLocationRelativeTo(owner);
    }

    private void addField(JPanel panel, String label, JTextField field) {
        panel.add(new JLabel(label));
        panel.add(field);
    }

    public void setValues(int height, double weight, int age, int pullUps, double sleepHours) {
        heightField.setText(String.valueOf(height));
        weightField.setText(String.valueOf(weight));
        ageField.setText(String.valueOf(age));
        pullUpsField.setText(String.valueOf(pullUps));
        sleepHoursField.setText(String.valueOf(sleepHours));
    }
}