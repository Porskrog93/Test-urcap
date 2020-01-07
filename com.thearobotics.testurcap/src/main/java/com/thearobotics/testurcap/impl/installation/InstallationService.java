package com.thearobotics.testurcap.impl.installation;

import java.util.Locale;

import com.thearobotics.testurcap.impl.TestDaemonService;
import com.thearobotics.testurcap.impl.style.Style;
import com.thearobotics.testurcap.impl.style.V3Style;
import com.thearobotics.testurcap.impl.style.V5Style;
import com.ur.urcap.api.contribution.ViewAPIProvider;
import com.ur.urcap.api.contribution.installation.ContributionConfiguration;
import com.ur.urcap.api.contribution.installation.CreationContext;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.domain.SystemAPI;
import com.ur.urcap.api.domain.data.DataModel;

public class InstallationService implements SwingInstallationNodeService<InstallationContribution, InstallationView>
{

	private final TestDaemonService daemonService;

	public InstallationService(TestDaemonService daemonService) {
		this.daemonService=daemonService;
	}

	@Override
	public void configureContribution(ContributionConfiguration configuration)
	{
	}

	@Override
	public String getTitle(Locale locale)
	{
		return "Test URCap";
	}

	@Override
	public InstallationView createView(ViewAPIProvider apiProvider)
	{
		SystemAPI systemAPI = apiProvider.getSystemAPI();
		Style style = systemAPI.getSoftwareVersion().getMajorVersion() >= 5 ? new V5Style() : new V3Style();
		return new InstallationView(style);
	}

	@Override
	public InstallationContribution createInstallationNode(
			InstallationAPIProvider apiProvider, InstallationView view, DataModel model, CreationContext context
	)
	{
		return new InstallationContribution(apiProvider, model, view);
	}
}
