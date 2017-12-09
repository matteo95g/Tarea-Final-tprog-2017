package GUI;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import interfaces.IControladorUsuario;
import logica.Registros;

import javax.swing.border.MatteBorder;
import java.awt.Color;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

@SuppressWarnings("serial")
public class VerRegistro extends JInternalFrame {


	private JTable table;
    private IControladorUsuario ICusu;
    
	public VerRegistro(IControladorUsuario ICu) {
		
		ICusu = ICu;
		setTitle("Ver Registro Acceso");	
		setClosable(true);
		setBounds(100, 100, 900, 650);
		getContentPane().setLayout(null);
				
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(25, 50, 850, 550);
		getContentPane().add(scrollPane);	
		table = new JTable();
		scrollPane.setViewportView(table);
		table.setBorder(new MatteBorder(1, 1, 1, 1, (Color) new Color(0, 0, 0)));
		//table.setEnabled(false);
		
		JButton btnRefresh = new JButton("Actualizar");
		btnRefresh.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				cargarDatos();
			}
		});
		btnRefresh.setBounds(45, 10, 63, 25);
		getContentPane().add(btnRefresh);
		
		
	}
	
	public void cargarDatos() {
		
		DefaultTableModel model = new DefaultTableModel();
		model.addColumn("#");
		model.addColumn("IP");
		model.addColumn("URL");
		model.addColumn("Navegador");
		model.addColumn("SO");
		model.addColumn("Fecha");
		
		
		
	    List<Registros> regOriginal = ICusu.getRegistros();
	    Queue<Registros> reg = new LinkedList<Registros>(regOriginal);
		
	    int tam = reg.size();
	    int i = 0;
	   for (Object obj : regOriginal) {
		   Registros registro = (Registros) obj;
		   String fecha = registro.getFecha().getTime().toString();
		   String num = String.valueOf(i+1);
		   i++;
		   model.addRow(new Object[] { num,registro.getDirIp(),registro.getDirURL(),registro.getNavegador(),registro.getSistOp(),fecha});
	   }
		
			
		table.setModel(model);
		TableColumn clna0 = table.getColumn("#");
		clna0.setPreferredWidth(5);
	//	clna0.setResizable(false);
		
		TableColumn clna1 = table.getColumn("IP");
		clna1.setPreferredWidth(7);
	//	clna1.setResizable(false);
		
		TableColumn clna2 = table.getColumn("URL");
		clna2.setPreferredWidth(150);
//		clna2.setResizable(false);
		
		TableColumn clna3 = table.getColumn("SO");
		clna3.setPreferredWidth(30);
//		clna3.setResizable(false);
		
		TableColumn clna4 = table.getColumn("Navegador");
		clna4.setPreferredWidth(30);
//		clna4.setResizable(false);
		
		TableColumn clna5 = table.getColumn("Fecha");
//		clna5.setResizable(false);
		
		
		
	}
}
