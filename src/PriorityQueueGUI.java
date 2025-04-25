import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.PriorityQueue;

public class PriorityQueueGUI extends JFrame {
    private PriorityQueue<Integer> pq;
    private JTextField inputField;
    private JTextArea displayArea;

    public PriorityQueueGUI() {
        pq = new PriorityQueue<>();

        setTitle("Priority Queue Visualizer");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel(new BorderLayout());

        // Input panel
        JPanel inputPanel = new JPanel(new FlowLayout());
        inputField = new JTextField(10);
        JButton addButton = new JButton("Add");
        JButton removeButton = new JButton("Remove");

        inputPanel.add(new JLabel("Enter number: "));
        inputPanel.add(inputField);
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        // Display area
        displayArea = new JTextArea(10, 30);
        displayArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(displayArea);

        mainPanel.add(inputPanel, BorderLayout.NORTH);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        add(mainPanel);

        // Add button action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    int value = Integer.parseInt(inputField.getText());
                    pq.offer(value);
                    updateDisplay();
                    inputField.setText("");
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(PriorityQueueGUI.this, "Please enter a valid integer.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });

        // Remove button action
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!pq.isEmpty()) {
                    pq.poll();
                    updateDisplay();
                } else {
                    JOptionPane.showMessageDialog(PriorityQueueGUI.this, "The queue is empty.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    private void updateDisplay() {
        displayArea.setText("Priority Queue Contents:\n" + pq.toString());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new PriorityQueueGUI().setVisible(true);
            }
        });
    }
}