import java.io.*;

public class DataReader
{
	public int accountNumber;
	public int accountAmount[];
	public int transactionNumber;
	public int transactions[][];

	private String inputFileName;

	public DataReader() throws IOException
	{
		this("data/data.bin");
	}

	public DataReader(String inputFileName) throws IOException
	{
		File f = new File(inputFileName);
		if (!f.exists())
			throw new FileNotFoundException("File " + inputFileName + " does not exist");
		this.inputFileName = inputFileName;
	}

	public void read() throws IOException
	{
		DataInputStream is = new DataInputStream(new FileInputStream(this.inputFileName));
		int accountNumber;
		int accountAmount[];
		int transactionNumber;
		int transactions[][];

		accountNumber = is.readInt();
		accountAmount = new int[accountNumber];
		for (int i = 0; i < accountNumber; i++)
			accountAmount[i] = is.readInt();

		transactionNumber = is.readInt();
		transactions = new int[transactionNumber][3];
		for (int i = 0; i < accountNumber; i++)
			for (int j = 0; j < 3; j++)
			transactions[i][j] = is.readInt();

		this.accountNumber = accountNumber;
		this.accountAmount = accountAmount;
		this.transactionNumber = transactionNumber;
		this.transactions = transactions;
	}

	public int getAccountSum()
	{
		int sum = 0;
		for (int amount : this.accountAmount)
			sum += amount;

		return (sum);
	}

}
