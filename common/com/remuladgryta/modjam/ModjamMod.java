package com.remuladgryta.modjam;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.Mod.PreInit;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.Mod.PostInit;

@Mod(modid="ModjamMod",name="Mod jam mod 2013", version="0.2")
public class ModjamMod {
	@Instance("ModjamMod")
	public static ModjamMod instance;
	
	@SidedProxy(clientSide="com.remuladgryta.modjam.ClientProxy",serverSide="com.remuladgryta.modjam.CommonProxy")
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
