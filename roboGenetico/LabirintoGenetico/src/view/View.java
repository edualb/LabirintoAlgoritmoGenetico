package view;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import javax.swing.JFrame;

import acharNo.achaNo;
import cromossomo.Cromossomo;

public class View extends JFrame{
	
	private static final long serialVersionUID = -2999916789569250780L;

	/*
	 *  0 -> N� n�o visitado
	 *  1 -> Parede
	 *  2 -> N� visitado
	 *  9 -> N� alvo
	 *  5 -> N� secund�rio
	 */
	List<Integer> caminho = new ArrayList<Integer>();
	int [][] labirinto;
	private Scanner scanner;
	int inicioX;
	int inicioY;
	int fimX;
	int fimY;
	int passosDados;
	Cromossomo cromossomo;
	
	public int[][] getLabirinto() {
		return labirinto;
	}

	public void setLabirinto(int[][] labirinto) {
		this.labirinto = labirinto;
	}

	public int getInicioX() {
		return inicioX;
	}

	public void setInicioX(int inicioX) {
		this.inicioX = inicioX;
	}

	public int getInicioY() {
		return inicioY;
	}

	public void setInicioY(int inicioY) {
		this.inicioY = inicioY;
	}

	public int getFimX() {
		return fimX;
	}

	public void setFimX(int fimX) {
		this.fimX = fimX;
	}

	public int getFimY() {
		return fimY;
	}

	public void setFimY(int fimY) {
		this.fimY = fimY;
	}

	public Cromossomo getCromossomo() {
		return cromossomo;
	}

	public void setCromossomo(Cromossomo cromossomo) {
		this.cromossomo = cromossomo;
	}

	public View() {
		labirinto = geraLabirinto();
		inicioX = 1;
		inicioY = 1;
		setTitle("Labirinto Gen�tico");
		setSize(720, 720);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	
	public void calculaCromossomo() {
		List<String> cromossomo = this.cromossomo.getCromossomo();
		for (int i = 0 ; i < cromossomo.size() - 1 ; i++) {
			String coordenadaInicio = cromossomo.get(i);
			String coordenadaFim = cromossomo.get(i+1);
			
			this.inicioX = Integer.parseInt(coordenadaInicio.substring(0, 3));
			this.inicioY = Integer.parseInt(coordenadaInicio.substring(3, 6));
			this.fimX = Integer.parseInt(coordenadaFim.substring(0, 3));
			this.fimY = Integer.parseInt(coordenadaFim.substring(3, 6));
			
			achaNo.procuraCaminho(labirinto, inicioX, inicioY, fimX, fimY, this.cromossomo.getCaminho());
			
			this.cromossomo.setAvaliacao(this.cromossomo.getCaminho().size());
			
			this.caminho = this.cromossomo.getCaminho();
			
			this.inicioX = 1;
			this.inicioY = 1;
		}
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		
		g.translate(50, 50);
		
		for(int linha = 0 ; linha < labirinto.length ; linha++) {
			for (int coluna = 0; coluna < labirinto[0].length ; coluna++) {
				Color color;
				
				switch (labirinto[linha][coluna]) {
					case 1 : 
						color = Color.BLACK;
						break;
					case 9 :
						color = Color.RED;
						break;
					case 5 :
						color = Color.BLUE;
						break;
					default:
						color = Color.WHITE;
						break;
				}
				
				g.setColor(color);
				g.fillRect(3 * coluna, 3 *linha, 3, 3);
				g.setColor(Color.BLACK);
				g.drawRect(3 *coluna, 3 * linha, 3, 3);
			}
		}
		
		for (int p = 0; p < caminho.size() ; p += 2) {
			int caminhoX = caminho.get(p);
			int caminhoY = caminho.get(p + 1);
			g.setColor(Color.GREEN);
			g.fillRect(3 * caminhoX, 3 * caminhoY, 3, 3);
		}
	}
	
	private int[][] geraLabirinto() {
		int [][] labirinto = new int[202][202];
		scanner = new Scanner(System.in);
		
		System.out.println("Inserira as coordenadas da sa�da x e y: ");
		fimX = scanner.nextInt();
		fimY = scanner.nextInt();
		
		labirinto[fimY][fimX] = 9;
		
		for (int  linha = 0 ; linha < labirinto.length ; linha++) {
			for (int coluna = 0 ; coluna < labirinto[0].length ; coluna++) {
				if (linha == 0 || coluna == 0 || linha == 201 || coluna == 201) {
					labirinto[linha][coluna] = 1;
				} else if (labirinto[linha][coluna] != 9) {
					labirinto[linha][coluna] = 0;
				}
			}
		}
		
		for (int i = 0 ; i < 1000 ; i++) {
			int geraX = getRandomNumberInRange(0, 201);
			int geraY = getRandomNumberInRange(0, 201);
			
			while (labirinto[geraY][geraX] != 9 && labirinto[geraY][geraX] != 1 && geraX != 1 && geraY != 1) {
				labirinto[geraY][geraX] = 1;
			}
		}
		
		return labirinto;
		
	}
	
	//https://www.mkyong.com/java/java-generate-random-integers-in-a-range/
	private static int getRandomNumberInRange(int min, int max) {

		if (min >= max) {
			throw new IllegalArgumentException("max must be greater than min");
		}

		Random r = new Random();
		return r.nextInt((max - min) + 1) + min;
	}
}
