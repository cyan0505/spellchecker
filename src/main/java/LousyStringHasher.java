public class LousyStringHasher
implements StringHasher
{
	public int hash(String s)
	{
		int h = 0;
		
		for (int i = 0; i < s.length(); ++i)
		{
			h += s.charAt(i);
		}
		
		return h;
	}
}
