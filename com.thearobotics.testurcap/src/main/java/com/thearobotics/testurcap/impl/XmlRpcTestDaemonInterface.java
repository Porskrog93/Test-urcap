package com.thearobotics.testurcap.impl;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

import org.apache.xmlrpc.XmlRpcException;
import org.apache.xmlrpc.client.XmlRpcClient;
import org.apache.xmlrpc.client.XmlRpcClientConfigImpl;

public class XmlRpcTestDaemonInterface
	{

		private final XmlRpcClient client;

		public XmlRpcTestDaemonInterface(String host, int port)
			{
				// Create configuration for client
				XmlRpcClientConfigImpl config = new XmlRpcClientConfigImpl();

				config.setEnabledForExceptions(true);
				try
					{
						config.setServerURL(new URL("http://" + host + ":" + port + "/RPC2"));
					} catch (MalformedURLException e)
					{
						e.printStackTrace();
					}
				config.setConnectionTimeout(1000);
				//

				// Create client
				client = new XmlRpcClient();

				// Apply configuration
				client.setConfig(config);
			}

		// Checks whether client is reachable, returns true if, throws XmlRpcException
		// if not, and returns false.
		public boolean isReachable()
			{
				try
					{
						client.execute("hello_world", new ArrayList<String>());
						return true;
					} catch (XmlRpcException e)
					{
						return false;
					}

			}
	}
