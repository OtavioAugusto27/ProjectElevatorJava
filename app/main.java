import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Predio predio = new Predio("Prédio UNIUBE");

        Elevator elevador = new Elevator();
        elevador.inicializar(5, 10); // capacidade 5, 10 andares (0..10)
        predio.adicionarElevador(elevador);

        ElevadorService service = new ElevadorService();

        System.out.println("Inicial: " + service.gerarStatus(elevador));

        // Embarcar 3 pessoas
        service.embarcarPessoas(elevador, 3);
        System.out.println("Após embarcar 3: " + service.gerarStatus(elevador));

        // Mover para o 5º andar
        service.moverElevador(elevador, 5);
        System.out.println("Após mover para 5: " + service.gerarStatus(elevador));

        // Desembarcar 2 pessoas
        service.desembarcarPessoas(elevador, 2);
        System.out.println("Após desembarcar 2: " + service.gerarStatus(elevador));

        // Colocar em manutenção e tentar mover (não deve mover)
        elevador.colocarEmManutencao();
        System.out.println("Após colocar em manutenção: " + service.gerarStatus(elevador));

        service.moverElevador(elevador, 2); // operação deve ser ignorada
        System.out.println("Tentativa mover em manutenção (sem mudança): " + service.gerarStatus(elevador));

        // Liberar manutenção e mover para o térreo
        elevador.liberarManutencao();
        service.moverElevador(elevador, 0);
        System.out.println("Após liberar manutenção e mover para 0: " + service.gerarStatus(elevador));
    }
    }

class ElevadorService {
    public String gerarStatus(Elevator elevador) {
        return "Andar: " + elevador.getAndarAtual()
                + ", Ocupação: " + elevador.getOcupacao() + "/" + elevador.getCapacidade()
                + ", Manutenção: " + (elevador.isEmManutencao() ? "SIM" : "NAO");
    }

    public boolean embarcarPessoas(Elevator elevador, int pessoas) {
        return elevador.embarcarPessoas(pessoas);
    }

    public boolean desembarcarPessoas(Elevator elevador, int pessoas) {
        return elevador.desembarcarPessoas(pessoas);
    }

    public boolean moverElevador(Elevator elevador, int andar) {
        return elevador.moverPara(andar);
    }
}

class Elevator {
    private int capacidade;
    private int andares;
    private int andarAtual;
    private int ocupacao;
    private boolean manutencao;

    public void inicializar(int capacidade, int andares) {
        this.capacidade = capacidade;
        this.andares = andares;
        this.andarAtual = 0;
        this.ocupacao = 0;
        this.manutencao = false;
    }

    public void colocarEmManutencao() {
        this.manutencao = true;
    }

    public void liberarManutencao() {
        this.manutencao = false;
    }

    public int getCapacidade() {
        return capacidade;
    }

    public int getAndares() {
        return andares;
    }

    public int getAndarAtual() {
        return andarAtual;
    }

    public int getOcupacao() {
        return ocupacao;
    }

    public boolean isEmManutencao() {
        return manutencao;
    }

    // Convenience methods that a service might call
    public boolean embarcarPessoas(int pessoas) {
        if (manutencao) return false;
        int cabem = capacidade - ocupacao;
        int embarcadas = Math.min(cabem, pessoas);
        ocupacao += embarcadas;
        return embarcadas == pessoas;
    }

    public boolean desembarcarPessoas(int pessoas) {
        if (manutencao) return false;
        int desembarcadas = Math.min(ocupacao, pessoas);
        ocupacao -= desembarcadas;
        return desembarcadas == pessoas;
    }
    public boolean moverPara(int andar) {
        if (manutencao || andar < 0 || andar > andares) return false;
        andarAtual = andar;
        return true;
    }
}

class Predio {
    private String nome;
    private List<Elevator> elevadores;

    public Predio(String nome) {
        this.nome = nome;
        this.elevadores = new ArrayList<>();
    }

    public void adicionarElevador(Elevator elevador) {
        this.elevadores.add(elevador);
    }

    public String getNome() {
        return nome;
    }

    public List<Elevator> getElevadores() {
        return elevadores;
    }
}