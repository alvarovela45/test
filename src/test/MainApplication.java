package test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

public class MainApplication {

	static Scanner sc = new Scanner(System.in);
	public static void main(String [] args)  {
			
		 int numeroMenu = 0;
		 while(numeroMenu != -1) {
			 System.out.println("------MENU-----");
			 System.out.println("INGRESE NUMERO DE OPCION Y PRESIONE ENTER: \n 1.- Ejercicio fecha: \n 2.- Ejercicio digitos: \n 3.- Ejercicio  empleados: \n Presione 0 para salir");
			 switch (sc.nextInt()) {
		
			 case 1:	
				 sc.nextLine();
				validationDate();
				 break;
			 case 2:
				 sc.nextLine();
				 validationDigit();
				 break;
			 case 3:
				 sc.nextLine();
				 reistrarEmpleados();
				 break;

			default:
				numeroMenu = -1;
				break;
			}
		 }
		 System.out.println("------GRACIAS :D-----");
		 
	}
	public static void validationDate() {
		 System.out.println("------TEST 1-----");
		 System.out.println("INGRESE CADENA DE FECHA: (dd/MM/yyyy)");
		
		 if(isValid(sc.nextLine())) {
			 System.out.println( "Fecha valida") ;
		 } else 
			 System.out.println( "Fecha invalida") ;
		
		 
		 
	}
	public static void validationDigit() {
		 System.out.println("------TEST 2-----");
		 System.out.println("INGRESE CADENA DE CARACTERES: ");
		 System.out.println("Numero de digitos: "+countDigits( sc.nextLine())) ;
		
		 
		 
	}
	public static void reistrarEmpleados() {
		 List<Empleado> listaEmpleados = new ArrayList<>();
		
		 
		 int numeroMenu = 0;
		 while(numeroMenu != -1) {
			 System.out.println("------TEST 3-----");
			 System.out.println("SELECCIONE ACCION\n1.-Registrar empleado\n2.-Obtener edad de empleado por Id\n3.-Obtener lista de empleados por orden alfabetico apellido"
			 		+ "\n4.-Obtener lista de empleados por orden de edad\nPRESIONE 0 PARA SALIR");
		
			 switch (sc.nextInt()) {
		
			 case 1:	
				 sc.nextLine();
				 listaEmpleados.add(insertEmpleado());
				 System.out.println("Empleado registrado con exito");
				 break;
			 case 2:
				 sc.nextLine();
				 Empleado empleadoEncontrado =findById(listaEmpleados);
				 if(empleadoEncontrado!=null) {
					 System.out.println("Id empleado : "+empleadoEncontrado.getIdEmpleado() + "\nEdad: " +calcularEdad(empleadoEncontrado.getFechaNacimiento()));
				 }
				
				 break;
			 case 3:
				 sc.nextLine();
				 sortByApellido(listaEmpleados);
				 break;
			 case 4:
				 sc.nextLine();
				 sortByEdad(listaEmpleados);
				 break;

			default:
				numeroMenu = -1;
				break;
			}
		 }
		
		 
		 
	}
	
	@SuppressWarnings("deprecation")
	public static Empleado insertEmpleado() {
		Empleado empleado = new Empleado();
		 String scanner;
		 System.out.println("INGRESE DATOS DEL EMPLEADO:\n ");
		 do {          
			 System.out.println("Ingrese id: ");
			 scanner=sc.nextLine(); 
			} while(!isNumber(scanner));
		 empleado.setIdEmpleado(Integer.parseInt(scanner));
		 
		 do {          
			 System.out.println("Ingrese nombre: ");
			 scanner=sc.nextLine(); 
			} while(isNumber(scanner));
		 empleado.setNombre(scanner);
		 
		
		 do {          
			 System.out.println("Ingrese apellido paterno: ");
			 scanner=sc.nextLine(); 
			} while(isNumber(scanner));
		 empleado.setApellidoPaterno(scanner);
	
		 do {          
			 System.out.println("Ingrese apellido materno: ");
			 scanner=sc.nextLine(); 
			} while(isNumber(scanner));
		 empleado.setApellidoMaterno(scanner);
		
		 do {          
			 System.out.println("Ingrese fecha de nacimiento 20/12/1990: ");
			 scanner=sc.nextLine(); 
			} while(!isValid(scanner));
		
		 SimpleDateFormat formatoDelTexto = new SimpleDateFormat("dd/MM/yyyy");
		 String strFecha =scanner;
		 try {

			 empleado.setFechaNacimiento( formatoDelTexto.parse(strFecha));

		 } catch (ParseException ex) {

		 ex.printStackTrace();

		 }
		 
		return empleado; 
	}
	public static boolean isNumber(String text) {
	    
	    try {
	    Integer.parseInt(text);
	      return true;
	    } catch (NumberFormatException ex) {
	       return false;
	    }
	}
	public static Empleado findById(List<Empleado> lista) {
		 System.out.println("INGRESE ID DEL EMPLEADO: ");
		 int id = sc.nextInt();
		 for(Empleado e : lista) {
			 if(e.getIdEmpleado()== id) {
				 return e;
			 }
		 } 
		 return null;
	}

	public static int countDigits(String cad) {
		int count = 0;
		for(int i = 0; i<cad.length();i++) {
			if( Character.isDigit(cad.charAt(i))) {
				count++;
			}
		}
		return count;
	}
	public static void sortByEdad(List<Empleado> lista) {
		Collections.sort(lista, new Comparator<Empleado>() {
			@Override
			public int compare(Empleado e1, Empleado e2) {
				return new Integer(calcularEdad(e2.getFechaNacimiento())).compareTo(new Integer(calcularEdad(e1.getFechaNacimiento())) );
			}
		});
		System.out.println("Lista ordenada por edad");
		for(Empleado e: lista) {
			System.out.println("Edad: "+ calcularEdad(e.getFechaNacimiento())+ "*****"+ e.toString()+"\n");
		}
	}
	public static void sortByApellido(List<Empleado> lista) {
		Collections.sort(lista, new Comparator<Empleado>() {
			@Override
			public int compare(Empleado e1, Empleado e2) {
				return e1.getApellidoPaterno().compareTo(e2.getApellidoPaterno());
			}
		});
		System.out.println("Lista ordenada por apellido");
		for(Empleado e: lista) {
			System.out.println(e.toString()+"\n");
		}
		
	}
	public static int calcularEdad(Date fecha) {
		 SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
		 String fechaCadena = formatoFecha.format(fecha);
		 String [] trunkDate = fechaCadena.split("/");
		 return getYears(Integer.parseInt(trunkDate[0]),Integer.parseInt(trunkDate[1]),Integer.parseInt(trunkDate[2]));
	
		      
		
	}
	static int getYears(int dia, int mes, int anio) {
		LocalDate fechaHoy = LocalDate.now();
		LocalDate fechaNacimiento = LocalDate.of(anio, mes, dia);
 
		return Period.between(fechaNacimiento, fechaHoy).getYears();
 
		
	}
	public static boolean isValid(String date)  {
	    

	    try {
	       
	        SimpleDateFormat formatoFecha = new SimpleDateFormat("dd/MM/yyyy");
	        formatoFecha.setLenient(false);
	        String [] trunkDate = date.split("/");
	        if(trunkDate.length == 3) {
	        	 formatoFecha.parse(trunkDate[0] + "/" +trunkDate[1] + "/" + trunkDate[2]);
	  	       return true;
	        } else return false;
	       
	    } catch (ParseException e) {
	      
	       return false;
	    }

	    
	}


}
