
package concesionario;

    

   
    public class Cliente extends Thread {
        private final Concesionario concesionario;
        private final String nombre;

        public Cliente(String nombre, Concesionario concesionario) {
            this.nombre = nombre;
            this.concesionario = concesionario;
        }

        @Override
        public void run() {
            try {
        
                String vehiculo = concesionario.getItem();

       
                System.out.println(nombre + " esta ocupando el " + vehiculo);

            
                Thread.sleep((long) (Math.random() * 3000) + 2000);
             
                System.out.println(nombre + " dejo libre el " + vehiculo);

                
                concesionario.putItem(vehiculo);

            } catch (InterruptedException e) {
            }
        }
    }

