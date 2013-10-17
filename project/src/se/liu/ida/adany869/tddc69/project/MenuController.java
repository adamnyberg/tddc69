package se.liu.ida.adany869.tddc69.project;

import se.liu.ida.adany869.tddc69.project.Networking.RiskNetwork;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MenuController implements MenuObserver {

    public void invokeMethod(String methodName, Object[] parameters){
        Class<?> thisClass = this.getClass();

        try{
            Class[] classesOfParameters;
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
    public void quit(){
        String[] objButtons = {"Yes", "No"};
        int promptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to exit?", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                objButtons, objButtons[1]);
        if (promptResult == 0) {
            //RiskNetwork.sendRisk(null);
            System.exit(0);
        }
    }

    //Used through invokeMethod()
    public void restart(){
        String[] objButtons = {"Yes", "No"};
        int promptResult = JOptionPane.showOptionDialog(null,
                "Are you sure you want to restart game and delete current play?", "Rwhisky",
                JOptionPane.DEFAULT_OPTION, JOptionPane.WARNING_MESSAGE, null,
                objButtons, objButtons[1]);
        if (promptResult == 0) {
            Run.main(null);
        }
    }
}
