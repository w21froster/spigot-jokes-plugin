package w21froster.jokes.plugin;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class main extends JavaPlugin implements Listener {

	@Override
    public void onEnable() {
		Bukkit.broadcastMessage("Jokes Plugin v0.1 loaded");
	    getServer().getPluginManager().registerEvents(this, this);
    }

    @Override
    public void onDisable() {
    }


	public static String GetJSON() throws Exception {
        String jsonS = "";
        URL url = new URL("https://icanhazdadjoke.com/");
        URLConnection conn = url.openConnection();
        conn.setRequestProperty("Accept", "application/json");
        conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/65.0.3325.181 Safari/537.36 (Spigot server plugin by William Foster, https://github.com/w21froster/spigot-jokes-plugin");
        conn.connect();
        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String inputLine;
        while((inputLine = in.readLine()) != null) {
            jsonS+=inputLine;
        }
	    Gson gson = new Gson();
	    JsonObject jsonObject= gson.fromJson(jsonS, JsonObject.class);
	    String joke = jsonObject.get("joke").getAsString();
	    in.close();
	    return joke;
	}
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) throws Exception
		{
				Bukkit.broadcastMessage("JokeBot: " + main.GetJSON());
	}
}


