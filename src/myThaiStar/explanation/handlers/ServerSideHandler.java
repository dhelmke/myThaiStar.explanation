package myThaiStar.explanation.handlers;

import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.widgets.Shell;

import myThaiStar.explanation.PlatformUtils.ProjectExplorerUtils;
import myThaiStar.explanation.views.GuideInformation;

public class ServerSideHandler {
	

    @Execute
    public void execute(Shell shell) {
    	String website = "https://github.com/devonfw/my-thai-star/wiki/java-design";
    	if(ProjectExplorerUtils.mtsjIsImported()) {  
    		GuideInformation.show(website);
    	}
    	else {
        
    		MessageDialog.openInformation(shell, "Information", "Please import My Thai Star first.");
    
    	}
    }

}
