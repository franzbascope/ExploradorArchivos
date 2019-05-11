package Arbol;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;
import javax.imageio.ImageIO;

public class arbol<E> {

    private BufferedImage folder;
    private BufferedImage file;
    private nodo<E> raiz;
    private final int ancho = 1000;
    private final int alto = 700;
    private int margenx = 30;
    private int margeny = 120;
    private final int espacio = 30;
    private final int tamanox = 140;
    private int tamanoy = 70;
    private boolean empezo = false;

    public arbol() {
        try {
            folder = ImageIO.read(new File("C:\\Users\\franz\\OneDrive\\Documentos\\NetBeansProjects\\Resources\\FileIcon.png"));
            file = ImageIO.read(new File("C:\\Users\\franz\\OneDrive\\Documentos\\NetBeansProjects\\Resources\\FolderIcon.png"));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public nodo<E> getRaiz() {
        return raiz;
    }

    public void setRaiz(nodo<E> raiz) {
        this.raiz = raiz;
    }

    public void insertar(nodo<E> obj, nodo<E> padre) {
        if (padre == null) {
            raiz = obj;
        } else {
            padre.agregarHijo(obj);
        }
    }

    public void eliminar(nodo<E> obj, nodo<E> padre) {
        if (padre == null) {
            if (obj.getValor().equals(raiz.getValor())) {
                raiz = null;
            }
        } else {
            padre.eliminarHijo(obj);
        }
    }

    public void buscar() {

    }

    public void insertarH(File f) {
        nodo inicial = new nodo(f.getPath());
        nodo<E> auxn;
        Queue<nodo> pila = new LinkedList<nodo>();
        pila.add(inicial);
        while (!pila.isEmpty()) {
            auxn = pila.poll();
            File auxf = new File((String) auxn.getValor());
            if (auxf.isDirectory()) {
                for (int i = 0; i < auxf.listFiles().length; i++) {
                    nodo nodo = new nodo(auxf.listFiles()[i].getPath());
                    pila.add(nodo);
                    if (raiz == null) {
                        raiz = auxn;
                    }
                    insertar(nodo, auxn);
                }
            }
        }
    }

    public void mostrarQ(nodo<E> inicial) {
        nodo<E> aux;
        Queue<nodo> pila = new LinkedList<nodo>();
        pila.add(inicial);
        while (!pila.isEmpty()) {
            aux = pila.poll();
            for (int i = 0; i < aux.getHijos().size(); i++) {
                pila.add(aux.getHijos().get(i));
            }
            System.out.println(aux.getValor());
        }
    }

    public void mostrar(nodo<E> inicial) {
        nodo<E> aux;
        Stack<nodo> pila = new Stack<nodo>();
        pila.add(inicial);
        while (!pila.empty()) {
            aux = pila.pop();
            File auxf = new File((String) aux.getValor());
            for (int i = 0; i < aux.getHijos().size(); i++) {
                File c = new File((String) aux.getHijos().get(i).getValor());
                pila.add(aux.getHijos().get(i));
            }

        }
    }

    public void mostrarH(nodo<E> inicial) {
        nodo<E> aux = inicial;
        File auxf = new File((String) aux.getValor());
        for (int i = 0; i < aux.getHijos().size(); i++) {
            File c = new File((String) aux.getHijos().get(i).getValor());
        }

    }

    public boolean isEmpezo() {
        return empezo;
    }

    public void setEmpezo(boolean empezo) {
        this.empezo = empezo;
    }

    public void Dibujar(nodo<E> inicial, Graphics g) {
        int vueltas = 0;
        margenx = 30;
        margeny = 120;
        for (int i = 0; i < inicial.getHijos().size(); i++) {
            g.setColor(Color.black);
            int y = tamanoy;
            margenx = margenx + espacio;
            File c = new File((String) inicial.getHijos().get(i).getValor());
            int x = (vueltas * tamanox) + margenx;
            if (x > ancho - tamanox && x < ancho) {
                margeny += tamanoy + 70;
                vueltas = 0;
                margenx = 30;
                x = (vueltas * tamanox) + margenx;
            }
            if (c.isDirectory()) {
                g.drawImage(file, x, margeny, tamanox, y, null);
            } else {
                g.drawImage(folder, x, margeny, tamanox, y, null);
            }
            if (c.getName().length() > 15) {
                g.drawString((c.getName().substring(0, 15)), x + 20, margeny + y + 20);
            } else {
                g.drawString((c.getName()), x + 20, margeny + y + 20);
            }
            inicial.getHijos().get(i).setX1(x);
            inicial.getHijos().get(i).setX2(x + tamanox);
            inicial.getHijos().get(i).setY1(margeny);
            inicial.getHijos().get(i).setY2(margeny + y);
            vueltas++;
        }

    }

}
