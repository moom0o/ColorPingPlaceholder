package me.moomoo.placeholder;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.craftbukkit.v1_12_R1.entity.CraftPlayer;

/**
 * This class will automatically register as a placeholder expansion
 * when a jar including this class is added to the directory
 * {@code /plugins/PlaceholderAPI/expansions} on your server.
 * <br>
 * <br>If you create such a class inside your own plugin, you have to
 * register it manually in your plugins {@code onEnable()} by using
 * {@code new YourExpansionClass().register();}
 */
public class Main extends PlaceholderExpansion {

    /**
     * This method should always return true unless we
     * have a dependency we need to make sure is on the server
     * for our placeholders to work!
     *
     * @return always true since we do not have any dependencies.
     */
    @Override
    public boolean canRegister(){
        return true;
    }

    /**
     * The name of the person who created this expansion should go here.
     *
     * @return The name of the author as a String.
     */
    @Override
    public String getAuthor(){
        return "moo";
    }

    /**
     * The placeholder identifier should go here.
     * <br>This is what tells PlaceholderAPI to call our onRequest
     * method to obtain a value if a placeholder starts with our
     * identifier.
     * <br>The identifier has to be lowercase and can't contain _ or %
     *
     * @return The identifier in {@code %<identifier>_<value>%} as String.
     */
    @Override
    public String getIdentifier(){
        return "1b1t";
    }

    /**
     * This is the version of this expansion.
     * <br>You don't have to use numbers, since it is set as a String.
     *
     * @return The version as a String.
     */
    @Override
    public String getVersion(){
        return "1.0.0";
    }

    /**
     * This is the method called when a placeholder with our identifier
     * is found and needs a value.
     * <br>We specify the value identifier in this method.
     * <br>Since version 2.9.1 can you use OfflinePlayers in your requests.
     *
     * @param  player
     *         A {@link org.bukkit.OfflinePlayer OfflinePlayer}.
     * @param  identifier
     *         A String containing the identifier/value.
     *
     * @return Possibly-null String of the requested identifier.
     */
    @Override
    public String onRequest(OfflinePlayer player, String identifier){

        // %example_placeholder1%
        if(identifier.equals("ping")){
            int ping = ((CraftPlayer) player).getHandle().ping;
            if(ping < 150){
                return ChatColor.GREEN + String.valueOf(ping);
            }
            if(ping > 150 && ping < 250){
                return ChatColor.YELLOW + String.valueOf(ping);
            }
            if(ping > 250 && ping < 500){
                return ChatColor.RED + String.valueOf(ping);
            }
            if(ping > 500){
                return ChatColor.DARK_RED + String.valueOf(ping);
            }
        }
        if(identifier.equals("tps")){
            double tps = Bukkit.getServer().getTPS()[0];
            double roundOff = Math.round(tps * 100.0) / 100.0;

            return (tps > 17.0D ? "§a" : (tps > 13.0D ? "§e" : (tps > 5.0D ? "§c" : "§4"))) + (tps > 20.0D ? "" : "") + String.format("%.2f", Math.min((double)Math.round(tps * 100.0D) / 100.0D, 20.0D));
        }

//        // %example_placeholder2%
//        if(identifier.equals("placeholder2")){
//            return "placeholder2 works";
//        }

        // We return null if an invalid placeholder (f.e. %example_placeholder3%)
        // was provided
        return null;
    }
}
