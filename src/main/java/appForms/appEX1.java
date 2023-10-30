package appForms;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;

public class appEX1 extends JFrame{
    private JPanel panel;
    private JTextField userFD;
    private JPasswordField passFD;
    private JButton loginBtn;

    public appEX1(){
        setTitle("Inicio de Sesion");
        setSize(500,300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        loginBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String username = userFD.getText();
                char[] passwordChars = passFD.getPassword();
                String password = new String(passwordChars);
                if (verifyCredentials(username,password)){
                    dispose();
                    openMainapp();
                } else {
                JOptionPane.showMessageDialog(appEX1.this, "Datos Invalidos", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    });
        add(panel);
        setLocationRelativeTo(null);
        setVisible(true);
}
        private boolean verifyCredentials(String username, String password) {
        return username.equals("postgres") && password.equals("1234");
    }
    private void openMainapp(){SwingUtilities.invokeLater(() -> new appEX2()); }



}
