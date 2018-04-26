package com.ebay.sd.jbehave;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.editor.impl.EditorImpl;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.vfs.VirtualFile;

import java.io.IOException;
import java.util.Objects;

/**
 * Created by mmerhav on 20/3/2017.
 */
public class StoriesEnumeratorAction extends AnAction {

	@Override
	public void actionPerformed(AnActionEvent e) {
		System.out.println(e);
		String startFrom = Messages.showInputDialog("Give enumerator start value", "Title", null, "1", null);

		if (startFrom != null) {

			EditorImpl editor = (EditorImpl) e.getDataContext().getData("editor");

			Objects.requireNonNull(editor, startFrom);

			VirtualFile virtualFile = editor.getVirtualFile();

			System.out.println(startFrom);
			StoriesEnumerator storiesEnumerator = new StoriesEnumeratorImpl();
			try {
				storiesEnumerator.enumerateStory(virtualFile.getCanonicalPath(), Integer.valueOf(startFrom));
			} catch (IOException e1) {
				e1.printStackTrace();
			}
		}
	}
}
