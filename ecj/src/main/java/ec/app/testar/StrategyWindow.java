package ec.app.testar;

import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@SuppressWarnings("serial")
public class StrategyWindow extends JFrame {
    boolean isPaused = false;

    private JTextArea strategyArea;
    private JLabel simplifiedLabel = new JLabel("Is simplified: ");
    private JLabel simplifiedValue = new JLabel();
    private JLabel generationLabel = new JLabel("Generation: ");
    private JLabel generationValue = new JLabel();
    private JLabel individualLabel = new JLabel("Individual: ");
    private JLabel individualValue = new JLabel();
    private JLabel runLabel = new JLabel("Run: ");
    private JLabel runValue = new JLabel();
    private JLabel startTimeLabel = new JLabel("Start time: ");
    private JLabel startTimeValue = new JLabel(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
    private JLabel indStartTimeLabel = new JLabel("Ind start time: ");
    private JLabel indStartTimeValue = new JLabel("");
    private JButton pauseButton = new JButton();
    private JButton stopButton = new JButton();

    public StrategyWindow(Evaluator evaluator) {
        init(evaluator);
    }

    private void init(Evaluator evaluator) {
        setBounds(1500, 20, 320, 600);


        JPanel panel = new JPanel();
        this.add(panel);

        strategyArea = new JTextArea();
        strategyArea.setLineWrap(true);
        strategyArea.setEditable(false);

        pauseButton.setText("Pause");
        pauseButton.addActionListener(evaluator);
        pauseButton.setActionCommand("pause");

        stopButton.setText("Stop");
        stopButton.addActionListener(evaluator);
        stopButton.setActionCommand("stop");

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(layout
                .createParallelGroup()
                .addGroup(
                        layout.createSequentialGroup()
                                .addComponent(pauseButton)
                                .addComponent(stopButton))
                .addComponent(strategyArea)
                .addGroup(
                        layout.createSequentialGroup()
                                .addGroup(
                                        layout.createParallelGroup(
                                                GroupLayout.Alignment.LEADING)
                                                .addComponent(simplifiedLabel)
                                                .addComponent(generationLabel)
                                                .addComponent(individualLabel)
                                                .addComponent(runLabel)
                                                .addComponent(startTimeLabel)
                                                .addComponent(indStartTimeLabel))
                                .addGroup(
                                        layout.createParallelGroup(
                                                GroupLayout.Alignment.TRAILING)
                                                .addComponent(simplifiedValue)
                                                .addComponent(generationValue)
                                                .addComponent(individualValue)
                                                .addComponent(runValue)
                                                .addComponent(startTimeValue)
                                                .addComponent(indStartTimeValue))));

        layout.setVerticalGroup(layout
                .createSequentialGroup()
                .addGroup(
                        layout.createParallelGroup()
                                .addComponent(pauseButton)
                                .addComponent(stopButton))
                .addComponent(strategyArea)
                .addGroup(
                        layout.createParallelGroup()
                                .addGroup(
                                        layout.createSequentialGroup()
                                                .addGroup(
                                                        layout.createParallelGroup(
                                                                GroupLayout.Alignment.LEADING)
                                                                .addComponent(
                                                                        simplifiedLabel)
                                                                .addComponent(
                                                                        simplifiedValue))
                                                .addGroup(
                                                        layout.createParallelGroup(
                                                                GroupLayout.Alignment.BASELINE)
                                                                .addComponent(
                                                                        generationLabel)
                                                                .addComponent(
                                                                        generationValue))
                                                .addGroup(
                                                        layout.createParallelGroup(
                                                                GroupLayout.Alignment.BASELINE)
                                                                .addComponent(
                                                                        individualLabel)
                                                                .addComponent(
                                                                        individualValue))
                                                .addGroup(
                                                        layout.createParallelGroup(
                                                                GroupLayout.Alignment.BASELINE)
                                                                .addComponent(
                                                                        runLabel)
                                                                .addComponent(
                                                                        runValue))
                                                .addGroup(
                                                        layout.createParallelGroup(
                                                                GroupLayout.Alignment.BASELINE)
                                                                .addComponent(
                                                                        startTimeLabel)
                                                                .addComponent(
                                                                        startTimeValue))
                                                .addGroup(
                                                        layout.createParallelGroup(
                                                                GroupLayout.Alignment.BASELINE)
                                                                .addComponent(
                                                                        indStartTimeLabel)
                                                                .addComponent(
                                                                        indStartTimeValue)))));
        layout.linkSize(SwingConstants.HORIZONTAL, pauseButton, stopButton);

    }

    void updateFields(Strategy strategy, int generation, int individual,
                      int run) {
        strategyArea.setText("Strategy: " + strategy.getSimple());
        simplifiedValue.setText("" + strategy.didItChange());
        generationValue.setText("" + generation);
        individualValue.setText("" + individual);
        runValue.setText("" + run);
        indStartTimeValue.setText(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
        repaint();
    }

    void togglePause(boolean isPaused) {
        if (isPaused) {
            pauseButton.setText("Paused");
        } else {
            pauseButton.setText("Pause");
        }
    }
}
