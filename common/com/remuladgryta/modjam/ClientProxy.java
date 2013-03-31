package com.remuladgryta.modjam;

import java.io.File;
import java.net.URL;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraftforge.common.MinecraftForge;

import cpw.mods.fml.common.FMLLog;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import edu.cmu.sphinx.util.props.ConfigurationManager;

public class ClientProxy extends CommonProxy {
	private ConfigurationManager cm;
	
	@Override
	public void preInit(FMLPreInitializationEvent e){
		super.preInit(e);
		URL url = this.getClass().getResource("/modjamconfig.xml");
		
		Handler[] handlers = FMLLog.getLogger().getHandlers();
		cm = new ConfigurationManager(url);
		cm.getRootLogger().setLevel(Level.SEVERE);
		Logger l = FMLLog.getLogger();
		for (Handler handler : handlers) {
			l.addHandler(handler);
		}
	}
	
	@Override
	public void init(FMLInitializationEvent e){
		super.init(e);

		API.get();//trigger api load
	}
	
	@Override
	public void postInit(FMLPostInitializationEvent e){
		super.postInit(e);
		API.get().compileDictionary();
		API.get().compileGrammar();
		
	}
	
	protected ConfigurationManager getConfigManager(){
		return cm;
	}
}
