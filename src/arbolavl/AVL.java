package arbolavl;

/**
 *
 * @author Blair
 */
public class AVL {
    private NodoAVL raiz;

    public AVL() {
        raiz = null;
    }
    
    public boolean esVacia(){
        return raiz==null;
    }
    
    public boolean esHoja(NodoAVL nodo){
        return nodo.getIzq()==null && nodo.getDer()==null;
    }
    
    public boolean tieneSoloHijoIzquierdo(NodoAVL nodo){
        return nodo.getIzq()!=null && nodo.getDer()==null;
    }
    
    public boolean tieneSoloHijoDerecho(NodoAVL nodo){
        return nodo.getIzq()==null && nodo.getDer()!=null;
    }
    
    public boolean tieneDosHijos(NodoAVL nodo){
        return nodo.getIzq()!=null && nodo.getDer()!=null;
    }
    
    public int obtenerAltura(NodoAVL nodo){
        if(nodo==null){
            return 0;
        }
        
        return nodo.getAltura();
    }
    
    public int obtenerFactorBalance(NodoAVL nodo){
        if(nodo==null){
            return 0;
        }
        
        return obtenerAltura(nodo.getDer()) - obtenerAltura(nodo.getIzq());
    }
    
    public NodoAVL rotacionIzquierda(NodoAVL nodoX){
        if(nodoX==null || nodoX.getDer()==null){
            return nodoX;
        }
        
        NodoAVL nodoY = nodoX.getDer();
        NodoAVL nodoTemp = nodoY.getIzq();
        
        nodoY.setIzq(nodoX);
        nodoX.setDer(nodoTemp);
        
        nodoX.setAltura(1 + Math.max(obtenerAltura(nodoX.getIzq()),obtenerAltura(nodoX.getDer())));
        nodoY.setAltura(1 + Math.max(obtenerAltura(nodoY.getIzq()),obtenerAltura(nodoY.getDer())));
        
        return nodoY;
    }
    
    public NodoAVL rotacionDerecha(NodoAVL nodoY){
        if(nodoY==null || nodoY.getIzq()==null){
            return nodoY;
        }
        
        NodoAVL nodoX = nodoY.getIzq();
        NodoAVL nodoTemp = nodoX.getDer();
        
        nodoX.setDer(nodoY);
        nodoY.setIzq(nodoTemp);
        
        nodoX.setAltura(1 + Math.max(obtenerAltura(nodoX.getIzq()),obtenerAltura(nodoX.getDer())));
        nodoY.setAltura(1 + Math.max(obtenerAltura(nodoY.getIzq()),obtenerAltura(nodoY.getDer())));
        
        return nodoX;
    }
    
    public NodoAVL balancear(NodoAVL nodo, int dato){
        if(nodo==null){
            return null;
        }
        int fb = obtenerFactorBalance(nodo);
        
        //Rotacion Derecha
        if(fb < -1 && dato < nodo.getIzq().getDato()){
            return rotacionDerecha(nodo);
        }
        
        //Rotacion Izquierda
        if(fb > 1 && dato > nodo.getDer().getDato()){
            return rotacionIzquierda(nodo);
        }
        
        //Rotacion Izquierda-Derecha
        if(fb < -1 && dato > nodo.getIzq().getDato()){
            nodo.setIzq(rotacionIzquierda(nodo.getIzq()));
            return rotacionDerecha(nodo);
        }
        
        //Rotacion Derecha-Izquierda
        if(fb > 1 && dato < nodo.getDer().getDato()){
            nodo.setDer(rotacionDerecha(nodo.getDer()));
            return rotacionIzquierda(nodo);
        }
        
        return nodo;
    }
    
    public void preorden(){
        preordenRecursivo(raiz);
    }
    
    public void preordenRecursivo(NodoAVL nodo){
        if(nodo==null){
            return;
        }
        
        System.out.println(nodo.getDato());
        preordenRecursivo(nodo.getIzq());
        preordenRecursivo(nodo.getDer());
    }
    
    public void inorden(){
        inordenRecursivo(raiz);
    }
    
    public void inordenRecursivo(NodoAVL nodo){
        if(nodo==null){
            return;
        }
        
        inordenRecursivo(nodo.getIzq());
        System.out.println(nodo.getDato());
        inordenRecursivo(nodo.getDer());
    }
    
