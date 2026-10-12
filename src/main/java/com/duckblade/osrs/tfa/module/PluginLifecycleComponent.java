package com.duckblade.osrs.tfa.module;

import com.duckblade.osrs.tfa.TfaConfig;
import com.duckblade.osrs.tfa.util.RaidState;

public interface PluginLifecycleComponent
{

	default boolean isEnabled(TfaConfig config, RaidState raidState)
	{
		return true;
	}

	default void startUp()
	{
	}

	default void shutDown()
	{
	}

}
