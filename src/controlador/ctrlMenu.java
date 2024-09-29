package controlador;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JPanel;
import vista.jfrCrud;
import vista.jfrGaleria;
import vista.jfrMenu;

public class ctrlMenu implements MouseListener, KeyListener {

    jfrMenu vista;
    jfrGaleria jfrGaleria;
    jfrCrud jfrCrud;

    // Constructor del controlador
    public ctrlMenu(jfrMenu vista, jfrGaleria jfrGaleria, jfrCrud jfrCrud) {
        this.vista = vista;
        this.jfrGaleria = jfrGaleria;
        this.jfrCrud = jfrCrud;

        // Añadir los listeners a los botones
        vista.btnCrud.addMouseListener(this);
        vista.btnGaleria.addMouseListener(this);
    }

    // Método para cambiar el panel activo en el contenedor
    private void cambiarPanel(JPanel contenedor, JPanel nuevoPanel) {
        contenedor.removeAll();  // Elimina todos los componentes actuales
        contenedor.add(nuevoPanel);  // Añade el nuevo panel
        contenedor.revalidate();  // Refresca el contenedor
        contenedor.repaint();  // Repinta el contenedor
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        // Si se hace clic en el botón de CRUD
        if (e.getSource() == vista.btnCrud) {
            // Inicialización perezosa (solo se crea la primera vez)
            if (jfrCrud == null) {
                jfrCrud = new jfrCrud();
                jfrCrud.initjfrCrud();
            }
            // Cambiar el panel contenido
            cambiarPanel(vista.jPContenedor, jfrCrud);
        }

        // Si se hace clic en el botón de Galería
        if (e.getSource() == vista.btnGaleria) {
            // Inicialización perezosa (solo se crea la primera vez)
            if (jfrGaleria == null) {
                jfrGaleria = new jfrGaleria();
            }
            // Cambiar el panel contenido
            cambiarPanel(vista.jPContenedor, jfrGaleria);
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

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
}
