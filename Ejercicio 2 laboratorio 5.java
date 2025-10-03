import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class silla {
    private String codigo;
    private Pasajero pasajero;
    
    public silla(Strinh codigo){
        this.codigo = codigo
    }
    public void asignarPasajero(Pasajero){
        this.Pasajero = pasajero
    }
}
public class piloto{
    private String idPiloto;
    private String nombre;
    private int horasVuelos;
    
    public Piloto(String idpiloto, String nombre, int horasVuelos){
        this.idpiloto = idpiloto;
        this.nombre ; nombre
        this.horasVuelos = horasVuelos;
    }
}
public class Vuelo{
    private String numeroVuelo;
    private String ciudadSalida;
    private String ciudadLlegada;
    private LocalDate fechaHoraSalida;
    private LocalDate fechaHoraLlegada;
    private Piloto piloto;
    private int totalSillas;
    private int numPasajeros = 0;
    private List<Sillas> silla;
    
    public Vuelo(String nuemroVuelo, String CiudadSalida, String ciudad llegada, piloto Piloto, int totalSillas ){
        this.numeroVuelo = numeroVuelo;
        this.ciudadSalida = ciudadSalida;
        this.ciudadLlegada = ciudadLlegada;
        this.piloto = piloto;
        this.totalSillas = totalSillas;
        this.sillas = new ArrayList<>();
        for (int i = 1; i <= totalSillas; i++) {
            sillas.add(new Silla("S" + i));
    }
  }
} 
 public boolean asignarPasajero(Pasajero pasajero) {
        for (Silla silla : sillas) {
            if (silla.pasajero == null) {
                silla.asignarPasajero(pasajero);
                numPasajeros++;
                return true;
            }
        }
        return false;
 }
  public List<Pasajero> mostrarListaPasajeros() {
        List<Pasajero> lista = new ArrayList<>();
        for (Silla silla : sillas) {
            if (silla.pasajero != null) {
                lista.add(silla.pasajero);
            }
        }
        return lista;
    }

    public Silla buscarSillaPorPasajeroId(String idPasajero) {
        for (Sillas : silla) {
            if (silla.pasajero != null && silla.pasajero.id.equals(idPasajero)) {
                return silla;
            }
        }
        return null;
    }

    public void mostrarDatosVuelo() {
        System.out.println("Vuelo: " + numeroVuelo + " de " + ciudadSalida + " a " + ciudadLlegada);
    }
    
    
public class Agencia {
    private List<Vuelo> vuelos;
    private int numVuelos;
    private int capacidadVuelos;

    public Agencia(int capacidadInicial) {
        this.capacidadVuelos = capacidadInicial;
        this.vuelos = new ArrayList<>();
    }

    public void crearVuelo(Vuelo v) {
        if (numVuelos < capacidadVuelos) {
            vuelos.add(v);
            numVuelos++;
        }
    }

    public Vuelo buscarVueloPorNumero(String numeroVuelo) {
        for (Vuelo v : vuelos) {
            if (v.numeroVuelo.equals(numeroVuelo)) {
                return v;
            }
        }
        return null;
    }

    public List<Vuelo> listarVuelos() {
        return vuelos;
    }

    public boolean comprarTiquete(String numeroVuelo, Pasajero pasajero) {
        Vuelo vuelo = buscarVueloPorNumero(numeroVuelo);
        if (vuelo != null) {
            return vuelo.asignarPasajero(pasajero);
        }
        return false;
    }

    public String buscarPasajero(String idPasajero) {
        for (Vuelo v : vuelos) {
            Silla silla = v.buscarSillaPorPasajeroId(idPasajero);
            if (silla != null) {
                return v.numeroVuelo;
            }
        }
        return null;
    }

}
public class Main {
    public static void main(String[] args) {
        // Crear agencia con capacidad para 5 vuelos
        Agencia agencia = new Agencia(5);

        // Crear piloto
        Piloto piloto1 = new Piloto("P001", "Carlos Perez", 1200);

        // Crear vuelo
        Vuelo vuelo1 = new Vuelo("V123", "Bogotá", "Medellín", piloto1, 3);
        vuelo1.fechaHoraSalida = LocalDate.of(2025, 10, 5);
        vuelo1.fechaHoraLlegada = LocalDate.of(2025, 10, 5);

        // Agregar vuelo a la agencia
        agencia.crearVuelo(vuelo1);

        // Crear pasajeros
        Pasajero pasajero1 = new Pasajero("PS001", "Ana Torres", 28, 'F');
        Pasajero pasajero2 = new Pasajero("PS002", "Luis Gómez", 34, 'M');
        Pasajero pasajero3 = new Pasajero("PS003", "Marta Díaz", 25, 'F');
        Pasajero pasajero4 = new Pasajero("PS004", "Pedro Ruiz", 45, 'M'); // Excede cupo

        // Comprar tiquetes
        agencia.comprarTiquete("V123", pasajero1);
        agencia.comprarTiquete("V123", pasajero2);
        agencia.comprarTiquete("V123", pasajero3);

        // Intento de compra adicional (vuelo lleno)
        boolean exito = agencia.comprarTiquete("V123", pasajero4);
        if (!exito) {
            System.out.println("El vuelo está lleno. No se pudo asignar el pasajero " + pasajero4.nombre);
        }

        // Mostrar datos del vuelo
        vuelo1.mostrarDatosVuelo();

        // Mostrar lista de pasajeros del vuelo
        System.out.println("Pasajeros del vuelo:");
        List<Pasajero> listaPasajeros = vuelo1.mostrarListaPasajeros();
        for (Pasajero p : listaPasajeros) {
            System.out.println("- " + p.nombre + " (ID: " + p.id + ")");
        }

        // Buscar en qué vuelo está un pasajero
        String vueloEncontrado = agencia.buscarPasajero("PS002");
        if (vueloEncontrado != null) {
            System.out.println("El pasajero con ID PS002 está en el vuelo: " + vueloEncontrado);
        } else {
            System.out.println("Pasajero no encontrado en ningún vuelo.");
        }
    }
}