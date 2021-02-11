package BananaShooter;

import java.awt.EventQueue;
import javax.swing.JFrame;

public class BananaShoot extends JFrame {

	public BananaShoot() {
        
        initUI();
    }
    
    private void initUI() {
        
        add(new Display());
        
        setResizable(false);
        pack();
        
        setTitle("Banana-Shooter 1.0");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		EventQueue.invokeLater(() -> {
            BananaShoot ex = new BananaShoot();
            ex.setVisible(true);
        });
    }
		

}