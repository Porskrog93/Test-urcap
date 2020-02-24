package com.thearobotics.testurcap.impl.installation;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.thearobotics.testurcap.impl.style.Style;
import com.ur.urcap.api.contribution.installation.swing.SwingInstallationNodeView;

public class InstallationView implements SwingInstallationNodeView<InstallationContribution>
	{
		private final Style style;

		protected JLabel statusLabel = new JLabel();

		JButton startButton = new JButton("Start Daemon");
		JButton stopButton = new JButton("Stop Daemon");

		InstallationView(Style style)
			{
				this.style = style;
			}

		@Override
		public void buildUI(JPanel panel, InstallationContribution contribution)
			{
				panel.add(createDaemonSection(contribution));
			}

		private Box createDaemonSection(final InstallationContribution contribution)
			{
				Box vBox = Box.createVerticalBox();
				Box hBox = Box.createHorizontalBox();

				startButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
							{
								contribution.installationDaemon.onStartClick();
							}
					});

				stopButton.addActionListener(new ActionListener()
					{
						@Override
						public void actionPerformed(ActionEvent e)
							{
								contribution.installationDaemon.onStopClick();
							}
					});

				hBox.add(startButton);
				hBox.add(stopButton);

				vBox.add(statusLabel);
				vBox.add(hBox);

				return vBox;
			}

	}
