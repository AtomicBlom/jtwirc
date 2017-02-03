package com.gikk.twirk.common.command.commands.faq;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;
import com.gikk.twirk.utils.Defaults;
import com.gikk.twirk.utils.MessageSending;
import com.gikk.twirk.utils.json.Save;
import org.apache.commons.lang3.text.WordUtils;
import todo.ChirpBot;

import java.io.IOException;

public class CommandCTT extends CommandBase
{

    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        if (args.length >= 2)
        {
            if (user.isMod() || user.isBroadcaster())
            {
                StringBuilder sb = new StringBuilder();
                int i = 1;
                while (i <= args.length - 1)
                {
                    if (i == 1)
                    {
                        sb.append(WordUtils.capitalize(args[i]));
                    }
                    else
                    {
                        sb.append(args[i]);
                    }
                    sb.append("+");
                    i++;
                }

                Defaults.cttText = sb.toString();
                ChirpBot.extra.setProperty("ctt", sb.toString());
                Save.properties();
                MessageSending.sendNormalMessage("Changed the CTT text. Also renewed the link.");
                try
                {
                    Save.dataList();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
        else
        {
            MessageSending.sendNormalMessage("Please support the stream by clicking on this link and tweet it around! : " + Defaults.getBitlyLink());
        }
    }
}
