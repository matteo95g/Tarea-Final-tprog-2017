package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Vector;

import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import excepciones.UsuarioNoExisteException;
import excepciones.UsuarioYaEsSeguidorException;
import interfaces.IControladorUsuario;
import logica.Colaborador;
import logica.Proponente;
import logica.Usuario;

@SuppressWarnings("serial")
public class SeguirUsuario extends JInternalFrame {
    private JComboBox<Usuario> comboBoxSeguidor;
    private JComboBox<Usuario> comboBoxSeguido ;
    private IControladorUsuario ICUsu;

    public SeguirUsuario(IControladorUsuario icusu) {
   	 ICUsu = icusu;
   	 setTitle("Seguir Usuario");
   	 setClosable(true);
   	 setMaximizable(true);
   	 setBounds(100, 100, 450, 300);
   	 getContentPane().setLayout(null);
   	 
   	 JButton btnAceptar = new JButton("Aceptar");
   	 btnAceptar.addActionListener(new ActionListener() {
   		 public void actionPerformed(ActionEvent e) {
   			 seguirAUsuario();
   		 }
   	 });
   	 btnAceptar.setBounds(311, 231, 117, 25);
   	 getContentPane().add(btnAceptar);
   	 
   	 comboBoxSeguidor = new JComboBox<Usuario>();
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
   	 Map<String,Proponente> proponentes = (HashMap<String,Proponente>)ICUsu.getProponentes();
   	 Map<String,Colaborador> colaboradores = (HashMap<String,Colaborador>) ICUsu.getColaboradores();
   	 Iterator<Entry<String, Proponente>> it = proponentes.entrySet().iterator();
   	 Iterator<Entry<String, Colaborador>> itc = colaboradores.entrySet().iterator();
   	 //Cargo Proponentes
   	 while (it.hasNext()) {
   	 	Map.Entry<String,Proponente> e = (Map.Entry<String,Proponente>)it.next();
   	 	Proponente prop = e.getValue();
   	 	model.add(prop);
   	 }
   	 //Cargo Colaboradores
   	 while (itc.hasNext()) {
   	 	Map.Entry<String,Colaborador> e = (Map.Entry<String,Colaborador>)itc.next();
   	 	Colaborador col = e.getValue();
   	 	model.add(col);
   	 }
   	 //Cargo todo en el combobox
   	 ComboBoxModel<Usuario> modelo1 = new DefaultComboBoxModel<Usuario>(model);
   	 ComboBoxModel<Usuario> modelo2 = new DefaultComboBoxModel<Usuario>(model);
   	 comboBoxSeguidor.setModel(modelo1);
   	 comboBoxSeguidor.setSelectedIndex(-1);
   	 comboBoxSeguido.setModel(modelo2);
   	 comboBoxSeguido.setSelectedIndex(-1);
   	 
    }
    
    public void seguirAUsuario(){
   	 if( comboBoxSeguidor.getSelectedItem() == null || comboBoxSeguido.getSelectedItem() == null ) {
   		 JOptionPane.showMessageDialog(this, "Debe seleccionar todos los campos", "Seguir Usuario",
                	JOptionPane.ERROR_MESSAGE);
   	 } else if(comboBoxSeguidor.getSelectedItem() ==  comboBoxSeguido.getSelectedItem()) {
   		 JOptionPane.showMessageDialog(this, "Un Usuario no se puede seguir a si mismo", "Seguir Usuario",
                	JOptionPane.ERROR_MESSAGE);
   	 } else {
   		 Usuario seguidor = (Usuario) comboBoxSeguidor.getSelectedItem();
   		 Usuario seguido = (Usuario) comboBoxSeguido.getSelectedItem();
   		 try {
   			 
   			 ICUsu.seguirUsuario(seguidor.getNickname(), seguido.getNickname());
   			 JOptionPane.showMessageDialog(this, "El usuario " + seguidor.getNickname() + " ahora sigue a " + seguido.getNickname(),
   					 "Alta de Perfil", JOptionPane.INFORMATION_MESSAGE);
   			 
   			 limpiarFormulario();
   			 setVisible(false);
   		 } catch (UsuarioYaEsSeguidorException e) {
   			 
   			 JOptionPane.showMessageDialog(this, e.getMessage(), "Seguir Usuario",
   					 JOptionPane.ERROR_MESSAGE);
   			 
   		 } catch (UsuarioNoExisteException e) {
   			 
   			 JOptionPane.showMessageDialog(this, e.getMessage(), "Seguir Usuario",
   					 JOptionPane.ERROR_MESSAGE);
   			 
   		 }
   	 }
    }
    
    public void limpiarFormulario() {
   	 cargarUsuarios();
   	 comboBoxSeguidor.setSelectedIndex(-1);
   	 comboBoxSeguido.setSelectedIndex(-1);
    }
    
}
