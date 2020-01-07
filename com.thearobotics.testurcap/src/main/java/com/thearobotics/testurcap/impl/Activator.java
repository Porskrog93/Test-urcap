package com.thearobotics.testurcap.impl;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.thearobotics.testurcap.impl.installation.InstallationService;
import com.thearobotics.testurcap.impl.main.MainService;
import com.ur.urcap.api.contribution.DaemonService;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeService;
import com.ur.urcap.api.contribution.program.swing.SwingProgramNodeService;

/**
 * Hello world activator for the OSGi bundle URCAPS contribution
 *
 */
public class Activator implements BundleActivator
{
	@Override
	public void start(BundleContext bundleContext) throws Exception
	{
		TestDaemonService daemonService = new TestDaemonService();
		InstallationService installationService = new InstallationService(daemonService);

		bundleContext.registerService(SwingInstallationNodeService.class, new InstallationService(daemonService), null);
		bundleContext.registerService(SwingProgramNodeService.class, new MainService(), null);
		bundleContext.registerService(DaemonService.class, daemonService, null);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception
	{
	}
}
