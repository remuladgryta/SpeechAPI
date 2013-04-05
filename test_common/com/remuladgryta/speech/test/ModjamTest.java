package com.remuladgryta.speech.test;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.Init;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;

@Mod(modid = "modjamtest",name ="Mod jam test mod 2013", version = "0.2", dependencies = "required-after:SpeechAPI")
public class ModjamTest {

	@SidedProxy(clientSide = "com.remuladgryta.speech.test.ClientProxy", serverSide = "com.remuladgryta.speech.test.CommonProxy")
	public static CommonProxy proxy;


	@Init
	public void init(FMLInitializationEvent e) {
		proxy.init();
	}

}
