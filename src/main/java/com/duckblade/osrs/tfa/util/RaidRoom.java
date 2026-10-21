package com.duckblade.osrs.tfa.util;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RaidRoom
{

	ARCHIVE_GUARDIAN(new int[]{16471}, "Archive Guardian"),
	BORK(new int[]{16727}, "Bork"),
	DREADNOTH(new int[]{16728}, "Dreadnoth the Jellied"),
	XARTH(new int[]{16726}, "Xarth"),
	OCELLUS(new int[]{16472}, "Ocellus"),
	;

	private final int[] regionIds;

	@Getter
	private final String displayName;

	public static RaidRoom forRegionId(int region)
	{
		for (RaidRoom r : RaidRoom.values())
		{
			for (int regionId : r.regionIds)
			{
				if (regionId == region)
				{
					return r;
				}
			}
		}

		return null;
	}

	public static RaidRoom forString(String roomName)
	{
		for (RaidRoom r : RaidRoom.values())
		{
			if (r.getDisplayName().equals(roomName))
			{
				return r;
			}
		}

		return null;
	}

	@Override
	public String toString()
	{
		return displayName;
	}
}
