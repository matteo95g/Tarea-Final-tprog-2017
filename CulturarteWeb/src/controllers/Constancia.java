package controllers;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Calendar;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.datatype.XMLGregorianCalendar;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

import publicador.DtColaboracion;
import publicador.DtPago;
import publicador.DtPaypal;
import publicador.DtTransferencia;
import publicador.Publicador;
import publicador.PublicadorService;

/**
 * Servlet implementation class Constancia
 */
@WebServlet("/constancia")
public class Constancia extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public static final String RUTA
    = System.getProperty("user.home")+System.getProperty("file.separator")+"Culturarte" +
    		System.getProperty("file.separator");
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Constancia() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
    	
    	PublicadorService service = new PublicadorService();
        Publicador port = service.getPublicadorPort();   
        
        String id = request.getParameter("id");
        DtColaboracion colab = port.getColaboracion(Integer.parseInt(id));
        

        
        try {

        	Font f3 = new Font(Font.FontFamily.HELVETICA, 30.0f, Font.BOLD, BaseColor.BLACK);
        	Font f2 = new Font(Font.FontFamily.HELVETICA, 15.0f, Font.BOLD, BaseColor.BLACK);
	        Document document = new Document();

	        PdfWriter.getInstance(document, new FileOutputStream(RUTA + "constancia.pdf"));

	        document.open();
	        // step 4
	        Integer dia = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
	        Integer mes = Calendar.getInstance().get(Calendar.MONTH) + 1;
	        Integer anio = Calendar.getInstance().get(Calendar.YEAR);
	        String fecha = dia.toString() + "/" + mes.toString() + "/" + anio.toString();	        
	        Paragraph fechaa = new Paragraph(fecha);
	        fechaa.setAlignment(Element.ALIGN_RIGHT);
	        document.add(fechaa);
	        //Image imagen = Image.getInstance("http://cmsfiles.sbp-romania.com/13357/C.png");
	        //imagen.scaleAbsolute(100f, 100f);
	        //document.add(imagen);
	        document.add(new Paragraph("Plataforma Culturarte",f3));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("Datos de la colaboracion realizada:",f2));
	        document.add(Chunk.NEWLINE);
	        document.add(new Paragraph("Titulo de la propuesta: " + colab.getPropuesta()));
	        document.add(new Paragraph("Monto colaborado: $ " + colab.getMonto().toString()));
	        XMLGregorianCalendar cal = colab.getFechaColaboracion();
	        int diaC = cal.getDay();
	        int mesC = cal.getMonth();
	        int anioC = cal.getYear();
	        document.add(new Paragraph("Fecha de la colaboracion: " + diaC + "/" + mesC + "/" + anioC));
	        document.add(new Paragraph("Retorno elegido: " + colab.getRetornoElegido().toString().toLowerCase()));
	        DtPago pago = colab.getPago();
	        if (pago instanceof DtPaypal) {
				DtPaypal dtPpal = (DtPaypal) pago;
				document.add(new Paragraph("Forma de pago: PayPal"));
				document.add(new Paragraph("Titular: " + dtPpal.getTitular()));
				document.add(new Paragraph("Cuenta: " + dtPpal.getCuenta()));
			}else if (pago instanceof DtTransferencia) {
				DtTransferencia dtTrans = (DtTransferencia) pago;
				document.add(new Paragraph("Forma de pago: Transferencia bancaria"));
				document.add(new Paragraph("Titular: " + dtTrans.getTitular()));
				document.add(new Paragraph("Cuenta: " + dtTrans.getCuenta()));
				document.add(new Paragraph("Banco: " + dtTrans.getBanco()));
			}
	        
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
//	        document.add(Chunk.NEWLINE);
	        Paragraph gracias = new Paragraph("Gracias por preferirnos");
	        gracias.setAlignment(Element.ALIGN_RIGHT);
	        document.add(gracias);
	        Paragraph firma = new Paragraph("Culturarte");
	        firma.setAlignment(Element.ALIGN_RIGHT);
	        document.add(firma);

	        document.close();
        }catch (Exception e) {
			e.printStackTrace();
		}
        
        
	}
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		request.getRequestDispatcher("home").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
		request.getRequestDispatcher("home").forward(request, response);;
	}

}
