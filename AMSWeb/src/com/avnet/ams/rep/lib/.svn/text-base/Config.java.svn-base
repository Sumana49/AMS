package com.avnet.ams.rep.lib;

import java.util.HashMap;
import java.util.Map;

/**
 * This class will hold rep values
 * 
 * @author Atechian
 *
 */
public class Config
{
	private Map attributes = null;
	public Config()
	{
		attributes = new HashMap(10);
	}
	protected void setAttribute(String attributeName, String attributeValue)
	{
		attributes.put(attributeName, attributeValue);
	}
	public Object getAttribute(String attributeName)
	{
		return attributes.get(attributeName);
	}
}
