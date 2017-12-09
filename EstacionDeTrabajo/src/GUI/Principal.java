package GUI;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import controladores.Configuracion;
import excepciones.UsuarioRepetidoException;
import excepciones.UsuarioYaColaboraException;
import interfaces.Fabrica;
import interfaces.IControladorCategoria;
import interfaces.IControladorColaboracion;
import interfaces.IControladorPropuesta;
import interfaces.IControladorUsuario;
import interfaces.IPersistencia;
import publicador.Publicador;


public class Principal {

    

    private JFrame frame;
    
	private IControladorCategoria ICCat;
	private IControladorUsuario ICUsu;
	private IControladorPropuesta ICProp;
	private IControladorColaboracion ICola;
	private Configuracion CONFIG;
	private IPersistencia IPer;
    
    
    private AltaPerfil altaPerfilInternalFrame;
    private AltaDePropuesta altaPropInternalFrame;
    private SeguirUsuario segUsrInternalFrame;
    private DejarDeSeguirUsuario dejSegUsrInternalFrame;
    private CancelarColaboracionAPropuesta cancelarColaboracionAInternalFrame;
    private AltaDeCategoria altaDeCatInternalFrame;
    private ConsultaDeColaboracionAPropuesta consultaDeColAPropInternalFrame;
    private ConsultaPerfilProponente consultaDePerPropInternalFrame;
    private ConsultaPerfilColaborador consultaDePerColaInternalFrame;
    private ModificarDatosDePropuesta modificarDatosPropInternalFrame;
    private RegistrarColaboracionAPropuesta registrarColaboracionaPropuesta;
    private ConsultaDePropuesta consultaDeProp;
    private ConsultaDePropuestaPorEstado consultaDePropuestaPorEstadoInternalFrame;
    private EvaluacionPropuesta evaluacionPropuesta;
    private VerRegistro verRegistro;
    private VerPropEliminados propEliminados;
    
    /**
     * Launch the application.
     */
    public static void main(String[] args) {
   	 EventQueue.invokeLater(new Runnable() {
   		 public void run() {
   			 Publicador publi = new Publicador();
   			 publi.publicar();
   			 try {
   				 Principal window = new Principal();
   				 window.frame.setVisible(true);
   			 } catch (Exception e) {
   				 e.printStackTrace();
   			 }
   		 }
   	 });
    }

