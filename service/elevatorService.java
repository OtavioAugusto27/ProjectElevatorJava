import model.Elevator;

public class elevatorService {

	public void moverElevador(Elevator elevador, int destino) {
		if (elevador == null)
			throw new NullPointerException("elevador não pode ser null");
		if (destino < 0 || destino > elevador.getTotalAndares())
			throw new IllegalArgumentException("destino fora do intervalo");
		if (elevador.isEmManutencao())
			return;

		while (elevador.getAndarAtual() < destino) {
			// subir retorna false se não for possível (manutenção ou topo)
			if (!elevador.subir())
				break;
		}
		while (elevador.getAndarAtual() > destino) {
			if (!elevador.descer())
				break;
		}
	}

	public void embarcarPessoas(Elevator elevador, int quantidade) {
		if (elevador == null)
			throw new NullPointerException("elevador não pode ser null");
		if (quantidade < 0)
			throw new IllegalArgumentException("quantidade negativa");
		if (elevador.isEmManutencao())
			return;

		int capacidadeDisponivel = elevador.getCapacidade() - elevador.getPessoasPresentes();
		int aEntrar = Math.min(capacidadeDisponivel, quantidade);
		for (int i = 0; i < aEntrar; i++)
			elevador.entrar();
	}

	public void desembarcarPessoas(Elevator elevador, int quantidade) {
		if (elevador == null)
			throw new NullPointerException("elevador não pode ser null");
		if (quantidade < 0)
			throw new IllegalArgumentException("quantidade negativa");
		if (elevador.isEmManutencao())
			return;

		int aSair = Math.min(quantidade, elevador.getPessoasPresentes());
		for (int i = 0; i < aSair; i++)
			elevador.sair();
	}

	public String gerarStatus(Elevator elevador) {
		if (elevador == null)
			throw new NullPointerException("elevador não pode ser null");
		String status = elevador.isEmManutencao() ? "Em manutenção" : "Operacional";
		return String.format("Andar: %d | Pessoas: %d | Capacidade: %d | Status: %s",
				elevador.getAndarAtual(),
				elevador.getPessoasPresentes(),
				elevador.getCapacidade(),
				status);
	}
}