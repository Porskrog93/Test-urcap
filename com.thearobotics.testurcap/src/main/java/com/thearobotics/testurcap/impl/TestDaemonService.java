package com.thearobotics.testurcap.impl;

import java.net.MalformedURLException;
import java.net.URL;

import com.ur.urcap.api.contribution.DaemonContribution;
import com.ur.urcap.api.contribution.DaemonService;

// The DaemonService interface handles initialising, pointing to the executables and returning the object.
public class TestDaemonService implements DaemonService
	{

		private DaemonContribution daemonContribution;

		private final String daemonPath = "/com/thearobotics/impl/daemon/";

		// init is called when the daemon service i registered in the activator. If the
		// daemon should be started automatically, the daemonContribution.start should
		// be called here.
		@Override
		public void init(DaemonContribution daemonContribution)
			{
				this.daemonContribution = daemonContribution;
				try
					{
						// Installs the specified daemon file or the folder that contains it and
						// additional files.
						daemonContribution.installResource(new URL("file:" + daemonPath));
					} catch (MalformedURLException e)
					{
						e.printStackTrace();
					}
			}

		// This provides Polyscope with the path to executable that will be started.
		@Override
		public URL getExecutable()
			{
				try
					{
						return new URL("file:" + daemonPath + "daemon.py");
					} catch (MalformedURLException e)
					{
						e.printStackTrace();
						return null;
					}
			}

		// Returns the object, and makes it possible for the programmer to call
		// daemonContribution.start(), .stop()
		public DaemonContribution getDaemonContribution()
			{
				return daemonContribution;
			}

	}
