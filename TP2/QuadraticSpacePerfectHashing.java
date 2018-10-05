import java.util.ArrayList;
import java.util.Random;

public class QuadraticSpacePerfectHashing<AnyType> 
{
	static int p = 46337;

	int a, b;
	AnyType[] items;

	QuadraticSpacePerfectHashing()
	{
		a=b=0; items = null;
	}

	QuadraticSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public int Size()
	{
		if( items == null ) return 0;

		return items.length;
	}

	public boolean containsKey(int key)
	{
		// A completer
		if (items[key % Size()] == null)
			return false;
		else
			return true;

	}

	public boolean containsValue(AnyType x )
	{
		// A completer
		if (items[getKey(x)] == null)
			return false;
		else
			return true;


	}

	public void remove (AnyType x) {
		// A completer
		items[getKey(x)] = null;
	}

	public int getKey (AnyType x) {
		// A completer
		return ((a * x.hashCode() + b) % p ) % Size();
		
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );
		int newSize = (int) Math.pow(array.size(), 2);
		items = (AnyType[]) new Object[newSize];

		if(array == null || array.size() == 0)
		{
			// A completer
			return;
		}
		if(array.size() == 1)
		{
			a = 0;
			b = 0;

			// A completer
			int positionItems = getKey(array.get(0)); //car il n'y a qu'un élément
			items[positionItems] = array.get(0);
			return;
		}
		
		// A completer
		a = generator.nextInt(p);
		b = generator.nextInt(p); // choisir a et b random entre 0 et p pour la formule
		int additionneur = 1;
		for (int i = 0; i < array.size(); i++) {
			int positionItems = getKey(array.get(i));
			if (items[positionItems] == null)
				items[positionItems] = array.get(i);
			else {
				while ( containsKey(positionItems) == true ) {
					positionItems = (positionItems + ((int) Math.pow(additionneur, 2)));
					additionneur++;
					
				}
				items[positionItems] = array.get(i);
			}
		}

	}

	
	
	public String toString () {
		String result = "";
		// A completer
		for (int i = 0; i < Size(); i++) {
			if (items[i] != null) {
				result += "(" + getKey(items[i]) + "," + items[i].toString() + "),";
			}
		}

		return result; 
	}

	public void makeEmpty () {
		// A completer
		for(int i = 0; i < items.length; i++) 
			items[i] = null;
   	}
}
