package com.thearobotics.testurcap.impl.installation;

import javax.swing.JPanel;

import com.thearobotics.testurcap.impl.style.Style;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;

public class InstallationView implements SwingInstallationNodeView<InstallationContribution>
{
	private final Style style;

	InstallationView(Style style)
	{
		this.style = style;
	}

	@Override
	public void buildUI(JPanel panel, InstallationContribution contribution)
	{

	}

}
