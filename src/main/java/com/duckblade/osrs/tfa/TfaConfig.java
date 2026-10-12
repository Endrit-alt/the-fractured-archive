package com.duckblade.osrs.tfa;

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
}
