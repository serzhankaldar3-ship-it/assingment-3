public class Viewer extends Person{

    private int age;

    public Viewer (String name, int age){
        super(name);
        this.age = age;
    }
    @Override
    public String getRole() { return " Viewer"; }
    public int getAge(){
        return age;
    }

}