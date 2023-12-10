package gtzzapienzapataverduzco;

import java.util.Iterator;
import java.util.*;

/**
 * @author Mariel S. Gtz. Zapien, Mariana Zapata Covarrubias & Mauricio Verduzco Chavira
 */

public class SetA<T> implements SetADT<T> {
    private T[] Set;
    private int cardinalidad;
    private final int MAX=1000;
    
    public SetA(){
        Set=(T[])new Object[MAX];
        cardinalidad=0;
    } 
   
    public SetA(int max){
        max=MAX;
        Set=(T[])new Object[max];
        cardinalidad=0;
    } 
    
    public int getCardinalidad(){
        return cardinalidad;
    }
    
    public boolean isEmpty(){
        return cardinalidad==0;
    }

    public T remove(T dato){
        T result;
        int i=0;
        result=null;
        while(i<cardinalidad && !dato.equals(Set[i])){
            i++;
        }
        if(i<cardinalidad){//si no lo encontro
            result=Set[i];
            Set[i]=Set[cardinalidad-1];
            Set[cardinalidad-1]=null;
            cardinalidad--;
        }
        return result;
    }
    
    public Iterator<T> iterator(){
        return new IteradorArreglo(Set, cardinalidad);
    }
    
    public void expandCapacity(){
        T[] nuevo=(T[]) new Object[Set.length*2];
        for(int i=0;i<Set.length;i++)
            nuevo[i]=Set[i];
        Set=nuevo;
    }
    
    public void add(T element){
        if(!(contains(element))){
            
            if(cardinalidad==Set.length)
                expandCapacity();
            
            Set[cardinalidad] = element;
            cardinalidad++;
        }
    }
    
    public boolean contains(T dato){
        Iterator <T> it = this.iterator();
        boolean resp = false;
        
        while(it.hasNext() && !resp){
            resp=it.next().equals(dato);
        }
        return resp;
    }
    
    public SetADT<T> union(SetADT<T> otro){
        Iterator <T> itT = this.iterator(), itO = otro.iterator();
        SetADT<T> un = new SetA();
        T dato;
        
        while(itT.hasNext()){
            dato = itT.next();
            if(!otro.contains(dato)){
                un.add(dato);
            }
        }
        
        while(itO.hasNext()){
            un.add(itO.next());
            
        }
        return un; 
    }
    
    public SetADT<T> unionR(SetADT otro){
        Iterator <T> itT = this.iterator(), itO = otro.iterator();
        SetADT<T> un = new SetA();
        
        unionR(un, itT);
        unionR(un, itO);
        
        return un;
    }
    
    private void unionR (SetADT<T> union, Iterator<T> it){
        if(it.hasNext()){           //ER
            union.add(it.next());   //AEB
            unionR(union, it);      
        }
        //EB  
    }
       
    public SetADT<T> inter(SetADT<T> otro){
        Iterator <T> it = this.iterator();
        SetADT<T> inter = new SetA();
        T dato;
        
        while(it.hasNext()){
            dato = it.next();
            if(otro.contains(dato)){
                inter.add(dato);
            }
        }
        return inter;
    }
    
    public SetADT<T> interR(SetADT<T> otro){
        SetADT<T> inter = new SetA();
        
        interR(inter, otro.iterator());
        
        return inter;
    }
    
    private void interR(SetADT<T> inter, Iterator<T> it){
        T dato;
        
        if(it.hasNext()){               //ER
            dato=it.next();
            if(this.contains(dato)){    //AEB
                inter.add(dato);
            }
            interR(inter, it);                            //EB
        }
    }

    public SetADT<T> diferencia(SetADT<T> otro){
        Iterator<T> it = this.iterator();
        SetADT<T> dif = new SetA();
        T dato;
        
        while(it.hasNext()){
            dato = it.next();
            if(!otro.contains(dato)){
                dif.add(dato);
            }
        }
        return dif;
    }

    public SetADT<T> diferR(SetADT<T> otro){
        SetADT<T> dif = new SetA();
                
        diferR(dif , this.iterator(), otro);

        return dif;
    }
    
    private void diferR(SetADT<T> dif, Iterator<T> it, SetADT<T> otro){
        if(it.hasNext()){
            T dato = it.next();
            if(!otro.contains(dato)){
                dif.add(dato);
            }
            diferR(dif, it, otro);
        }
    }
    
    public boolean equals(SetADT<T> otro){
        if(this.getCardinalidad() != otro.getCardinalidad()){   //EB
            return false;
        }else{
            return equals(otro.iterator());
        }
    }
    
    private boolean equals(Iterator<T> it){
        if(it.hasNext()){                   //ER
            if(this.contains(it.next())){   //AEB
                return equals(it);
            }else{                          //EB
                return false;
            }
        }                                   //EV
        return true;
    }

    
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0 ; i<cardinalidad; i++)
            sb.append(Set[i].toString() + ",");
        sb.append("]");
        return sb.toString();
    }
        
    
    
    
}