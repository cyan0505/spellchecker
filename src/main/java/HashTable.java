import java.util.ArrayList;


public class HashTable
{
	private int numberOfBuckets;
	private ArrayList[] table;
	private StringHasher hasher;


	public HashTable(int tableSize, StringHasher hasher) {
	    this.hasher = hasher;
        this.numberOfBuckets = tableSize;
        this.table = new ArrayList[numberOfBuckets];
        for (int i = 0; i < numberOfBuckets; i++) {
            table[i] = new ArrayList<String>();
        }
    }

	public void add(String s)
	{
        int index = getIndex(s);
        table[index].add(s);
    }

	public boolean lookup(String s)
	{

        int index = getIndex(s);

        for(int i = 0; i < table[index].size(); i++) {
            if(table[index].get(i).equals(s))
                return true;
        }
        return false;
	}

	public void remove(String s)
	{
        int index = getIndex(s);

        for(int i = 0; i < table[index].size(); i++) {
            if(table[index].get(i).equals(s))
                 table[index].remove(i);
        }
	}

	public int getIndex(String s){
        int hashCode = hasher.hash(s);
        int index = hashCode % numberOfBuckets;
        if(index < 0){
            index *= -1;
        }
        return index;
    }

    public ArrayList[] getTable() {
        return table;
    }
}
