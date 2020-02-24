package com.thearobotics.testurcap.impl.installation;

import com.thearobotics.testurcap.impl.TestDaemonService;
import com.ur.urcap.api.contribution.InstallationNodeContribution;
import com.ur.urcap.api.contribution.installation.InstallationAPIProvider;
import com.ur.urcap.api.domain.data.DataModel;
import com.ur.urcap.api.domain.script.ScriptWriter;

public class InstallationContribution implements InstallationNodeContribution
	{

		private final InstallationView view;

		protected final InstallationDaemon installationDaemon;

		private DataModel model;

		public InstallationContribution(
				InstallationAPIProvider apiProvider, DataModel model, InstallationView view,
				TestDaemonService daemonService
		)
			{
				this.model = model;
				this.view = view;
				this.installationDaemon = new InstallationDaemon(daemonService, view, model);
			}

		@Override
		public void openView()
			{
				installationDaemon.startTimer();
			}

		@Override
		public void closeView()
			{
				installationDaemon.stopTimer();
			}

		public boolean isDefined()
			{
				return installationDaemon.daemonRunning();
			}

		@Override
		public void generateScript(ScriptWriter writer)
			{
			}

	}
