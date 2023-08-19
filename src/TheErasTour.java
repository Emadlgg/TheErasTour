import java.util.Scanner;
import java.util.Random;

public class TheErasTour {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Localidad localidad1 = new Localidad(20);
        Localidad localidad5 = new Localidad(20);
        Localidad localidad10 = new Localidad(20);
        Comprador compradorActual = null;

        while (true) {
            System.out.println("The Eras Tour Tickets");
            System.out.println("1. Nuevo comprador");
            System.out.println("2. Nueva solicitud de boletos");
            System.out.println("3. Consultar disponibilidad total");
            System.out.println("4. Consultar disponibilidad individual");
            System.out.println("5. Reporte de caja");
            System.out.println("6. Código Especial");
            System.out.println("7. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del comprador: ");
                    String nombre = scanner.next();
                    System.out.print("Email del comprador: ");
                    String email = scanner.next();
                    System.out.print("Cantidad de boletos a comprar: ");
                    int cantidadBoletos = scanner.nextInt();
                    System.out.print("Presupuesto máximo: ");
                    int presupuestoMaximo = scanner.nextInt();
                    compradorActual = new Comprador(nombre, email, cantidadBoletos, presupuestoMaximo);
                    System.out.println("Comprador registrado.");
                    break;

                case 2:
                    if (compradorActual == null) {
                        System.out.println("Debe registrar un comprador primero.");
                        break;
                    }

                    if (compradorActual.tieneCodigoEspecial()) {
                        System.out.println("El comprador tiene un código especial. No puede participar en la compra regular.");
                        break;
                    }

                    if (!localidad1.tieneEspacio() && !localidad5.tieneEspacio() && !localidad10.tieneEspacio()) {
                        System.out.println("Todas las localidades están agotadas.");
                        break;
                    }

                    Random random = new Random();
                    int ticket = random.nextInt(15000) + 1;
                    int a = random.nextInt(15000) + 1;
                    int b = random.nextInt(15000) + 1;

                    if (ticket >= Math.min(a, b) && ticket <= Math.max(a, b)) {
                        System.out.println("¡Felicidades! Su ticket es apto para comprar boletos.");
                        System.out.println("Seleccionando localidad aleatoria...");
                        int localidadSeleccionada = random.nextInt(3); // 0: Localidad 1, 1: Localidad 5, 2: Localidad 10
                        Localidad localidadElegida = null;
                        String nombreLocalidad = "";
                        int precioLocalidad = 0;

                        switch (localidadSeleccionada) {
                            case 0:
                                localidadElegida = localidad1;
                                nombreLocalidad = "Localidad 1";
                                precioLocalidad = 100;
                                break;
                            case 1:
                                localidadElegida = localidad5;
                                nombreLocalidad = "Localidad 5";
                                precioLocalidad = 500;
                                break;
                            case 2:
                                localidadElegida = localidad10;
                                nombreLocalidad = "Localidad 10";
                                precioLocalidad = 1000;
                                break;
                        }

                        if (localidadElegida != null) {
                            System.out.println("Se le asignó la " + nombreLocalidad + ".");
                            int boletosDeseados = compradorActual.getCantidadBoletos();
                            if (boletosDeseados > localidadElegida.boletosDisponibles()) {
                                boletosDeseados = localidadElegida.boletosDisponibles();
                            }

                            if (boletosDeseados > 0) {
                                int costoTotal = boletosDeseados * precioLocalidad;
                                int montoMinimo = costoTotal - compradorActual.getPresupuestoMaximo();
                                if (compradorActual.getPresupuestoMaximo() >= 500) {
                                    montoMinimo = 0; // Permitir la compra si el presupuesto es mayor o igual a 500
                                }

                                if (costoTotal <= compradorActual.getPresupuestoMaximo() || montoMinimo == 0) {
                                    localidadElegida.venderBoletos(boletosDeseados);
                                    System.out.println("¡Boletos vendidos con éxito!");
                                } else {
                                    System.out.println("El costo total excede el presupuesto del comprador.");
                                    System.out.println("Monto mínimo requerido: $" + montoMinimo);
                                }
                            } else {
                                System.out.println("No hay boletos disponibles en la " + nombreLocalidad + ".");
                            }
                        }
                    } else {
                        System.out.println("Lo sentimos, su ticket no es apto para comprar boletos.");
                    }
                    break;

                case 3:
                    System.out.println("Disponibilidad total:");
                    System.out.println("Localidad 1: Vendidos " + localidad1.getBoletosVendidos() + ", Disponibles " + localidad1.boletosDisponibles());
                    System.out.println("Localidad 5: Vendidos " + localidad5.getBoletosVendidos() + ", Disponibles " + localidad5.boletosDisponibles());
                    System.out.println("Localidad 10: Vendidos " + localidad10.getBoletosVendidos() + ", Disponibles " + localidad10.boletosDisponibles());
                    break;

                case 4:
                    System.out.print("Seleccione una localidad (1, 5, 10): ");
                    int localidadSeleccionada = scanner.nextInt();
                    Localidad localidad = null;

                    switch (localidadSeleccionada) {
                        case 1:
                            localidad = localidad1;
                            break;
                        case 5:
                            localidad = localidad5;
                            break;
                        case 10:
                            localidad = localidad10;
                            break;
                    }

                    if (localidad != null) {
                        System.out.println("Boletos disponibles en la Localidad " + localidadSeleccionada + ": " + localidad.boletosDisponibles());
                    } else {
                        System.out.println("Localidad no válida.");
                    }
                    break;

                case 5:
                    int totalRecaudado = (localidad1.getBoletosVendidos() * 100) + (localidad5.getBoletosVendidos() * 500) + (localidad10.getBoletosVendidos() * 1000);
                    System.out.println("Total recaudado: $" + totalRecaudado);
                    break;

                case 6:
                    if (compradorActual == null) {
                        System.out.println("Debe registrar un comprador primero.");
                        break;
                    }

                    System.out.print("Ingrese el código especial (número de la secuencia de Fibonacci): ");
                    int codigoEspecial = scanner.nextInt();
                    boolean esFibonacci = checkFibonacci(codigoEspecial);

                    if (esFibonacci) {
                        System.out.println("Código especial válido. Se puede comprar un boleto en la Localidad 10 por $20000.");
                        if (localidad10.tieneEspacio()) {
                            if (localidad10.venderBoletos(1)) {
                                System.out.println("¡Boleto vendido con éxito!");
                            } else {
                                System.out.println("La Localidad 10 está agotada.");
                            }
                        } else {
                            System.out.println("La Localidad 10 está agotada.");
                        }
                    } else {
                        System.out.println("Código especial inválido.");
                    }
                    break;

                case 7:
                    System.out.println("Saliendo del programa...");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Opción inválida. Por favor, seleccione una opción válida.");
                    break;
            }
        }
    }

    private static boolean checkFibonacci(int n) {
        if (n == 0 || n == 1) {
            return true;
        }

        int a = 0;
        int b = 1;

        while (b <= n) {
            int temp = b;
            b = a + b;
            a = temp;

            if (b == n) {
                return true;
            }
        }

        return false;
    }
}