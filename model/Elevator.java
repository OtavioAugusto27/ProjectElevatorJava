package model;

public class Elevator {
	private int andarAtual;
	private int totalAndares;
	private int capacidade;
	private int pessoasPresentes;
	private boolean emManutencao;

	public Elevator() {
		this.andarAtual = 0;
		this.totalAndares = 0;
		this.capacidade = 1;
		this.pessoasPresentes = 0;
		this.emManutencao = false;
	}

	public void inicializar(int capacidade, int totalAndares) {
		if (capacidade <= 0 || totalAndares < 0) {
			throw new IllegalArgumentException("capacidade>0 e totalAndares>=0 são obrigatórios");
		}
		this.capacidade = capacidade;
		this.totalAndares = totalAndares;
		this.andarAtual = 0;
		this.pessoasPresentes = 0;
		this.emManutencao = false;
	}

	public boolean entrar() {
		if (emManutencao)
			return false;
		if (pessoasPresentes < capacidade) {
			pessoasPresentes++;
			return true;
		}
		return false;
	}

	public boolean sair() {
		if (emManutencao)
			return false;
		if (pessoasPresentes > 0) {
			pessoasPresentes--;
			return true;
		}
		return false;
	}

	public boolean subir() {
		if (emManutencao)
			return false;
		if (andarAtual < totalAndares) {
			andarAtual++;
			return true;
		}
		return false;
	}

	public boolean descer() {
		if (emManutencao)
			return false;
		if (andarAtual > 0) {
			andarAtual--;
			return true;
		}
		return false;
	}

	public void colocarEmManutencao() {
		this.emManutencao = true;
	}

	public void liberarManutencao() {
		this.emManutencao = false;
	}

	// Getters
	public int getAndarAtual() {
		return andarAtual;
	}

	public int getTotalAndares() {
		return totalAndares;
	}

	public int getCapacidade() {
		return capacidade;
	}

	public int getPessoasPresentes() {
		return pessoasPresentes;
	}

	public boolean isEmManutencao() {
		return emManutencao;
	}
}