package com.remuladgryta.modjam;

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
	private LinkedList<URL> dictList;
	private LinkedList<JSGFEntry> grammarList;
	private Recognizer recognizer;
	private JSGFGrammar jsgfGrammar;
	private BaseRecognizer jsapiRecognizer;
	private RuleGrammar ruleGrammar;
	private	ConfigurationManager cm;
	private static API instance = null;

	private class JSGFEntry {
		private String name, rule;
		private boolean isPublic;

		public JSGFEntry(String name, String rule, boolean isPublic) {
			this.name = name;
			this.rule = rule;
			this.isPublic = isPublic;
		}

		public void addTo(RuleGrammar ruleGrammar) {
			try{
				Rule newRule = ruleGrammar.ruleForJSGF(rule);
				ruleGrammar.setRule(name, newRule, isPublic);
				ruleGrammar.setEnabled(name,true);
			}catch(GrammarException e){
				LogHelper.log(Level.WARNING, "Someone tried to add the rule '"+rule+"', but it's malformed. Skipping.");
			}
			
			
		}
	}

	private API(ConfigurationManager configurationManager) {
		cm = configurationManager;
		dictList = new LinkedList<URL>();
		grammarList = new LinkedList<JSGFEntry>();
	}


	public static API get() {
		if (ModjamMod.proxy != null && instance == null && ((ClientProxy)ModjamMod.proxy).getConfigManager() != null) {
			instance = new API(((ClientProxy)ModjamMod.proxy).getConfigManager());
		}
		return instance;
	}

	public void addGrammarEntry(String name, String jsgf, boolean isPublic) {
		grammarList.add(new JSGFEntry(name, jsgf, isPublic));
	}

	public void addDictionaryEntry(URL u) {
		dictList.add(u);
	}

	protected void compileGrammar() {
		recognizer = (Recognizer) cm.lookup("recognizer");
		jsgfGrammar = (JSGFGrammar) cm.lookup("jsgfGrammar");

		jsapiRecognizer = new BaseRecognizer(jsgfGrammar.getGrammarManager());
		try {
			jsapiRecognizer.allocate();

			recognizer.allocate();
			jsgfGrammar.loadJSGF("empty");
		} catch (Exception e) {
			e.printStackTrace();
		}
		ruleGrammar = new BaseRuleGrammar(jsapiRecognizer,
				jsgfGrammar.getRuleGrammar());
		
		for(JSGFEntry e:grammarList){
			e.addTo(ruleGrammar);
		}
		
		try {
			jsgfGrammar.commitChanges();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSGFGrammarParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JSGFGrammarException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	protected void compileDictionary() {
		/*
		 * File dir = new File(Minecraft.getMinecraftDir(),"resources/speech");
		 * dir.mkdirs(); File f = new File(dir,"dictionary.dict");
		 * 
		 * try{ f.createNewFile(); FileOutputStream fos = new
		 * FileOutputStream(f); byte[] buffer = new byte[1000]; for(URL dpart :
		 * dictList){ InputStream is = dpart.openStream(); int len; while((len =
		 * is.read(buffer)) != -1){ fos.write(buffer, 0,len ); } } fos.close();
		 * }catch (IOException e){ e.printStackTrace(); } URL u = null; try{ u =
		 * f.toURI().toURL(); }catch(MalformedURLException e){
		 * e.printStackTrace(); }
		 */

		FastDictionary d = (FastDictionary) cm.lookup("dictionary");

		d = new FastDictionary(
				d.getWordDictionaryFile(),// u,
				d.getFillerDictionaryFile(), dictList, false, "", false, false,
				(UnitManager) cm.lookup("unitManager"));

		cm.removeConfigurable("dictionary");
		cm.addConfigurable(d, "dictionary");

		try {
			d.allocate();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
