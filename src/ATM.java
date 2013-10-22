import java.util.Stack;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.RejectedExecutionException;

class ATM implements Runnable
{
	private final Stack<int[]> transactions;
	private final ExecutorService consumer;
	private final Bank bank;


	public ATM(int transactions[][], ExecutorService c, Bank bank)
	{
		this.consumer = c;
		this.bank = bank;
		this.transactions = new Stack<int[]>();
		for (int transaction[] : transactions)
		{
			this.transactions.push(transaction);
		}
	}

	public ATM(ExecutorService c, Bank bank)
	{
		this.consumer = c;
		this.bank = bank;
		this.transactions = new Stack<int[]>();
	}

	public void addTransaction(int transaction[])
	{
		this.transactions.push(transaction);
	}

	@Override
	public void run()
	{
		while (!transactions.empty())
		{
			try
			{
				consumer.submit(new Consumer(bank, transactions.pop()));
			}
			catch (RejectedExecutionException e)
			{
				System.out.println("Transacion have not been preformed: " + e.getMessage());
			}
		}
	}
}
