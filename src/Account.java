import java.util.concurrent.atomic.AtomicInteger;

public class Account
{
	private AtomicInteger amount;

	public Account(int amount)
	{
		this.amount = new AtomicInteger(amount);
	}

	public int getAmount()
	{
		return amount.get();
	}

	public void changeAmount(int delta)
	{
		amount.addAndGet(delta);
	}
}
