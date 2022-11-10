package model;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class PersonaDAO {
    private PreparedStatement pst;
    private ResultSet rs;
    
    public boolean addPerson (Persona persona){

        boolean state = false;
        Connection connect = null;

        try{

            connect = ConnectionPool.getInstance().getConnection();

            if(connect != null){

                String sql = "INSERT INTO persona (id_number, id_type, name, last_name, day_birthday, gender, email, phone,state) VALUES (?,?,?,?,?,?,?,?,?)";

                pst = connect.prepareStatement(sql);

                pst.setInt(1, persona.getId_number());
                pst.setString(2, persona.getId_type());
                pst.setString(3, persona.getName());
                pst.setString(4, persona.getLast_name());
                pst.setString(5, persona.getDay_birthday());
                pst.setString(6, persona.getGender());
                pst.setString(7, persona.getEmail());
                pst.setString(8, persona.getPhone());
                pst.setInt(9, 1);

                int res = pst.executeUpdate();

                state = res > 0;

            }else{
                System.out.println("Conexión Fallida");
            }


        }catch(Exception ex){

            System.out.println(ex.getMessage());
        
        }finally{
            try {
                ConnectionPool.getInstance().closeConnection(connect);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        return state;
    }

    public ArrayList<Persona> selectPerson(String filter, ArrayList<String> data){

        ArrayList<Persona> list = new ArrayList<>();
        Persona persona;

        Connection connect = null;

        try{

            connect = ConnectionPool.getInstance().getConnection();

            if(connect != null){

                String sql="";

                switch (filter) {
                    
                    case "Name":  
                        sql = "SELECT * FROM persona WHERE name REGEXP ? OR last_name REGEXP ?  AND state=1";
                        pst = connect.prepareStatement(sql);
                        pst.setString(1, data.get(0));
                        pst.setString(2, data.get(0));                                             
                    break;
                
                    case "Id_number":                        
                        sql = "SELECT * FROM persona WHERE id_type=? AND id_number=?  AND state=1";
                        pst = connect.prepareStatement(sql);
                        pst.setString(1, data.get(0));
                        pst.setInt(2, Integer.parseInt(data.get(1)));                                             
                    break;

                    default:
                        sql = "SELECT * FROM persona WHERE 1  AND state=1";
                        pst = connect.prepareStatement(sql);                    
                    break;

                }

                rs = pst.executeQuery();

                while(rs.next()){  

                    persona = new Persona();
                    
                    persona.setId(rs.getInt("id"));
                    persona.setId_type(rs.getString("id_type"));
                    persona.setId_number(rs.getInt("id_number"));
                    persona.setName(rs.getString("name"));
                    persona.setLast_name(rs.getString("last_name"));
                    persona.setEmail(rs.getString("email"));
                    persona.setPhone(rs.getString("phone"));
                    persona.setDay_birthday(rs.getString("day_birthday"));
                    persona.setGender(rs.getString("gender"));

                    list.add(persona);

                }


            }else{
                System.out.println("Conexión Fallida");
            }


        }catch(Exception ex){

            System.out.println(ex.getMessage());
        
        }finally{
            try {
                ConnectionPool.getInstance().closeConnection(connect);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }        


        return list;

    }

    public boolean updatePerson(Persona persona){

        boolean state = false;
        Connection connect = null;

        try{

            connect = ConnectionPool.getInstance().getConnection();

            if(connect != null){

                String sql = "UPDATE persona SET id_type=?, id_number=?, name=?, last_name=?, phone=?, email=?, day_birthday=?, gender=? WHERE id=?";

                pst = connect.prepareStatement(sql);
                pst.setString(1, persona.getId_type());
                pst.setInt(2, persona.getId_number());
                pst.setString(3, persona.getName());
                pst.setString(4, persona.getLast_name());
                pst.setString(5, persona.getPhone());
                pst.setString(6, persona.getEmail());
                pst.setString(7, persona.getDay_birthday());
                pst.setString(8, persona.getGender());
                pst.setInt(9, persona.getId());

                int res = pst.executeUpdate();

                state = res > 0;

            }else{
                System.out.println("Conexión Fallida");
            }


        }catch(Exception ex){

            System.out.println(ex.getMessage());
        
        }finally{
            try {
                ConnectionPool.getInstance().closeConnection(connect);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        return state;


    }

    public boolean deletePerson(int id){

        boolean state = false;
        Connection connect = null;

        try{

            connect = ConnectionPool.getInstance().getConnection();

            if(connect != null){

                // String sql = "DELETE FROM persona WHERE id=?";

                String sql = "UPDATE persona SET state=? WHERE id=?";

                pst = connect.prepareStatement(sql);
                pst.setInt(1, 0);
                pst.setInt(2, id);

                int res = pst.executeUpdate();

                state = res > 0;

            }else{
                System.out.println("Conexión Fallida");
            }


        }catch(Exception ex){

            System.out.println(ex.getMessage());
        
        }finally{
            try {
                ConnectionPool.getInstance().closeConnection(connect);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }


        return state;


    }

    public boolean validateId(String oldData[], String newData[]){
        boolean state = false;
        if(oldData!=null){
            if(!oldData[0].equals(newData[0]) || !oldData[1].equals(newData[1])){}
                ArrayList<String> data = new ArrayList<>();
                data.add(newData[0]);
                data.add(newData[1]);
                ArrayList<Persona> list = selectPerson("Id_number", data);

                if(list.size()>0){
                    state= true;
                }
        }else{
            ArrayList<String> data = new ArrayList<>();
            data.add(newData[0]);
            data.add(newData[1]);
            ArrayList<Persona> list = selectPerson("Id_number", data);

            if(list.size()>0){
                state= true;
            }
        }
       return state;
    }
    

}
    

