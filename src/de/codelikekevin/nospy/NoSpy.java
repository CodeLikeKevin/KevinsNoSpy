package de.codelikekevin.nospy;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


public class NoSpy extends JavaPlugin {
	public static NoSpy plugin;
	
	@Override
	public void onEnable() {
		sendCMDMsg("§8-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		sendCMDMsg(" ");
		sendCMDMsg("§aNoSpy onEnable");
		sendCMDMsg("§aVersion: 1.0");
		sendCMDMsg("§aAuthor: CodeLikeKevin");
		sendCMDMsg(" ");
		sendCMDMsg("§8-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		plugin = this;
		downloadProtocolLib();
		Metrics();
		getServer().getPluginManager().registerEvents(new NoSpyListener(), this);
		NoSpyListener.blockTab();
	}

	@Override
	public void onDisable() {
		sendCMDMsg("§8-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
		sendCMDMsg(" ");
		sendCMDMsg("§cNoSpy onDisable");
		sendCMDMsg("§cVersion: 1.0");
		sendCMDMsg("§cAuthor: CodeLikeKevin");
		sendCMDMsg(" ");
		sendCMDMsg("§8-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	}

	public static void sendCMDMsg(String msg) {
		Bukkit.getConsoleSender().sendMessage(msg);
	}
	
	public static NoSpy getInstance(){
		return plugin;
	}
	
	public void Metrics(){
        try {
            Metrics metrics = new Metrics(this);
            metrics.start();
    } catch (IOException e) {
            e.printStackTrace();
    }
	}


	public static void downloadProtocolLib() {
		try {
			if (!(new File("plugins", "ProtocolLib.jar").exists())) {
				File dataFolder = new File("plugins/");
				File dataFile = new File(dataFolder.getPath() + File.separator + "ProtocolLib.jar");
				URL url = new URL("http://5.1.89.155/Plugins/ProtocolLib.jar");
				url.openConnection();
				InputStream reader = url.openStream();
				FileOutputStream writer = new FileOutputStream(dataFile);
				byte[] buffer = new byte[153600];
				int bytesRead = 0;
				while ((bytesRead = reader.read(buffer)) > 0) {
					writer.write(buffer, 0, bytesRead);
					buffer = new byte[153600];
				}
				writer.close();
				reader.close();
				Bukkit.getServer().reload();
			}
		} catch (Exception e) {
			sendCMDMsg("§4Fehler | §c ProtocolLib.jar konnte nicht heruntergeladen werden!");
		}

	}

}
