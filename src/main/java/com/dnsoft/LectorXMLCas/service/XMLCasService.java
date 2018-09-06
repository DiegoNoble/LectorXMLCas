package com.dnsoft.LectorXMLCas.service;

import com.dnsoft.LectorXMLCas.beans.Datos;
import com.dnsoft.LectorXMLCas.beans.Root;
import com.dnsoft.utiles.MeDateConverter;
import java.io.File;
import java.io.FileReader;

import com.thoughtworks.xstream.XStream;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTextArea;

public class XMLCasService {

    public List<Root> leerXML(JTextArea log, String ruta) {
        List<Root> roots = new ArrayList();
        log.append("\n Leyendo archivos XML: ");
        try {
            File directorio = new File(ruta);
            File[] archivos = directorio.listFiles();
            for (File archivo : archivos) {
                //System.out.println(archivo.getName());
                log.append("\n" + archivo.toString());
                log.setCaretPosition(log.getDocument().getLength());

                FileReader reader = new FileReader(new File(archivo.toString()));

                XStream xStream = new XStream();
                xStream.processAnnotations(Root.class);
                xStream.registerConverter(new MeDateConverter("yyyy-MM-dd"));
                Root root = (Root) xStream.fromXML(reader);
                roots.add(root);
                for (Datos dato : root.getListTable()) {
                    dato.setNombre_Archivo(archivo.getName());

                }
            }

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return roots;
    }

    /*public void generarXML(Root root) {

     XStream xStream = new XStream();
     xStream.autodetectAnnotations(true);
     try {
     PrintWriter writer = new PrintWriter(new File("D://prueba.xml"));
     writer.println("<?xml version='1.0' encoding='UTF-8' ?>");
     writer.print(xStream.toXML(root));

     writer.flush();
     writer.close();

     } catch (FileNotFoundException e) {
     // TODO Auto-generated catch block
     e.printStackTrace();
     } catch (Exception e) {
     e.printStackTrace();
     }
     }*/
}
