package com.thearobotics.testurcap.impl.installation;

import java.awt.EventQueue;
import java.util.Timer;
import java.util.TimerTask;

import com.thearobotics.testurcap.impl.TestDaemonService;
import com.thearobotics.testurcap.impl.XmlRpcTestDaemonInterface;
import com.ur.urcap.api.contribution.DaemonContribution;
import com.ur.urcap.api.domain.data.DataModel;

public class InstallationDaemon
	{

		private final TestDaemonService daemonService;
		private XmlRpcTestDaemonInterface xmlRpcDaemonInterface;
		private final InstallationView view;
		private DataModel model;

		private static final String DAEMON_ENABLED_KEY = "daemon_enabled";

		private Timer uiTimer;
		private Boolean timerPaused = false;
		public static final int PORT = 40405;

		public InstallationDaemon(TestDaemonService daemonService, InstallationView view, DataModel model)
			{
				this.daemonService = daemonService;
				this.view = view;
				this.model = model;

				xmlRpcDaemonInterface = new XmlRpcTestDaemonInterface("127.0.0.1", PORT);
			}

		protected void updateDaemonStatus()
			{
				updateButtons();
				updateStatusLabel();

			}

		private void updateButtons()
			{
				if (daemonRunning())
					{
						view.startButton.setEnabled(false);
						view.stopButton.setEnabled(true);
					} else
					{
						view.startButton.setEnabled(true);
						view.stopButton.setEnabled(false);
					}
			}

		private void updateStatusLabel()
			{
				switch (getDaemonState())
					{
					case RUNNING:
						view.statusLabel.setText("Daemon runs");
						break;
					case STOPPED:
						view.statusLabel.setText("Daemon stopped");
						break;
					case ERROR:
						view.statusLabel.setText("Daemon failed");
						break;
					}
			}

		private DaemonContribution.State getDaemonState()
			{
				return daemonService.getDaemonContribution().getState();
			}

		private void toggleDaemonStatus()
			{
				new Thread(new Runnable()
					{
						@Override
						public void run()
							{

								if (model.get(DAEMON_ENABLED_KEY, true))
									{
										try
											{
												timerPaused = true;
												view.statusLabel.setText("Starting Daemon...");
												startDaemon(5000);

											} catch (Exception e)
											{
												System.err.println("Error when starting daemon");
												e.printStackTrace();
											} finally
											{
												timerPaused = false;
											}
									} else
									{
										daemonService.getDaemonContribution().stop();
									}
							}
					}).start();

			}

		private void startDaemon(long timeOutMs) throws InterruptedException
			{
				daemonService.getDaemonContribution().start();
				daemonService.getExecutable();
				long timeOutPoint = System.nanoTime() + timeOutMs * 1000 * 1000;
				while (System.nanoTime() < timeOutPoint && (!daemonRunning() || !xmlRpcDaemonInterface.isReachable()))
					{
						Thread.sleep(100);
					}
			}

		protected void startTimer()
			{
				uiTimer = new Timer(true);
				uiTimer.schedule(new TimerTask()
					{
						@Override
						public void run()
							{
								EventQueue.invokeLater(new Runnable()
									{
										@Override
										public void run()
											{
												if (!timerPaused)
													{
														updateDaemonStatus();
													}
											}
									});
							}
					}, 0, 1000);
			}

		protected void stopTimer()
			{
				if (uiTimer != null)
					{
						uiTimer.cancel();
					}
			}

		protected Boolean daemonRunning()
			{
				if (getDaemonState() == DaemonContribution.State.RUNNING)
					{
						return true;
					} else
					{
						return false;
					}
			}

		protected void onStartClick()
			{
				model.set(DAEMON_ENABLED_KEY, true);
				toggleDaemonStatus();
			}

		protected void onStopClick()
			{
				model.set(DAEMON_ENABLED_KEY, false);
				toggleDaemonStatus();
			}
	}
