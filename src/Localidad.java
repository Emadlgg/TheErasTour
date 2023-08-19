public class Localidad {
    private int capacidadTotal;
    private int boletosVendidos;

    public Localidad(int capacidadTotal) {
        this.capacidadTotal = capacidadTotal;
        this.boletosVendidos = 0;
    }

    public boolean tieneEspacio() {
        return boletosVendidos < capacidadTotal;
    }

    public boolean venderBoletos(int cantidad) {
        if (boletosVendidos + cantidad <= capacidadTotal) {
            boletosVendidos += cantidad;
            return true;
        }
        return false;
    }

    public int boletosDisponibles() {
        return capacidadTotal - boletosVendidos;
    }

    public int getBoletosVendidos() {
        return boletosVendidos;
    }
}