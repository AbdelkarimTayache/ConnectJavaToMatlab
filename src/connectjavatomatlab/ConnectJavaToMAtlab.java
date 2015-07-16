/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package connectjavatomatlab;

import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;
import matlabcontrol.MatlabConnectionException;
import matlabcontrol.MatlabInvocationException;
import matlabcontrol.MatlabProxy;
import matlabcontrol.MatlabProxyFactory;
import matlabcontrol.MatlabProxyFactoryOptions;
import matlabcontrol.extensions.MatlabNumericArray;
import matlabcontrol.extensions.MatlabTypeConverter;

/**
 *
 * @author clt
 */
public class ConnectJavaToMAtlab {

    private static MatlabProxyFactory factory;
    private static MatlabProxy proxy;
    private static MatlabProxyFactoryOptions options;
    private static double result;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws MatlabInvocationException {
        try {
            // TODO code application logic here
              options = new MatlabProxyFactoryOptions.Builder().
                    setUsePreviouslyControlledSession(true).build();
              factory = new MatlabProxyFactory(options);
              proxy = factory.getProxy();
              
              
              
              
              //Test 2 
                  //Create a 4x3x2 array filled with random values
    proxy.eval("array = randn(4,3,2);c='xavier'");

    //Print a value of the array into the MATLAB Command Window
    proxy.eval("disp(['entry: ' num2str(array(3, 2, 1))])");

    //Get the array from MATLAB
    MatlabTypeConverter processor = new MatlabTypeConverter(proxy);
    MatlabNumericArray array = processor.getNumericArray("array");
    MatlabNumericArray d = processor.getNumericArray("d");
    Object g = proxy.getVariable("c");
            System.out.println("c = "+Arrays.deepToString((String[]) g));
    
    //Print out the same entry, using Java's 0-based indexing
    System.out.println("entry array: " +  Arrays.deepToString(array.getRealArray3D()));
    System.out.println("entry d : " + Arrays.deepToString(d.getRealArray2D()));
    
    //Convert to a Java array and print the same value again    
    double[][][] javaArray = array.getRealArray3D();
    System.out.println("entry: " + javaArray[2][1][0]);
    
        //By specifying 3 return arguments, returns as String arrays the loaded M-files, MEX files, and Java classes
    Object[] inmem = proxy.returningFeval("myfunc", 1,"hello world");
    Object[] f = proxy.returningEval("'helloe' ",1);
    System.out.println("Java classes loaded:");
    
    System.out.println(Arrays.toString( f )); 
        
    //Retrieve MATLAB's release date by providing the -date argument
    Object[] releaseDate = proxy.returningFeval("version", 1, "-date");
    System.out.println("MATLAB Release Date: " + releaseDate[0]);
             // Test 1
            // execute the function even if inside a m file
           // proxy.eval("who");
              // execute the code directly 
//            String f = "display('hello')";
//            Object[] d = proxy.returningEval(f, 1);
//            Object arg = d[0];
//            for(int i=0;i<((double[]) arg).length;i++){
//             result = ((double[]) arg)[i];
//            
//                     
//                     
//            System.err.println(result+""+"\n");}
           // disconnecting the proxy 
            System.exit(0);
        } catch (MatlabConnectionException ex) {   
            System.out.println("érror");
            System.exit(0);

            Logger.getLogger(ConnectJavaToMAtlab.class.getName()).log(Level.SEVERE, null, ex);

        }
    }
}
