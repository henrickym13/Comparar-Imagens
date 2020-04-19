package Controlador;

import Vista.VistaPrincipal;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileReader;
import javax.imageio.ImageIO;
import javax.swing.JDesktopPane;
import javax.swing.JOptionPane;

public class ControlPrincipal implements ActionListener {

	VistaPrincipal vista;
	File fileImagen;
	String imagen1;
	String imagen2;

	public ControlPrincipal(VistaPrincipal v) {
		this.vista = v;
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("Buscar Primeira Imagem")) {
			int returnVal = vista.jfcExaminarEntrada.showOpenDialog(vista);
			if (returnVal == vista.jfcExaminarEntrada.APPROVE_OPTION) {
				fileImagen = vista.jfcExaminarEntrada.getSelectedFile();
				vista.txt_url_left.setText(fileImagen.toString());
				imagen1 = fileImagen.toString();
			}

		}

		if (e.getActionCommand().equals("Carregar Primeira Imagem")) {
			if (fileImagen != null) {
				cargarImagen(vista.jDesktopPane1, fileImagen);
			}
		}

		if (e.getActionCommand().equals("Buscar Segunda Imagem")) {
			int returnVal = vista.jfcExaminarEntrada.showOpenDialog(vista);
			if (returnVal == vista.jfcExaminarEntrada.APPROVE_OPTION) {
				fileImagen = vista.jfcExaminarEntrada.getSelectedFile();
				vista.txt_url_right.setText(fileImagen.toString());
				imagen2 = fileImagen.toString();
			}
		}

		if (e.getActionCommand().equals("Carregar Segunda Imagem")) {
			if (fileImagen != null) {
				cargarImagen(vista.jDesktopPane2, fileImagen);
			}
		}

		if (e.getActionCommand().equals("Comparar Imagens")) {

			try {
				boolean found = false;

				FileReader fr1 = new FileReader(getImagen1());
				FileReader fr2 = new FileReader(getImagen2());

				while (true) {
					int pix1 = fr1.read();
					int pix2 = fr2.read();

					if (pix1 != pix2) {
						break;
					}

					// pix1 e pix2 são iguais....

					// se um dos dois for -1, o arquivo acabou
					if (pix1 == -1) {
						found = true;

						break;
					}
				}
				if (found) {
					JOptionPane.showMessageDialog(vista, "As Imagens são iguais");
				} else {
					JOptionPane.showMessageDialog(vista, "As Imagens são diferentes");
				}

			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
	}

	public String getImagen1() {
		return imagen1;
	}

	public String getImagen2() {
		return imagen2;
	}

	public void cargarImagen(javax.swing.JDesktopPane jDeskp, File fileImagen) {
		try {
			BufferedImage image = ImageIO.read(fileImagen);
			jDeskp.setBorder(new PintaImagen(image));
		} catch (Exception e) {
			JOptionPane.showMessageDialog(jDeskp, "não foi possivel carregar a imagem");
		}
	}
}
