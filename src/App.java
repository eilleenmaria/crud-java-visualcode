import model.Persona;
import model.PersonaDAO;
import java.util.ArrayList;

import javax.swing.JOptionPane;
public class App {
    public static void main(String[] args) throws Exception {
        PersonaDAO model = new PersonaDAO();
        int menu = Integer.parseInt(JOptionPane.showInputDialog("CRUD EXAMPLE\n"+
        "1- Insert Data.\n"+
        "2- Select Data.\n"+
        "3- Update Data.\n"+
        "4- Delete Data.\n"+
        "5- Exit."));                                                  

        switch(menu){

            case 1:
                
                Persona persona = new Persona();

                persona.setId_number(1349);
                persona.setId_type("Cedula de Ciudadania");
                persona.setName("Pablo Cesar");
                persona.setLast_name("Perea");
                persona.setDay_birthday("1990/08/09");
                persona.setEmail("Example@gmail.com");
                persona.setPhone("34067854");
                persona.setGender("Masculino");

                if(!model.validateId(null, new String[]{persona.getId_type(), String.valueOf(persona.getId_number())})){
                    if(model.addPerson(persona)){
                        System.out.println("Guardado");
                    }else{
                        System.out.println("No Guardado");
                    }
                }else{
                    System.out.println("El documento ya se encuentra almacenado en la base de datos");
                }
                

            break;

            case 2:

                // ArrayList<String> data=new ArrayList<>();
                // data.add("Cedula de Ciudadania");
                // data.add("1345");
                
                ArrayList<Persona> list = model.selectPerson("Todo", null);

                for(int i=0; i<list.size(); i++){

                    System.out.println(list.get(i).getId_number() + " " + list.get(i).getName() + " "+ list.get(i).getLast_name());
                
                }
                
            break;

            case 3:

                persona = new Persona();

                persona.setId(1);
                persona.setId_number(1345);
                persona.setId_type("Cedula de Ciudadania");
                persona.setName("Maria Carolina");
                persona.setLast_name("Perez");
                persona.setDay_birthday("1994/05/09");
                persona.setEmail("ExampleT@gmail.com");
                persona.setPhone("876544");
                persona.setGender("Femenino");
        
                if(!model.validateId(new String[]{persona.getId_type(), String.valueOf(persona.getId_number())},new String[]{"Cedula de ciudadania", String.valueOf(1349)} )){
                    persona.setId_number(1349);
                    persona.setId_type("Cedula de ciudadania");   

                    if(model.updatePerson(persona)){
                        System.out.println("Los datos han sido modificados de manera exitosa");
                    }else{
                        System.out.println("No se pudo modificar la informacion");
                    }
     
                }else{
                    System.out.println("El numero de documento ya se encuentra registrado en la base de datos");
                }
                
            break;

            case 4:

                int confirm = JOptionPane.showConfirmDialog(null, "¿Esta seguro que desea realizar el proceso?\n"
                                                                 +"Todos los datos se perderan");

                if(confirm == JOptionPane.YES_OPTION){
                    if(model.deletePerson(3)){
                        System.out.println("Los datos han sido eliminados de manera exitosa");
                    }else{
                        System.out.println("No se pudo eliminar la información");
                    }
                }

            break;

            case 5:
                System.out.println("Saliendo del sistema...");
                System.out.println("Proceso finalizado");
            break;

        }



    }

}
