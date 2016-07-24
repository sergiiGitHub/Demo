
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableExample{

	public static void main(String[] args) {
		Collar c = new Collar(3);
		
		Dog d = new Dog(c, 8 , new Tail(true));
		d.setWeight(42);
		System.out.println("before: " + d);
		try {
			FileOutputStream fs = new FileOutputStream("testSer.ser");
			ObjectOutputStream os = new ObjectOutputStream(fs);
			os.writeObject(d);
			os.close();
		} catch (Exception e) { e.printStackTrace(); }

		try {
			FileInputStream fis = new FileInputStream("testSer.ser");
			ObjectInputStream ois = new ObjectInputStream(fis);
			d = (Dog) ois.readObject();
			ois.close();
		} catch (Exception e) { 
			e.printStackTrace();
		}
		System.out.println("after: " + d );
	}
}

class Animal{
	private int weight;
	public Animal() {
		setWeight(0);
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(super.toString())
		.append("; weight: ")
		.append(getWeight())
		.toString();
	}
}


// if super parent are not serializable the constructor will call for parent 
class Dog extends Animal implements Serializable{
	// if not make implements Serializable obtaint java.io.NotSerializableException: Collar
	private Collar collar;
	//if we could make class implement Serializable 
	private transient Tail tail;
	private int dogSize;
	
	int array[] = { 1, 2, 3 }; 

	public Dog(Collar collar, int size, Tail tail) {
		setCollar(collar);
		setDogSize(size);
		this.setTail(tail);
	}
	public Collar getCollar() {
		return collar;
	}
	public void setCollar(Collar theCollar) {
		this.collar = theCollar;
	}
	public int getDogSize() {
		return dogSize;
	}
	public void setDogSize(int dogSize) {
		this.dogSize = dogSize;
	}
	public Tail getTail() {
		return tail;
	}
	public void setTail(Tail tail) {
		this.tail = tail;
	}

	private void writeObject(ObjectOutputStream os) {
		// throws IOException {
		try {
			os.defaultWriteObject();
			os.writeBoolean(getTail().isCut());
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	private void readObject( ObjectInputStream is ){
		try {
			is.defaultReadObject();
			tail = new Tail(is.readBoolean());
		} catch (Exception e) { 
			e.printStackTrace();
		}
	}
	
	@Override
	public String toString() {
		return new StringBuilder( super.toString() )
		.append("; ")
		.append(collar)
		.append("; ")
		.append(tail)
		.toString();
	}
}

/**
 * 
 * @author sergii
 * 
 */
class Tail{
	private boolean isCut;
	public Tail(boolean aIsCut) {
		this.isCut = aIsCut;
	}

	public boolean isCut(){
		return isCut;
	}
	
	@Override
	public String toString() {
		return new StringBuilder(super.toString())
		.append("is cut:")
		.append(isCut())
		.toString();
	}
}

class Collar implements Serializable {
	private int collarSize;
	public Collar(int size) { 
		collarSize = size;
	}

	public int getCollarSize() { 
		return collarSize; 
	}
	
	@Override
	public String toString() {
		return new StringBuilder("Collar size: ")
		.append( collarSize )
		.toString();
	}
}