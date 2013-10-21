import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.*;

public class Main
{
	public static Bank bank = new Bank();
	public static DataReader dr;

	public static void main(String[] args) throws InterruptedException, IOException
	{
		String dataFileName;
		if (args.length < 1)
		{
			dataFileName = DataGenerator.generate();
			System.out.println("Default data file created :" + dataFileName);
		}
		else
			dataFileName = args[0];

		dr = new DataReader(dataFileName);
		dr.read();
		System.out.println("Check sum after file reading is " + dr.getAccountSum() + "$.");

		for (int amount : dr.accountAmount)
			bank.addAccount(amount);

		ExecutorService consumer = new ThreadPoolExecutor(
				1,
				4,
				30,
				TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(100),
				new ThreadPoolExecutor.AbortPolicy()
				//new ThreadPoolExecutor.CallerRunsPolicy()
				//new ThreadPoolExecutor.DiscardPolicy()
				//new ThreadPoolExecutor.DiscardOldestPolicy()
		);

		LinkedList<ATM> atm = new LinkedList<ATM>();
		for (int i = 0; i < 5; i++)
			atm.add(new ATM(consumer, bank));

		int targetIndex = 0;
		for (int transaction[] : dr.transactions)
			atm.get(targetIndex % 5).addTransaction(transaction);

		LinkedList<Thread> threads = new LinkedList<Thread>();
		for (ATM a : atm)
			threads.add(new Thread(a));

		long timeStart = System.currentTimeMillis();
		for (Thread t : threads)
			t.start();
		for (Thread t : threads)
			t.join();

		consumer.awaitTermination(3, TimeUnit.SECONDS);
		consumer.shutdown();

		while (!consumer.awaitTermination(10, TimeUnit.SECONDS))
		{
			System.out.println("Awaiting completion of threads.");
		}
		long workTime = System.currentTimeMillis() - timeStart;
		System.out.println("Sum after transactions is " + bank.getSum() + "$");
		System.out.println(dr.transactionNumber + " transactions must been done.");
		System.out.println(bank.getTransactionNumber() + " transactions have been done.");
		System.out.println("Work time is: " + workTime + "ms.");

	}
}
