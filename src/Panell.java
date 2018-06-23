import org.w3c.dom.events.EventException;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Panell extends JPanel {

    private JButton numbers[] = new JButton[10];
    private Font font = new Font("SanSerif", Font.BOLD, 10);
    private JTextField output = new JTextField();
    private JButton backSpace = new JButton("C");
    private JButton equ = new JButton("=");
    private JButton plus = new JButton("+");
    private JButton minus = new JButton("-");
    private JButton multi = new JButton("*");
    private JButton div = new JButton("/");
    private String mark = "empty";
    private int num1 = 0;
    private int num2;

    public Panell() {
        setLayout(null);
        setFocusable(true);
        grabFocus();

        backSpace.setBounds(10, 240, 50, 50);
        backSpace.setFont(font);
        add(backSpace);
        backSpace.addActionListener(new DoBackSpase());

        equ.setBounds(130, 240, 50, 50);
        equ.setFont(font);
        add(equ);
        equ.addActionListener(new DoEqu());

        plus.setBounds(190, 60, 50, 50);
        plus.setFont(font);
        add(plus);
        plus.addActionListener(new DoPlus());

        minus.setBounds(190, 120, 50, 50);
        minus.setFont(font);
        add(minus);
        minus.addActionListener(new DoMinus());

        multi.setBounds(190, 180, 50, 50);
        multi.setFont(font);
        add(multi);
        multi.addActionListener(new DoMulti());

        div.setBounds(190, 240, 50, 50);
        div.setFont(font);
        add(div);
        div.addActionListener(new DoDiv());

        numbers[0] = new JButton("0");
        numbers[0].setBounds(70, 240, 50, 50);
        numbers[0].setFont(font);
        add(numbers[0]);

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                numbers[(x + 1) + (y * 3)] = new JButton((x + 1) + (y * 3) + "");
                numbers[(x + 1) + (y * 3)].setBounds(x * (50 + 10) + 10, y * (50 + 10) + 60, 50, 50);
                numbers[(x + 1) + (y * 3)].setFont(font);
                add(numbers[(x + 1) + (y * 3)]);
            }
        }

        output.setBounds(10, 10, 230, 50);
        output.setFont(font);
        output.setEditable(false);
        add(output);

        ActionListener listener = (ActionEvent e) -> {
            JButton button = (JButton) e.getSource();
            output.setText(output.getText() + button.getText());
        };

        for (JButton button : numbers) {
            button.addActionListener(listener);
        }

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char symvol = e.getKeyChar();

                if (!Character.isDigit(symvol))
                    return;
                output.setText(output.getText() + symvol);

            }
        });
    }

    private class DoBackSpase implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            output.setText("");
        }
    }

    private class DoPlus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DoCalculate(getMark());
            setMark("plus");
        }
    }

    private class DoMinus implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DoCalculate(getMark());
            setMark("minus");
        }
    }

    private class DoMulti implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DoCalculate(getMark());
            setMark("multi");
        }
    }

    private class DoDiv implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DoCalculate(getMark());
            setMark("div");
        }
    }


    private class DoEqu implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            DoCalculate(getMark());
            output.setText(String.valueOf(getNum1()));
            setNum1(0);
            setMark("empty");
        }
    }

    private void DoCalculate(String minMarkEqu) {
        String dataToParse;
        int subNumber = 0;
        dataToParse = output.getText();
        try {
            subNumber = Integer.parseInt(dataToParse);
        } catch (EventException ex) {
            System.out.print(ex);
        }
        setNum2(subNumber);
        output.setText("");
        switch (minMarkEqu) {
            case ("empty"):
                setNum1(getNum2());
                break;
            case ("plus"):
                setNum1(getNum1() + getNum2());
                break;
            case ("minus"):
                setNum1(getNum1() - getNum2());
                break;
            case ("multi"):
                setNum1(getNum1() * getNum2());
                break;
            case ("div"):
                setNum1(getNum1() / getNum2());
                break;
        }
    }

    private int getNum2() {
        return num2;
    }

    private int getNum1() {
        return num1;
    }

    private String getMark() {
        return mark;
    }

    private void setMark(String s) {
        mark = s;
    }

    private void setNum1(int a) {
        num1 = a;
    }

    private void setNum2(int b) {
        num2 = b;
    }
}
