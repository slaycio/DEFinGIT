package pl.altoriis.def;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.Rectangle;
import org.eclipse.swt.widgets.MessageBox;
import pl.altoriis.def.mainWindow;


class pTools {

public static Point a2p(Rectangle inArea, Integer xMinus, Integer yMinus){
	
return 	new Point(inArea.width-xMinus,inArea.height-yMinus);
	
}
	
	
public static Boolean yesNo(String title, String question){
		
		int style = SWT.ICON_QUESTION | SWT.YES | SWT.NO;

		MessageBox dialogTemp = new MessageBox(mainWindow.get().shell, style);

		dialogTemp.setText(title);

		dialogTemp.setMessage(question);

		if (dialogTemp.open() == SWT.YES) {
			return true;	
		} else {
			return false;
		}
	}
	
public static void msg(String title, String question){
		
		int style = SWT.ICON_ERROR | SWT.OK ;

		MessageBox dialogTemp = new MessageBox(mainWindow.get().shell, style);

		dialogTemp.setText(title);

		dialogTemp.setMessage(question);
		
		if (dialogTemp.open() == SWT.OK) {
			
		}
	}
	
	
	
}
