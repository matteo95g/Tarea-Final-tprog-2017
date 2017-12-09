package GUI;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;

import javax.swing.JButton;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.UIManager;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

import excepciones.CategoriaRepetidaException;
import interfaces.IControladorCategoria;
import logica.Categoria;

import javax.swing.JFrame;
import javax.swing.JScrollPane;


@SuppressWarnings("serial")
public class AltaDeCategoria extends JInternalFrame {
	
	private JTextField textFieldCategoria;
	private IControladorCategoria ICCat;
	private JTextField textFieldPadre;
	private JTree tree;
	private DefaultMutableTreeNode nodoSeleccionado;

	public AltaDeCategoria(IControladorCategoria iccat) {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		
		ICCat = iccat;
        
		setClosable(true);
		setTitle("Alta de Categoria");
		setBounds(100, 100, 320, 500);
		getContentPane().setLayout(null);
		
		JLabel lblIngreseCategoria = new JLabel("Nombre de Categoria:");
		lblIngreseCategoria.setBounds(30, 340, 161, 15);
		getContentPane().add(lblIngreseCategoria);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(30, 39, 217, 297);
		getContentPane().add(scrollPane);
		
		tree = new JTree();
		scrollPane.setViewportView(tree);
		tree.getSelectionModel().addTreeSelectionListener(new TreeSelectionListener() {
			public void valueChanged(TreeSelectionEvent e) {
				nodoSeleccionado = (DefaultMutableTreeNode)tree.getLastSelectedPathComponent();
				if (nodoSeleccionado == null) {
					return;
				} else {
					Object nodeInfo = nodoSeleccionado.getUserObject();
					String categoria = (String)nodeInfo;
					textFieldPadre.setText(categoria);
				}
			}
		});
		tree.setBackground(UIManager.getColor("Button.background"));
		
		textFieldCategoria = new JTextField();
		textFieldCategoria.setBounds(40, 358, 231, 19);
		getContentPane().add(textFieldCategoria);
		textFieldCategoria.setColumns(10);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
	           public void actionPerformed(ActionEvent e) {
	        	   limpiarFormulario();
	               setVisible(false);
	           }
	       });
		btnCancelar.setBounds(52, 431, 117, 25);
		getContentPane().add(btnCancelar);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AltaCategoriaActionPerformed(e);
			}
		});
		btnConfirmar.setBounds(181, 431, 117, 25);
		getContentPane().add(btnConfirmar);
		
		JLabel lblSeleccioneUbicacionDe = new JLabel("Seleccione ubicacion de la categoria:");
		lblSeleccioneUbicacionDe.setBounds(12, 12, 259, 15);
		getContentPane().add(lblSeleccioneUbicacionDe);
		
		textFieldPadre = new JTextField();
		textFieldPadre.setEditable(false);
		textFieldPadre.setBounds(40, 400, 231, 19);
		getContentPane().add(textFieldPadre);
		textFieldPadre.setColumns(10);
		
		JLabel lblUbicacion = new JLabel("Subcategoria de:");
		lblUbicacion.setBounds(30, 380, 122, 15);
		getContentPane().add(lblUbicacion);

	}
	
	protected void AltaCategoriaActionPerformed(ActionEvent e) {
		
		String nombreCat = textFieldCategoria.getText();
		String padreCat = textFieldPadre.getText();
		
		if (checkFormulario()) {
			try {
				ICCat.agregarCategoria(nombreCat, padreCat);
				
				JOptionPane.showMessageDialog(this, "La categoria se ha creado con éxito", "Alta de Categoria",
                        JOptionPane.INFORMATION_MESSAGE);
				
			} catch (CategoriaRepetidaException ex) {
				
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Alta de Categoria", JOptionPane.ERROR_MESSAGE);
				
			}
			
			limpiarFormulario();
            setVisible(false);
		}
	}
	
	public void cargarCategorias() {
		List<Categoria> categorias = (LinkedList<Categoria>)ICCat.getCategorias();
		ListIterator<Categoria> iter = categorias.listIterator();
		Categoria root = iter.next();
		DefaultMutableTreeNode top = new DefaultMutableTreeNode(root.getNombre());
		createNodes(top,root);
		DefaultTreeModel newModel = new DefaultTreeModel(top);
		tree.setModel(newModel);
	}

	private void createNodes(DefaultMutableTreeNode node, Categoria categ) {
		if (categ.getHijas() != null) {
			ListIterator<Categoria> iter = categ.getHijas().listIterator();
			while (iter.hasNext()) {
				Categoria categHija = iter.next();
				DefaultMutableTreeNode nodoHijo = new DefaultMutableTreeNode(categHija.getNombre());
				node.add(nodoHijo);
				createNodes(nodoHijo,categHija); 
			}
		}
	}
	
	private boolean checkFormulario() {
        String nombreCat = textFieldCategoria.getText();

        if (nombreCat.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Escriba un nombre para la categoria", "Alta de Categoria",
                    JOptionPane.ERROR_MESSAGE);
            return false;
        }

        return true;
    }
	
	public void limpiarFormulario() {
		cargarCategorias();
		textFieldCategoria.setText("");
		textFieldPadre.setText("");
	}
}
