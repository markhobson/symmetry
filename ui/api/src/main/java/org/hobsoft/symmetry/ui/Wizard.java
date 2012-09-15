/*
 * $HeadURL: https://svn.iizuka.co.uk/components/kozo/tags/0.6.0-beta-1/ui/api/src/main/java/uk/co/iizuka/kozo/ui/Wizard.java $
 * 
 * (c) 2005 IIZUKA Software Technologies Ltd.  All rights reserved.
 */
package org.hobsoft.symmetry.ui;

import java.beans.EventSetDescriptor;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import org.hobsoft.symmetry.support.bean.EventSets;
import org.hobsoft.symmetry.ui.event.ActionEvent;
import org.hobsoft.symmetry.ui.event.WizardEvent;
import org.hobsoft.symmetry.ui.event.WizardListener;

/**
 * 
 * 
 * @author Mark Hobson
 */
public class Wizard extends VBox
{
	// TODO: allow easy visual customisation - titles, custom buttons etc
	// TODO: add page validation to limit next/finish
	// TODO: make Wizard extend OptionBox
	// TODO: add i18n for actions
	
	// constants --------------------------------------------------------------
	
	private static final EventSetDescriptor WIZARD_CANCELLED_EVENT = EventSets.getDescriptor(Wizard.class, "wizard",
		"wizardCancelled");
	
	private static final EventSetDescriptor WIZARD_FINISHED_EVENT = EventSets.getDescriptor(Wizard.class, "wizard",
		"wizardFinished");
	
	// fields -----------------------------------------------------------------
	
	private Deck pageDeck;
	
	private Button cancelButton;
	
	private Button backButton;
	
	private Button nextButton;
	
	private Button finishButton;
	
	// types ------------------------------------------------------------------
	
	private class DeckPropertyChangeListener implements PropertyChangeListener
	{
		// PropertyChangeListener methods -----------------------------------------
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void propertyChange(PropertyChangeEvent event)
		{
			Component page = getPage();
			
			if (page != null)
			{
				backButton.setEnabled(getPreviousPage(page) != null);
				nextButton.setEnabled(getNextPage(page) != null);
			}
		}
	}
	
	private class CancelAction extends DefaultAction
	{
		// constructors -----------------------------------------------------------
		
		public CancelAction()
		{
			setName("Cancel");
			setDescription("Cancel this wizard");
			setMnemonic('C');
		}
		
		// ActionListener methods ------------------------------------------------- 
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			doCancel();
		}
	}
	
	private class BackAction extends DefaultAction
	{
		// constructors -----------------------------------------------------------
		
		public BackAction()
		{
			setName("< Back");
			setDescription("Go to previous page");
			setMnemonic('B');
		}
		
		// ActionListener methods ------------------------------------------------- 
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			doBack();
		}
	}
	
	private class NextAction extends DefaultAction
	{
		// constructors -----------------------------------------------------------
		
		public NextAction()
		{
			setName("Next >");
			setDescription("Go to next page");
			setMnemonic('N');
		}
		
		// ActionListener methods ------------------------------------------------- 
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			doNext();
		}
	}
	
	private class FinishAction extends DefaultAction
	{
		// constructors -----------------------------------------------------------
		
		public FinishAction()
		{
			setName("Finish");
			setDescription("Finish this wizard");
			setMnemonic('F');
		}
		
		// ActionListener methods ------------------------------------------------- 
		
		/**
		 * {@inheritDoc}
		 */
		@Override
		public void actionPerformed(ActionEvent event)
		{
			doFinish();
		}
	}
	
	// constructors -----------------------------------------------------------
	
	public Wizard()
	{
		pageDeck = new Deck();
		DeckPropertyChangeListener deckPropertyChangeListener = new DeckPropertyChangeListener();
		pageDeck.addPropertyChangeListener(Box.COMPONENTS_PROPERTY, deckPropertyChangeListener);
		pageDeck.addPropertyChangeListener(Deck.SELECTED_INDEX_PROPERTY, deckPropertyChangeListener);
		add(pageDeck);
		
		cancelButton = new Button(new CancelAction());
		cancelButton.setTransient(true);
		
		backButton = new Button(new BackAction());
		backButton.setTransient(true);
		
		nextButton = new Button(new NextAction());
		nextButton.setTransient(true);
		
		finishButton = new Button(new FinishAction());
		finishButton.setTransient(true);
		
		Box footer = new HBox();
		footer.add(cancelButton);
		footer.add(backButton);
		footer.add(nextButton);
		footer.add(finishButton);
		add(footer);
	}
	
	// public methods ---------------------------------------------------------
	
	public void addWizardListener(WizardListener listener)
	{
		addListener(WizardListener.class, listener);
	}
	
	public void removeWizardListener(WizardListener listener)
	{
		removeListener(WizardListener.class, listener);
	}
	
	public WizardListener[] getWizardListeners()
	{
		return getListeners(WizardListener.class);
	}
	
	public void addPage(Component page)
	{
		pageDeck.add(page);
	}
	
	public void addPages(Component... pages)
	{
		for (Component page : pages)
		{
			addPage(page);
		}
	}
	
	public void removePage(Component page)
	{
		pageDeck.remove(page);
	}
	
	public void removePages(Component... pages)
	{
		for (Component page : pages)
		{
			removePage(page);
		}
	}
	
	public Component getPage()
	{
		return pageDeck.getSelectedComponent();
	}
	
	public int getPageCount()
	{
		return pageDeck.getComponentCount();
	}
	
	public void doCancel()
	{
		fireEvent(WIZARD_CANCELLED_EVENT, new WizardEvent(this));
	}
	
	public void doBack()
	{
		Component previousPage = getPreviousPage(getPage());
		
		if (previousPage != null)
		{
			pageDeck.setSelectedComponent(previousPage);
		}
	}
	
	public void doNext()
	{
		Component nextPage = getNextPage(getPage());
		
		if (nextPage != null)
		{
			pageDeck.setSelectedComponent(nextPage);
		}
	}
	
	public void doFinish()
	{
		fireEvent(WIZARD_FINISHED_EVENT, new WizardEvent(this));
	}
	
	// protected methods ------------------------------------------------------
	
	protected Component getPreviousPage(Component page)
	{
		int index = pageDeck.indexOf(page);
		return (index > 0) ? pageDeck.get(index - 1) : null;
	}
	
	protected Component getNextPage(Component page)
	{
		int index = pageDeck.indexOf(page);
		return (index < pageDeck.getComponentCount() - 1) ? pageDeck.get(index + 1) : null;
	}
}
