package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;
import java.util.Map.Entry;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.UsuarioNoExisteException;
import interfaces.IControladorUsuario;
import logica.Colaborador;
import logica.Proponente;
import logica.Usuario;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

@SuppressWarnings("serial")
public class DejarDeSeguirUsuario extends JInternalFrame {

	private JComboBox<Usuario> comboBoxSeguidor;
	private JComboBox<Usuario> comboBoxSeguido;
	private IControladorUsuario ICUsu;

	public DejarDeSeguirUsuario(IControladorUsuario icusu) {
		ICUsu = icusu;
		setTitle("Dejar de Seguir Usuario");
		setClosable(true);
		setMaximizable(true);
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);

		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				dejarDeSeguirActionPerformed();
			}
		});
		btnAceptar.setBounds(182, 231, 117, 25);
		btnAceptar.setBounds(311, 231, 117, 25);

		getContentPane().add(btnAceptar);

		comboBoxSeguidor = new JComboBox<Usuario>();
		comboBoxSeguidor.addItemListener(new ItemListener() {
			public void itemStateChanged(ItemEvent arg0) {
				Usuario sel = (Usuario) comboBoxSeguidor.getSelectedItem();
				if (sel != null) {
					cargarSeguidores(sel.getNickname());
				}
			}
		});
		comboBoxSeguidor.setBounds(100, 54, 328, 24);
		getContentPane().add(comboBoxSeguidor);

		comboBoxSeguido = new JComboBox<Usuario>();
		comboBoxSeguido.setBounds(100, 114, 328, 24);
		getContentPane().add(comboBoxSeguido);

		JLabel lblSeguidor = new JLabel("Seguidor:");
		lblSeguidor.setBounds(12, 59, 70, 15);
		getContentPane().add(lblSeguidor);

		JLabel lblSeguido = new JLabel("Seguido:");
		lblSeguido.setBounds(12, 119, 70, 15);
		getContentPane().add(lblSeguido);

		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
			}
		});
		btnCancelar.setBounds(182, 231, 117, 25);
		getContentPane().add(btnCancelar);

	}

	public void cargarUsuarios() {
		Vector<Usuario> model = new Vector<Usuario>();
		Map<String, Proponente> proponentes = (HashMap<String, Proponente>) ICUsu.getProponentes();
		Map<String, Colaborador> colaboradores = (HashMap<String, Colaborador>) ICUsu.getColaboradores();
		Iterator<Entry<String, Proponente>> it = proponentes.entrySet().iterator();
		Iterator<Entry<String, Colaborador>> itc = colaboradores.entrySet().iterator();
		// Cargo Proponentes
		while (it.hasNext()) {
			Map.Entry<String, Proponente> e = (Map.Entry<String, Proponente>) it.next();
			Proponente prop = e.getValue();
			model.add(prop);
		}
		// Cargo Colaboradores
		while (itc.hasNext()) {
			Map.Entry<String, Colaborador> e = (Map.Entry<String, Colaborador>) itc.next();
			Colaborador col = e.getValue();
			model.add(col);
		}
		// Cargo todo en el combobox
		ComboBoxModel<Usuario> modelo1 = new DefaultComboBoxModel<Usuario>(model);
		comboBoxSeguidor.setModel(modelo1);
		// Cargo los seguidores del usuario selecccionado x defecto
		Usuario sel = (Usuario) comboBoxSeguidor.getSelectedItem();
		if (sel != null) {
			cargarSeguidores(sel.getNickname());
		}

	}

	public void cargarSeguidores(String usuario) {
		Vector<Usuario> model = new Vector<Usuario>();
		Map<String, Usuario> seguidos = (HashMap<String, Usuario>) ICUsu.getSeguidos(usuario);
		//
		if (seguidos != null) {
			Iterator<Entry<String, Usuario>> it = seguidos.entrySet().iterator();
			while (it.hasNext()) {
				Map.Entry<String, Usuario> e = (Map.Entry<String, Usuario>) it.next();
				Usuario seguido = e.getValue();
				model.add(seguido);
			}
			ComboBoxModel<Usuario> modelo1 = new DefaultComboBoxModel<Usuario>(model);
			comboBoxSeguido.setModel(modelo1);
		}
	}

	public void dejarDeSeguirActionPerformed() {
		Usuario seguidor = (Usuario) comboBoxSeguidor.getSelectedItem();
		Usuario seguido = (Usuario) comboBoxSeguido.getSelectedItem();
		if (checkFormulario()) {
			try {
				
				ICUsu.dejarSeguirUsuario(seguidor.getNickname(), seguido.getNickname());
				
				JOptionPane.showMessageDialog(this, "El usuario " + seguidor.getNickname() + " ya no sigue a " + seguido.getNickname(),
						"Alta de Perfil", JOptionPane.INFORMATION_MESSAGE);
				
				limpiarFormulario();
				setVisible(false);
			} catch (UsuarioNoExisteException e) {
				JOptionPane.showMessageDialog(this, e.getMessage(), "Dejar de Seguir Usuario",
						JOptionPane.ERROR_MESSAGE);
			}			
		}
	}
	
	
	public boolean checkFormulario() {
		if (comboBoxSeguidor.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Debe seleccionar un usuario seguidor", "Dejar Seguir Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (comboBoxSeguido.getSelectedIndex() == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione un usuario para dejar de seguir", "Dejar Seguir Usuario",
					JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	public void limpiarFormulario() {
		cargarUsuarios();
		comboBoxSeguidor.setSelectedIndex(-1);
		comboBoxSeguido.setSelectedIndex(-1);
	}

}
