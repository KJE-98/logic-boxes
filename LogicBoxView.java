import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class LogicBoxView extends JFrame implements ActionListener {
    LogicBoxControl control;
    JPanel output;
    JTextField input;
    JPanel mainPanel;
    JButton returnButton;
    JButton updateButton;
    Map<JLabel, Box> iconToBox = new HashMap<>();
    JComboBox ifBoxSelect;
    JComboBox thenBoxSelect;
    JButton addTie;

    LogicBoxView() {

        this.mainPanel = new JPanel();
        this.mainPanel.setLayout(new FlowLayout());

        this.output = new JPanel();
        this.output.setLayout(new FlowLayout());

        this.input = new JTextField("Enter Statements Here");

        this.returnButton = new JButton("Enter");
        this.returnButton.addActionListener(this);

        this.updateButton = new JButton("Update");
        this.updateButton.addActionListener(this);

        this.ifBoxSelect = new JComboBox();
        this.thenBoxSelect = new JComboBox();

        this.addTie = new JButton("Add Dependancy");
        this.addTie.addActionListener(this);

        this.setLayout(new GridLayout(1, 2));

        //add things to panel
        this.mainPanel.add(this.input);
        this.mainPanel.add(this.returnButton);
        this.mainPanel.add(this.updateButton);
        this.mainPanel.add(this.ifBoxSelect);
        this.mainPanel.add(this.thenBoxSelect);
        this.mainPanel.add(this.addTie);

        // add componenets to this
        this.add(this.mainPanel);
        this.add(this.output);

        this.setSize(900, 400);

        this.setVisible(true);
    }

    void linkControl(LogicBoxControl control) {
        this.control = control;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // If Return is Pressed
        if (e.getSource() == this.returnButton) {
            System.out.println("I got an action");
            String statement = this.input.getText();
            this.input.setText("Enter Statement");
            this.control.addBox(new Box(statement, true));
        }
        // if update is pressed
        else if (e.getSource() == this.updateButton) {
            this.control.update();
        }
        //If dependency is added
        else if (e.getSource() == this.addTie) {
            System.out.println("dependency action");
            Box ifBox = (Box) this.ifBoxSelect.getSelectedItem();
            Box thenBox = (Box) this.thenBoxSelect.getSelectedItem();
            this.control.addIfThen(ifBox, thenBox);
        } else {
            for (JLabel text : this.iconToBox.keySet()) {
                if (e.getSource() == text) {
                    this.iconToBox.get(text).validate();
                    this.control.update();
                    System.out.println("i got an action!");
                    break;
                }

            }
        }
    }

    public void showBoxes(Set<Box> boxes) {
        this.output.removeAll();
        this.ifBoxSelect.removeAllItems();
        this.thenBoxSelect.removeAllItems();
        this.iconToBox.clear();
        for (Box box : boxes) {
            System.out.println(box.statement);

            JLabel boxVisual = new JLabel(box.statement);
            boxVisual.setOpaque(true);

            boxVisual.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    LogicBoxView.this.iconToBox.get(boxVisual).validate();
                    LogicBoxView.this.control.update();
                    System.out.println("I got an action");
                }

            }

            );
            this.iconToBox.put(boxVisual, box);
            this.output.add(boxVisual);

            if (box.state == true) {
                boxVisual.setBackground(Color.GREEN);
            }
            if (box.state == false) {
                boxVisual.setBackground(Color.RED);
            }
            this.ifBoxSelect.addItem(box);
            this.thenBoxSelect.addItem(box);
        }
        this.setVisible(true);

    }

}
