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

		if(array == null || array.size() == 0)
		{
			// A completer
			ArrayList<AnyType> nouveau = new ArrayList<AnyType>(array.size() + 1);
			array = nouveau;
			return;
		}
		if(array.size() == 1)
		{
			a = 0;
			b = 0;

			// A completer
			array.get(0) = null;
			return;
		}

		// A completer

	}

	
	
	public String toString () {
		String result = "";
		
		// A completer
		
		
		return result; 
	}

	public void makeEmpty () {
		// A completer
		for(int i = 0; i < items.length; i++) 
			items[i] = null;
   	}
}
