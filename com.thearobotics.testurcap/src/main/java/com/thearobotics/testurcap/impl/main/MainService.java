package com.thearobotics.testurcap.impl.main;

import java.util.Locale;

import com.thearobotics.testurcap.impl.style.Style;
import com.thearobotics.testurcap.impl.style.V3Style;
import com.thearobotics.testurcap.impl.style.V5Style;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.program.ContributionConfiguration;
import com.ur.urcap.api.contribution.program.CreationContext;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;

public class MainService implements SwingProgramNodeService<MainContribution, MainView>
{

	@Override
	public String getId()
	{
		return "TestURcapNode";
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration)
	{
		configuration.setChildrenAllowed(true);
	}

	@Override
	public String getTitle(Locale locale)
	{
		return "Test URCap";
	}

	@Override
	public MainView createView(ViewAPIProvider apiProvider)
	{
		SystemAPI systemAPI = apiProvider.getSystemAPI();
		Style style = systemAPI.getSoftwareVersion().getMajorVersion() >= 5 ? new V5Style() : new V3Style();
		return new MainView(style);
	}

	@Override
	public MainContribution createNode(
			ProgramAPIProvider apiProvider, MainView view, DataModel model, CreationContext context
	)
	{
		return new MainContribution(apiProvider, model, view);
	}

}
