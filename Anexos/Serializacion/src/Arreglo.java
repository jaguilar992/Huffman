import java.io.Serializable;
import java.util.Arrays;

public class Arreglo implements Serializable
{
	private Alumno [] alumnos = new Alumno[10];

	public int largo()
	{
		return alumnos.length;
	}

	public Alumno getAlumnos(int pos)
	{
		return alumnos[pos];
	}

	public void setAlumnos(Alumno x, int pos)
	{
		alumnos[pos] = x;
	}

	@Override
	public String toString() {
		return "Arreglo [alumnos=" + Arrays.toString(alumnos) + "]";
	}
	
	
}