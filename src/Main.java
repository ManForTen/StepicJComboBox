import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    static JPanel panel = new JPanel ();

    static void addObject(JComponent o, int n, float alignmentX){ // Метод добавления объектов на панель
        o.setAlignmentX(alignmentX); // Горизонтальное выравнивание
        panel.add(o);
        panel.add(Box.createVerticalStrut(n)); // Пустой промежуток после него
    };

    public static void main(String[] args) {
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); // Менеджер для выравнивания
        panel.add(Box.createVerticalStrut(10)); // Пустые промежутки
        JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int width = 100, height = 100;
        frame.setBounds(dim.width / 2 - width / 2, dim.height / 2 - height / 2, width, height);
        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Красный");
        comboBox.addItem("Оранжевый");
        comboBox.addItem("Зеленый");
        comboBox.setMaximumSize(new Dimension(100,30)); // Максимальный размер, чтобы не растягивался по всей форме
        addObject(comboBox,10,Component.CENTER_ALIGNMENT); // Добавляем его

        JCheckBox checkBox = new JCheckBox("Свой вариант");
        addObject(checkBox,10,Component.CENTER_ALIGNMENT);

        JTextField textBox = new JTextField();
        textBox.setEnabled(false);
        textBox.setMaximumSize(new Dimension(100,30));
        addObject(textBox,10,Component.CENTER_ALIGNMENT);

        JButton button = new JButton("Ответить");
        addObject(button,10,Component.CENTER_ALIGNMENT);

        JLabel label = new JLabel("Ответ:");
        addObject(label,10,Component.CENTER_ALIGNMENT);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setText("Ответ: "+(checkBox.isSelected()?textBox.getText():comboBox.getSelectedItem().toString())); // Считываем значение из комбобокса или текстбокса, если чекбокс выбран
            }
        });

        checkBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textBox.setEnabled(checkBox.isSelected()); // Если чекбокс нажат, то текстбокс доступен
                comboBox.setEnabled(!checkBox.isSelected()); // Если чекбокс нажат, то комбобокс недоступен
            }
        });

        frame.add(panel);
        frame.pack();
        frame.setVisible(true);
    }
}