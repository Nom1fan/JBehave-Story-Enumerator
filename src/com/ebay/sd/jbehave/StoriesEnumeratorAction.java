package com.ebay.sd.jbehave;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.ui.Messages;

import java.io.IOException;

/**
 * Created by mmerhav on 20/3/2017.
 */
public class StoriesEnumeratorAction extends AnAction {

    @Override
    public void actionPerformed(AnActionEvent e) {
        System.out.println(e);
        String startFrom = Messages.showInputDialog("Give enumerator start value", "Title", null, "1", null);
        System.out.println(startFrom);
        StoriesEnumerator storiesEnumerator = new StoriesEnumeratorImpl();
        try {
            storiesEnumerator.enumerateStory(((EditorImpl)e.getDataContext().getData("editor")).getVirtualFile().getCanonicalPath(), Integer.valueOf(startFrom));
        } catch (IOException e1) {
            e1.printStackTrace();
        }
        // TODO: insert action logic here
    }
}
