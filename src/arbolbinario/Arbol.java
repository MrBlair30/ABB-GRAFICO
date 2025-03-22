package arbolbinario;

/**
 *
 * @author Blair
 */
public class Arbol {

    private Nodo raiz;

    public Arbol() {
        raiz = null;
    }
    
    public boolean esVacia() {
        return raiz == null;
    }
    
    public boolean esHoja(Nodo nodo){
        return nodo.getIzq()==null && nodo.getDer()==null;
    }
    
    public boolean esInterno(Nodo nodo){
        return nodo.getIzq()!=null || nodo.getDer()!=null;
    }
    
    public boolean tieneSoloHijoDerecho(Nodo nodo){
        return nodo.getIzq()==null && nodo.getDer()!=null;
    }
    
    public boolean tieneSoloHijoIzquierdo(Nodo nodo){
        return nodo.getIzq()!=null && nodo.getDer()==null;
    }
    
    public boolean tieneDosHijos(Nodo nodo){
        return nodo.getIzq()!=null && nodo.getDer()!=null;
    }
    
    //****************************Insertar****************************
    public void insertar(int dato) {
        Nodo nuevo = new Nodo(dato);
        if (esVacia()) {
            raiz = nuevo;
            System.out.println("Se inserto el primer nodo del arbol (Raiz) --> " + dato);
            return;
        }
        Nodo aux = raiz;
        while (true) {
            if (dato < aux.getDato()) {
                if (aux.getIzq() == null) {
                    aux.setIzq(nuevo);
                    System.out.println("Nuevo nodo insertado a la izquierda de [" + aux.getDato() + "] --> " + dato);
                    return;
                }
                aux = aux.getIzq();
            } else if (dato > aux.getDato()) {
                if (aux.getDer() == null) {
                    aux.setDer(nuevo);
                    System.out.println("Nuevo nodo insertado a la derecha de [" + aux.getDato() + "] --> " + dato);
                    return;
                }
                aux = aux.getDer();
            }else{
                System.out.println("No se pueden insertar nodos repetidos...");
                return;
            }
        }
    }
    //****************************Recorrer Preorden ****************************
    public void preorden() {
        preordenRecursivo(raiz);
    }

    public void preordenRecursivo(Nodo nodo) {
        if (nodo == null) {
            return;
        }
        System.out.println(nodo.getDato());
        preordenRecursivo(nodo.getIzq());
        preordenRecursivo(nodo.getDer());
    }
    //****************************Recorrer Inorden****************************
    public void inorden(){
        inordenRecursivo(raiz);
    }
    
    public void inordenRecursivo(Nodo nodo){
        if(nodo==null){
            return;
        }
        inordenRecursivo(nodo.getIzq());
        System.out.println(nodo.getDato());        
        inordenRecursivo(nodo.getDer());
    }
    
    //****************************Recorrer Postorden****************************
    public void postorden(){
        postordenRecursivo(raiz);
    }
    
    public void postordenRecursivo(Nodo nodo){
        if(nodo==null){
            return;
        }
        postordenRecursivo(nodo.getIzq());
        postordenRecursivo(nodo.getDer());
        System.out.println(nodo.getDato());        
    }
    
    //****************************Eliminar****************************
    public void eliminarTodo(){
        raiz = null;
    }
    
    public void eliminarPorNum(int datoAEliminar){
        if(esVacia()){
            System.out.println("El Arbol esta vacio");
            return;
        }                               
        raiz = eliminarRecursivo(raiz, datoAEliminar);                    
    }
    
    public Nodo eliminarRecursivo(Nodo nodo,int datoAEliminar){
        if(nodo==null){
            return null;
        }
        
        if(datoAEliminar<nodo.getDato()){
            nodo.setIzq(eliminarRecursivo(nodo.getIzq(), datoAEliminar));
        }else if(datoAEliminar>nodo.getDato()){
            nodo.setDer(eliminarRecursivo(nodo.getDer(), datoAEliminar));
        }else{
            //Caso 1: EL nodo a eliminar es una hoja(no tiene hijos)
            if(esHoja(nodo)){
                return null;
                
            //Caso 2: El nodo a eliminar solo tiene un hijo(izquierdo o derecho)
            }else if(tieneSoloHijoIzquierdo(nodo)){
                return nodo.getIzq();
            }else if(tieneSoloHijoDerecho(nodo)){
                return nodo.getDer();
                
            //Caso 3: El nodo a eliminar tiene dos hijos
            }else if(tieneDosHijos(nodo)){
                Nodo predecesor = nodo.getIzq();
                while(predecesor.getDer()!=null){
                    predecesor = predecesor.getDer();
                }
                nodo.setDato(predecesor.getDato());
                nodo.setIzq(eliminarRecursivo(nodo.getIzq(), predecesor.getDato()));
            }               
        }
        return nodo;
    }


    
    //****************************Buscar****************************
        public Nodo buscar(int datoABuscar){
            if(esVacia()){
                System.out.println("El Arbol esta vacio...");
                return null;
            }        
            Nodo datoEncontado = buscarRecursivo(raiz, datoABuscar);
            if(datoEncontado!=null){
                System.out.println("Se encontro el dato: "+datoABuscar);
            }else{
                System.out.println("No se encontro el dato");
            }
            return datoEncontado;
        }

        public Nodo buscarRecursivo(Nodo nodo, int datoABuscar){
            if(nodo==null){
                return null;
            }
            if(nodo.getDato()==datoABuscar){               
                return nodo;
            }
            
            if(datoABuscar<nodo.getDato()){
                return buscarRecursivo(nodo.getIzq(), datoABuscar);
            }else if(datoABuscar>=nodo.getDato()){
                return buscarRecursivo(nodo.getDer(), datoABuscar);
            }
            
            return null;
        }
        
        //****************************Modificar****************************
        public boolean modificar(int datoAModificar, int nuevoDato){
            if(esVacia()){
                System.out.println("EL Arbol esta vacio");
                return false;
            }
            Nodo datoEncontrado = buscar(datoAModificar);
            if(datoEncontrado==null){
                System.out.println("No existe el dato que quiere modificar...");
                return false;
            }else{
                eliminarPorNum(datoAModificar);
                insertar(nuevoDato);
                System.out.println("Se cambio el dato ["+datoAModificar+"] por el dato --> "+nuevoDato);
                return true;
            }
        }
    
    public Nodo getRaiz() {
        return raiz;
    }

    
    
    public void insertar2(int dato){
        insertarRecursivo(raiz, dato);
    }
    public void insertarRecursivo(Nodo nodo, int dato){
        Nodo nuevo = new Nodo(dato);
        if(esVacia()){
            raiz = nuevo;
            return;
        }
        if(dato<nodo.getDato()){
            if(nodo.getIzq()==null){
                nodo.setIzq(nuevo);
                return;
            }
            insertarRecursivo(nodo.getIzq(), dato);
        }else if(dato>=nodo.getDato()){
            if(nodo.getDer()==null){
                nodo.setDer(nuevo);
                return;
            }
            insertarRecursivo(nodo.getDer(), dato);
        }
    }
                        

}
