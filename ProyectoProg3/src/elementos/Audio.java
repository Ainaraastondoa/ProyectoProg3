package elementos;

import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.sound.sampled.*;

public class Audio extends Thread {
	
	public Audio(String string) {
		super(string);
	}
	
	public static void lanzaAudioEnHilo( String fichero ) {
	    Audio.lanzaAudio(Audio.class.getResource(fichero));
	}
	public void run() {
		lanzaAudioEnHilo(super.getName());
	}
	
	/** Lanza un audio indicado en un fichero wav
	 * @param ficheroWav	Path correcto del fichero wav indicado
	 */
	public void lanzaAudio( String ficheroWav ) {
	    int BUFFER_SIZE = 128000;
	    AudioInputStream flujoAudio = null;
	    AudioFormat formatoAudio = null;
	    SourceDataLine lineaDatosSonido = null;
	    File ficSonido = null;
        try {
            ficSonido = new File(ficheroWav);
            flujoAudio = AudioSystem.getAudioInputStream(ficSonido);
            formatoAudio = flujoAudio.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, formatoAudio);
            lineaDatosSonido = (SourceDataLine) AudioSystem.getLine(info);
            lineaDatosSonido.open(formatoAudio);
            lineaDatosSonido.start();
            int bytesLeidos = 0;
            byte[] bytesAudio = new byte[BUFFER_SIZE];
            while (bytesLeidos != -1) {
                try {
                    bytesLeidos = flujoAudio.read(bytesAudio, 0, bytesAudio.length);
                } catch (IOException e) { }
                if (bytesLeidos >= 0) {
                    lineaDatosSonido.write(bytesAudio, 0, bytesLeidos);
                }
            }
        } catch (Exception e) {
        	// Excepci�n si el fichero es nulo, err�neo, o wav incorrecto
        }
    	if (lineaDatosSonido != null) {
            lineaDatosSonido.drain();
            try {
            	lineaDatosSonido.close();
                flujoAudio.close();
            } catch (Exception e) {}
    	}
	}
	
	/** Lanza un audio indicado en un fichero wav
	 * @param ficheroWav	Recurso del fichero wav
	 */
	public static void lanzaAudio( URL recurso ) {
	    int BUFFER_SIZE = 128000;
	    AudioInputStream flujoAudio = null;
	    AudioFormat formatoAudio = null;
	    SourceDataLine lineaDatosSonido = null;
        try {
            flujoAudio = AudioSystem.getAudioInputStream(recurso);
            formatoAudio = flujoAudio.getFormat();
            DataLine.Info info = new DataLine.Info(SourceDataLine.class, formatoAudio);
            lineaDatosSonido = (SourceDataLine) AudioSystem.getLine(info);
            lineaDatosSonido.open(formatoAudio);
            lineaDatosSonido.start();
            int bytesLeidos = 0;
            byte[] bytesAudio = new byte[BUFFER_SIZE];
            while (bytesLeidos != -1) {
                try {
                    bytesLeidos = flujoAudio.read(bytesAudio, 0, bytesAudio.length);
                } catch (IOException e) { }
                if (bytesLeidos >= 0) {
                    lineaDatosSonido.write(bytesAudio, 0, bytesLeidos);
                }
            }
        } catch (Exception e) {
        	// Excepci�n si el fichero es nulo, err�neo, o wav incorrecto
        }
    	if (lineaDatosSonido != null) {
            lineaDatosSonido.drain();
            try {
            	lineaDatosSonido.close();
                flujoAudio.close();
            } catch (Exception e) {}
    	}
	}
}
