package arbolbinario.GUI;

import arbolavl.AVL;
import arbolavl.NodoAVL;
import arbolbinario.Arbol;
import arbolbinario.Nodo;
import java.awt.*;
import javax.swing.*;

/**
 *
 * @author Blair
 */
public class Menu extends JFrame{
    BotonModificado btnBuscar,btnEliminar,btnModificar,btnRecorrer,btnVolver;
    Arbol arbol;
    AVL arbolAVL;
    ArbolGUI arbolgui;
    Nodo datoEncontrado;    
    NodoAVL datoEncontradoAVL;    
    
    public Menu(Arbol arbolMenu, AVL arbolAVLMenu, ArbolGUI arbolguiColor){
        arbol = arbolMenu;
        arbolAVL = arbolAVLMenu;
        arbolgui = arbolguiColor;
        setTitle("Menú de opciones");
        setSize(220,400);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new GridLayout(5,1));
        
        btnRecorrer = new BotonModificado("Recorrer");
        btnModificar = new BotonModificado("Modificar");
        btnBuscar = new BotonModificado("Buscar");
        btnEliminar = new BotonModificado("Eliminar");
        btnVolver = new BotonModificado("Volver",new Color(0xFFFFFF),new Color(0xEE0000),new Color(0xEE0000));
        
        btnRecorrer.addActionListener(e -> recorrer());
        btnModificar.addActionListener(e -> modificar());
        btnBuscar.addActionListener(e -> buscar());
        btnEliminar.addActionListener(e -> eliminar());
        btnVolver.addActionListener(e -> volver());
        
        add(btnRecorrer);
        add(btnModificar);
        add(btnBuscar);
        add(btnEliminar);
        add(btnVolver);
        
        //setVisible(true);
    }

    private void recorrer() {
        String[] opciones = {"Preorden","Inorden","Postorden","Volver"};
                int eleccion = JOptionPane.showOptionDialog(null, "Eliga una opción de recorrido", "Recorrer Árbol",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
                switch(eleccion){
                    case 0:
                        if(!arbolgui.balancear){
                            System.out.println("Recorrido en Preorden");
                            arbol.preorden();
                        }else{
                            System.out.println("Recorrido en Preorden");
                            arbolAVL.preorden();
                        }
                        break;
                    case 1:
                        if(!arbolgui.balancear){
                            System.out.println("Recorrido en Inorden");
                            arbol.inorden();
                        }else{
                            System.out.println("Recorrido en Inorden");
                            arbolAVL.inorden();
                        }                        
                        break;
                    case 2:
                        if(!arbolgui.balancear){
                            System.out.println("Recorrido en Postorden");
                            arbol.postorden();
                        }else{
                            System.out.println("Recorrido en Postorden");
                            arbolAVL.postorden();
                        }
                        break;
                    case 3:
                        break;
                }
    }

    private void eliminar() {
        int datoAEliminar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número que desea eliminar"));
        if(!arbolgui.balancear){
            Nodo nodoAEliminar = arbol.buscar(datoAEliminar);
            if (nodoAEliminar != null) {
                arbol.eliminarPorNum(datoAEliminar);
                Nodo nodoEliminado = arbol.buscar(datoAEliminar);
                if (nodoEliminado == null) {
                    JOptionPane.showMessageDialog(null, "Dato eliminado [" + datoAEliminar + "]");
                }
                arbolgui.panel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese dato para ser modificado");
            }
        }else{
            NodoAVL nodoAEliminar = arbolAVL.buscar(datoAEliminar);
            if (nodoAEliminar != null) {
                arbolAVL.eliminar(datoAEliminar);
                NodoAVL nodoEliminado = arbolAVL.buscar(datoAEliminar);
                if (nodoEliminado == null) {
                    JOptionPane.showMessageDialog(null, "Dato eliminado [" + datoAEliminar + "]");
                }
                arbolgui.panel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese dato para ser modificado");
            }
        }
    }

    private void buscar() {
        int datoABuscar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número que desea buscar"));
        if(!arbolgui.balancear){
            datoEncontrado = arbol.buscar(datoABuscar);
            if (datoEncontrado != null) {
                JOptionPane.showMessageDialog(null, "Se encontró el dato:\n" + datoEncontrado.getDato());
                arbolgui.setNodoEncontrado(datoEncontrado);
                arbolgui.pintarNodoEncontrado = true;
                arbolgui.panel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Dato no encontrado en el Árbol");
            }
        }else{
            datoEncontradoAVL = arbolAVL.buscar(datoABuscar);
            if (datoEncontradoAVL != null) {
                JOptionPane.showMessageDialog(null, "Se encontró el dato:\n" + datoEncontradoAVL.getDato());
                arbolgui.setNodoEncontradoAVL(datoEncontradoAVL);
                arbolgui.pintarNodoEncontrado = true;
                arbolgui.panel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "Dato no encontrado en el Árbol");
            }
        }
    }

    private void modificar() {
        int datoAModificar = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el número que desea modificar"));
        int nuevoDato = Integer.parseInt(JOptionPane.showInputDialog("Ingrese el nuevo número"));
        if(!arbolgui.balancear){
            boolean estaModificado = arbol.modificar(datoAModificar, nuevoDato);
            if (estaModificado) {
                JOptionPane.showMessageDialog(null, "Dato modificado[" + datoAModificar + "] por nuevo dato --> " + nuevoDato);
                arbolgui.pintarNodoEncontrado = true;
                arbolgui.panel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese dato para ser modificado");
            }
        }else{
            boolean estaModificado = arbolAVL.modificar(datoAModificar, nuevoDato);
            if (estaModificado) {
                JOptionPane.showMessageDialog(null, "Dato modificado[" + datoAModificar + "] por nuevo dato --> " + nuevoDato);
                arbolgui.pintarNodoEncontrado = true;
                arbolgui.panel.repaint();
            } else {
                JOptionPane.showMessageDialog(null, "No existe ese dato para ser modificado");
            }
        }
    }

    private void volver() {
        dispose();
    }
    
}
