package cromossomo;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import view.View;

public class Cromossomo {
	
	int [][] labirinto;
	List<String> cromossomo = new ArrayList<String>();
	List<Integer> caminho = new ArrayList<Integer>();
	int avaliacao;
	int atualY;
	
	public Cromossomo() {
		
	}
	
	public Cromossomo(View view) {
		this.labirinto = view.getLabirinto();
		atualY = view.getInicioY();
		
		String primeiroGene = completeToLeft(String.valueOf(view.getInicioX()), '0', 3) + completeToLeft(String.valueOf(view.getInicioY()), '0', 3);
		cromossomo.add(primeiroGene);
		
		for (int i = 1 ; i < 8 ; i++) {
			cromossomo.add(montaGene(view.getFimX(), view.getFimX()));
		}
		
		String ultimoGene = completeToLeft(String.valueOf(view.getFimX()), '0', 3) + completeToLeft(String.valueOf(view.getFimY()), '0', 3);
		cromossomo.add(ultimoGene);
		
		view.setLabirinto(this.labirinto);
	}
	
	public int[][] getLabirinto() {
		return labirinto;
	}

	public List<String> getCromossomo() {
		return cromossomo;
	}

	public int getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(int avaliacao) {
		this.avaliacao = avaliacao;
	}

	public List<Integer> getCaminho() {
		return caminho;
	}

	public void setCaminho(List<Integer> caminho) {
		this.caminho = caminho;
	}

	public String montaGene(int fimX, int fimY) {
		int geraX = getRandomNumberInRange(1, fimX - 1);
		int geraY = getRandomNumberInRange(1, fimY - 1);
		
		do {
			geraX = getRandomNumberInRange(1, fimX - 1);
			geraY = getRandomNumberInRange(1, fimY - 1);
			
			if (labirinto[geraY][geraX] == 9) {
				geraX = getRandomNumberInRange(1, fimX - 1);
				geraY = getRandomNumberInRange(1, fimY - 1);
			} else if (labirinto[geraY][geraX] == 1) {
				geraX = getRandomNumberInRange(1, fimX - 1);
				geraY = getRandomNumberInRange(1, fimY - 1);
			} else if (labirinto[geraY][geraX] == 5) {
				geraX = getRandomNumberInRange(1, fimX - 1);
				geraY = getRandomNumberInRange(1, fimY - 1);
			} else if (geraX == 1) {
				geraX = getRandomNumberInRange(1, fimX - 1);
				geraY = getRandomNumberInRange(1, fimY - 1);
			} else if (geraY == 1) {
				geraX = getRandomNumberInRange(1, fimX - 1);
				geraY = getRandomNumberInRange(1, fimY - 1);
			}
		} while (geraY < this.atualY);
		
		this.atualY = geraY;
		labirinto[geraY][geraX] = 5;
		
		String x = completeToLeft(String.valueOf(geraX), '0', 3);
		String y = completeToLeft(String.valueOf(geraY), '0', 3);

		return x + y;
	}
	
	
	//http://www.guj.com.br/t/inserir-zeros-a-esquerda/70374
	public static String completeToLeft(String value, char c, int size) {
		String result = value;
		while (result.length() < size) {
			result = c + result;
		}
		return result;
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
