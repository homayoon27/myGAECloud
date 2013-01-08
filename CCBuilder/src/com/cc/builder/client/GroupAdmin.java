package com.cc.builder.client;

import com.cc.builder.shared.UtilFunctions;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Grid;
import com.google.gwt.user.client.ui.HasText;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;

public class GroupAdmin extends Composite implements HasText {

	private static GroupAdminUiBinder uiBinder = GWT
			.create(GroupAdminUiBinder.class);

	interface GroupAdminUiBinder extends UiBinder<Widget, GroupAdmin> {
	}

	public GroupAdmin() {
		initWidget(uiBinder.createAndBindUi(this));
	}

	@UiField
	Grid grid;

	@UiField
	Label gnameLabel;
	@UiField
	Label gdescLabel;

	@UiField
	TextBox gnameTBox;
	@UiField
	TextBox gdescTBox;
	
	@UiField
	Button button;

	public GroupAdmin(String firstName) {
		initWidget(uiBinder.createAndBindUi(this));
		button.setText("Create");
		grid.setTitle("Group Admin");
		grid.setBorderWidth(1);
		grid.setSize("300px", "50px");
		grid.setHTML(1, 0, "homayoon");
		grid.setHTML(1, 1, UtilFunctions.currentDateStr());
		
		gnameLabel.setText("Name: ");
		gdescLabel.setText("Description: ");
	}

	@UiHandler("button")
	void onClick(ClickEvent e) {
		Window.alert("Hello!");
	}

	public void setText(String text) {
		button.setText(text);
	}

	public String getText() {
		return button.getText();
	}

}
