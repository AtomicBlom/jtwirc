package com.gikk.twirk.common.command;

import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

@SuppressWarnings("ALL")
public abstract class CommandBase
{
	public boolean isActive;
    public String message;
    public String msgWOCommand;
    public String command;
    public String user;
    public String channel;
    public String[] args;

    public CommandBase()
    {
    }

    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        this.message = message.getContent();
        this.command = this.message.split(" ")[0];
		if (!(this.command.length() == this.message.length()))
		{
			this.msgWOCommand = this.message.substring(command.length() + 1);
		}
        this.user = user.getName();
        this.args = this.message.split(" ");
        this.channel = message.getTarget();
    }
}