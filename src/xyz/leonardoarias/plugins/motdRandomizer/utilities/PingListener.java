package xyz.leonardoarias.plugins.motdRandomizer.utilities;

import java.io.File;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.ServerListPingEvent;

import xyz.leonardoarias.plugins.motdRandomizer.Main;

public class PingListener implements Listener {

	private Main main;
	final int MOTD_LENGTH = 45;

	public PingListener(Main main) {
		this.main = main;
	}

	@EventHandler
	protected void onPing(ServerListPingEvent e) { /* Called every time someone refreshes the server list */
		Random random = new Random();
		int i = random.nextInt(main.motd.length);
		int j = random.nextInt(main.icons.length);
		String motd = main.motd[i];
		String icon = main.icons[j];

		/* Custom Max Players */
		if (main.getConfig().getBoolean("Max-Players.Enabled"))
			e.setMaxPlayers(main.getConfig().getInt("Max-Players.Max"));

		/* Custom MOTD */
		if (motd.indexOf("%CENTER%") != -1) {
			motd = motd.replaceAll("%CENTER%", "");
			motd = motd.replaceAll("%n%", "\n");
			motd = TextFormatter.Center(motd, MOTD_LENGTH);
			motd = TextFormatter.Color(motd);
			e.setMotd(motd);
		} else {
			motd = motd.replaceAll("%n%", "\n");
			motd = TextFormatter.Color(motd);
			e.setMotd(motd);
		}
		
		/* Custom Icon */
		if (main.getConfig().getBoolean("Custom-Icon.Enabled")) {
			try {
				e.setServerIcon(Bukkit.loadServerIcon(new File(icon)));
			} catch (Exception excep) {
				main.console.sendMessage(TextFormatter.Color(main.prefix + " &4ERROR: &ecould not find file " + icon));
			}
		}
	}
}
