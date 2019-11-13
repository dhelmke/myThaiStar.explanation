package myThaiStar.explanation.PlatformUtils;

import java.util.ArrayList;
import java.util.List;



import org.eclipse.core.filesystem.EFS;
import org.eclipse.core.filesystem.IFileStore;
import org.eclipse.core.resources.IContainer;
import org.eclipse.core.resources.IFile;

import org.eclipse.core.resources.IProject;
import org.eclipse.core.resources.IResource;
import org.eclipse.core.resources.IWorkspace;
import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.CoreException;

import org.eclipse.jface.viewers.ISelectionProvider;
import org.eclipse.jface.viewers.StructuredSelection;

import org.eclipse.ui.IViewPart;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.PartInitException;
import org.eclipse.ui.PlatformUI;
import org.eclipse.ui.ide.IDE;

public class ProjectExplorerUtils {
	
	
	
	
	@SuppressWarnings("static-access")
	public static IProject[] getProjects() {		
		IWorkspace workspace = ResourcesPlugin.getPlugin().getWorkspace();        
		IProject [] projects = workspace.getRoot().getProjects();
		return projects;
	}
	
    private static List<IFile> findAllProjectFiles(IContainer container) throws CoreException {
        IResource[] members = container.members();
        List<IFile> list = new ArrayList<>();
        for (IResource member : members) {
            if (member instanceof IContainer) {
                IContainer c = (IContainer) member;
                list.addAll(findAllProjectFiles(c));
            } else if (member instanceof IFile) {
                list.add((IFile) member);
            }
        }
        return list;
    }
		
	@SuppressWarnings("static-access")
	public static List<IFile> getAllProjectFiles(String fileName){			
		IWorkspace workspace = ResourcesPlugin.getPlugin().getWorkspace();
        List<IFile> projectfiles=null;
		IProject [] projects = workspace.getRoot().getProjects();
		List<IFile> foundfiles= new ArrayList<>();
        for (IProject project : projects) {
            try {
                projectfiles = findAllProjectFiles(project);
                for (IResource resource : projectfiles) {
                    if (resource.getName().contains(fileName)){
                    	System.out.println(fileName+resource.getName());
                    	foundfiles.add((IFile)resource);                    
                    }
                }
            } catch (CoreException e) {
                e.printStackTrace();
            }
        }       
        return foundfiles;
	}
	
	public static boolean mtsjIsImported() {
		IProject[] allProjects= ProjectExplorerUtils.getProjects();
		for (IProject project:allProjects) {
			if(project.getName().contains("mtsj")) {
				return true;
			}
		}		
		return false;		
	}

	public static boolean openFileInEditor(List<IFile> filesToOpen) {
		for(IFile filetoOpen:filesToOpen) {	        
	           IFileStore fileStore = EFS.getLocalFileSystem().getStore(filetoOpen.getLocationURI());
	           IWorkbenchPage page = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage();
	           try {
	               IDE.openEditorOnFileStore( page, fileStore );	               
				   IViewPart viewPart = page.findView("org.eclipse.ui.navigator.ProjectExplorer");
				   ISelectionProvider selProvider = viewPart.getSite().getSelectionProvider();
				   selProvider.setSelection(new StructuredSelection(filetoOpen));				
							
	               return true;
	           } catch ( PartInitException e ) {
	               
	           }
	        }
		return false;
	}

}
