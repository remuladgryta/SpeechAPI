package com.remuladgryta.speech;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;

@Mod(modid="SpeechAPI",name="Speech API core", version="1.0")
public class SpeechAPI {
	@Instance("SpeechAPI")
	public static SpeechAPI instance;
	
	@SidedProxy(clientSide="com.remuladgryta.speech.ClientProxy",serverSide="com.remuladgryta.speech.CommonProxy")
	public static CommonProxy proxy;
	
	@PreInit
	public void preinit(FMLPreInitializationEvent e){
		proxy.preInit(e);
	}
	
	@Init
	public void init(FMLInitializationEvent e){
		proxy.init(e);
	}
	
	@PostInit
	public void postInit(FMLPostInitializationEvent e){
		proxy.postInit(e);
	}
}
