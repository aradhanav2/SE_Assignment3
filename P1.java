//Prioritizer 1
import java.lang.reflect.Array;
import java.util.Comparator;

class GlobalVals<T>
{
public int maxSize = 10;
public T[] main_arr;
public T[] temp_arr;
public boolean phase = true;

@SuppressWarnings("unchecked")
//CONSTRUCTOR
public GlobalVals(Class<T> cl, int size)
{
main_arr = (T[]) Array.newInstance(cl, size);
temp_arr = (T[]) Array.newInstance(cl, size);
maxSize = size;
}

}

interface Prioritizer<T>
{
public void insert(T t);
public T removeNextInOrder();
public T removeAny();
int compareTo(Object o);
public void changePhase();
public int size(T c[]);
public void check();
}

public class P1<T> extends GlobalVals<T> implements Prioritizer<T>, Comparator<T>
{
//CONSTRUCTOR
public P1(Class<T> cl, int size)
{
super(cl, size);
}

int index = -1;
int index1 = -1;

public void insert(T a)
{
check();
if (index < maxSize)
{
main_arr[++index] = a;
temp_arr[++index1]=a;
}
}


public T removeNextInOrder() {
changePhase();
System.out.println("In Removal Phase in order");
T x=temp_arr[0];
for (int i = 0; i < index; i++) {
temp_arr[i] = temp_arr[i + 1];
}
for(int i=0;i<index;i++)
{
if(x==main_arr[i])
{
T y=main_arr[i];
for(int j=i;j<index;j++) {
main_arr[j]=main_arr[j+1];
}
}
}
index--;
index1--;
System.out.println("removed element:"+x);
return x;
}

public T removeAny() {
changePhase();
System.out.println("In Removal Phase of any order");
int z = (int) ((Math.random() * ((index - 0) + 1)) + 0);
T x = main_arr[z];
for (int i = z; i < index; i++) {
main_arr[i] = main_arr[i + 1];
}
index--;
System.out.println("removed element:"+x);
return x;
}

public void changePhase()
{
if (phase == true)
{
phase = false;
sort(temp_arr);
}
}

public void display()
{
System.out.println("array is:");
for (int i = 0; i <= index; i++)
{
System.out.println(main_arr[i]);
}
}


public int size(T c[])
{
return c.length;
}

public void check()
{
if (phase == false)
{
phase=true;
System.out.println("status: changed to insertion phase to insert");
}
}


@Override
public int compare(T o1, T o2)
{
Class c=o1.getClass();
if(c.equals(Integer.class))
{
int obj1=(int)o1;
int obj2=(int)o2;

if(obj1>obj2)
return 1;
else if(obj1==obj2)
return 0;
else
return -1;
}
else if(c.equals(Double.class))
{
Double obj1=(Double)o1;
Double obj2=(Double)o2;

if(obj1>obj2)
return 1;
else if(obj1==obj2)
return 0;
else
return -1;
}
else
{
String obj1=o1.toString();
String obj2=o2.toString();
return obj1.compareTo(obj2);
}
}

@Override
public int compareTo(Object o) {
// TODO Auto-generated method stub
return 0;
}
public void sort(T a[])
{
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
P1 p = new P1(Object.class, 20);
p.insert(10);
p.insert(21);
p.insert(100);
p.insert(2);
p.insert(4);
p.insert(8);
p.insert(101);
p.insert(51);
p.removeNextInOrder();
p.removeNextInOrder();
p.removeAny();
p.insert(12);
p.display();
}
}