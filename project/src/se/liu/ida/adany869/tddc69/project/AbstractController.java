package se.liu.ida.adany869.tddc69.project;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
                //TODO: what to be sent with invokemethod and to the listeners?
public abstract class AbstractController {
    public void invokeMethod(String methodName, Object[] parameters){
        AbstractController subClass = this;
        Class aClass = this.getClass();

        Class[] classesOfParameters;
        try{
            if (parameters != null){
                classesOfParameters = new Class[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    classesOfParameters[i] = parameters[i].getClass();
                }
            }
            else classesOfParameters = null;
            //classesOfParameters[0] = parameters.class;
            //Board[] param2 = new Board[1];
            //param2[0] = b;
            Method m = aClass.getMethod(methodName, classesOfParameters);
            m.invoke(this, parameters);
            // m.invoke(TTV, param2) = TTV.convertToText(b);
        }
        catch(NoSuchMethodException e) {
            System.out.println(e.toString());
        }
        catch(InvocationTargetException e) {
            System.out.println(e.toString());
        }
        catch(IllegalAccessException e) {
            System.out.println(e.toString());
        }
    }
}
