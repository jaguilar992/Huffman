import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class Serializacion
{
	public static void main(String[] args) throws IOException
	{
		ObjectOutputStream out = null;
		ObjectInputStream in = null;

		try
		{
			FileOutputStream fos = new FileOutputStream("Archivo.bin");
			FileInputStream fis = new FileInputStream("Archivo.bin");

			out = new ObjectOutputStream(fos);
			in = new ObjectInputStream(fis);

			Arreglo arreglo = new Arreglo();

			Alumno o1 = new Alumno();

			o1.setId(111);
			o1.setNombre("Rony Rodriguez");
			o1.setCarrera("Ingenieria en Sistemas");
			o1.setPromedio(89.78f);

			arreglo.setAlumnos(o1,0);
			arreglo.setAlumnos(o1,1);
			arreglo.setAlumnos(o1,2);
			arreglo.setAlumnos(o1,3);

			System.out.println("Objetos almacenadios en arreglo");

			for(int i=0; i<arreglo.largo(); i++)
			{
				System.out.println(arreglo.getAlumnos(i));
			}

			Arreglo o2;

			out.writeObject(arreglo);

			o2 = (Arreglo)in.readObject();

			System.out.println("Aluimnos almacenados en o2");

			for(int i=0; i<o2.largo(); i++)
			{
				System.out.println(o2.getAlumnos(i));
			}

		}
		catch(IOException e)
		{
			System.out.println("Error en lectura/escritura del archivo!!!");
		}
		catch(ClassNotFoundException e)
		{
			System.out.println("La definicion de la clase no existe!!!");
		}
		finally
		{
			out.close();
			in.close();
		}
	}
}