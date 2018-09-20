/**
 * Classe ArrayQueue
 * @author Dalyna Pak et William Balea
 * @date : 19 septembre 2018
 */

public class ArrayQueue<AnyType> implements Queue<AnyType>
{
	private int size = 0;		//Nombre d'elements dans la file.
	private int startindex = 0;	//Index du premier element de la file
	private AnyType[] table;
   
	@SuppressWarnings("unchecked")
	public ArrayQueue() 
	{
		size = 0;
		startindex = 0;
		table = (AnyType[]) new Queue[size];
		
	}
	
	//Indique si la file est vide
	public boolean empty() 
	{ 
		return size == 0; 
	}
	
	//Retourne la taille de la file
	public int size() 
	{ 
		return size; 
	}
	
	//Retourne l'element en tete de file
	//Retourne null si la file est vide
	//complexité asymptotique: O(1)
	public AnyType peek()
	{
		if (empty())
			return null;
		else 
			return table[startindex];
		
	}
	
	//Retire l'element en tete de file
	//complexité asymptotique: O(1)
	public void pop() throws EmptyQueueException
	{
		if (empty())
			throw new EmptyQueueException();
		else {
			for (int i = startindex; i < size(); i++)
				table[i]= table[i+1];
			size--;
			
		}		
	}
	
	//Ajoute un element a la fin de la file
	//Double la taille de la file si necessaire (utiliser la fonction resize definie plus bas)
	//complexité asymptotique: O(1) ( O(N) lorsqu'un redimensionnement est necessaire )
	@SuppressWarnings("unchecked")
	public void push(AnyType item)
	{	    
	    if (size == 0)
	    	size = 1;
			AnyType[] tp = (AnyType[]) new Queue[size];
			table = tp;
		if(table.length == size)
			resize(2);		
		table[size - 1] = item;
		size++;
	}
	//Redimensionne la file. La capacite est multipliee par un facteur de resizeFactor.
	//Replace les elements de la file au debut du tableau
	//complexité asymptotique: O(N)
	@SuppressWarnings("unchecked")
	private void resize(int resizeFactor)
	{
		AnyType[] tp = (AnyType[]) new Queue[size*resizeFactor];
		for(int i=0; i < size;i++)
		{
			tp[i] = table[i];
		}
		table = tp;
	}   
}

