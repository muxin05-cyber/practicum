package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.UnaryOperator;

public class GUI extends JFrame{
    int counter = 0;
    private Map<Integer, UnaryOperator<Stew_instruction>> decoratorMap = new HashMap<>();
    private List<JCheckBox> checkboxes = new ArrayList<>();
    private List<Integer> list = new ArrayList<>();
    public GUI(){
        setTitle("Конструктор блюда:");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 700);
        setLocationRelativeTo(null);

        JPanel main_panel = new JPanel(new BorderLayout(15, 10));
        main_panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createTitledBorder("Выберите добавки:"));
        JButton make_order = new JButton("Подтвердить выбор");

        String [] options = {"Двойная порция оленины", "Нордская лепёшка", "Снежные ягоды", "Огненный соус"};
        int i = 0;
        for (String option : options) {
            JCheckBox checkBox = new JCheckBox(option);
            final int index = i;
            checkBox.addItemListener(new ItemListener() {
                @Override
                public void itemStateChanged(ItemEvent e) {

                    if (e.getStateChange() == ItemEvent.SELECTED) {
                        if (!list.contains(index)) {
                            counter += 1;
                            list.add(index);
                        }
                    } else {
                        list.remove(Integer.valueOf(index));
                        counter -= 1;
                    }
                    if (list.size() > 0 && list.size() <= 3) {
                        make_order.setEnabled(true);
                    } else {
                        make_order.setEnabled(false);
                    }
                }
            });
            checkBox.setFont(new Font("Arial", Font.PLAIN, 14));
            checkboxes.add(checkBox);
            panel.add(checkBox);
            i++;
        }
        decoratorMap.put(0, stew -> new Meat(stew));
        decoratorMap.put(1, stew -> new Bread(stew));
        decoratorMap.put(2, stew -> new Berries(stew));
        decoratorMap.put(3, stew -> new Sauce(stew));

        main_panel.add(panel);



        JPanel rightPanel = new JPanel();
        rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Действия"));

        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setBorder(BorderFactory.createTitledBorder("Результат"));
        JTextArea textArea = new JTextArea();
        centerPanel.add(textArea);


        make_order.setFont(new Font("Arial", Font.BOLD, 12));
        make_order.setBackground(new Color(70, 130, 200));
        make_order.setForeground(Color.WHITE);
        make_order.setFocusPainted(false);

        make_order.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Stew_instruction stew = new Stew();
                for (Integer i : list){
                    stew = decoratorMap.get(i).apply(stew);
                }
                LocalDateTime now = LocalDateTime.now();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy HH:mm:ss");
                String formattedTime = now.format(formatter);

                String c = "Состав:\n"+stew.getDescription() + "\nЦена: " + stew.getCost() + "\nВремя заказа: "+formattedTime;
                textArea.setText(c);


            }
        });

        rightPanel.add(make_order);
        main_panel.add(panel, BorderLayout.WEST);
        main_panel.add(rightPanel, BorderLayout.EAST);
        main_panel.add(centerPanel, BorderLayout.CENTER);

        add(main_panel);
        setVisible(true);


    }


}
