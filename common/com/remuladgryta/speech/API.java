package com.remuladgryta.speech;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

import edu.cmu.sphinx.linguist.acoustic.UnitManager;
import edu.cmu.sphinx.linguist.dictionary.FastDictionary;
import edu.cmu.sphinx.jsgf.JSGFGrammar;
import edu.cmu.sphinx.jsgf.JSGFGrammarException;
import edu.cmu.sphinx.jsgf.JSGFGrammarParseException;
import edu.cmu.sphinx.recognizer.Recognizer;
import edu.cmu.sphinx.frontend.util.Microphone;
import edu.cmu.sphinx.result.Result;
import edu.cmu.sphinx.util.props.ConfigurationManager;
import edu.cmu.sphinx.util.props.PropertyException;
import java.io.IOException;
import java.net.URL;

import javax.speech.EngineException;
import javax.speech.EngineStateError;
import javax.speech.recognition.GrammarException;
import javax.speech.recognition.Rule;
import javax.speech.recognition.RuleGrammar;
import javax.speech.recognition.RuleParse;

import com.sun.speech.engine.recognition.BaseRecognizer;
import com.sun.speech.engine.recognition.BaseRuleGrammar;

@SideOnly(Side.CLIENT)
public class API {
	
	private static API instance = null;

	

	private API() {

	}


	public static API get() {
		if (SpeechAPI.proxy != null && instance == null && ((ClientProxy)SpeechAPI.proxy).getConfigManager() != null) {
			instance = new API();
		}
		return instance;
	}

	public void addGrammarEntry(String name, String jsgf, boolean isPublic) {
		ConfigurationHelper.get().addGrammarEntry(name, jsgf, isPublic);
	}

	public void addDictionaryEntry(URL u) {
		ConfigurationHelper.get().addDictionaryEntry(u);
	}

	public int requestRecording(String modname) {
		return RecordingTracker.instance().requestRecording(modname);
	}
	
	public int releaseRecordingHandler(String modname) {
		return RecordingTracker.instance().releaseRecordingHandler(modname);
	}
	
	public boolean releaseAllHandlers(String modname){
		return RecordingTracker.instance().releaseAllHandlers(modname);
	}


}
