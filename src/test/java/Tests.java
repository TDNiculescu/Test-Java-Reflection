import com.javaReflection.Goat;
import org.junit.Test;
import static junit.framework.TestCase.assertEquals;

public class Tests {

    @Test
    public void testClassName() {
        Object goat = new Goat( "goat" );
        Class<?> clazz = goat.getClass();

        assertEquals("Goat", clazz.getSimpleName());
        assertEquals("com.javaReflection.Goat", clazz.getCanonicalName());
        assertEquals("com.javaReflection.Goat", clazz.getName());
    }

}
