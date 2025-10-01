import javax.swing.*;
import java.awt.*;

public class ToDoApp extends JFrame {
    private JTextField taskField;
    private JButton addButton, deleteButton;
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;

    public ToDoApp() {
        setTitle("To-Do App");
        setSize(400, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Use BorderLayout with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);

        // === TOP PANEL ===
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        taskField = new JTextField();
        taskField.setFont(new Font("SansSerif", Font.PLAIN, 16));

        addButton = new JButton("+ Add Task");
        addButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        addButton.setBackground(new Color(0, 153, 255));
        addButton.setForeground(Color.WHITE);
        addButton.setFocusPainted(false);
        addButton.setBorderPainted(false);

        topPanel.add(taskField, BorderLayout.CENTER);
        topPanel.add(addButton, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);

        // === CENTER PANEL (List) ===
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setFont(new Font("SansSerif", Font.PLAIN, 15));
        JScrollPane scrollPane = new JScrollPane(taskList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);

        // === BOTTOM PANEL ===
        JPanel bottomPanel = new JPanel();
        deleteButton = new JButton("🗑 Delete Task");
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        deleteButton.setBackground(new Color(255, 51, 51));
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false);
        deleteButton.setBorderPainted(false);
        bottomPanel.add(deleteButton);

        mainPanel.add(bottomPanel, BorderLayout.SOUTH);

        // === ACTIONS ===
        addButton.addActionListener(e -> {
            String task = taskField.getText().trim();
            if (!task.isEmpty()) {
                taskListModel.addElement(task);
                taskField.setText("");
            }
        });

        // Press Enter to add task
        taskField.addActionListener(e -> addButton.doClick());

        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
                taskListModel.remove(selectedIndex);
            } else {
                JOptionPane.showMessageDialog(this,
                        "Please select a task to delete.",
                        "No Task Selected",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ToDoApp app = new ToDoApp();
            app.setVisible(true);
        });
    }
}
