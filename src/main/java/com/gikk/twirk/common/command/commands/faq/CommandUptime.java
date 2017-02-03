package com.gikk.twirk.common.command.commands.faq;

import com.gikk.twirk.common.command.CommandBase;
import com.gikk.twirk.types.twitchMessage.TwitchMessage;
import com.gikk.twirk.types.users.TwitchUser;

public class CommandUptime extends CommandBase
{
    @Override
    public void channelCommand(TwitchUser user, TwitchMessage message)
    {
        super.channelCommand(user, message);
        /*final String[] uptime = new String[1];

		ChirpBot.twitch.streams().get("WeAllPlayCast", new StreamResponseHandler() {
			@Override
			public void onSuccess(Stream stream) {
				uptime[0] = String.valueOf(stream.getCreatedAt());
			}

			@Override
			public void onFailure(int statusCode, String statusMessage, String errorMessage) {
				System.out.println(String.format("ERROR %d %s, With message %s", statusCode, statusMessage, errorMessage));
			}

			@Override
			public void onFailure(Throwable throwable) {
				throwable.printStackTrace();
			}
		});
		if (uptime[0] != null) {
			long ts = System.currentTimeMillis();
			Date localTime = new Date(ts);
			String format = "yyyy/MM/dd HH:mm:ss";
			SimpleDateFormat sdf = new SimpleDateFormat(format);

			sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
			Date gmtTime = new Date(sdf.format(localTime));
			System.out.println("Twitch " + uptime[0]);
			String uptimeUTC = gmtTime.getYear() + 1900 + " " + gmtTime.getMonth() + 1 + " " + gmtTime.getDate() + " " + gmtTime.getHours() + " " + gmtTime.getMinutes() + " " + gmtTime.getSeconds();
			System.out.println("UTC " + uptimeUTC);

			uptime[0] = uptime[0].replace("-", " ").replace("T", " ").replace(":", " ").replace("Z", "");
			String twitch[] = uptime[0].split(" ");
			String year = twitch[0];
			String month = twitch[1];
			String day = twitch[2];
			String hour = twitch[3];
			String minute = twitch[4];
			String second = twitch[5];
			String local[] = uptimeUTC.split(" ");
			String yearL = local[0];
			String monthL = local[1];
			String dayL = local[2];
			String hourL = local[3];
			String minuteL = local[4];
			String secondL = local[5];

			String twitchDate = month + "/" + day + "/" + year + " " + hour + ":" + minute + ":" + second;
			String localDate = monthL + "/" + dayL + "/" + yearL + " " + hourL + ":" + minuteL + ":" + secondL;

			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");

			Date d1;
			Date d2;

			try {
				d1 = simpleDateFormat.parse(twitchDate);
				d2 = simpleDateFormat.parse(localDate);

				long diff = d2.getTime() - d1.getTime();

				long diffHours = diff / (60 * 60 * 1000) % 24;
				long diffMinutes = diff / (60 * 1000) % 60;

				if (!Defaults.whisperToggle) {
					if (uptime[0].equals("") || uptime[0] == null) {
						MessageSending.sendNormalMessage(ChirpBot.config.getProperty("autoJoinChannel") + " is currently offline.");
					} else {
						if (diffHours > 0) {
							MessageSending.sendNormalMessage(ChirpBot.config.getProperty("autoJoinChannel") + " is currently live for " + diffHours + " hours and " + diffMinutes + " minutes.");
						} else {
							MessageSending.sendNormalMessage(ChirpBot.config.getProperty("autoJoinChannel") + " is currently live for " + diffMinutes + " minutes.");
						}
					}

				} else {
					if (uptime[0].equals("") || uptime[0] == null) {
						MessageSending.sendWhisper(user.getName().toLowerCase(), ChirpBot.config.getProperty("autoJoinChannel") + " is currently offline.");
					} else {
						if (diffHours > 0) {
							MessageSending.sendWhisper(user.getName().toLowerCase(), ChirpBot.config.getProperty("autoJoinChannel") + " is currently live for " + diffHours + " hours and " + diffMinutes + " minutes.");
						} else {
							MessageSending.sendWhisper(user.getName().toLowerCase(), ChirpBot.config.getProperty("autoJoinChannel") + " is currently live for " + diffMinutes + " minutes.");
						}
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			if (!Defaults.whisperToggle) {
				MessageSending.sendNormalMessage(ChirpBot.config.getProperty("autoJoinChannel") + " is currently offline.");
			} else {
				MessageSending.sendWhisper(user.getName().toLowerCase(), ChirpBot.config.getProperty("autoJoinChannel") + " is currently offline.");
			}
		}*/
    }
}
