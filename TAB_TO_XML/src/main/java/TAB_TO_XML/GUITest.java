package TAB_TO_XML;

import org.junit.jupiter.api.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import static org.junit.Assert.assertNotEquals;

import static org.junit.jupiter.api.Assertions.*;


import java.io.File;

public class GUITest {

	
	 @Test
	    public void testgetExtension()  {
	       	
	       assertEquals("txt",GUI.getExtension("CaprichoArabe.txt"));
	       
	    }
	 
	 	 
	 @Test
	    public void testaccept()  {
	    
		 	
		File file =new File("/Tab_TO_XML/src/main/java/Capricho Arabe.txt");
		
		Boolean exp,auc;
		
		auc=GUI.accept(file);
		
		 
		 assertEquals(true,auc);
		 
	       
	    }

	 
	 @Test
	    public void testaccept2()  {
	    
		 	
		File file =new File("/Tab_TO_XML/src/main/java/lab6_1.txt");
		
		Boolean exp,auc;
		
		auc=GUI.accept(file);
		
		 
		 assertEquals(true,auc);
		 
	       
	    }
	 
	 
}
