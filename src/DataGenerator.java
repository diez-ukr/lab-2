import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Random;

public class DataGenerator
{
	private static void resetFile(String fileName) throws IOException
	{
		File f = new File(fileName);
		if (f.exists())
			f.delete();
		f.getParentFile().mkdirs();
		f.createNewFile();
	}

	public static void generate() throws IOException
	{
		generate(10, "data/data.bin");
	}

	public static void generate(int accountNumber) throws IOException
	{
		generate(accountNumber, "data/data.bin");
	}

	public static void generate(String fileName) throws IOException
	{
		generate(10, fileName);
	}

	public static void generate(int accountNumber, String fileName) throws IOException
	{
		resetFile(fileName);
		DataOutputStream os = new DataOutputStream(new FileOutputStream(fileName));
		Random rand = new Random(System.currentTimeMillis());

		os.writeInt(accountNumber);

		for (int i = 0; i < accountNumber; i++)
			os.writeInt(rand.nextInt(1000));

		int transactionNumber = rand.nextInt(1000);
		os.writeInt(transactionNumber);

		for (int i = 0; i < transactionNumber; i++)
		{
			os.writeInt(rand.nextInt(accountNumber));
			os.writeInt(rand.nextInt(accountNumber));
			os.writeInt(rand.nextInt(5) + 1); // transaction size 1$ - 5$
		}

		os.close();
	}
}