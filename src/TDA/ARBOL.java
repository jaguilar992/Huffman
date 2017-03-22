package TDA;

import java.util.Arrays;

/**
 *
 * @author jaguilar992
 */
public class ARBOL {
    private static final int N = 10000;
    public int raiz=-1;
    public int [] hijo_mas_izq = new int[ARBOL.N];
    public Object [] data = new Object[ARBOL.N];;
    public int [] hermano_dere = new int[ARBOL.N];
    private boolean [] memoria = new boolean[N];
    
  
    public ARBOL(){
        Arrays.fill(this.hijo_mas_izq,-1);
        Arrays.fill(this.hermano_dere,-1);
    };
    
    //1 PADRE
    public Object PADRE(Object nodo){
        int index = indice_nodo(nodo);
        if (nodo!=this.RAIZ() && index!=-1) {
            //PRIMERO COMPROBAR SI ES HIJO IZQUIERDO DE ALGUN NODO
            int hijo_izq_de = ES_HIJO_IZQ_DE(nodo);
            if (hijo_izq_de != -1) {
                return this.data[hijo_izq_de];
            } else {
                // SINO, SE CREA LA LISTA DE HERMANOS IZQUIERDOS DEL NODO nodo
                LISTA hermanos = new LISTA();
                hermanos.INSERTA(index, hermanos.PRIMERO());
                int h_der=ES_HERM_DER_DE(this.data[index]);
                while(h_der!=-1){
                    hermanos.INSERTA(h_der, hermanos.PRIMERO());
                    h_der=ES_HERM_DER_DE(this.data[h_der]);
                    //System.out.println(hermanos);
                }
                // Y SE DEVOLVERA EL PADRE DEL NODO MAS A LA IQUIERDA QUE ES HERMANO DE nodo
                int indice_hermano_izquierdo = (int)hermanos.RECUPERA(hermanos.PRIMERO());
                return this.data[ES_HIJO_IZQ_DE(this.data[indice_hermano_izquierdo])];
            }
        }else{
            return null;
        }
    }
    
    //2 HIJO_MAS_IZQ
    public Object HIJO_MAS_IZQ(Object nodo){
        for (int i = 0; i <data.length ; i++) {
            if (nodo.equals(this.data[i])) {
                int j = hijo_mas_izq[i];
                if (j!=-1) {
                    return this.data[j];
                }else{
                    return null;
                }
            }
        }
        return null;
    }
    
    //3 HERMANO_DER
    public Object HERMANO_DER(Object nodo){
        for (int i = 0; i <data.length ; i++) {
            if (nodo.equals(this.data[i])) {
                int j = hermano_dere[i];
                if (j!=-1) {
                    return this.data[j];
                }else{
                    return null;
                }
            }
        }
        return null;
    }
    
    //4 ETIQUETA
    public Object ETIQUETA(Object nodo){
        return nodo;
    }
       
    //5 RAIZ::Object(Nodo) // OJO
    public Object RAIZ(){
        try {
            Object nodo_raiz = this.data[this.raiz];
            return nodo_raiz;
        } catch (ArrayIndexOutOfBoundsException e) {
            return null;
        }
    }
    
   //6 ANULA()::void
    public void ANULA(){
        this.raiz=-1;
        this.hijo_mas_izq = new int[ARBOL.N];
        this.data = new Object[ARBOL.N];
        this.hermano_dere = new int[N];
        this.memoria = new boolean[N];
        
    }
    
    //7 CREAi (v,A_1,A_2,...,A_i) :: ARBOL
    public  int CREA(Object nodo){ // CREA0
        int m = memoria_i();
        try {
            this.data[m]=nodo;
            return m;
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }        
    }
    
    public int CREA(Object nodo, int ...Ak){ //CREAk
        int m = memoria_i();
        try {
            this.raiz=m;
            this.data[m]=nodo;
            this.hijo_mas_izq[m]=Ak[0];
            this.hermano_dere[m]=-1;
            for (int i = 0; i < Ak.length-1; i++) {
                this.hijo_mas_izq[Ak[i]]=-1;
                this.hermano_dere[Ak[i]]=Ak[i+1];
            }
            this.hermano_dere[Ak[Ak.length-1]]=-1;
            this.hijo_mas_izq[Ak[Ak.length-1]]=-1;
            return m;
        } catch (ArrayIndexOutOfBoundsException e) {
            return -1;
        }  
        
    }
    
    
    // METODOS ACCESORIOS
    public int memoria_i(){
        for (int i = 0; i < N; i++) {
            if (memoria[i]==false) {
                memoria[i]=true;
                return i;
            }
        }
        System.err.println("Límite de memoria en ARBOL excedido");
        return -1;
    }
    
    public int indice_nodo(Object nodo){
        for (int i = 0; i < N; i++) {
            if (nodo.equals(this.data[i])) {
                return i;
            }
        }
        return -1;
    }

    public int ES_HIJO_IZQ_DE(Object nodo){
        // Verificar si es hijo más izquierdo de algun nodo
        // En caso de serlo, devuelve el indice del nodo padre
        // Sino es hijo mas izquierdo de nadie devuelve -1
        
        // Buscar indice_nodo de objeto en this.data
        int index = indice_nodo(nodo);
        int hijo_izq_de = -1;
        for (int i = 0; i < N; i++) {
            //System.out.println(this.hijo_mas_izq[i]);
            if (this.hijo_mas_izq[i] == index) {
                hijo_izq_de = i;
                break;
            }
        }
        return hijo_izq_de;
    }
    
    
    public int ES_HERM_DER_DE(Object nodo){
        // Verificar si es hermano derecho de algun nodo
        // En caso de serlo, devuelve el indice del nodo hermano izquierdo
        // Sino es hermano derecho (Nodo se encuentra a la izquierda de todos sus hermanos) de nadie devuelve -1

        // Buscar indice_nodo de objeto en this.data
        int index = indice_nodo(nodo);
        int hermano_der_de = -1;
        for (int i = 0; i < N; i++) {
            if (this.hermano_dere[i] == index) {
                hermano_der_de = i;
                break;
            }
        }
        return hermano_der_de;
    }
    
}
