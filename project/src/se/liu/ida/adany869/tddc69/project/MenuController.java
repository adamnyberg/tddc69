package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MenuController implements MenuObserver {

    public MenuController() {

    }

    public void invokeMethod(String methodName, Object[] parameters){
        Class<?> thisClass = this.getClass();

        Class[] classesOfParameters;
        try{
            if (parameters != null){
                classesOfParameters = new Class[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    classesOfParameters[i] = parameters[i].getClass();
                }
            }
            else classesOfParameters = null;
            Method m = thisClass.getMethod(methodName, classesOfParameters);
            m.invoke(this, parameters);
        }
        catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.toString());
        }
    }

    public void update(ActionEvent e){
        invokeMethod(e.getActionCommand(), null);
    }

    //Used through invokeMethod()
    public void Quit(){
        String ObjButtons[] = {"Yes", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to exit?", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);
        if (PromptResult == 0) {
            System.exit(0);
        }
    }

    //Used through invokeMethod()
    public void Restart(){
        String ObjButtons[] = {"Yes", "No"};
        int PromptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to restart game and delete current play?", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                ObjButtons, ObjButtons[1]);
        if (PromptResult == 0) {
            Run.main(null);
        }
    }
}
