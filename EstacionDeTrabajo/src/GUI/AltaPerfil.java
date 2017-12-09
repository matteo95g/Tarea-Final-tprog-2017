package GUI;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;

import javax.swing.JOptionPane;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.filechooser.FileNameExtensionFilter;

import excepciones.UsuarioRepetidoException;
import interfaces.IControladorUsuario;

import javax.swing.JPasswordField;


@SuppressWarnings("serial")
public class AltaPerfil extends JInternalFrame {
	
	private JFormattedTextField formattedTextFieldNick;
	private JFormattedTextField formattedTextFieldNombre;
	private JFormattedTextField formattedTextFieldApellido;
	private JFormattedTextField formattedTextFieldMail;
	private JFormattedTextField dir;
	private JFormattedTextField web;
	private JPasswordField contra;
	private JPasswordField confir;
	private JComboBox<String> comboBoxPerfil;
	private JTextArea bio;
	private File foto;
	private JComboBox<String> comboBoxDia;
	private JComboBox<String> comboBoxMes;
	private JComboBox<String> comboBoxAnio;
	private JFormattedTextField txtImg;
	private IControladorUsuario ICUsu;
	
	
	public AltaPerfil(IControladorUsuario icusu) {
		
		ICUsu = icusu;
		
		setTitle("Alta de Perfil");
        setClosable(true);
        setBounds(60, 40, 450, 341);
        getContentPane().setLayout(null);
        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        
        JLabel lblNickname = new JLabel("NickName:*");
        lblNickname.setBounds(12, 12, 99, 15);
        getContentPane().add(lblNickname);
        
        formattedTextFieldNick = new JFormattedTextField();
        formattedTextFieldNick.setBounds(186, 10, 244, 19);
        getContentPane().add(formattedTextFieldNick);
        
        JLabel lblNombre = new JLabel("Nombre:*");
        lblNombre.setBounds(12, 39, 99, 15);
        getContentPane().add(lblNombre);
        
        JLabel lblApellido = new JLabel("Apellido:*");
        lblApellido.setBounds(12, 66, 99, 15);
        getContentPane().add(lblApellido);
        
        JLabel lblEmail = new JLabel("e-Mail:*");
        lblEmail.setBounds(12, 93, 99, 15);
        getContentPane().add(lblEmail);
        
        JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento:*");
        lblFechaDeNacimiento.setBounds(12, 169, 163, 15);
        getContentPane().add(lblFechaDeNacimiento);
        
        JLabel lblImagen = new JLabel("Imagen:");
        lblImagen.setBounds(12, 223, 69, 15);
        getContentPane().add(lblImagen);
        
        txtImg = new JFormattedTextField();
        txtImg.setEditable(false);
        txtImg.setBounds(86, 221, 263, 19);
        getContentPane().add(txtImg);
        
        formattedTextFieldNombre = new JFormattedTextField();
        formattedTextFieldNombre.setBounds(186, 37, 244, 19);
        getContentPane().add(formattedTextFieldNombre);
        
        formattedTextFieldApellido = new JFormattedTextField();
        formattedTextFieldApellido.setBounds(186, 64, 244, 19);
        getContentPane().add(formattedTextFieldApellido);
        
        formattedTextFieldMail = new JFormattedTextField();
        formattedTextFieldMail.setBounds(186, 91, 244, 19);
        getContentPane().add(formattedTextFieldMail);        
        
        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	AltaPerfilActionPerformed(e);
            }
        });
        btnGuardar.setBounds(303, 281, 117, 25);
        getContentPane().add(btnGuardar);        
        
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               limpiarFormulario();
               setVisible(false);
            }
        });
        btnCancelar.setBounds(164, 281, 117, 25);
        getContentPane().add(btnCancelar);
        
        JLabel lblDireccion = new JLabel("Direccion:*");
        lblDireccion.setBounds(12, 285, 99, 15);
        lblDireccion.setVisible(false);
        getContentPane().add(lblDireccion);
        
        JLabel lblBiografia = new JLabel("Biografia:");
        lblBiografia.setBounds(12, 315, 99, 15);
        lblBiografia.setVisible(false);
        getContentPane().add(lblBiografia);
        
        JLabel lblPaginaWeb = new JLabel("Pagina Web:");
        lblPaginaWeb.setBounds(12, 400, 99, 15);
        //lblPaginaWeb.setVisible(false);
        getContentPane().add(lblPaginaWeb);
        
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(125, 315, 244, 66);
        getContentPane().add(scrollPane);
        
        bio = new JTextArea();
        scrollPane.setViewportView(bio);
        bio.setLineWrap(true);
        bio.setEditable(false);
        bio.setVisible(false);
        bio.setBackground(new Color(238,238,238));
        bio.setVisible(true);
        
        dir = new JFormattedTextField();
        dir.setEditable(false);
        dir.setVisible(false);
        dir.setBounds(125, 285, 244, 19);
        getContentPane().add(dir);
        
        web = new JFormattedTextField();
        web.setEditable(false);
        //web.setVisible(false);
        web.setBounds(125, 400, 244, 19);
        getContentPane().add(web);
        
        JButton buscImg = new JButton("...");
        buscImg.setBounds(372, 223, 37, 15);
        buscImg.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	foto = null;
                JFileChooser chooser = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter(
                    "Imagenes JPG & PNG", "jpg", "png");
                chooser.setFileFilter(filter);
                int returnVal = chooser.showOpenDialog(getParent());
                if(returnVal == JFileChooser.APPROVE_OPTION) {
                	foto = chooser.getSelectedFile();
                	txtImg.setText(chooser.getSelectedFile().getAbsolutePath());
                }
                
            }
        });
        getContentPane().add(buscImg);
        
        comboBoxDia = new JComboBox<String>();
        comboBoxDia.setBackground(Color.WHITE);
        comboBoxDia.setModel(new DefaultComboBoxModel<String>(new String[] {"Dia", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29", "30", "31"}));
        comboBoxDia.setBounds(86, 187, 60, 24);
        getContentPane().add(comboBoxDia);
        
        comboBoxMes = new JComboBox<String>();
        comboBoxMes.setBackground(Color.WHITE);
        comboBoxMes.setModel(new DefaultComboBoxModel<String>(new String[] {"Mes", "Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Setiembre", "Octubre", "Noviembre", "Diciembre"}));
        comboBoxMes.setBounds(164, 185, 99, 24);
        getContentPane().add(comboBoxMes);
        
        comboBoxAnio = new JComboBox<String>();
        comboBoxAnio.setBackground(Color.WHITE);
        comboBoxAnio.setModel(new DefaultComboBoxModel<String>(new String[] {"Año", "2017", "2016", "2015", "2014", "2013", "2012", "2011", "2010", "2009", "2008", "2007",
				"2006", "2005", "2004", "2003", "2002", "2001", "2000", "1999", "1998", "1997", "1996", "1995", "1994", "1993", "1992", "1991", "1990", "1989",
				"1988", "1987", "1986", "1985", "1984", "1983", "1982", "1981", "1980", "1979", "1978", "1977", "1976", "1975", "1974", "1973", "1972", "1971",
				"1970", "1969", "1968", "1967", "1966", "1965", "1964", "1963", "1962", "1961", "1960", "1959", "1958", "1957", "1596", "1955", "1954", "1953",
				"1952", "1951", "1950", "1949", "1948", "1947", "1946", "1945", "1944", "1943", "1942", "1941", "1940", "1939", "1938", "1937", "1936", "1935",
				"1934", "1933", "1932", "1931", "1930", "1929", "1928", "1927", "1926", "1925", "1924", "1923", "1922", "1921", "1920", "1919", "1918", "1917",
				"1916", "1915", "1914", "1913", "1912", "1911", "1910", "1909", "1908", "1907", "1906", "1905", "1904", "1903", "1902", "1901", "1900"}));
        comboBoxAnio.setBounds(275, 185, 69, 24);
        getContentPane().add(comboBoxAnio);
        
        comboBoxPerfil = new JComboBox<String>();
        comboBoxPerfil.setBackground(Color.WHITE);
        comboBoxPerfil.setModel(new DefaultComboBoxModel<String>(new String[] {"Seleccione un perfil", "Colaborador","Proponente"}));
        comboBoxPerfil.addItemListener(new ItemListener() {           
        	public void itemStateChanged(ItemEvent e) {
        		String cmbBoxPerfSel = (String) comboBoxPerfil.getSelectedItem();
        		if (cmbBoxPerfSel == "Proponente") {
        			setSize(450, 495);
        			lblDireccion.setVisible(true);
        			lblBiografia.setVisible(true);
        			lblPaginaWeb.setVisible(true);
                    bio.setVisible(true);
                	bio.setEditable(true);
                    bio.setBackground(new Color(255,255,255));
                    dir.setVisible(true);
                    dir.setEditable(true);
                    web.setVisible(true);
                    web.setEditable(true);
                    btnGuardar.setBounds(172, 426, 117, 25);
                    btnCancelar.setBounds(301, 426, 117, 25);
                } else {
                	setSize(450, 340);
                    lblDireccion.setVisible(false);
                    lblBiografia.setVisible(false);
                    lblPaginaWeb.setVisible(false);
                    bio.setVisible(false);
                	bio.setEditable(false);
                    bio.setBackground(new Color(238,238,238));
                    dir.setVisible(false);
                    dir.setEditable(false);
                    web.setVisible(false);
                    web.setEditable(false);
                    btnGuardar.setBounds(303, 281, 117, 25);
                    btnCancelar.setBounds(164, 281, 117, 25);
                }
            }
        });
        comboBoxPerfil.setBounds(86, 245, 177, 24);
        getContentPane().add(comboBoxPerfil);
        
        JLabel lblPerfil = new JLabel("Perfil:*");
        lblPerfil.setBounds(12, 250, 66, 15);
        getContentPane().add(lblPerfil);
        
        JLabel lblContrasea = new JLabel("Contraseña:*");
        lblContrasea.setBounds(12, 120, 99, 15);
        getContentPane().add(lblContrasea);
        
        JLabel lblConfirmarContrasea = new JLabel("Confirmar contraseña:*");
        lblConfirmarContrasea.setBounds(12, 147, 177, 15);
        getContentPane().add(lblConfirmarContrasea);
        
        contra = new JPasswordField();
        contra.setBounds(186, 118, 244, 19);
        getContentPane().add(contra);
        
        confir = new JPasswordField();
        confir.setBounds(186, 145, 244, 19);
        getContentPane().add(confir);
        
 
 	 }
	
	protected void AltaPerfilActionPerformed(ActionEvent e) {
		
		String nick = formattedTextFieldNick.getText();
		String nombre = formattedTextFieldNombre.getText();
		String apellido = formattedTextFieldApellido.getText();
		String mail = formattedTextFieldMail.getText();
		String dia = (String)comboBoxDia.getSelectedItem();
		String mes = (String)comboBoxMes.getSelectedItem();
		String anio = (String)comboBoxAnio.getSelectedItem();
		String perfil = (String)comboBoxPerfil.getSelectedItem();
		String direccion = dir.getText();
		String biografia = bio.getText();
		String dirWeb = web.getText();
		char[] pass = contra.getPassword();
		String cont = new String(pass);

		File fotoFile = foto;
		
		if (checkFormulario()) {
			int iDia = Integer.parseInt(dia);
			int iAnio = Integer.parseInt(anio);
			int iMes;
			if (mes.equals("Enero")) {
				iMes = 0;
			} else if (mes.equals("Febrero")) {
				iMes = 1;
			} else if (mes.equals("Marzo")) {
				iMes = 2;
			} else if (mes.equals("Abril")) {
				iMes = 3;
			} else if (mes.equals("Mayo")) {
				iMes = 4;
			} else if (mes.equals("Junio")) {
				iMes = 5; 
			} else if (mes.equals("Julio")) {
				iMes = 6;
			} else if (mes.equals("Agosto")) {
				iMes = 7;
			} else if (mes.equals("Setiembre")) {
				iMes = 8;
			} else if (mes.equals("Octubre")) {
				iMes = 9;
			} else if (mes.equals("Noviembre")) {
				iMes = 10;
			} else {
				iMes = 11;
			}
			
			try {
				if (fotoFile != null) {
					
					String extImg = null;
					
					String absolutePath = fotoFile.getAbsolutePath();
					int i = absolutePath.lastIndexOf('.');
					if (i > 0) {
						extImg = "." + absolutePath.substring(i+1);
					}

					byte[] fotoBytes = new byte[(int) fotoFile.length()];
					FileInputStream fotoInputStream = new FileInputStream(fotoFile);
					fotoInputStream.read(fotoBytes);
					fotoInputStream.close();
					
					ICUsu.agregarUsuario(nick, nombre, apellido, mail, iDia, iMes, iAnio, fotoBytes, extImg, perfil, direccion, biografia,
							dirWeb,cont);
					
				} else {
					
					byte[] arrayVacio = new byte[0];
					
					ICUsu.agregarUsuario(nick, nombre, apellido, mail, iDia, iMes, iAnio, arrayVacio, null, perfil, direccion, biografia,
							dirWeb,cont);
					
				}
				
				JOptionPane.showMessageDialog(this, "El Usuario " + nick + " se ha creado con éxito", "Alta de Perfil",
                        JOptionPane.INFORMATION_MESSAGE);
				
				limpiarFormulario();
				setVisible(false);
				
			} catch (UsuarioRepetidoException ex) {
				
				JOptionPane.showMessageDialog(this, ex.getMessage(), "Alta de Perfil", JOptionPane.ERROR_MESSAGE);
				
			} catch (FileNotFoundException e1) {
			} catch (IOException e1) {
			}
		}
	}
	
	static Pattern ptr = Pattern.compile("(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*:(?:(?:\\r\\n)?[ \\t])*(?:(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*)(?:,\\s*(?:(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*|(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)*\\<(?:(?:\\r\\n)?[ \\t])*(?:@(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*(?:,@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*)*:(?:(?:\\r\\n)?[ \\t])*)?(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\"(?:[^\\\"\\r\\\\]|\\\\.|(?:(?:\\r\\n)?[ \\t]))*\"(?:(?:\\r\\n)?[ \\t])*))*@(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*)(?:\\.(?:(?:\\r\\n)?[ \\t])*(?:[^()<>@,;:\\\\\".\\[\\] \\000-\\031]+(?:(?:(?:\\r\\n)?[ \\t])+|\\Z|(?=[\\[\"()<>@,;:\\\\\".\\[\\]]))|\\[([^\\[\\]\\r\\\\]|\\\\.)*\\](?:(?:\\r\\n)?[ \\t])*))*\\>(?:(?:\\r\\n)?[ \\t])*))*)?;\\s*)");
	public static boolean validarMail(String emailStr) {
        Matcher matcher = ptr.matcher(emailStr);
        return matcher.find();
	}

	public void limpiarFormulario() {
		formattedTextFieldNick.setText("");
		formattedTextFieldNombre.setText("");
		formattedTextFieldApellido.setText("");
		formattedTextFieldMail.setText("");
		txtImg.setText("");
		comboBoxDia.setSelectedIndex(0);
		comboBoxMes.setSelectedIndex(0);
		comboBoxAnio.setSelectedIndex(0);
		comboBoxPerfil.setSelectedIndex(0);
		dir.setText("");
		bio.setText("");
		web.setText("");
		foto = null;
		contra.setText("");
		confir.setText("");
	}

	private boolean checkFormulario() {
		String nick = formattedTextFieldNick.getText();
		String nombre = formattedTextFieldNombre.getText();
		String apellido = formattedTextFieldApellido.getText();
		String mail = formattedTextFieldMail.getText();
		String dia = (String)comboBoxDia.getSelectedItem();
		String mes = (String)comboBoxMes.getSelectedItem();
		String anio = (String)comboBoxAnio.getSelectedItem();
		String perfil = (String)comboBoxPerfil.getSelectedItem();
		String direccion = dir.getText();
		char[] pass = contra.getPassword();
		char[] conf = confir.getPassword();
		String cont = new String(pass);
		String confi = new String(conf);
		
		if(!cont.contentEquals(confi)){
			JOptionPane.showMessageDialog(this, "La contraseña y la confirmacion debe ser igual", "Alta de Perfil",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (nick.isEmpty() || nombre.isEmpty() || apellido.isEmpty() || mail.isEmpty() || dia.equals("Dia") ||
				mes.equals("Mes") || anio.equals("Año") || perfil.equals("Seleccione un perfil") || (cont.isEmpty()) || (confi.isEmpty())) {
			JOptionPane.showMessageDialog(this, "Rellene los campos obligatorios(*)", "Alta de Perfil",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		if (!validarMail(mail)) {
			JOptionPane.showMessageDialog(this, "Ingrese un mail valido", "Alta de Perfil",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		if (perfil.equals("Proponente") && direccion.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Rellene los campos obligatorios(*)", "Alta de Perfil",
                    JOptionPane.ERROR_MESSAGE);
            return false;
		}
		if (!checkFecha()) {
			JOptionPane.showMessageDialog(this, "Ingrese una fecha valida", "Alta de Perfil",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		if (!ICUsu.nickLibre(nick)) {
			JOptionPane.showMessageDialog(this, "El nick ya se encuentra en uso", "Alta de Perfil",
                    JOptionPane.ERROR_MESSAGE);
			return false;
		}
		return true;
	}
	
	private boolean checkFecha() {
		String dia = (String)comboBoxDia.getSelectedItem();
		String mes = (String)comboBoxMes.getSelectedItem();
		int iDia = Integer.parseInt(dia);
		int iMes;
		if (mes.equals("Enero")) {
			iMes = 1;
		} else if (mes.equals("Febrero")) {
			iMes = 2;
		} else if (mes.equals("Marzo")) {
			iMes = 3;
		} else if (mes.equals("Abril")) {
			iMes = 4;
		} else if (mes.equals("Mayo")) {
			iMes = 5;
		} else if (mes.equals("Junio")) {
			iMes = 6;
		} else if (mes.equals("Julio")) {
			iMes = 7;
		} else if (mes.equals("Agosto")) {
			iMes = 8;
		} else if (mes.equals("Setiembre")) {
			iMes = 9;
		} else if (mes.equals("Octubre")) {
			iMes = 10;
		} else if (mes.equals("Noviembre")) {
			iMes = 11;
		} else {
			iMes = 12;
		}
		
		if ((iMes == 3) || (iMes == 5) || (iMes == 8) || (iMes == 10)) {
			if (iDia > 30) {
				return false;
			}
		}
		if ((iMes==1) && (iDia > 28)){
			return false;
		}
		return true;
	}
}
