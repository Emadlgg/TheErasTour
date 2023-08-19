
public class Comprador {
    private String nombre;
    private String email;
    private int cantidadBoletos;
    private int presupuestoMaximo;
    private boolean tieneCodigoEspecial;

    public Comprador(String nombre, String email, int cantidadBoletos, int presupuestoMaximo) {
        this.nombre = nombre;
        this.email = email;
        this.cantidadBoletos = cantidadBoletos;
        this.presupuestoMaximo = presupuestoMaximo;
        this.tieneCodigoEspecial = false;
    }

    public boolean tieneCodigoEspecial() {
        return tieneCodigoEspecial;
    }

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public int getPresupuestoMaximo() {
        return presupuestoMaximo;
    }
}