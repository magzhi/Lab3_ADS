package Classes;

/**
 * Dummy class used for testing purposes.
 */
public class MyTestingClass {
    private String name;

    /**
     * Constructor of the dummy class.
     * @param name Person name
     */
    public MyTestingClass(String name){
        this.name = name;
    }

    /**
     * Computes a custom hash code for this instance based on its name.
     * Each name character is converted into integer after being
     * subtracted from 'a' char to make numbers manageable in size.
     * @return hash code of this element.
     */
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

    /**
     * Compares this instance with another object for equality.
     * @param obj The object to be compared.
     * @return True if the given object is equal to this element.
     */
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
