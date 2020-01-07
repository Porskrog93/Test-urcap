package com.thearobotics.testurcap.impl.installation;

import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class InstallationContribution implements InstallationNodeContribution
{

	private DataModel model;

	public InstallationContribution(InstallationAPIProvider apiProvider, DataModel model, InstallationView view)
	{
		this.model = model;
	}

	@Override
	public void openView()
	{
	}

	@Override
	public void closeView()
	{
	}

	public boolean isDefined()
	{
		return true;
	}

	@Override
	public void generateScript(ScriptWriter writer)
	{
	}
}
