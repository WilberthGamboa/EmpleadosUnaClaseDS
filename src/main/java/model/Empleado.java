package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.itextpdf.text.BaseColor;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import com.opencsv.CSVWriter;

public class Empleado {
    private String nombre;
    private int horasTrabajadas;
    private int numHorasRegulares;

    public Empleado() {

    }

    public Empleado(String nombre, int numHorasRegulares) {
        this.nombre = nombre;
        this.numHorasRegulares = numHorasRegulares;
        this.horasTrabajadas = numHorasRegulares * 20;

    }

    public int reporteHoras() {
        return horasTrabajadas;
    }

    public double calcularPago() {
        double pago;
        pago = this.horasTrabajadas * 100;

        return pago;
    }

    // genera archivo csv
    public boolean almacenar(LinkedList<Empleado> listaEmpleados) throws FileNotFoundException, DocumentException {
        boolean bandera = false;
        int contador = 0;
        File file = new File("DocumentosGenerados/ReporteEmpleados.csv");

        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);

            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String[] { "Nombre empleado", "HorasTrabajadas", "DINERO SIUUUUU" });
            for (int i = 0; i < listaEmpleados.size(); i++) {
                System.out.print(i);

                data.add(new String[] { listaEmpleados.get(i).nombre,
                        String.valueOf(listaEmpleados.get(i).reporteHoras()),
                        String.valueOf(listaEmpleados.get(i).calcularPago()) });

            }
            writer.writeAll(data);
            contador++;
            // closing writer connection
            writer.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("DocumentosGenerados/ReporteEmpleados.pdf"));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);

        document.add(new Paragraph("Nombre empleado " + "Horas Trabajadas " + " Dinero a pagar"));
        for (int i = 0; i < listaEmpleados.size(); i++) {
            document.add(new Paragraph(
                    listaEmpleados.get(i).nombre + " " + String.valueOf(listaEmpleados.get(i).reporteHoras() + " "
                            + String.valueOf(listaEmpleados.get(i).calcularPago()))));

        }

        document.close();
        contador++;
        if (contador == 2) {
            bandera = true;

        }

        return bandera;
    }

    public int numHorasRegulares() {
        return numHorasRegulares;
    }

    public static void main(String[] args) throws FileNotFoundException, DocumentException {
        // Creamos linkedlist empleados
        Empleado test = new Empleado();
        LinkedList<Empleado> listaEmpleados = new LinkedList<Empleado>();

        // creamos algunos empleados
        Empleado wilberth = new Empleado("Wilberth Manuel", 100);
        Empleado ariel = new Empleado("Ariel", 50);
        Empleado jacob = new Empleado("Jacob", 150);

        listaEmpleados.add(wilberth);
        listaEmpleados.add(ariel);
        listaEmpleados.add(jacob);

        if (test.almacenar(listaEmpleados)) {
            System.out.println("Documentos generados con exito");

        } else {
            System.out.println("Documentos no generados");

        }

    }

}
