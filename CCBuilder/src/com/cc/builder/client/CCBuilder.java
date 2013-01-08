package com.cc.builder.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;

/**
 * Entry point classes define <code>onModuleLoad()</code>.
 */
public class CCBuilder implements EntryPoint {

	/**
	 * This is the entry point method.
	 */
	public void onModuleLoad() {
		final Label errorLabel = new Label();
		GroupAdmin groupAdmin = new GroupAdmin("Go-Together");
		RootPanel.get("groupAdminContainer").add(groupAdmin);
		RootPanel.get("errorLabelContainer").add(errorLabel);
	}
}
