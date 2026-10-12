package com.duckblade.osrs.tfa.features;

import com.duckblade.osrs.tfa.TfaConfig;
import com.duckblade.osrs.tfa.module.PluginLifecycleComponent;
import com.duckblade.osrs.tfa.util.RaidState;
import javax.inject.Inject;
import javax.inject.Singleton;
import joptsimple.internal.Strings;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.runelite.api.Client;
import net.runelite.api.events.ClientTick;
import net.runelite.api.events.ScriptPostFired;
import net.runelite.api.gameval.InterfaceID;
import net.runelite.api.widgets.Widget;
import net.runelite.client.eventbus.Subscribe;
import net.runelite.client.util.Text;

@Slf4j
@Singleton
@RequiredArgsConstructor(onConstructor_ = @Inject)
public class LobbyBoardHighlighter
	implements PluginLifecycleComponent
{

	private static final int SCRIPT_ID_TFA_PARTYDETAILS_ADDMEMBER = 10053;
	private static final int SCRIPT_ID_TFA_PARTYLIST_ADDLINE = 10094;

	private final Client client;

	private boolean needScanPartyMembers;
	private boolean needScanPartyList;

	@Override
	public boolean isEnabled(TfaConfig config, RaidState raidState)
	{
		return config.lobbyBoardFriendHighlighter();
	}

	@Subscribe
	public void onScriptPostFired(ScriptPostFired e)
	{
		if (e.getScriptId() == SCRIPT_ID_TFA_PARTYDETAILS_ADDMEMBER)
		{
			needScanPartyMembers = true;
		}
		else if (e.getScriptId() == SCRIPT_ID_TFA_PARTYLIST_ADDLINE)
		{
			needScanPartyList = true;
		}
	}

	@Subscribe
	public void onClientTick(ClientTick e)
	{
		if (needScanPartyMembers)
		{
			scanPartyMembers();
			needScanPartyMembers = false;
		}

		if (needScanPartyList)
		{
			scanPartyList();
			needScanPartyList = false;
		}
	}

	private void scanPartyMembers()
	{
		log.trace("tfa addmember");
		Widget memberList = client.getWidget(InterfaceID.TfaPartydetails.MEMBERS_LIST);
		if (memberList == null)
		{
			return;
		}

		for (int i = 0; i < 8; i++)
		{
			// 13 elts per row, name is second in each row
			replaceName(memberList.getChild((14 * i) + 1));
		}
	}

	private void scanPartyList()
	{
		log.trace("tfa addline");
		Widget partyList = client.getWidget(InterfaceID.TfaPartylist.LIST);
		Widget[] staticChildren;
		if (partyList == null || (staticChildren = partyList.getStaticChildren()) == null)
		{
			return;
		}

		for (Widget w : staticChildren)
		{
			replaceName(w.getChild(2));
		}
	}

	private void replaceName(Widget memberName)
	{
		if (memberName == null || Strings.isNullOrEmpty(memberName.getText()))
		{
			return;
		}

		String member = Text.removeTags(memberName.getText());
		if (isFriend(member))
		{
			memberName.setText("<img=32> " + member);
			memberName.setTextColor(0x00ff00);
		}
		else if (isIgnored(member))
		{
			memberName.setText("<img=11> " + member);
			memberName.setTextColor(0xff0000);
		}
	}

	private boolean isFriend(String name)
	{
		return !Strings.isNullOrEmpty(name) &&
			client.getFriendContainer().findByName(Text.removeTags(name)) != null;
	}

	private boolean isIgnored(String name)
	{
		return !Strings.isNullOrEmpty(name) &&
			client.getIgnoreContainer().findByName(Text.removeTags(name)) != null;
	}
}
