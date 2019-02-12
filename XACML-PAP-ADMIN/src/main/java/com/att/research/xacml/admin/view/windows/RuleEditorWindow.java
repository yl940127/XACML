/*
 *
 *          Copyright (c) 2014,2019  AT&T Knowledge Ventures
 *                     SPDX-License-Identifier: MIT
 */
package com.att.research.xacml.admin.view.windows;

import oasis.names.tc.xacml._3_0.core.schema.wd_17.EffectType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.RuleType;

import com.att.research.xacml.admin.XacmlAdminUI;
import com.vaadin.annotations.AutoGenerated;
import com.vaadin.data.Buffered.SourceException;
import com.vaadin.data.Validator.InvalidValueException;
import com.vaadin.event.ShortcutAction.KeyCode;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickEvent;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Label;
import com.vaadin.ui.OptionGroup;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.UI;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;

public class RuleEditorWindow extends Window {

	/*- VaadinEditorProperties={"grid":"RegularGrid,20","showGrid":true,"snapToGrid":true,"snapToObject":true,"movingGuides":false,"snappingDistance":10} */

	@AutoGenerated
	private VerticalLayout mainLayout;
	@AutoGenerated
	private Button buttonSave;
	@AutoGenerated
	private TextArea textAreaDescription;
	@AutoGenerated
	private OptionGroup optionGroupEffect;
	@AutoGenerated
	private Label labelRuleID;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final RuleEditorWindow self = this;
	private final RuleType rule;
	private boolean isSaved = false;
	/**
	 * The constructor should first build the main layout, set the
	 * composition root and then do any custom initialization.
	 *
	 * The constructor will not be automatically regenerated by the
	 * visual editor.
	 */
	public RuleEditorWindow(RuleType rule) {
		buildMainLayout();
		//setCompositionRoot(mainLayout);
		setContent(mainLayout);
		//
		// Save
		//
		this.rule = rule;
		//
		// Close shortcut
		//
		this.setCloseShortcut(KeyCode.ESCAPE);
		//
		// Initialize
		//
		this.initializeLabel();
		this.initializeOption();
		this.initializeText();
		this.initializeButton();
	}
	
	protected void initializeLabel() {
		if (this.rule.getRuleId() == null) {
			this.rule.setRuleId(((XacmlAdminUI)UI.getCurrent()).newRuleID());
		}
		this.labelRuleID.setValue(this.rule.getRuleId());
	}

	protected void initializeOption() {
		this.optionGroupEffect.setRequiredError("You MUST select an effect (Permit or Deny for the rule.");
		this.optionGroupEffect.addItem("Permit");
		this.optionGroupEffect.addItem("Deny");
		if (this.rule.getEffect() == null) {
			this.rule.setEffect(EffectType.PERMIT);
		}
		if (this.rule.getEffect() == EffectType.PERMIT) {
			this.optionGroupEffect.setValue("Permit");
		} else {
			this.optionGroupEffect.setValue("Deny");
		}
	}

	protected void initializeText() {
		this.textAreaDescription.setValue(this.rule.getDescription());
	}

	protected void initializeButton() {
		this.buttonSave.addClickListener(new ClickListener() {
			private static final long serialVersionUID = 1L;

			@Override
			public void buttonClick(ClickEvent event) {
				try {
					//
					// Commit
					//
					self.optionGroupEffect.commit();
					self.textAreaDescription.commit();
					//
					// Save everything
					//
					if (self.optionGroupEffect.getValue() == "Permit") {
						self.rule.setEffect(EffectType.PERMIT);
					} else {
						self.rule.setEffect(EffectType.DENY);
					}
					self.rule.setDescription(self.textAreaDescription.getValue());
					//
					// Set ourselves as saved
					//
					self.isSaved = true;
					//
					// Close the window
					//
					self.close();
				} catch (SourceException | InvalidValueException e) {
					//
					// VAADIN will show the required error message to the user
					//
				}
			}			
		});
	}
	
	public boolean isSaved() {
		return this.isSaved;
	}
	
	public RuleType getRule() {
		return this.rule;
	}

	@AutoGenerated
	private VerticalLayout buildMainLayout() {
		// common part: create layout
		mainLayout = new VerticalLayout();
		mainLayout.setImmediate(false);
		mainLayout.setWidth("-1px");
		mainLayout.setHeight("-1px");
		mainLayout.setMargin(true);
		mainLayout.setSpacing(true);
		
		// top-level component properties
		setWidth("-1px");
		setHeight("-1px");
		
		// labelRuleID
		labelRuleID = new Label();
		labelRuleID.setCaption("Rule ID");
		labelRuleID.setImmediate(false);
		labelRuleID.setWidth("100.0%");
		labelRuleID.setHeight("-1px");
		labelRuleID.setValue("Label");
		mainLayout.addComponent(labelRuleID);
		mainLayout.setExpandRatio(labelRuleID, 1.0f);
		
		// optionGroupEffect
		optionGroupEffect = new OptionGroup();
		optionGroupEffect.setCaption("Choose the effect.");
		optionGroupEffect.setImmediate(false);
		optionGroupEffect.setWidth("-1px");
		optionGroupEffect.setHeight("-1px");
		optionGroupEffect.setInvalidAllowed(false);
		optionGroupEffect.setRequired(true);
		mainLayout.addComponent(optionGroupEffect);
		
		// textAreaDescription
		textAreaDescription = new TextArea();
		textAreaDescription.setCaption("Enter a description for the Rule.");
		textAreaDescription.setImmediate(false);
		textAreaDescription.setWidth("100.0%");
		textAreaDescription.setHeight("-1px");
		textAreaDescription.setNullSettingAllowed(true);
		textAreaDescription.setNullRepresentation("");
		mainLayout.addComponent(textAreaDescription);
		mainLayout.setExpandRatio(textAreaDescription, 1.0f);
		
		// buttonSave
		buttonSave = new Button();
		buttonSave.setCaption("Save");
		buttonSave.setImmediate(true);
		buttonSave.setWidth("-1px");
		buttonSave.setHeight("-1px");
		mainLayout.addComponent(buttonSave);
		mainLayout.setComponentAlignment(buttonSave, new Alignment(48));
		
		return mainLayout;
	}

}
