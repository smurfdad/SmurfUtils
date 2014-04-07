package es.smurfdad.utils.windows;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class CommandLine {

    public static List<String> execute(String pComando) throws IOException{
        List<String> resultado = new ArrayList<String>();
        BufferedReader input = null;
        try{
        Process pr = Runtime.getRuntime().exec(pComando);
        input = new BufferedReader(new InputStreamReader(pr.getInputStream()));

        // BufferedWriter output = new BufferedWriter(new FileWriter("networklist.properties", false));

        String linea;

        while ((linea = input.readLine()) != null) {
            resultado.add(linea);
        }
        }catch(IOException e){
            throw e;
        }finally{
            if (input != null){
                input.close();
            }
        }

        return resultado;

    }
}
