package myThaiStar.explanation.handlers;

import java.util.ArrayList;
import java.util.List;


import org.eclipse.core.resources.IFile;
import org.eclipse.e4.core.di.annotations.Execute;
import org.eclipse.jface.dialogs.MessageDialog;

import org.eclipse.swt.widgets.Shell;


import myThaiStar.explanation.PlatformUtils.ProjectExplorerUtils;
import myThaiStar.explanation.views.GuideInformation;

public class DependencyInjectionHandler {
    
	
	
    @Execute
    public void execute(Shell shell) {
    	
    	boolean fileOpenSuccess = false;
    	String filename = "DishmanagementImpl";
    	String website ="https://github.com/devonfw/devon4j/blob/develop/documentation/guide-dependency-injection.asciidoc";
    	if(ProjectExplorerUtils.mtsjIsImported()) {    	
    		List<IFile> result=new ArrayList<>();
    		result=ProjectExplorerUtils.getAllProjectFiles(filename);
    		fileOpenSuccess=ProjectExplorerUtils.openFileInEditor(result);        
    		GuideInformation.show(website);
    		if(!fileOpenSuccess) {
    			MessageDialog.openInformation(shell,"Information", "Could not open file "+filename+".");
    		}
    	}
    	else {
    		 MessageDialog.openInformation(shell,"Information", "Please import My Thai Star.");
    	}   	
    }
    	
    	
}
