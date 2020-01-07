package com.thearobotics.testurcap.impl.main;

import javax.swing.JPanel;

import com.thearobotics.testurcap.impl.style.Style;
import com.ur.urcap.api.contribution.ContributionProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeView;

public class MainView implements SwingProgramNodeView<MainContribution>
{

	private final Style style;

	public MainView(Style style)
	{
		this.style = style;
	}

	@Override
	public void buildUI(JPanel panel, ContributionProvider<MainContribution> provider)
	{

	}

}
