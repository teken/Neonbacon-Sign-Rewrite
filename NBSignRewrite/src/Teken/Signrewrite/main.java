package Teken.Signrewrite;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin{
	public static Plugin instance;
	static final String name = "Rewrite Sign";
	static final String textName = "["+name+"] ";

	public main(){
		instance = this;
	}

	@Override
	public void onEnable(){
		getLogger().info(name+" has been enabled");
		this.getCommand("rws").setExecutor(this);
		this.getCommand("rewritesign").setExecutor(this);
	}

	@Override
	public void onDisable(){
		getLogger().info(name+" has been disabled");
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args){
		Player p = (Player) sender;
		if (cmd.getName().equalsIgnoreCase("rewritesign") || cmd.getName().equalsIgnoreCase("rws")) {
			if(args.length == 0){
				sendHelpMessage(p);
				return true;
			}else if(args[0].equalsIgnoreCase("line")){
				int lineNo = 0;
				String newLine = "";
				try{
					lineNo = Integer.parseInt(args[1]) - 1;
				}catch(Exception e){}
				for(int x = 2;x < args.length;x++){
					newLine += " "+args[x];
				}
				textToColor(newLine);
				if(newLine.length() > 15){
					p.sendMessage(textName+"New line is too long, it has to be 15 characters");
					return true;
				}else{
					Block b = p.getTargetBlock(null, 100);
					if(b != null){
						if(b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST){
							Sign s = (Sign)b.getState(); 
							s.setLine(lineNo, newLine);
							s.update();
						}else{
							p.sendMessage(textName+"Selected block is not a sign");
						}
						return true;
					}else{
						p.sendMessage(textName+"Unable to find selected block");
						return true;
					}
				}
			}else if(args[0].equalsIgnoreCase("sign")){
				String newLine = "";
				for(int x = 2;x < args.length;x++){
					newLine += " "+args[x];
				}
				textToColor(newLine);
				String[] newLines = newLine.split(",");
				for(int q = 0;q < newLines.length;q++){
					if(newLines[q].length() > 15){
						p.sendMessage(textName+"New line is too long, it has to be 15 characters");
						return true;
					}
				}
				Block b = p.getTargetBlock(null, 100);
				if(b != null){
					if(b.getType() == Material.WALL_SIGN || b.getType() == Material.SIGN_POST){
						Sign s = (Sign)b.getState();
						for(int x=0;x<4;x++){
							s.setLine(x, newLines[x]);	
						}
						s.update();
					}else{
						p.sendMessage(textName+"Selected block is not a sign");
					}
					return true;
				}else{
					p.sendMessage(textName+"Unable to find selected block");
					return true;
				}
			}else{
				sendHelpMessage(p);
				return true;
			}
		}
		return false;
	}

	public void sendHelpMessage(Player p){
		String[] cmd = new String[] {
				"===============Moderator Mode===============",
				ChatColor.GREEN + "/rws - All commands can be run using this alias",
				ChatColor.GREEN + "/rewritesign line <Line Number> <Line Message>",
				ChatColor.GREEN + "/rewritesign sign <Line 1 Message, Line 2 Message, Line 3 Message, Line 4 Message>"};
		String[] admin = new String[] { "ADMIN COMMANDS:",
				ChatColor.DARK_RED + "/modmode admin ban <Player Name> - ban a player from using "+name.toLowerCase(),
				ChatColor.DARK_RED + "/modmode admin unban <Player Name> - unban a player",
				ChatColor.DARK_RED + "/modmode admin kick <Player Name> - kicks people out of "+name.toLowerCase()};
		p.sendMessage(cmd);
		if(p.isOp())p.sendMessage(admin);
		p.sendMessage("============================================");
	}

	public static String textToColor(String text)
	{
		text = text.replaceAll("&0", ChatColor.BLACK+"");
		text = text.replaceAll("&1", ChatColor.DARK_BLUE+"");
		text = text.replaceAll("&2", ChatColor.DARK_GREEN+"");
		text = text.replaceAll("&3", ChatColor.DARK_AQUA+"");
		text = text.replaceAll("&4", ChatColor.DARK_RED+"");
		text = text.replaceAll("&5", ChatColor.DARK_PURPLE+"");
		text = text.replaceAll("&6", ChatColor.GOLD+"");
		text = text.replaceAll("&7", ChatColor.GRAY+"");
		text = text.replaceAll("&8", ChatColor.DARK_GRAY+"");
		text = text.replaceAll("&9", ChatColor.BLUE+"");
		text = text.replaceAll("&A", ChatColor.GREEN+"");
		text = text.replaceAll("&B", ChatColor.AQUA+"");
		text = text.replaceAll("&C", ChatColor.RED+"");
		text = text.replaceAll("&D", ChatColor.LIGHT_PURPLE+"");
		text = text.replaceAll("&E", ChatColor.YELLOW+"");
		text = text.replaceAll("&F", ChatColor.WHITE+"");
		text = text.replaceAll("&a", ChatColor.GREEN+"");
		text = text.replaceAll("&b", ChatColor.AQUA+"");
		text = text.replaceAll("&c", ChatColor.RED+"");
		text = text.replaceAll("&d", ChatColor.LIGHT_PURPLE+"");
		text = text.replaceAll("&e", ChatColor.YELLOW+"");
		text = text.replaceAll("&u", ChatColor.UNDERLINE+"");
		text = text.replaceAll("&U", ChatColor.UNDERLINE+"");
		text = text.replaceAll("&n", ChatColor.BOLD+"");
		text = text.replaceAll("&N", ChatColor.BOLD+"");
		text = text.replaceAll("&o", ChatColor.ITALIC+"");
		text = text.replaceAll("&O", ChatColor.ITALIC+"");
		text = text.replaceAll("&i", ChatColor.ITALIC+"");
		text = text.replaceAll("&I", ChatColor.ITALIC+"");
		//Magic will be built in, in version 1.2
		text = text.replaceAll("&k", ChatColor.MAGIC+"");
		text = text.replaceAll("&K", ChatColor.MAGIC+"");
		return text;
	}
}


