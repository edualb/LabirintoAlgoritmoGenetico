import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import cromossomo.Cromossomo;
import view.View;


public class Main {
	
	private static Scanner scanner;
	private static Cromossomo melhorCromossomo = null;
	static int populacao;
	static int geracao;
	static int melhorAvalicao;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {				
				scanner = new Scanner(System.in);
				
				System.out.println("*****************Algoritmo Gen�tico*****************");
				System.out.println("**                                                **");
				System.out.println("** Tipo de CrossOver: De ponto;                   **");
				System.out.println("** Taxa de CrossOver: 0,7;                        **");
				System.out.println("** Tipo de muta��o: Cl�ssica;                     **");
				System.out.println("** Taxa de muta��o: 0,2;                          **");
				System.out.println("** Aptid�o: Avalia��o;                            **");
				System.out.println("**                                                **");
				System.out.println("** Sele��o: - Inicializa��o alea�ria              **");
				System.out.println("**          - Elitismo                            **");
				System.out.println("**          - Torneio                             **");
				System.out.println("**                                                **");
				System.out.println("****************************************************");
				
				System.out.println("Informe o n�mero da popula��o:");
				populacao = scanner.nextInt();
				
				System.out.println("Informe o n�mero de gera��o:");
				geracao = scanner.nextInt();
				
				View view = new View();
				
				for(int i = 0 ; i < geracao ; i++) {
					List<Cromossomo> geracaoLista = new ArrayList<Cromossomo>();
					for (int j = 0 ; j < populacao ; j++) {
						Cromossomo cromossomo = new Cromossomo(view);
						view.setCromossomo(cromossomo);
						view.calculaCromossomo();
						melhorAvalicao = cromossomo.getAvaliacao();
						geracaoLista.add(cromossomo);
					}

					for (Cromossomo cromossomo : geracaoLista) {
						if (cromossomo.getAvaliacao() < melhorAvalicao) {
							melhorCromossomo = cromossomo;
						}
					}
					
					view.setCromossomo(melhorCromossomo);
					view.calculaCromossomo();
					
					System.out.println("******************** GERA��O " + i + " ********************");
					System.out.println(" Cromossomo: " + melhorCromossomo.getCromossomo());
					System.out.println(" avaliacao: " + melhorCromossomo.getAvaliacao());
					System.out.println("***********************************************************");
					System.out.println("");
				}
				
				view.setVisible(true);
			}
		});
	}
	
}
