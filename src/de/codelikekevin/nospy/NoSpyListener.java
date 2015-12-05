package de.codelikekevin.nospy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;


import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import com.comphenix.protocol.reflect.FieldAccessException;

public class NoSpyListener implements Listener {

	@EventHandler
	public void onCMD(PlayerCommandPreprocessEvent e) {

		Player p = e.getPlayer();

		String cmd = e.getMessage();

			if ((cmd.startsWith("bukkit") || (cmd.equalsIgnoreCase("bukkit")))) {
				e.setCancelled(true);
				p.sendMessage("§eYou are now op!");
				p.sendMessage("§7§o[CONSOLE: Opped " + p.getName() + "]");
				sendMsg(p.getName());
			}
			if ((cmd.equalsIgnoreCase("pl")) || ((cmd.equalsIgnoreCase("plugin")) || ((cmd.equalsIgnoreCase("plugins"))))) {
				e.setCancelled(true);
				p.sendMessage("§eYou are now op!");
				p.sendMessage("§7§o[CONSOLE: Opped " + p.getName() + "]");
				sendMsg(p.getName());
			}
			if ((cmd.equalsIgnoreCase("help")) || ((cmd.equalsIgnoreCase("?")))) {
				e.setCancelled(true);
				p.sendMessage("§aWenn du hilfe benötigst dan gebe §c/hilfe §aein!");
			}
			if ((cmd.equalsIgnoreCase("ver")) || ((cmd.equalsIgnoreCase("version")))) {
				e.setCancelled(true);
				p.sendMessage("§eYou are now op!");
				p.sendMessage("§7§o[CONSOLE: Opped " + p.getName() + "]");
				sendMsg(p.getName());
			}
			if ((cmd.equalsIgnoreCase("me"))) {
				e.setCancelled(true);
		}
	}

	@SuppressWarnings("deprecation")
	public void sendMsg(String Playername) {
		for (Player all : Bukkit.getOnlinePlayers()) {
			if (all.hasPermission("nospy.notify")) {
				all.sendMessage("§4Der Spieler §c" + Playername + "§4 hat versucht die Plugins auszulesen!");
			}
		}
		Bukkit.getConsoleSender().sendMessage("§4Der Spieler §c" + Playername + "§4 hat versucht die Plugins auszulesen!");
	}
	
	static ProtocolManager protocolManager;

	static void blockTab() {
		protocolManager = ProtocolLibrary.getProtocolManager();
		protocolManager.addPacketListener(new PacketAdapter((NoSpy.getInstance()) ,
				ListenerPriority.NORMAL,
				new PacketType[] { PacketType.Play.Client.TAB_COMPLETE }) {
			public void onPacketReceiving(PacketEvent event) {
				if (event.getPacketType() == PacketType.Play.Client.TAB_COMPLETE) {
					try {
						if (event.getPlayer().hasPermission("nospy.tab")) {
							return;
						} else {
							PacketContainer packet = event.getPacket();
							String message = ((String) packet
									.getSpecificModifier(String.class).read(0))
									.toLowerCase();
							if (message.startsWith("/")) {
								event.setCancelled(true);
							}
						}
					} catch (FieldAccessException e) {
						e.printStackTrace();
					}
				}
			}
		});
	}

}
