package arbolavl;

/**
 *
 * @author Blair
 */
public class NodoAVL {
    private int dato, altura;
    private NodoAVL izq,der;

    public NodoAVL(int dato) {
        this.dato = dato;
        this.altura = 1;
        this.izq = null;
        this.der = null;
    }

    public int getDato() {
        return dato;
    }

    public void setDato(int dato) {
        this.dato = dato;
    }

    public int getAltura() {
        return altura;
    }

    public void setAltura(int altura) {
        this.altura = altura;
    }   
    
    public NodoAVL getIzq() {
        return izq;
    }

    public void setIzq(NodoAVL izq) {
        this.izq = izq;
    }

    public NodoAVL getDer() {
        return der;
    }

    public void setDer(NodoAVL der) {
        this.der = der;
    }

    @Override
    public String toString() {
        return "NodoAVL{" + "dato=" + dato + ", altura=" + altura + ", izq=" + izq + ", der=" + der + '}';
    }   
    
    
}
