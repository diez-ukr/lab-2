import java.io.IOException;

public class Main {

    public static void main(String[] args) {
	    try
	    {
		    DataGenerator.generate();
		    DataReader dr = new DataReader();
		    dr.read();
		    System.out.println("check sum is " + dr.getAccountSum() + "$.");
	    } catch (IOException e)
	    {
		    System.out.println(e.getMessage());
	    }
    }
}
