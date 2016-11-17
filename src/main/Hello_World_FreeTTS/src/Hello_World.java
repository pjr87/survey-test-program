import com.sun.speech.freetts.Voice;
import com.sun.speech.freetts.VoiceManager;

public class Hello_World {

    public static void main(String[] args) {
        
        String voiceName = "kevin16";
        
        VoiceManager voiceManager = VoiceManager.getInstance();
        Voice voice = voiceManager.getVoice(voiceName);

        voice.allocate();
        voice.speak("Hello world!");
        voice.deallocate();
    }
}
