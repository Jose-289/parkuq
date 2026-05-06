package services;

import model.Vehiculo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class VehiculoServices {
    private ArrayList<VehiculoServices> reportes;

    Parqueadero p;

    public VehiculoServices(Parqueadero p){
        this.reportes = new ArrayList<>();
        this.p = p;
    }

    public ArrayList<VehiculoServices> getReportes() {
        return reportes;
    }

    public void setReportes(ArrayList<VehiculoServices> reportes) {
        this.reportes = reportes;
    }
    public int vehiculosIngresadosPorDia (LocalDateTime fecha){
        LocalDate fechaIngreso = fecha.toLocalDate();

        long conteo = p.getVehiculos().stream()
                .filter(vehiculo -> vehiculo.getHoraIngreso().toLocalDate().equals(fechaIngreso))
                .count();
        return conteo == 0 ? 0 : (int)conteo;
    }
}
