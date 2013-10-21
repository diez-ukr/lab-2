public class Consumer implements  Runnable
{
	private final Bank bank;
	private final int transaction[];

	public Consumer(Bank bank, int[] transaction)
	{
		this.bank = bank;
		this.transaction = transaction;
	}

	@Override
	public void run()
	{
		bank.preformTransaction(transaction[0], transaction[1], transaction[2]);
	}
}
