package br.jogoteca.system.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class FileUtilRepository {
	public static Object ReadFromFile(String fileName) {
        Object instanciaLocal = null;

        File in = new File(fileName);
        FileInputStream fis = null;
        ObjectInputStream ois = null;
        try {
            fis = new FileInputStream(in);
            ois = new ObjectInputStream(fis);
            
            // Se alguma exceção ocorrer, um objeto NULL será retornado
            instanciaLocal = ois.readObject();
        } catch (Exception e) {
            System.out.println("Não há arquivo com o nome '" + fileName + "' para ser processado. Um novo arquivo será criado");
        } finally {
            if (ois != null) {
                try {
                    ois.close();
                } catch (IOException e) {
                }
            }
        }

        return instanciaLocal;
	}
	public static void saveFile(Object instance, String fileName) {
		 if (instance == null) {
	            return;
	        }
	        File out = new File(fileName);
	        FileOutputStream fos = null;
	        ObjectOutputStream oos = null;

	        try {
	            fos = new FileOutputStream(out);
	            oos = new ObjectOutputStream(fos);
	            oos.writeObject(instance);
	        } catch (Exception e) {
	            e.printStackTrace();
	        } finally {
	            if (oos != null) {
	                try {
	                    oos.close();
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }
	        }
		
	}

}
