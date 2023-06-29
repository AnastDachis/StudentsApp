public class Student {
    private String name,lastname,am;

    public Student(){        
        name="";
        lastname="";
        am="";
    }
    
    public Student(String n,String l,String p){
        name=n;
        lastname=l;
        am=p;
    }
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getAm() {
        return am;
    }

    public void setAm(String am) {
        this.am = am;
    }

    public String toString() {
        String s=am+" , "+ name +" , "+ lastname;
        return  s;  
    }
}
