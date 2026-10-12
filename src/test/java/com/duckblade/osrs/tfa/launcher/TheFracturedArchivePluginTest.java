package com.duckblade.osrs.tfa.launcher;

import com.duckblade.osrs.tfa.TfaPlugin;
import com.duckblade.osrs.tfa.launcher.debugplugins.TfaDebugPlugin;
import net.runelite.client.RuneLite;
import net.runelite.client.externalplugins.ExternalPluginManager;

public class TheFracturedArchivePluginTest
{
	public static void main(String[] args) throws Exception
	{
		ExternalPluginManager.loadBuiltin(TfaPlugin.class, TfaDebugPlugin.class);
		RuneLite.main(args);
	}
}