package superhero.view;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import superhero.controller.SuperheroController;
import superhero.model.SuperheroModel;

/** Main window. It refreshes itself when the active model sends a notification. */
public class MainView extends JFrame implements SuperheroModel.ModelListener {

    private final SuperheroModel model;
    private final JLabel heightLabel = new JLabel("Рост: —");
    private final JLabel weightLabel = new JLabel("Вес: —");
    private final JLabel ageLabel = new JLabel("Возраст: —");
    private final JLabel pullUpsLabel = new JLabel("Подтягивания: —");
    private final JLabel sleepLabel = new JLabel("Сон: —");
    private final JLabel levelLabel = new JLabel("Уровень: данные не введены");

    public MainView(SuperheroModel model, SuperheroController controller) {
        this.model = model;
        model.addListener(this);

        setTitle("Калькулятор уровня супергероя");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(430, 320);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Калькулятор уровня супергероя", SwingConstants.CENTER);
        title.setFont(title.getFont().deriveFont(Font.BOLD, 18f));

        JPanel dataPanel = new JPanel(new GridLayout(6, 1, 0, 7));
        dataPanel.setBorder(BorderFactory.createEmptyBorder(15, 30, 15, 30));
        dataPanel.add(heightLabel);
        dataPanel.add(weightLabel);
        dataPanel.add(ageLabel);
        dataPanel.add(pullUpsLabel);
        dataPanel.add(sleepLabel);
        levelLabel.setFont(levelLabel.getFont().deriveFont(Font.BOLD));
        dataPanel.add(levelLabel);

        JButton inputButton = new JButton("Ввести данные");
        inputButton.addActionListener(event -> controller.openInputDialog(this));
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(inputButton);

        add(title, BorderLayout.NORTH);
        add(dataPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }

    @Override
    public void onModelChanged() {
        heightLabel.setText("Рост: " + model.getHeight() + " см");
        weightLabel.setText("Вес: " + model.getWeight() + " кг");
        ageLabel.setText("Возраст: " + model.getAge() + " лет");
        pullUpsLabel.setText("Подтягивания: " + model.getPullUps());
        sleepLabel.setText("Сон: " + model.getSleepHours() + " ч");
        levelLabel.setText("Уровень: " + model.getLevel().toUpperCase());
    }
}