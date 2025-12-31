
package concesionario;

import java.util.concurrent.Semaphore;

public class Concesionario {


    private static final int MAX_AVAILABLE = 4;
    

    private final Semaphore semaforo = new Semaphore(MAX_AVAILABLE, true);

  
    protected String[] items = {"Vehiculo 1", "Vehiculo 2", "Vehiculo 3", "Vehiculo 4"};
    
  
    protected boolean[] used = new boolean[MAX_AVAILABLE];




    public String getItem() throws InterruptedException {
        semaforo.acquire(); // 1. Pedir permiso
        return getNextAvailableItem(); // 2. Coger coche
    }

    public void putItem(String x) {
        if (markAsUnused(x)) { 
            semaforo.release(); 
        }
    }

    
    protected synchronized String getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; 
    }

    protected synchronized boolean markAsUnused(String item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item.equals(items[i])) { // Usamos .equals para Strings
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else {
                    return false;
                }
            }
        }
        return false;
    }





public static void main (String[] args) {
       Concesionario concesionarioCompartido = new Concesionario();
        
        System.out.println("Prueba de concesionario de los 4 coches y 9 usuarios");


        for (int i = 1; i <= 9; i++) {
            
            new Cliente("Cliente " + i, concesionarioCompartido).start();
}
   }

   

    
}