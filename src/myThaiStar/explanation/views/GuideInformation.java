package myThaiStar.explanation.views;




import javax.annotation.PostConstruct;

import org.eclipse.e4.ui.di.Focus;
import org.eclipse.swt.SWT;
import org.eclipse.swt.browser.Browser;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Composite;


public class GuideInformation {
   
    private static  Browser browser;
   
    @PostConstruct
    public void createControls(Composite parent) {
        parent.setLayout(new GridLayout(2, false));
        browser = new Browser(parent, SWT.NONE);  
        try {
            browser.setUrl("https://github.com/devonfw");
        } catch (Exception e1) {
            e1.printStackTrace();
        }       
        browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));       
    }

    
    
    public static void show(String selection) {
    	try { 
            browser.setUrl(selection);

        } catch (Exception e1) {
            e1.printStackTrace();
        }        
        browser.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, true, 2, 1));      
    }
    
    @Focus
    public void onFocus() {
        browser.setFocus();
    }

}
