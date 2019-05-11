package Arbol;

import java.util.ArrayList;

public class nodo<E> {

    private int x1;
    private int x2;
    private int y1;
    private int y2;

    public int getX1() {
        return x1;
    }

    public void setX1(int x1) {
        this.x1 = x1;
    }

    public int getX2() {
        return x2;
    }

    public void setX2(int x2) {
        this.x2 = x2;
    }

    public int getY1() {
        return y1;
    }

    public void setY1(int y1) {
        this.y1 = y1;
    }

    public int getY2() {
        return y2;
    }

    public void setY2(int y2) {
        this.y2 = y2;
    }

    public nodo(E valor) {
        this.valor = valor;
    }

    public E getValor() {
        return valor;
    }

    public void setValor(E valor) {
        this.valor = valor;
    }

    public ArrayList<nodo<E>> getHijos() {
        return hijos;
    }

    public void setHijos(ArrayList<nodo<E>> hijos) {
        this.hijos = hijos;
    }

    public nodo<E> getPadre() {
        return padre;
    }

    public void setPadre(nodo<E> padre) {
        this.padre = padre;
    }
    private E valor;
    private ArrayList<nodo<E>> hijos = new ArrayList();
    private nodo<E> padre;

    public void agregarHijo(nodo<E> obj) {
        obj.setPadre(this);
        hijos.add(obj);
    }

    public void eliminarHijo(nodo<E> obj) {
        nodo<E> temp;
        for (int i = 0; i < hijos.size(); i++) {
            temp = (nodo<E>) hijos.get(i);
            if (temp.getValor().equals(obj.getValor())) {
                hijos.remove(i);
            }

        }
    }
}
