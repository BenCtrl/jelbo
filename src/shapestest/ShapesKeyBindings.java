/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package shapestest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.KeyStroke;


public class ShapesKeyBindings {
    public static void addKeyBinding(JComponent component, int keyCode, String id, ActionListener actionListener){         //component= that we're adding the key binding to  : keyCode = the key number code : id = the name of our binding (input and action map combined)
        InputMap IM = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);                                    //Allows us to deal with key bindings as long as window is in focus
        ActionMap AM = component.getActionMap();                                                                   //What actions to do indexed by the input map STRINGS.
        
        IM.put(KeyStroke.getKeyStroke(keyCode, 0, false), id);              //Each Key binding has a unique pairing of KeyID + String Name.
        AM.put(id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        });
    }
    
    public static void addReleaseBinding(JComponent component, int keyCode, String id, ActionListener actionListener){      //component= that we're adding the key binding to  : keyCode = the key number code : id = the name of our binding (input and action map combined)
        InputMap IM = component.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);                                             //Allows us to deal with key bindings as long as window is in focus
        ActionMap AM = component.getActionMap();                                                                            //What actions to do indexed by the input map STRINGS.
        
        IM.put(KeyStroke.getKeyStroke(keyCode, 0, true), id);                                                               //Each Key binding has a unique pairing of KeyID + String Name.
        AM.put(id, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                actionListener.actionPerformed(e);
            }
        });
    }
}
