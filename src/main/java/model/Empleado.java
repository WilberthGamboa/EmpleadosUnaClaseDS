package model;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import com.opencsv.CSVWriter;

public class Empleado {
    private String nombre;
    private int horasTrabajadas;
    private int numHorasRegulares;
    
    public Empleado(){

    }
    public Empleado (String nombre, int numHorasRegulares){
        this.nombre= nombre;
        this.numHorasRegulares = numHorasRegulares;
        this.horasTrabajadas=numHorasRegulares*20;
        
    }

    
    public int reporteHoras(){
        return horasTrabajadas;
    }


    public double calcularPago(){
        double pago;
        pago = this.horasTrabajadas*100;
        
        return pago;
    }

//genera archivo csv 
    public boolean almacenar(LinkedList<Empleado> listaEmpleados){
        File file = new File("ReporteEmpleados.csv");
  
        try {
            // create FileWriter object with file as parameter
            FileWriter outputfile = new FileWriter(file);
      
            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);
      
            // create a List which contains String array
            List<String[]> data = new ArrayList<String[]>();
            data.add(new String []{"Nombre empleado", "HorasTrabajadas","DINERO SIUUUUU"});
            for (int i = 0; i < listaEmpleados.size(); i++) {
                System.out.print(i);

                data.add(new String[] { listaEmpleados.get(i).nombre, String.valueOf(listaEmpleados.get(i).reporteHoras()), String.valueOf(listaEmpleados.get(i).calcularPago())});
               
            
            }
            writer.writeAll(data);
          
            
           
           
      
            // closing writer connection
            writer.close();
        }
        catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }





        return false;
    }


    public int numHorasRegulares(){
        return numHorasRegulares;
    }



    public static void main(String[] args) {
        //Creamos linkedlist empleados
        Empleado test = new Empleado();
        LinkedList<Empleado> listaEmpleados = new LinkedList<Empleado>();

        //creamos algunos empleados
        Empleado wilberth = new Empleado("Wilberth Manuel", 100);
        Empleado ariel = new Empleado("Ariel", 50);
        Empleado jacob = new Empleado("Jacob", 150);

        listaEmpleados.add(wilberth);
        listaEmpleados.add(ariel);
        listaEmpleados.add(jacob);

        test.almacenar(listaEmpleados);



        


        
    }
    
}
