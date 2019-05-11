/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GUI;

import Arbol.arbol;
import Arbol.nodo;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Panel<E> extends JPanel implements ActionListener, MouseListener {

    nodo<E> actual;
    JButton Bentrar = new JButton("Entrar");
    JButton Batras = new JButton("Atras");
    JTextField Jdireccion = new JTextField();
    arbol a;
    private int x;
    private int y;
    private boolean empezo = false;

    public Panel() {
        this.setBackground(Color.white);
        addMouseListener(this);
        Bentrar.addActionListener(this);
        Batras.addActionListener(this);
        this.setLayout(null);
        Bentrar.setBounds(430, this.getHeight() + 20, 80, 40);
        Batras.setBounds(520, this.getHeight() + 20, 80, 40);
        Jdireccion.setBounds(20, this.getHeight() + 20, 400, 30);
        Jdireccion.setVisible(true);
        this.add(Bentrar);
        this.add(Batras);
        this.add(Jdireccion);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.black);
        if (empezo == true) {
            a.Dibujar(actual, g);
        }
        updateUI();
        this.revalidate();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == Batras) {
            if (actual == a.getRaiz()) {
                File f = new File((String) a.getRaiz().getValor());
                if (f.getParentFile().exists()) {
                    a = new arbol();
                    try {
                        a.insertarH(f.getParentFile());
                    } catch (Exception ex) {
                        System.out.println("Error: " + ex);
                        JOptionPane.showMessageDialog(this, "NO se pudo ir atras");
                        return;
                    }
                    actual = a.getRaiz();
                    empezo = true;
                    repaint();
                } else {
                    JOptionPane.showMessageDialog(this, "Estas en la raiz");
                }
            } else {
                Jdireccion.setText((String) actual.getPadre().getValor());
                actual = actual.getPadre();
                repaint();
            }

        }
        if (e.getSource() == Bentrar) {
            if (Jdireccion.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Ingrese una direccion");
            } else {
                File f = new File(Jdireccion.getText());
                if (!f.exists()) {
                    JOptionPane.showMessageDialog(this, "Ingrese una direccion Valida");
                } else {
                    System.out.println("hola");
                    a = new arbol();
                    a.insertarH(f);
                    actual = a.getRaiz();
                    empezo = true;
                    repaint();
                }
            }
        }

    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getClickCount() == 2) {
            x = e.getX();
            y = e.getY();
            int x1;
            int x2;
            int y1;
            int y2;
            nodo<E> aux;
            for (int i = 0; i < actual.getHijos().size(); i++) {
                File c = new File((String) actual.getHijos().get(i).getValor());
                aux = actual.getHijos().get(i);
                x1 = aux.getX1();
                x2 = aux.getX2();
                y1 = aux.getY1();
                y2 = aux.getY2();
                if (x >= x1 && x <= x2 && y >= y1 && y <= y2) {
                    actual = aux;
                    repaint();
                }
            }
            Jdireccion.setText((String) actual.getValor());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

}
