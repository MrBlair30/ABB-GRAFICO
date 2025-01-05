package arbolbinario.GUI;

/**
 *
 * @author Blair
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.LineBorder;
public class BotonModificado extends JButton{

    public BotonModificado(String text) {
        setText(text);          
        setBackground(new Color(0xFFFFFF));        
        setForeground(new Color(0x4422EE)); 
        setBorder(new LineBorder(new Color(0x4422EE), 3));
        
        setFont(new Font("Arial", Font.BOLD, 14)); 
        setFocusPainted(false);
        setBorderPainted(true);
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                setBackground(new Color(0x4422EE));
                setForeground(new Color(0xFFFFFF));
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                setBackground(new Color(0xFFFFFF));
                setForeground(new Color(0x4422EE));
            }
        });
    }

    public BotonModificado(String text, Color bg, Color fg, Color mouseEntered) {
        setText(text);
        setBackground(bg);
        setForeground(fg);
        setBorder(new LineBorder(fg, 3));
        setFont(new Font("Arial",Font.BOLD,14));   
        setFocusPainted(false);
        setBorderPainted(true);
        
        this.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseEntered(MouseEvent e){
                setBackground(mouseEntered);
                setForeground(new Color(0xFFFFFF));
            }
            
            @Override
            public void mouseExited(MouseEvent e){
                setBackground(bg);
                setForeground(fg);
            }
        });
        
    }
    
    @Override
    public void addActionListener(ActionListener l) {
        super.addActionListener(l); 
    }
    
}