    /**
     * Create the application.
     */
    public Principal() {
   	 initialize();
   	 
   	 //Inicializamos la fabrica y los controladores
   	 interfaces.Fabrica fabrica = Fabrica.getInstance();
    	ICCat = fabrica.getIControladorCategoria();
    	ICUsu = fabrica.getIControladorUsuario();
    	ICProp = fabrica.getIControladorPropuesta();
    	ICola = fabrica.getIControladorColaboracion();
    	CONFIG = Configuracion.getInstancia();
        IPer = fabrica.getIPersistencia();   
   	 
   	 
    	CONFIG.createPathImagenes();
   	 
   	 //Alta de perfil InternalFrame
   	 altaPerfilInternalFrame = new AltaPerfil(ICUsu);
   	 altaPerfilInternalFrame.setLocation(100, 50);
   	 altaPerfilInternalFrame.setVisible(false);   	 
   	 frame.getContentPane().add(altaPerfilInternalFrame);
   	 
   	 //Alta propuesta InternalFrame
   	 altaPropInternalFrame = new AltaDePropuesta(ICUsu,ICCat,ICProp);
   	 altaPropInternalFrame.setLocation(100, 50);
   	 altaPropInternalFrame.setVisible(false);
   	 frame.getContentPane().add(altaPropInternalFrame);
   	 
   	 //Seguir Usuario Internal Frame
   	 segUsrInternalFrame = new SeguirUsuario(ICUsu);
   	 segUsrInternalFrame.setLocation(100, 50);
   	 segUsrInternalFrame.setVisible(false);
   	 frame.getContentPane().add(segUsrInternalFrame);
   	 
   	 //Dejar de Seguir Usuario Internal Frame
   	 dejSegUsrInternalFrame = new DejarDeSeguirUsuario(ICUsu);
   	 dejSegUsrInternalFrame.setLocation(100, 50);
   	 dejSegUsrInternalFrame.setVisible(false);
   	 frame.getContentPane().add(dejSegUsrInternalFrame);
   	 
   	 //Cancelar Colaboracion a Propuesta Internal Frame
   	 cancelarColaboracionAInternalFrame = new CancelarColaboracionAPropuesta(ICUsu,ICola);
   	 cancelarColaboracionAInternalFrame.setLocation(100, 50);
   	 cancelarColaboracionAInternalFrame.setVisible(false);
   	 frame.getContentPane().add(cancelarColaboracionAInternalFrame);
   	 
   	 //Alta de categoria InternalFrame
   	 altaDeCatInternalFrame = new AltaDeCategoria(ICCat);
   	 altaDeCatInternalFrame.setLocation(100, 50);
   	 altaDeCatInternalFrame.setVisible(false);   	 
   	 frame.getContentPane().add(altaDeCatInternalFrame);
   	 
   	 //Consulta de col a prop Internal Frame
   	 consultaDeColAPropInternalFrame = new ConsultaDeColaboracionAPropuesta(ICUsu,ICola);
   	 consultaDeColAPropInternalFrame.setLocation(100, 50);
   	 consultaDeColAPropInternalFrame.setVisible(false);   	 
   	 frame.getContentPane().add(consultaDeColAPropInternalFrame);
   	 
   	 //Consulta de perfil de prop Internal Frame
   	 consultaDePerPropInternalFrame = new ConsultaPerfilProponente(ICUsu,CONFIG);
   	 consultaDePerPropInternalFrame.setLocation(100, 50);
   	 consultaDePerPropInternalFrame.setVisible(false);   	 
   	 frame.getContentPane().add(consultaDePerPropInternalFrame);
   	 
   	 
   	 //Conculta de perfil de Colaborador Internal Frame
   	 consultaDePerColaInternalFrame = new ConsultaPerfilColaborador(ICUsu,CONFIG);
   	 consultaDePerColaInternalFrame.setLocation(100, 50);
   	 consultaDePerColaInternalFrame.setVisible(false);   	 
   	 frame.getContentPane().add(consultaDePerColaInternalFrame);
   	 
   	 //Modificar Datos Prop Internal Frame
   	 modificarDatosPropInternalFrame = new ModificarDatosDePropuesta(ICProp, ICCat, CONFIG);
   	 modificarDatosPropInternalFrame.setLocation(100, 50);
   	 modificarDatosPropInternalFrame.setVisible(false);   	 
   	 frame.getContentPane().add(modificarDatosPropInternalFrame);
   	 
   	 
   	 //Registro Colaboracion A Propu Internal Frame
   	 registrarColaboracionaPropuesta = new RegistrarColaboracionAPropuesta(ICUsu,ICProp,ICola);
   	 registrarColaboracionaPropuesta.setLocation(100, 50);
   	 registrarColaboracionaPropuesta.setVisible(false);
   	 frame.getContentPane().add(registrarColaboracionaPropuesta);
   	 
   	 //Consulta de Propuesta Internal Frame
   	 consultaDeProp = new ConsultaDePropuesta(ICProp);
   	 consultaDeProp.setLocation(100, 50);
   	 consultaDeProp.setVisible(false);
   	 frame.getContentPane().add(consultaDeProp);
   	 
   	 //Consulta de Propuesta por estado Internal Frame
   	 consultaDePropuestaPorEstadoInternalFrame = new ConsultaDePropuestaPorEstado(ICProp,CONFIG);
   	 consultaDePropuestaPorEstadoInternalFrame.setLocation(100, 50);
   	 consultaDePropuestaPorEstadoInternalFrame.setVisible(false);
   	 frame.getContentPane().add(consultaDePropuestaPorEstadoInternalFrame);
   	 
  	 //EvaluacionProp
   	 evaluacionPropuesta = new EvaluacionPropuesta(ICProp);
   	 evaluacionPropuesta.setLocation(100,50);
   	 evaluacionPropuesta.setVisible(false);
   	 frame.getContentPane().add(evaluacionPropuesta);
   	 
   	 //VerRegistroAcceso
   	 verRegistro = new VerRegistro(ICUsu);
   	 verRegistro.setLocation(100, 50);
   	 verRegistro.setVisible(false);
   	 frame.getContentPane().add(verRegistro);
   	 
   	 //Proponentes Eliminados
   	 propEliminados = new VerPropEliminados(IPer);
   	 propEliminados.setLocation(100, 50);
   	 propEliminados.setVisible(false);
   	 frame.getContentPane().add(propEliminados);
    }

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setTitle("CULTURARTE");
		frame.setBounds(70, 70, 1500, 900);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		
		JMenuBar menuBar = new JMenuBar();
		frame.setJMenuBar(menuBar);
		
