package com.duckblade.osrs.tfa;

import com.duckblade.osrs.tfa.features.hporbs.HpOrbMode;
import net.runelite.client.config.Config;
import net.runelite.client.config.ConfigGroup;
import net.runelite.client.config.ConfigItem;
import net.runelite.client.config.ConfigSection;

@ConfigGroup(TfaConfig.CONFIG_GROUP)
public interface TfaConfig extends Config
{

	String CONFIG_GROUP = "thefracturedarchive";

	// Sections

	@ConfigSection(
		name = "Miscellaneous",
		description = "Miscellaneous configurations.",
		position = 0,
		closedByDefault = false
	)
	String SECTION_MISCELLANEOUS = "sectionMiscellaneous";

	@ConfigItem(
		keyName = "lobbyBoardFriendHighlighter",
		name = "Friend Highlight",
		description = "Highlight friends' names on the lobby party finder board.",
		section = SECTION_MISCELLANEOUS,
		position = 0
	)
	default boolean lobbyBoardFriendHighlighter()
	{
		return true;
	}

	@ConfigItem(
		keyName = "hpOrbsMode",
		name = "HP Orbs",
		description = "Removes HP orbs from the screen or replaces them with health bars.",
		position = 1,
		section = SECTION_MISCELLANEOUS
	)
	default HpOrbMode hpOrbsMode()
	{
		return HpOrbMode.ORBS;
	}

	@ConfigItem(
		keyName = "hideFadeTransition",
		name = "Hide Fade Transition",
		description = "Hides the fade transition between loading zones.",
		position = 4,
		section = SECTION_MISCELLANEOUS
	)
	default boolean hideFadeTransition()
	{
		return false;
	}
}
