package Classes;

public class MyTestingClass {
    private String name;

    public MyTestingClass(String name){
        this.name = name;
    }

    @Override
    public int hashCode(){
        int hash = 1;
        for (int i = 0; i < name.length(); i++) {
            hash = hash * 31 + (int) (name.charAt(i) - 'a' + 1);
            //System.out.println("Debug hash is: " + hash);
        }
        //System.out.println("Debug hash is: " + hash);
        return hash;
    }

    @Override
    public boolean equals(Object obj){
        if (obj == null) {
            return false;
        }

        if (obj.getClass() != this.getClass()) {
            return false;
        }

        MyTestingClass other = (MyTestingClass) obj;

        if (!this.name.equals(other.name)) {
            return false;
        }

        return true;
    }
}