    public void postorden(){
        postordenRecursivo(raiz);
    }
    
    public void postordenRecursivo(NodoAVL nodo){
        if(nodo==null){
            return;
        }
        
        postordenRecursivo(nodo.getIzq());
        postordenRecursivo(nodo.getDer());
        System.out.println(nodo.getDato());
    }
    
    public void insertar(int dato){
        raiz = insertarRecursivo(raiz, dato);
    }
    
    public NodoAVL insertarRecursivo(NodoAVL nodo, int dato){
        if(nodo==null){
            return new NodoAVL(dato);
        }
        
        if(dato < nodo.getDato()){
            nodo.setIzq(insertarRecursivo(nodo.getIzq(), dato));
        }else if(dato > nodo.getDato()){
            nodo.setDer(insertarRecursivo(nodo.getDer(), dato));
        }else{
            return nodo;
        }
        
        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzq()), obtenerAltura(nodo.getDer())));
        
        return balancear(nodo, dato);
    }
    
    public void eliminar(int datoAEliminar){
        raiz = eliminarRecursivo(raiz, datoAEliminar);
    }
    
    public NodoAVL eliminarRecursivo(NodoAVL nodo, int datoAELiminar){
        if(nodo==null){
            return null;
        }
        
        if(datoAELiminar < nodo.getDato()){
            nodo.setIzq(eliminarRecursivo(nodo.getIzq(), datoAELiminar));
        }else if(datoAELiminar > nodo.getDato()){
            nodo.setDer(eliminarRecursivo(nodo.getDer(), datoAELiminar));
        }else{
            //Caso 1: Es una hoja
            if(esHoja(nodo)){
                return null;
                
            //Caso 2: Solo tiene un hijo
            }else if(tieneSoloHijoIzquierdo(nodo)){
                return nodo.getIzq();
            }else if(tieneSoloHijoDerecho(nodo)){
                return nodo.getDer();
                
            //Caso 3: Tiene dos hijos
            }else if(tieneDosHijos(nodo)){
                NodoAVL predecesor = nodo.getIzq();
                while(predecesor.getDer()!=null){
                    predecesor = predecesor.getDer();
                }
                
                nodo.setDato(predecesor.getDato());
                nodo.setIzq(eliminarRecursivo(nodo.getIzq(), predecesor.getDato()));
            }            
        }
        
        nodo.setAltura(1 + Math.max(obtenerAltura(nodo.getIzq()), obtenerAltura(nodo.getDer())));
        
        return balancear(nodo, datoAELiminar);
    }
    
    public NodoAVL buscar(int datoABuscar){
        if(esVacia()){
            System.out.println("El Arbol esta vacio, no se puede buscar...");
            return null;
        }
        NodoAVL datoEncontrado = buscarRecursivo(raiz, datoABuscar);
        
        if(datoEncontrado!=null){
            System.out.println("Se encontro el dato: "+datoEncontrado);
        }else{
            System.out.println("No se encontro el dato...");
        }
        return datoEncontrado;
    }
    
    public NodoAVL buscarRecursivo(NodoAVL nodo, int datoABuscar){
        if(nodo==null){
            return null;
        }
        
        if(datoABuscar < nodo.getDato()){
            return buscarRecursivo(nodo.getIzq(), datoABuscar);
        }else if(datoABuscar > nodo.getDato()){
            return buscarRecursivo(nodo.getDer(), datoABuscar);
        }else{
            return nodo;
        }
    }
    
    public boolean modificar(int datoAModificar, int nuevoDato){
            if(esVacia()){
                System.out.println("EL Arbol esta vacio");
                return false;
            }
            NodoAVL datoEncontrado = buscar(datoAModificar);
            if(datoEncontrado==null){
                System.out.println("No existe el dato que quiere modificar...");
                return false;
            }else{
                eliminar(datoAModificar);
                insertar(nuevoDato);
                System.out.println("Se cambio el dato ["+datoAModificar+"] por el dato --> "+nuevoDato);
                return true;
            }
        }
    
    public void eliminarTodo(){
        raiz = null;
    }

    public NodoAVL getRaiz() {
        return raiz;
    }
        
}
