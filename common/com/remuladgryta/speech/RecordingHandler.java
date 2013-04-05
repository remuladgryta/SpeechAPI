package com.remuladgryta.speech;

import java.util.logging.Level;

import javax.speech.recognition.RuleGrammar;

import com.sun.speech.engine.recognition.BaseRuleGrammar;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.util.props.ConfigurationManager;

@SideOnly(Side.CLIENT)
public class RecordingHandler {
	private static RecordingHandler instance;
	private Recognizer recognizer;
	private Microphone microphone;
	private RecognitionThread recogThread;
	private ConfigurationManager cm;
	private RuleGrammar ruleGrammar;
	private RecordingHandler() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			cm = ((ClientProxy) SpeechAPI.proxy)
					.getConfigManager();
			recognizer = (Recognizer) cm.lookup("recognizer");
			
			microphone = (Microphone) cm.lookup("microphone");
			ruleGrammar = new BaseRuleGrammar(ConfigurationHelper.get().jsapiRecognizer, ConfigurationHelper.get().jsgfGrammar.getRuleGrammar());
		}

	}

	public static RecordingHandler instance() {
		if (instance == null)
			instance = new RecordingHandler();
		return instance;
	}

	public void startRecording() {
		if(microphone == null || !microphone.startRecording()){
			LogHelper.log(Level.SEVERE,"Unable to start microphone");
			return;
		}
			//recognizer = (Recognizer) cm.lookup("recognizer");
			recogThread = new RecognitionThread(recognizer, ruleGrammar);	
			recogThread.start();
	}

	public void stopRecording() {
		recogThread.cancel();
		recogThread = null;
		microphone.stopRecording();
		
	}
}
