package tdas;

import java.io.Serializable;
import java.util.Arrays;

/**
 *
 * @author jaguilar992
 */
public class ARBOL implements Serializable {
    private static final int N = 1000;
    private int raiz=-1;
    private int [] hijo_mas_izq = new int[ARBOL.N];
    private Object [] data = new Object[ARBOL.N];;
    private int [] hermano_dere = new int[ARBOL.N];
    private boolean [] memoria = new boolean[N];
    private int n=0; // Numero de nodos
    private int peso=0;
    
  
    public ARBOL(){
        Arrays.fill(this.hijo_mas_izq,-1);// SETEA todos los elementos en -1
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
    
    // 2.B HIJO_MAS_DER
    public Object HIJO_MAS_DER(Object nodo){
        for (int i = 0; i < this.data.length; i++) {
            if (this.data[i].equals(nodo)) {
                Object j=this.HIJO_MAS_IZQ(nodo);
                if (j!=null) {
                    Object hijo = null;
                    Object h_der = this.HERMANO_DER(j);
                    while (h_der!=null) {                        
                        hijo=h_der;
                        h_der=this.HERMANO_DER(h_der);
                    }
                    return hijo;
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
    
// TRABAJANDO
       //7 CREAi (v,A_1,A_2,...,A_i) :: ARBOL
//    public  int CREA(Object nodo){ // CREA0
//        int m = memoria_i();
//        try {
//            this.data[m]=nodo;
//            return m;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return -1;
//        }        
//    }
    
    public static ARBOL CREA(Object nodo){
        ARBOL creado = new ARBOL();
        int m = creado.memoria_i();
        creado.data[m]=nodo;
        creado.raiz=m;
        creado.setN(1);
        return creado;
    }
    
//    public int CREA(Object nodo, int ...Ak){ //CREAk
//        int m = memoria_i();
//        try {
//            this.raiz=m;
//            this.data[m]=nodo;
//            this.hijo_mas_izq[m]=Ak[0];
//            this.hermano_dere[m]=-1;
//            for (int i = 0; i < Ak.length-1; i++) {
//                this.hijo_mas_izq[Ak[i]]=-1;
//                this.hermano_dere[Ak[i]]=Ak[i+1];
//            }
//            this.hermano_dere[Ak[Ak.length-1]]=-1;
//            this.hijo_mas_izq[Ak[Ak.length-1]]=-1;
//            return m;
//        } catch (ArrayIndexOutOfBoundsException e) {
//            return -1;
//        }    
//    }
    
    public static ARBOL CREA(Object nodo, ARBOL ...Ak){
        ARBOL creado = new ARBOL();
        int peso = 0, n = 0;
        for (int i=0; i<Ak.length; i++) {
            for (int j = 0; j < Ak[i].getN(); j++) {
                int k = creado.memoria_i(); // Reservar memoria secuencial
                creado.data[k]=Ak[i].data[j];
                if (Ak[i].hijo_mas_izq[j]!=-1) {
                    creado.hijo_mas_izq[k]=Ak[i].hijo_mas_izq[j]+n;
                }
                if(Ak[i].hermano_dere[j]!=-1){
                    //System.out.println(Ak[i].data[j]+"+"+Ak[i].hermano_dere[j]+"+"+Ak[i].data[Ak[i].hermano_dere[j]]+"+"+n);
                    creado.hermano_dere[k]=Ak[i].hermano_dere[j]+n;
                }
            }
          if (i!=Ak.length-1) {
             // System.out.println(Ak[i].RAIZ()+"+"+Ak[i+1].RAIZ());
           creado.hermano_dere[n+Ak[i].raiz]=n+Ak[i].getN()-1+Ak[i+1].getN();
          }
            n += Ak[i].getN();
            peso += Ak[i].getPeso();
        }
        
        creado.setN(n+1);
        creado.setPeso(peso);
        
        //RAIZ
        int m = creado.memoria_i();
        creado.data[m]=nodo;
        creado.hijo_mas_izq[m]=Ak[0].raiz;
        creado.raiz=m;
        
        return creado;
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

    public int getN() {
        return n;
    }

    public void setN(int n) {
        this.n = n;
    }
    
    @Override
    public String toString(){
        String p = "";
        p+=("i\tHI\tData\tHD\n");
        for (int i = 0; i < this.getN(); i++) {
            p+=(i+"\t"+this.hijo_mas_izq[i]+"\t"+this.data[i]+"\t"+this.hermano_dere[i]+"\n");
        }
        return p;
    }

    /**
     * @return the getPeso
     */
    public int getPeso() {
        return peso;
    }

    /**
     * @param peso the getPeso to set
     */
    public void setPeso(int peso) {
        this.peso = peso;
    }
}
