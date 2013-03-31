package com.remuladgryta.modjam.test;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "modjamtest", version = "0.1", dependencies = "required-after:ModjamMod")
public class ModjamTest {

	@SidedProxy(clientSide = "com.remuladgryta.modjam.test.ClientProxy", serverSide = "com.remuladgryta.modjam.test.CommonProxy")
	public static CommonProxy proxy;


	@Init
	public void init(FMLInitializationEvent e) {
		proxy.init();
	}

}
