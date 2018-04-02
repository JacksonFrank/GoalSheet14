import java.io.FileNotFoundException;
import java.util.*;

public interface Callback {
	public void getSets(Set<String> stringSet, String target) throws FileNotFoundException;
}
