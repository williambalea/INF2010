import java.util.Random;
import java.util.ArrayList;

public class LinearSpacePerfectHashing<AnyType>
{
	static int p = 46337;

	QuadraticSpacePerfectHashing<AnyType>[] data;
	int a, b;

	LinearSpacePerfectHashing()
	{
		a=b=0; data = null;
	}

	LinearSpacePerfectHashing(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	public void SetArray(ArrayList<AnyType> array)
	{
		AllocateMemory(array);
	}

	@SuppressWarnings("unchecked")
	private void AllocateMemory(ArrayList<AnyType> array)
	{
		Random generator = new Random( System.nanoTime() );
		data = new QuadraticSpacePerfectHashing[array.size()];

		if(array == null || array.size() == 0)
		{
			// A completer
			return;
		}
		if(array.size() == 1)
		{
			a = b = 0;

			// A completer
			int positionItems = getKey(array.get(0)) % Size(); //car il n'y a qu'un élément
			data[positionItems] = (QuadraticSpacePerfectHashing<AnyType>) array.get(0);
			return;
		}

		// A completer
		int additionneur = 1;
		ArrayList<AnyType> temp = new ArrayList<AnyType>(additionneur);
		a = generator.nextInt(p);
		b = generator.nextInt(p); // choisir a et b random entre 0 et p pour la formule
		for (int i = 0; i < array.size(); i++) {
			int positionItems = getKey(array.get(i)) % Size();
			if (data[positionItems] == null)
				data[positionItems] =  array.get(i);
			else {
				while ( containsKey(positionItems) == true ) {
					positionItems = additionneur;
					additionneur++;
					
				}
				data[positionItems] = (QuadraticSpacePerfectHashing<AnyType>) array.get(i);
			}
		}
	}

	public int Size()
	{
		if( data == null ) return 0;

		int size = 0;
		for(int i=0; i<data.length; ++i)
		{
			size += (data[i] == null ? 1 : data[i].Size());
		}
		return size;
	}

	public boolean containsKey(int key)
	{
		// A completer
		if (data[key % Size()] == null)
			return false;
		else
			return true;

	}
	
	public int getKey (AnyType x) {
		// A completer
		return ((a * x.hashCode() + b) % p );
	}
	
	public boolean containsValue (AnyType x) {
		// A completer
		if (data[getKey(x) % Size()] == null)
			return false;
		else
			return true;
	}
	
	public void remove (AnyType x) {
		// A completer
		data[getKey(x) % Size()] = null;
		
	}

	public String toString () {
		String result = "";
		
		// A completer
		for (int i = 0; i < Size(); i++) {
			if (data[i] != null) {
				result += "(" + getKey(data[i]) + "," + data[i].toString() + "),";
			}
		}
		
		return result; 
	}

	public void makeEmpty () {
		// A completer
		for(int i = 0; i < data.length; i++) 
			data[i] = null;
   	}
	
}
