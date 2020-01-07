package com.thearobotics.testurcap.impl.main;

import com.ur.urcap.api.contribution.ProgramNodeContribution;
import com.ur.urcap.api.contribution.program.ProgramAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class MainContribution implements ProgramNodeContribution
{

	private DataModel model;

	public MainContribution(ProgramAPIProvider apiProvider, DataModel model, MainView view)
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

	@Override
	public String getTitle()
	{
		return "Test URCap";
	}
}
