/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tabla;

import com.itextpdf.html2pdf.HtmlConverter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author Admin
 */
public class GenerateFile {

    public void insertData(String htmlFilePath) {

        try {

            File file = new File(htmlFilePath);            
            BufferedReader reader = new BufferedReader(new FileReader(file));

            StringBuilder htmlContent = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {

                htmlContent.append(line).append("\n");
            }

            reader.close();

            String startMarker = "<!-- START -->";
            String endMarker = "<!-- END -->";

            int startIdx = htmlContent.indexOf(startMarker) + startMarker.length();
            int endIdx = htmlContent.indexOf(endMarker);

            StringBuilder row = new StringBuilder();

            for (int i = 0; i < NewJFrame.modelo.getRowCount(); i++) {

                row.append("<tr>");
                for (int j = 0; j < NewJFrame.modelo.getColumnCount(); j++) {

                    String value = NewJFrame.modelo.getValueAt(i, j).toString();
                    row.append("<td style='text-align: center;'>").append(value).append("</td>");
                }
                row.append("</tr>");
            }
            
            
            htmlContent.replace(startIdx, endIdx, "\n" + row.toString() + "\n");

            // Escribir el contenido actualizado en el archivo HTML
            FileWriter writer = new FileWriter(htmlFilePath);
            writer.write(htmlContent.toString());
            writer.close();

            JOptionPane.showMessageDialog(null, "Datos insertados correctamente en el archivo HTML.");

        } catch (Exception e) {
        }

    }

    public void exportPDF(String htmlFilePath) {
        
        try {
             HtmlConverter.convertToPdf(new FileInputStream(htmlFilePath), new FileOutputStream("Pedido.pdf"));
              JOptionPane.showMessageDialog(null, "Factura generada correctamente.");
        } catch (Exception e) {
        }
        
       

    }

}
