
import java.io.*;
import java.lang.*;
import java.lang.reflect.Array;
import java.util.Comparator;

class Vars<T>
{
	public int arrSize = 10;
	public T[] m;
	public boolean flag = true;
	public T[]n;


	@SuppressWarnings("unchecked")
		public Vars(Class<T> cl, int size)
			{
				m = (T[]) Array.newInstance(cl, size);
				n = (T[]) Array.newInstance(cl, size);
				arrSize = size;
			}

}

interface Prioritizer3<T>
 {
    public void insert(T t);
    public void changePhase();
    public int size(T c[]);
    public T removeNextInOrder();
    public T removeAny();
    int compareTo(Object o);
    public void check();
 }

public class Aradhana<T> extends Vars<T> implements Prioritizer3<T>, Comparator<T>
{

   public Aradhana(Class<T> cl, int size)
   {
	   	super(cl, size);
   }

   int index = -1;
   int index1 = -1;

   public void insert(T a)//insertion
   {
	//System.out.println("in insert method");
	   check();
	   if (index < arrSize)
	   	{
           m[++index] = a;
           n[++index1]=a;
        }
   	}

   public void changePhase()//to check if its in insertion phase or not and changing accordingly
   {
   	//System.out.println("in change phase");
      if (flag == true)
        {
          flag = false;
        }
   }
   
   public int size(T c[])//getting the size
    {
       return c.length;
    }


    public T removeAny() // removing in random order
    {
	 //System.out.println("In remove any method before change phase");
      changePhase();
      System.out.println("Removal Phase of any order");
      int K = (int) ((Math.random() * ((index - 0) + 1)) + 0);
      T x = m[K];
       for (int i = K; i < index; i++) 
        {
                m[i] = m[i + 1];
        }
       index--;
       System.out.println(x);
       return x;
    }

    public T removeNextInOrder() //remove only after sorting
    {
	//System.out.println("in removenextinorder before change phase");
      changePhase();
      sort(n);
      System.out.println("In Removal Phase in order");
      T x=n[0];
      for (int i = 0; i < index; i++) {
      n[i] = n[i + 1];
      }
      for(int i=0;i<index;i++)
       {
          if(x==m[i])
            {
               T y=m[i];
                for(int j=i;j<index;j++)
                {
                  m[j]=m[j+1];
                }
             }
       }
      index--;
      index1--;
      System.out.println(x);
      return x;
   }

  public void display()//displaying 
      {
	    //System.out.println("displaying");
         for (int i = 0; i <= index; i++)
          {
            System.out.println(m[i]);
          }
       }




 public void check() 
   {
     if (flag == true)
      {
         System.out.println(" insertion phase entered");
      }
     else
      {
        flag=true;
        System.out.println("  changed to insertion phase");
      }
    }


@Override
  public int compare(T o1, T o2) 
    {
       Class c=o1.getClass();
       if(c.equals(Integer.class))
          {
           int object1=(int)o1;
           int object2=(int)o2;

            if(object1>object2)
            return 1;
            else if(object1==object2)
            return 0;
            else
            return -1;
          }
       else if(c.equals(Double.class))
        {
          Double object1=(Double)o1;
          Double object2=(Double)o2;

                  if(object1>object2)
                    return 1;
                  else if(object1==object2)
                    return 0;
                  else
                   return -1;
         }
       else
        {
           String object1=o1.toString();
           String object2=o2.toString();
           return object1.compareTo(object2);
        }
     }

@Override
public int compareTo(Object o) {
// TODO Auto-generated method stub
return 0;
}
public void sort(T a[])
{
	//System.out.println("sorting");
      for (int i = 0; i <= index; i++)
        {
           for (int j = i + 1; j <= index; j++)
                 {
                     if (compare(a[i], a[j])>0)
                       {
                          T temp = a[i];
                          a[i] = a[j];
                          a[j] = temp;
                       }
                  }
         }
 }



public static void main(String[] args)
{
   Aradhana p = new Aradhana(Object.class, 10);
   //p.check();
   //p.insert(67);
   //p.insert(1);
   //p.insert(34);
   //p.insert(6);
   //p.insert(0);
   //p.insert(-100);
    //p.insert("clemson is a good university");
    p.insert("java generics is being learnt");
    p.insert("my project is implementing or not");
    p.insert("will it be executed?");
   
    p.display();
    //System.out.println("---------------------------");
    p.removeAny();
    //System.out.println("---------------------------");
    p.display();
    //System.out.println("---------------------------");
    p.removeNextInOrder();
    //System.out.println("---------------------------");
    p.removeNextInOrder();
    //System.out.println("---------------------------");
    p.removeAny();
    //System.out.println("---------------------------");
    p.display();
    //System.out.println("---------------------------");
 }
}
