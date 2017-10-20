import java.text.DecimalFormat;
import java.util.Scanner;

public class Main {
	static Scanner sc = new Scanner(System.in);
	static DecimalFormat df = new DecimalFormat("#0.00");
	public static void main(String[] args) {
		Pedido pedido = new Pedido();
		try {
			pedido = pedidoSelecionado();
		} catch (Exception e) {
			System.out.println("Erro, Reinicie a Operação");
			System.exit(0);
		}
		double troco = efetuarPagamento(pedido);
		if(troco > 0) {
           calcularTroco(troco);
		}
	}

	static Pedido pedidoSelecionado() {
		int op = 0;
		while (op < 1 || op > 8) {
			System.out.println("Selecione a Opção desejada:\nOpções              Preço Unitário");
			System.out.println("1 - Café Curto          R$ 0,70");
			System.out.println("2 - Café Longo          R$ 0,65");
			System.out.println("3 - Café com Leite      R$ 0,80");
			System.out.println("4 - Capuccinno          R$ 0,80");
			System.out.println("5 - Mocaccinno          R$ 0,90");
			System.out.println("6 - Chocolate Quente    R$ 0,75");
			System.out.println("7 - Chá                 R$ 0,55");
			System.out.println("Sem açúcar              N/A");
			System.out.println("Com mais açúcar         N/A");
			System.out.println("8 - Sair");
			op = sc.nextInt();
		}
		if (op == 8) {
			System.out.println("Obrigado, Até Mais!!");
			System.exit(0);
		}
		Pedido pedido = new Pedido();
		switch (op) {
		case 1:
			pedido.setNomeProduto("Café Curto");
			pedido.setPreco(0.7);
			break;
		case 2:
			pedido.setNomeProduto("Café Longo");
			pedido.setPreco(0.65);
			break;
		case 3:
			pedido.setNomeProduto("Café com Leite");
			pedido.setPreco(0.8);
			break;
		case 4:
			pedido.setNomeProduto("Capuccino");
			pedido.setPreco(0.8);
			break;
		case 5:
			pedido.setNomeProduto("Mocaccino");
			pedido.setPreco(0.9);
			break;
		case 6:
			pedido.setNomeProduto("Chocolate Quente");
			pedido.setPreco(0.75);
			break;
		case 7:
			pedido.setNomeProduto("Chá");
			pedido.setPreco(0.55);
			break;
		default:
			break;
		}
		int nivelAcucar = 0;
		while (nivelAcucar < 1 || nivelAcucar > 3) {
			System.out.println("Selecione o nível de açúcar:\n1 - Sem açúcar\n2 - Com mais açúcar\n3 - Cancelar");
			nivelAcucar = sc.nextInt();
		}
		if (nivelAcucar == 1) {
			pedido.setAcucar(false);
		} else if (nivelAcucar == 2) {
			pedido.setAcucar(true);
		} else if (nivelAcucar == 3) {
			System.out.println("Obrigado, Até Mais!!");
			System.exit(0);
		}
		return pedido;

	}

	static double efetuarPagamento(Pedido pedido) {
		System.out.println("Efetuar Pagamento");
		System.out.println("Este equipamento aceita moedas de: R$ 0,05, R$ 0,10, R$ 0,25, R$ 0,50 e R$ 1,00");
		int cont = 1;
		double total = 0;
		double moeda;
		boolean isValid;
		while (total < pedido.getPreco()) {
			isValid = false;
			moeda = 0;
			while (!isValid) {
				System.out.println("Coloque a " + cont + "ª moeda");
				moeda = sc.nextDouble();
				if (moeda == 0.05 || moeda == 0.1 || moeda == 0.25 || moeda == 0.5 || moeda == 1) {
					total = total + moeda;
					isValid = true;
				}
			}
			cont++;
			DecimalFormat df = new DecimalFormat("#0.00");
			if (total < pedido.getPreco()) {
				System.out.println("Ainda restam " + df.format(pedido.getPreco() - total));
			} else if (total > pedido.getPreco()) {
				System.out.println("Troco " + df.format(total - pedido.getPreco()));
			} else {
				System.out.println("Pagamento Realizado Com Sucesso!!");
			}
		}
		return total - pedido.getPreco();

	}

	static void calcularTroco(double troco) {
		System.out.println("Calculando Troco ...");
		double total = troco;
		while (troco > 0) {
			if (troco / 1 >= 1) {
				System.out.println("Moedas de R$ 1,00 :" + (int) (troco / 1));
				troco = troco - ((int) (troco / 1));
			}
			if ((troco / 0.5)+0.00000001 >= 1) {
				System.out.println("Moedas de R$ 0,50 :" + (int) ((troco / 0.5)+0.000000001));
				troco = troco - ((int) ((troco / 0.5)+0.0000000001))*0.5;
			}
			if (((troco / 0.25)+0.000000001) >= 1) {
				System.out.println("Moedas de R$ 0,25 :" + (int) ((troco / 0.25)+0.000000001));
				troco = troco - ((int) ((troco / 0.25)+0.000000001))*0.25;
			}
			
			if (((troco / 0.1)+0.000000001) >= 1) {
				System.out.println("Moedas de R$ 0,10 :" + (int) ((troco / 0.1)+0.000000001));
				troco = troco - ((int) ((troco / 0.1)+0.000000001))*0.1;
			}
			if (((troco / 0.05)+0.000000001) >= 1) {
				System.out.println("Moedas de R$ 0,05 :" + (int) ((troco / 0.05)+0.000000001));
				troco = troco - ((int) ((troco / 0.05)+0.000000001))*0.05;
			}
		}
		System.out.println("Total: "+df.format(total));
	}
}
