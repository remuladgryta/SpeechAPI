SpeechAPI
==========

Inspired by modjam easter 2013

This mod depends on the third party library CMU Sphinx 4 available at http://cmusphinx.sourceforge.net/ as well as the Java Speech API.
The specific build artifacts from those projects that are used are:

* jsapi.jar,
* jsapi-1.-base.jar,
* sphinx4.jar,
* WJJ_8gau_13dCep_16k_40mel_130Hz_6800Hz.jar


These dependencies are bundled with the compiled version of this mod according to their respective licenses (http://cmusphinx.sourceforge.net/sphinx4/license.terms and http://pastebin.com/gSGM0rUh).

USAGE
-----
The SpeechAPI.jar doesn't do anything on its own, but rather provides an API for other mods. 
The modjamtest.jar however makes very basic use of this api. Type start into the chat window and it'll start listening for speech input and log any detected valid sentences. Type stop and it'll stop listening.

Valid sentences to speak are:
* `<greeting> <name>` where `<greeting>` is either `"hello"` or `"good morning"` and `<name>` is either  `"paul"`, `"evandro"` or `"will"`.
* `"fus"` (homeage to thu'um)

**There's more to come, so stay tuned!**