		JMenu mnSistema = new JMenu("Sistema");
		menuBar.add(mnSistema);
		
		JMenuItem mntmSalir = new JMenuItem("Salir");
		mntmSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                frame.setVisible(false);
                frame.dispose();
            }
        });
		
		JMenuItem mntmCargarDatosDe = new JMenuItem("Cargar datos de prueba");
		mntmCargarDatosDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                // Salgo de la aplicación
                
					try {
						CONFIG.cargarDatos();
					    IPer.cargarCategorias();
					} catch (UsuarioRepetidoException | IOException | UsuarioYaColaboraException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				
            }
        });
		
		JMenuItem mntmVerRegistro = new JMenuItem("Ver Registro Acceso");
		mntmVerRegistro.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	verRegistro.cargarDatos();
            	verRegistro.setVisible(true);
    
            }
        });
		
		
		JMenuItem mntmVerPropEli = new JMenuItem("Ver Proponentes Eliminados");
		mntmVerPropEli.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
            	propEliminados.cargarDatos();
            	propEliminados.setVisible(true);
    
            }
        });
		
		
		
		mnSistema.add(mntmCargarDatosDe);
		mnSistema.add(mntmVerPropEli);
		mnSistema.add(mntmVerRegistro);
		mnSistema.add(mntmSalir);
		
		
		JMenu mnUsuarios = new JMenu("Usuarios");
		menuBar.add(mnUsuarios);
		
		JMenuItem altPerf = new JMenuItem("Alta de Perfil");
		altPerf.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un usuario
            	altaPerfilInternalFrame.limpiarFormulario();
            	altaPerfilInternalFrame.setVisible(true);
            }
        });
		mnUsuarios.add(altPerf);
		
		JMenuItem mntmConsultaDePerfil = new JMenuItem("Consulta de Perfil de Proponente");
		mntmConsultaDePerfil.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consultaDePerPropInternalFrame.limpiarFormulario();
            	consultaDePerPropInternalFrame.setVisible(true);
            }
        });
		mnUsuarios.add(mntmConsultaDePerfil);
		
		JMenuItem mntmSeguirUsuario = new JMenuItem("Seguir Usuario");
		mntmSeguirUsuario.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar un usuario
            	segUsrInternalFrame.limpiarFormulario();
            	segUsrInternalFrame.setVisible(true);
            }
        });
		
		JMenuItem mntmConsultaDePerfil_1 = new JMenuItem("Consulta de Perfil de Colaborador");
		mntmConsultaDePerfil_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				consultaDePerColaInternalFrame.limpiarFormulario();
				consultaDePerColaInternalFrame.setVisible(true);
			}
		});
		
		mnUsuarios.add(mntmConsultaDePerfil_1);
		mnUsuarios.add(mntmSeguirUsuario);
		
		JMenuItem mntmDejarDeSeguir = new JMenuItem("Dejar de Seguir Usuario");
		mntmDejarDeSeguir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dejSegUsrInternalFrame.limpiarFormulario();
            	dejSegUsrInternalFrame.setVisible(true);
            }
        });
		mnUsuarios.add(mntmDejarDeSeguir);
		
		JMenu mnPropuestas = new JMenu("Propuestas");
		menuBar.add(mnPropuestas);
		
		JMenuItem altProp = new JMenuItem("Alta de Propuesta");
		altProp.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de propuesta
            	altaPropInternalFrame.cargarCategorias();
            	altaPropInternalFrame.cargarProponentes();
            	altaPropInternalFrame.setVisible(true);
            }
        });
		mnPropuestas.add(altProp);
		
		JMenuItem mntmModificarDatosDe = new JMenuItem("Modificar Datos de Propuesta");
		mntmModificarDatosDe.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para alta de propuesta
            	modificarDatosPropInternalFrame.limpiarFormulario();
            	modificarDatosPropInternalFrame.setVisible(true);
            }
        });
		mnPropuestas.add(mntmModificarDatosDe);
		
		JMenuItem mntmConsultaDePropuesta = new JMenuItem("Consulta de Propuesta");
		mntmConsultaDePropuesta.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	consultaDeProp.cargarPropuestas();
                // Muestro el InternalFrame para registrar una categoria
            	consultaDeProp.setVisible(true);
            }
        });
		mnPropuestas.add(mntmConsultaDePropuesta);
		
		JMenuItem mntmConsultaDePropuestaPorEstado = new JMenuItem("Consulta de Propuesta por Estado");
		mntmConsultaDePropuestaPorEstado.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar una categoria
            	consultaDePropuestaPorEstadoInternalFrame.limpiarFormulario();
            	consultaDePropuestaPorEstadoInternalFrame.setVisible(true);
            }
        });
		mnPropuestas.add(mntmConsultaDePropuestaPorEstado);
		
		JMenuItem mntmAltaDeCategoria = new JMenuItem("Alta de Categoria");
		mntmAltaDeCategoria.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar una categoria
            	altaDeCatInternalFrame.limpiarFormulario();
            	altaDeCatInternalFrame.setVisible(true);
            }
        });
		mnPropuestas.add(mntmAltaDeCategoria);
		
		
		JMenuItem mntmEvaluarProp = new JMenuItem("Evaluar Propuesta");
		mntmEvaluarProp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {			
				evaluacionPropuesta.cargarPropuestas();
				evaluacionPropuesta.setVisible(true);
			}
		});
		mnPropuestas.add(mntmEvaluarProp);
		
		
		
		JMenu mnColaboraciones = new JMenu("Colaboraciones");
		menuBar.add(mnColaboraciones);
		
		JMenuItem mntmRegistrarColaboracionA = new JMenuItem("Registrar Colaboracion a Propuesta");
		mntmRegistrarColaboracionA.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				registrarColaboracionaPropuesta.cargarDatos();
				registrarColaboracionaPropuesta.setVisible(true);
				
			}
		});
		mnColaboraciones.add(mntmRegistrarColaboracionA);
		
		JMenuItem mntmConsultaDeColaboracion = new JMenuItem("Consulta de Colaboracion a Propuesta");
		mntmConsultaDeColaboracion.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para registrar una categoria
            	consultaDeColAPropInternalFrame.cargarDatos();
            	consultaDeColAPropInternalFrame.setVisible(true);
            }
        });
		mnColaboraciones.add(mntmConsultaDeColaboracion);
		
		JMenuItem mntmCancelarColaboracionA = new JMenuItem("Cancelar Colaboracion a Propuesta");
		mntmCancelarColaboracionA.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Muestro el InternalFrame para cancelar colaboracion 
            	cancelarColaboracionAInternalFrame.cargarDatos();
            	cancelarColaboracionAInternalFrame.setVisible(true);
            }
        });
		mnColaboraciones.add(mntmCancelarColaboracionA);
	}
}
