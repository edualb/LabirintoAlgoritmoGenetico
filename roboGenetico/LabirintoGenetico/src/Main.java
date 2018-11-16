import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.SwingUtilities;

import cromossomo.Cromossomo;
import view.View;


public class Main {
	
	private static Scanner scanner;
	static int populacao;
	static int geracao;

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {				
				scanner = new Scanner(System.in);
				
				System.out.println("*****************Algoritmo Genético*****************");
				System.out.println("**                                                **");
				System.out.println("** Tipo de CrossOver: De ponto;                   **");
				System.out.println("** Taxa de CrossOver: 0,7;                        **");
				System.out.println("** Tipo de mutação: Clássica;                     **");
				System.out.println("** Taxa de mutação: 0,2;                          **");
				System.out.println("** Aptidão: Avaliação;                            **");
				System.out.println("**                                                **");
				System.out.println("** Seleção: - Inicialização aleaória              **");
				System.out.println("**          - Elitismo                            **");
				System.out.println("**          - Torneio                             **");
				System.out.println("**                                                **");
				System.out.println("****************************************************");
				
				System.out.println("Informe o número da população:");
				populacao = scanner.nextInt();
				
				System.out.println("Informe o número de geração:");
				geracao = scanner.nextInt();
				
				View view = new View();
				
				for(int i = 0 ; i < geracao ; i++) {
					List<Cromossomo> geracaoLista = new ArrayList<Cromossomo>();
					for (int j = 0 ; j < populacao ; j++) {
						Cromossomo cromossomo = new Cromossomo(view);
						view.setCromossomo(cromossomo);
						view.calculaCromossomo();
						
						geracaoLista.add(cromossomo);
					}

					for (Cromossomo cromossomo : geracaoLista) {
						System.out.println("Cromossomo: " + cromossomo.getCromossomo());
						System.out.println("avaliacao: " + cromossomo.getAvaliacao());
						System.out.println("caminho: " + cromossomo.getCaminho());
						System.out.println("");
					}
				}
				
				view.setVisible(true);
			}
		});
	}
	
}
