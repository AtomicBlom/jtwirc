# jtwirc
[![](https://jitpack.io/v/techbrew-mc/jtwirc.svg)](https://jitpack.io/#techbrew-mc/jtwirc)

Java library for Twitch Chat Bot functionality.

The library is intended to make communication via Twitch chat as easy as possible, and uses Java objects to represent most events that can occur in Twitch chat. 

Java 8 compatible.

##Installation

Add this project as a Gradle dependency via [jitpack.io](https://github.com/techbrew-mc/jtwirc):

```groovy
repositories {
    maven { url 'https://jitpack.io' }
}
dependencies {
    compile "com.github.urgrue:Java-Twitch-Api-Wrapper:0.3.1"
}
```

#Usage
####Basic usage
```Java
  final Twirk twirk = new TwirkBuilder(channel, SETTINGS.MY_NICK, SETTINGS.MY_PASS).build();
  twirk.connect();
  ...
  twirk.close();
```
####Events
All events which can be reacted to are listed in [TwirkListener.java](https://github.com/techbrew-mc/jtwirc/blob/master/twirc/src/main/java/com/gikk/twirk/events/TwirkListener.java) This snippet will make your bot respond to any channel
message with a "pong USER_NAME".
```Java
  twirk.addIrcListener( new TwirkListenerBaseImpl() { 
    public void onPrivMsg( TwitchUser sender, TwitchMessage message) {
      twirk.channelMessage("pong " + sender.getDisplayName() );
    }
  } );
```

For a more complex example, which shows how to connect properly and how to write simple bot commands, check out the example code in `src/example/java`

####Extendable
You can make Twirk use your own implementation of all event types by using custom builder classes. By extending the types Builder interface, and then passing an instance of your custom builder to the TwirkBuilder, you can use your own custom implementation of whichever type you want.
```Java
  final Twirk twirk = new TwirkBuilder(channel, SETTINGS.MY_NICK, SETTINGS.MY_PASS)
    .setSubscriberEventBuilder( new MySubscriberEventBuilder() )
    .build();
```
This will make the Twirk instance build instances of your custom implementation of `SubscriberEvent`

#License
This library is a fork of https://github.com/Gikkman/Java-Twirk and is licensed under the [MIT License](https://tldrlegal.com/license/mit-license). 

