package models;

public class Incidencia {

    private int id;
    private int idUsuario;
    private String descripcion;
    private String estado;

    public Incidencia() {}

    public Incidencia(int id, int idUsuario, String descripcion, String estado) {
        this.id = id;
        this.idUsuario = idUsuario;
        this.descripcion = descripcion;
        this.estado = estado;
    }

    public int getId() { return id; }
    public int getIdUsuario() { return idUsuario; }
    public String getDescripcion() { return descripcion; }
    public String getEstado() { return estado; }

    public void setId(int id) { this.id = id; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
    public void setEstado(String estado) { this.estado = estado; }
}