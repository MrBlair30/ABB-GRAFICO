package arbolbinario;

/**
 *
 * @author Blair
 */
public class Nodo {
    private int dato;
    private Nodo izq,der;

    public Nodo(int dato) {
        this.dato = dato;
        this.izq = null;
        this.der = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public Nodo getIzq() {
        return izq;
    }

    public void setIzq(Nodo izq) {
        this.izq = izq;
    }

    public Nodo getDer() {
        return der;
    }

    public void setDer(Nodo der) {
        this.der = der;
    }
    
    
}
