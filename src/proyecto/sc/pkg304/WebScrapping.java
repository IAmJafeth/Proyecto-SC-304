/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyecto.sc.pkg304;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author cerda
 */

public class WebScrapping {

    private static final String TOKEN = "ARM267615P";
    private static final String EMAIL = "jair@yopmail.com";
    private static final String URL = "https://gee.bccr.fi.cr/Indicadores/Suscripciones/WS/wsindicadoreseconomicos.asmx/ObtenerIndicadoresEconomicos";

    public static String cambiodolar() {
        try {
            String codigoIndicador = "317";
            String fechaInicio = "05/03/2025";
            String fechaFinal = "05/03/2025";
            String nombre = "jair";
            String subNiveles = "N";

            String urlString = URL + "?Indicador=" + codigoIndicador
                    + "&FechaInicio=" + fechaInicio
                    + "&FechaFinal=" + fechaFinal
                    + "&Nombre=" + nombre
                    + "&SubNiveles=" + subNiveles
                    + "&CorreoElectronico=" + EMAIL
                    + "&Token=" + TOKEN;

            URL url = new URL(urlString);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            StringBuilder content = new StringBuilder();
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                content.append(inputLine);
            }
            in.close();
            conn.disconnect();

            String xmlResponse = content.toString();
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(new java.io.ByteArrayInputStream(xmlResponse.getBytes()));

            NodeList valorList = doc.getElementsByTagName("NUM_VALOR");
            if (valorList.getLength() > 0) {
                return valorList.item(0).getTextContent();
            } else {
                return "No se encontró el valor del tipo de cambio.";
            }
        } catch (IOException | ParserConfigurationException | DOMException | SAXException e) {
            e.getMessage();
            return "Error al obtener el tipo de cambio.";
        }
    }
}