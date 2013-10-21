import java.util.LinkedList;
import java.util.concurrent.atomic.AtomicInteger;

public class Bank
{
	private LinkedList<Account> accounts;
	private AtomicInteger transactionNumber;

	public Bank()
	{
		this.accounts = new LinkedList<Account>();
		transactionNumber = new AtomicInteger(0);
	}

	public void addAccount(Account account)
	{
		accounts.push(account);
	}

	public void addAccount(int amount)
	{
		accounts.push(new Account(amount));
	}

	public int getSum()
	{
		int sum = 0;
		for (Account account : accounts)
		{
			sum += account.getAmount();
		}
		return (sum);
	}

	public void preformTransaction(int srcIndex, int dstIndex, int delta)
	{
		accounts.get(srcIndex).changeAmount(-delta);
		accounts.get(dstIndex).changeAmount(delta);
		transactionNumber.addAndGet(1);
	}

	public int getTransactionNumber()
	{
		return this.transactionNumber.get();
	}
}
