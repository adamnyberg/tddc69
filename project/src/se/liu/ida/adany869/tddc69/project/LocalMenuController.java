package se.liu.ida.adany869.tddc69.project;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * This class listens to the menu bar and declares what's going to happen when a menu object is pressed.
 */
public class LocalMenuController implements MenuObserver {

    //A method for running other methods, only getting a string of the action.
    public void invokeMethod(String methodName, Object[] parameters){
        Class<?> thisClass = this.getClass();

        try{
            Class<?>[] classesOfParameters;
            if (parameters != null){
                classesOfParameters = new Class[parameters.length];
                for (int i = 0; i < parameters.length; i++) {
                    classesOfParameters[i] = parameters[i].getClass();
                }
            }
            else classesOfParameters = null;
            Method method = thisClass.getMethod(methodName, classesOfParameters);
            method.invoke(this, parameters);
        }
        catch(NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            System.out.println(e.toString());
        }
    }

    public void update(ActionEvent e){
        invokeMethod(e.getActionCommand().toLowerCase(), null);
    }

    //Used through invokeMethod()
    @SuppressWarnings("UnusedDeclaration")
    public void quit(){
        String[] objButtons = {"Yes", "No"};
        int promptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to exit?", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                objButtons, objButtons[1]);
        if (promptResult == 0) {
            GameController.quit();
        }
    }

    //Used through invokeMethod()
    @SuppressWarnings("UnusedDeclaration")
    public void restart(){
        String[] objButtons = {"Yes", "No"};
        int promptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to restart game and delete current play?", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                objButtons, objButtons[1]);
        if (promptResult == 0) {
            GameController.restart();
        }
    }
}
