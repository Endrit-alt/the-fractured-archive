package com.duckblade.osrs.tfa.util;

import lombok.Value;

@Value
public class RaidStateChanged
{

	RaidState previousState;
	RaidState newState;

}
