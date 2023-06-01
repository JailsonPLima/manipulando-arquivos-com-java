import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App {
  public static void main(String[] args) {
    final String nomeDoArquivo = "dados.json";
    final String regex = "\\d+.\\d+";
    
    try {
      lerExibirNoConsole(nomeDoArquivo, regex);
    } catch(FileNotFoundException e) {
      // e.printStackTrace();
      System.out.println(
        "Revise o caminho ou nome do arquivo.\n" 
        + e.getCause() + "\n" + e.getMessage()
      );
    } catch(IOException e) {
      // e.printStackTrace();
      System.out.println(
        "Erro inesperado do sistema.\n" + e.getCause() + "\n" + e.getMessage()
      );
    } finally {
      System.out.println("\nPrograma encerrado.");
    }
  }

  public static void lerExibirNoConsole(String nomeDoArquivo, String regex) throws IOException {
    Pattern pattern = Pattern.compile(regex);

    List<Double> numeros = new LinkedList<>();

    File arquivo = new File(nomeDoArquivo);

    BufferedReader leitor = new BufferedReader(new FileReader(arquivo.getName()));
    String linha = leitor.readLine();

    while(linha != null) {
      Matcher matcher = pattern.matcher(linha);
      if(matcher.find()) {
        String dado = matcher.group();
        Double stringParaDouble = Double.parseDouble(dado);
        numeros.add(stringParaDouble);
      }
      linha = leitor.readLine();
    }

    for(Double n : numeros) {
      if(numeros.indexOf(n) == numeros.size() - 1) {
        System.out.print(n + ".");
      } else {
        System.out.print(n + ", ");
      }
    }

    System.out.println("\n\nO maior número da lista é: " + Collections.max(numeros));
    
    leitor.close();
  }
}
