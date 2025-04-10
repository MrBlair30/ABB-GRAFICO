package arbolbinario.GUI;

import arbolbinario.Arbol;
import arbolbinario.Nodo;
import arbolavl.AVL;
import arbolavl.NodoAVL;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 *
 * @author Blair
 */
public class ArbolGUI extends JFrame {
    Arbol arbol;
    AVL arbolAVL;
    BotonModificado btnInsertar, btnInsertarAlAzar, btnEliminarTodo, btnMenu, btnBalancear;
    String nombreBotonBalanceo = "Balancear Árbol";
    private Color colorNodo;
    Nodo nodoEncontrado = null;
    NodoAVL nodoEncontradoAVL = null;
    boolean pintarNodoEncontrado;
    boolean balancear = false;
    
    PanelConDibujos panel;
    int arbolDato;

    int raizX = 600;
    int raizY = 20;
    int raizAncho = 50;
    int raizAltura = 50;

    public ArbolGUI() {

        arbol = new Arbol();
        arbolAVL = new AVL();
        
        Menu menu = new Menu(arbol,arbolAVL,this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Árbol Binario");
        setSize(1200, 700);

        panel = new PanelConDibujos();
        panel.setBackground(new Color(0x34495E));
        JScrollPane scroll = new JScrollPane(panel);
        panel.setLayout(null);
        getContentPane().add(scroll, BorderLayout.CENTER);
        panel.requestFocusInWindow();

        btnInsertar = new BotonModificado("Insertar Nodos al Árbol");
        btnInsertar.setBounds(0, 0, 220, 50);
        btnInsertar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                arbolDato = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número que quiere insertar al árbol"));
                    arbol.insertar(arbolDato);
                    arbolAVL.insertar(arbolDato);
                    pintarNodoEncontrado = false;
                    colorNodo = new Color(0x4422EE);
                    repaint();
            }

        });
        panel.add(btnInsertar);

        btnInsertarAlAzar = new BotonModificado("Insertar Nodos Aleatorios");
        btnInsertarAlAzar.setBounds(225, 0, 220, 50);
        btnInsertarAlAzar.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                arbolDato = (int) (Math.round(Math.random() * 100)) + 1;                
                    arbol.insertar(arbolDato);               
                    arbolAVL.insertar(arbolDato);
                    pintarNodoEncontrado = false;
                    colorNodo = new Color(0x4422EE);
                    repaint();                
            }

        });
        panel.add(btnInsertarAlAzar);
        
        btnMenu = new BotonModificado("Opciones", new Color(0xFFFFFF), new Color(0x44CC22), new Color(0x44CC22));
        btnMenu.setBounds(35, 55, 150, 50);       
        btnMenu.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {                
                //Menu menu = new Menu(arbol,this);
                menu.setVisible(true);                
            }
            
        });
        panel.add(btnMenu);
        
        
        btnBalancear = new BotonModificado(nombreBotonBalanceo);
        btnBalancear.setBounds(960, 55, 220, 50);
        btnBalancear.addActionListener(new ActionListener(){
            
            @Override
            public void actionPerformed(ActionEvent e) {
                balancear = !balancear;
                if (!balancear) {
                    nombreBotonBalanceo = "Balancear Árbol";
                } else {
                    nombreBotonBalanceo = "Desbalancear Árbol";
                }
                btnBalancear.setText(nombreBotonBalanceo);
                repaint();
            }
        
        });
        panel.add(btnBalancear);
        
        btnEliminarTodo = new BotonModificado("Eliminar Todo el Árbol",new Color(0xFFFFFF),new Color(0xEE0000),new Color(0xEE0000));
        btnEliminarTodo.setBounds(960, 0, 220, 50);
        btnEliminarTodo.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                    arbol.eliminarTodo();
                    arbolAVL.eliminarTodo();
                    repaint();
            }

        });
        panel.add(btnEliminarTodo);

        //setVisible(true);
    }

    public void dibujarNodo(Graphics g, Nodo nodo, int x, int y, int espacioX) {
        if (nodo == null) {
            return;
        }

        //dibujar nodo
        if(nodo == nodoEncontrado && pintarNodoEncontrado){
            g.setColor(new Color(0x44CC22));
        }else{
            g.setColor(colorNodo);
        }
        g.fillOval(x, y, raizAncho, raizAltura);        
        g.drawOval(x, y, raizAncho, raizAltura);
        
        //dibujar dato
        g.setColor(new Color(0xFFFFFF));
        g.setFont(new Font("Arial", Font.BOLD, 12));
        if ( (nodo.getDato() >= 0 && nodo.getDato() <= 9) || (nodo.getDato() >= -9 && nodo.getDato() <= 0) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 10 && nodo.getDato() <= 99) || (nodo.getDato() >= -99 && nodo.getDato() <= -10) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 5, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 100 && nodo.getDato() <= 999) || (nodo.getDato() >= -999 && nodo.getDato() <= -100) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 10, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 100 && nodo.getDato() <= 999) || (nodo.getDato() >= -999 && nodo.getDato() <= -100) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 15, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 1000 && nodo.getDato() <= 9999) || (nodo.getDato() >= -9999 && nodo.getDato() <= -1000) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 20, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 10000 && nodo.getDato() <= 99999) || (nodo.getDato() >= -99999 && nodo.getDato() <= -10000) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 25, y + raizAltura / 2);
        } else {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 30, y + raizAltura / 2);
        }

        //dibujar nodo a la izquierda        
        g.setColor(new Color(0XFFFFFF));        
        if (nodo.getIzq() != null) {
            g.drawLine(x + raizAncho / 2, y + raizAltura, x - espacioX + raizAncho / 2, y + 100);
            dibujarNodo(g, nodo.getIzq(), x - espacioX, y + 100, espacioX / 2);
        }

        //dibujar nodo a la derecha
        if (nodo.getDer() != null) {
            g.drawLine(x + raizAncho / 2, y + raizAltura, x + espacioX + raizAncho / 2, y + 100);
            dibujarNodo(g, nodo.getDer(), x + espacioX, y + 100, espacioX / 2);
        }

    }    

    public void dibujarNodoAVL(Graphics g, NodoAVL nodo, int x, int y, int espacioX) {
        if (nodo == null) {
            return;
        }

        //dibujar nodo
        if(nodo == nodoEncontradoAVL && pintarNodoEncontrado){
            g.setColor(new Color(0x44CC22));
        }else{
            g.setColor(colorNodo);
        }
        g.fillOval(x, y, raizAncho, raizAltura);        
        g.drawOval(x, y, raizAncho, raizAltura);
        
        //dibujar dato
        g.setColor(new Color(0xFFFFFF));
        g.setFont(new Font("Arial", Font.BOLD, 12));
        if ( (nodo.getDato() >= 0 && nodo.getDato() <= 9) || (nodo.getDato() >= -9 && nodo.getDato() <= 0) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 10 && nodo.getDato() <= 99) || (nodo.getDato() >= -99 && nodo.getDato() <= -10) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 5, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 100 && nodo.getDato() <= 999) || (nodo.getDato() >= -999 && nodo.getDato() <= -100) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 10, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 100 && nodo.getDato() <= 999) || (nodo.getDato() >= -999 && nodo.getDato() <= -100) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 15, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 1000 && nodo.getDato() <= 9999) || (nodo.getDato() >= -9999 && nodo.getDato() <= -1000) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 20, y + raizAltura / 2);
        } else if ( (nodo.getDato() >= 10000 && nodo.getDato() <= 99999) || (nodo.getDato() >= -99999 && nodo.getDato() <= -10000) ) {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 25, y + raizAltura / 2);
        } else {
            g.drawString(String.valueOf(nodo.getDato()), x + raizAncho / 2 - 30, y + raizAltura / 2);
        }

        //dibujar nodo a la izquierda        
        g.setColor(new Color(0XFFFFFF));        
        if (nodo.getIzq() != null) {
            g.drawLine(x + raizAncho / 2, y + raizAltura, x - espacioX + raizAncho / 2, y + 100);
            dibujarNodoAVL(g, nodo.getIzq(), x - espacioX, y + 100, espacioX / 2);
        }

        //dibujar nodo a la derecha
        if (nodo.getDer() != null) {
            g.drawLine(x + raizAncho / 2, y + raizAltura, x + espacioX + raizAncho / 2, y + 100);
            dibujarNodoAVL(g, nodo.getDer(), x + espacioX, y + 100, espacioX / 2);
        }

    }    
    
    class PanelConDibujos extends JPanel {
        
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            if (!arbol.esVacia()) {
                if(!balancear){
                    dibujarNodo(g, arbol.getRaiz(), raizX, raizY, 300);                
                }else{
                    dibujarNodoAVL(g, arbolAVL.getRaiz(), raizX, raizY, 300);                
                }
            }
        }

        @Override
        public Dimension getPreferredSize() {
            return new Dimension(5200, 5200);
        }
    }

    public void setColorNodo(Color colorNodo) {
        this.colorNodo = colorNodo;
    }

    public void setNodoEncontrado(Nodo nodoEncontrado) {
        this.nodoEncontrado = nodoEncontrado;
    }

    public void setNodoEncontradoAVL(NodoAVL nodoEncontradoAVL) {
        this.nodoEncontradoAVL = nodoEncontradoAVL;
    }
    

    public static void main(String[] args) {
        ArbolGUI agui = new ArbolGUI();
        agui.setVisible(true);
    }
}
