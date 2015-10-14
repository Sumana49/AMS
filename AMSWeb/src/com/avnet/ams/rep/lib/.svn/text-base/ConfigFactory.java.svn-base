package com.avnet.ams.rep.lib;

import java.util.Enumeration;
import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.spi.ObjectFactory;


public class ConfigFactory implements ObjectFactory
{
	private static Config config = null;
	public Object getObjectInstance(
		Object object,
		Name name,
		Context nameCtx,
		Hashtable environment)
		throws NamingException
	{
		if(config == null)
		{
			config = new Config();
			Reference ref = (Reference) object;
			Enumeration addrs = ref.getAll();
			RefAddr addr = null;
			String entryName = null;
			String value = null;
			while (addrs.hasMoreElements())
			{
				addr = (RefAddr) addrs.nextElement();
				entryName = addr.getType();
				value = (String) addr.getContent();
				config.setAttribute(entryName, value);
			}	
		}
		return config;
	}
}
