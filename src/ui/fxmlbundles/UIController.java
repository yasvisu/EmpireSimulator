package ui.fxmlbundles;

import application.ScreenManager;

public abstract class UIController {
    private ScreenManager screenManager;

    public void setScreenManager(ScreenManager screenManager) {
	this.screenManager = screenManager;
    }

    public ScreenManager getScreenManager(){
	return this.screenManager;
    }
}
