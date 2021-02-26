package mc.lovexyn0827.mcwmem;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Properties;

import org.apache.logging.log4j.LogManager;

import net.fabricmc.loader.api.FabricLoader;

public class Options extends Properties{
	private static final long serialVersionUID = 2005082720050104L;
	private static final File OPTION_FILE = new File(FabricLoader.getInstance().getGameDir().toString()+"/mcwmem.prop");
	private final Options defaults;
	
	public Options(boolean buildDefault) {
		super();
		System.out.println(OPTION_FILE.getAbsolutePath());
		if(!buildDefault) {
			this.defaults = new Options(true);
			this.defaults.put("alignMode", "topRight");
			this.defaults.put("entityExplosionRaysVisiblity", "false");
			this.defaults.put("entityExplosionRayLife", "300");
			this.defaults.put("serverSyncedBox", "false");
			this.defaults.put("mobFastKill", "false");
			this.defaults.put("enabledTools", "false");
			this.defaults.put("entityExplosionInfluence", "false");
			this.load();
		}else {
			this.defaults = null;
		}
	}
	
	public String getDefault(String key) {
		return this.defaults.getProperty(key);
	}
	
	public void load() {
		try (FileInputStream in =new FileInputStream(OPTION_FILE)) {
			load(in);
		} catch (IOException e) {
			if(e instanceof FileNotFoundException) {
				writeDefault();
				load();
				return;
			}
			LogManager.getLogger().fatal("Failed to open mcwmem.prop");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	public void save() {
		try (FileOutputStream in =new FileOutputStream(OPTION_FILE)) {
			store(in,"MCWMEMod Options");
		} catch (IOException e) {
			LogManager.getLogger().fatal("Failed to write mcwmem.prop");
			e.printStackTrace();
			System.exit(-1);
		}
	}
	
	private void writeDefault() {
		this.defaults.save();
	}
}