package application;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Contract;
import model.entities.Installment;
import model.service.ContractService;
import model.service.PpService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		
		
		System.out.println("Entre os dados do contrato:");
		try {
			System.out.print("Numero: ");
			int number = sc.nextInt();
			
			System.out.print("Data (dd/MM/yyyy):");
			sc.nextLine();
			LocalDate date = LocalDate.parse(sc.nextLine(), fmt);
			
			System.out.print("Valor do contrato: ");
			double totalValue = sc.nextDouble();
			
			Contract contract = new Contract(number, date, totalValue);
			
			System.out.print("Entre com o numero de parcelas:");
			int months = sc.nextInt();
			
			ContractService cs = new ContractService(new PpService());
			cs.processContract(contract, months);
			
			System.out.println("Parcelas:");
			for(Installment install : contract.getInstallment()) {
				System.out.println(install.toString());
			}
		}catch(InputMismatchException input) {
			System.out.println("Error! Dados incorretos: tipo de caracteres invalidos");
		}finally {
			sc.close();
		}
	}

}
