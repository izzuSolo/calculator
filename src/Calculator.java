import javax.swing.*;

public class Calculator{

    private JFrame window;

    public Calculator(){
        window = new JFrame("Калькулятор");
        window.setSize(250, 335);
        window.add(new Panell());
        window.setLocationRelativeTo(null);
        window.setResizable(false);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setVisible(true);
    }

    public static void main(String[] args){

        SwingUtilities.invokeLater(new Runnable(){
            @Override
            public void run(){
                new Calculator();
            }
        });

    }
}