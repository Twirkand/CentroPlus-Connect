package models;

public class Reserva {

    private int id;
    private int idUsuario;
    private int idActividad;
    private String estado;

    public Reserva() {}

    public Reserva(int id, int idUsuario, int idActividad, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.idActividad = idActividad;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getIdUsuario() { return idUsuario; }
    public int getIdActividad() { return idActividad; }
    public String getEstado() { return estado; }

    public void setId(int id) { this.id = id; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public void setIdActividad(int idActividad) { this.idActividad = idActividad; }
    public void setEstado(String estado) { this.estado = estado; }
}