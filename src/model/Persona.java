package model;

public class Persona {
    private int id;
    private int id_number;
    private String id_type ;
    private String name;
    private String last_name;
    private String day_birthday;
    private String gender;
    private String email;
    private String phone;

    public Persona(){}
   
    public Persona(int id, int id_number, String id_type, String name, String last_name, String day_birthday, String gender, String email, String phone){
        this.id = id;
        this.id_number = id_number;
        this.id_type = id_type;
        this.name = name;
        this.last_name = last_name;
        this.day_birthday = day_birthday;
        this.gender = gender;
        this.email = email;
        this.phone = phone;      

    }
    public int getId(){
        return this.id;
    }
    public void setId(int id){
        this.id = id;
    }
    public int getId_number(){
        return this.id_number;
    }
    public void setId_number(int id_number){
        this.id_number = id_number;
    }
    public String getId_type(){
        return this.id_type;
    }
    public void setId_type(String id_type){
        this.id_type = id_type;
    }
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getLast_name(){
        return this.last_name;
    }
    public void setLast_name(String last_name){
        this.last_name = last_name;
    }
    public String getDay_birthday(){
        return this.day_birthday;
    }
    public void setDay_birthday(String day_birthday){
        this.day_birthday = day_birthday;
    }
    public String getGender(){
        return this.gender;
    }
    public void setGender(String gender){
        this.gender= gender;
    }
    public String getEmail(){
        return this.email;
    }
    public void setEmail(String email){
        this.email = email;
    }
    public String getPhone(){
        return this.phone;
    }
    public void setPhone(String phone){
        this.phone = phone;
    }

}
