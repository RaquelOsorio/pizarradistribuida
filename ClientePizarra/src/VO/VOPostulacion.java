package VO;

import java.io.Serializable;

public class VOPostulacion implements Serializable {
    private String Titulo;  //titulo del articulo
    private String descripción;  // descripcion del articulo
    int ID;
    int IDArticulo;
    int IDConvocatoria;
    int Evaluadores[];
    int EditorRed[];
    int TComentarios[];
    int Criterios[];
    private String User;
    private int estado;

    public VOPostulacion() {
    }

    public VOPostulacion(String Titulo, String descripcion,int ID, int IDArticulo, int IDConvocatoria, int[] Evaluadores, int[] EditorRed, int[] TComentarios, int[] Criterios,
                         String User, int estado) {
        this.Titulo = Titulo;
        this.descripción = descripcion;
        this.ID = ID;
        this.IDArticulo = IDArticulo;
        this.IDConvocatoria = IDConvocatoria;
        this.Evaluadores = Evaluadores;
        this.EditorRed = EditorRed;
        this.TComentarios = TComentarios;
        this.Criterios = Criterios;
        this.User = User;
        this.estado = estado;

        this.Evaluadores = new int[Evaluadores.length];
        for (int i = 0; i < this.Evaluadores.length; i++) {
            this.Evaluadores[i] = Evaluadores[i];
        }

        this.EditorRed = new int[EditorRed.length];
        for (int i = 0; i < this.EditorRed.length; i++) {
            this.EditorRed[i] = EditorRed[i];
        }

        this.TComentarios = new int[TComentarios.length];
        for (int i = 0; i < this.TComentarios.length; i++) {
            this.TComentarios[i] = TComentarios[i];
        }

        this.Criterios = new int[Criterios.length];
        for (int i = 0; i < this.Criterios.length; i++) {
            this.Criterios[i] = Criterios[i];
        }

        this.User = User;
        this.estado = estado;
    }

    public int[] getCriterios() {
        return Criterios;
    }

    public void setCriterios(int[] Criterios) {
        this.Criterios = Criterios;
    }

    public int[] getEditorRed() {
        return EditorRed;
    }

    public void setEditorRed(int[] EditorRed) {
        this.EditorRed = EditorRed;
    }

    public int[] getEvaluadores() {
        return Evaluadores;
    }

    public void setEvaluadores(int[] Evaluadores) {
        this.Evaluadores = Evaluadores;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getIDArticulo() {
        return IDArticulo;
    }

    public void setIDArticulo(int IDArticulo) {
        this.IDArticulo = IDArticulo;
    }

    public int getIDConvocatoria() {
        return IDConvocatoria;
    }

    public void setIDConvocatoria(int IDConvocatoria) {
        this.IDConvocatoria = IDConvocatoria;
    }

    public int[] getTComentarios() {
        return TComentarios;
    }

    public void setTComentarios(int[] TComentarios) {
        this.TComentarios = TComentarios;
    }

    /**
     * @return the Titulo
     */
    public String getTitulo() {
        return Titulo;
    }

    /**
     * @param Titulo the Titulo to set
     */
    public void setTitulo(String Titulo) {
        this.Titulo = Titulo;
    }

    /**
     * @return the descripción
     */
    public //titulo del articulo
    String getDescripción() {
        return descripción;
    }

    /**
     * @param descripción the descripción to set
     */
    public void setDescripción(String descripción) {
        this.descripción = descripción;
    }

    /**
     * @return the User
     */
    public String getUser() {
        return User;
    }

    /**
     * @param User the User to set
     */
    public void setUser(String User) {
        this.User = User;
    }

    /**
     * @return the estado
     */
    public int getEstado() {
        return estado;
    }

    /**
     * @param estado the estado to set
     */
    public void setEstado(int estado) {
        this.estado = estado;
    }


}
