package model.service;

import java.time.LocalDate;

import model.entities.Contract;
import model.entities.Installment;

public class ContractService {
	
	private OnlinePaymentService ops;
	
	public ContractService(OnlinePaymentService ops) {
		this.ops = ops;
	}
	
	public void processContract(Contract contract, Integer months) {
		double basicquota = contract.getTotalValue() / months;
		for(int i=1; i<=months; i++) {
			LocalDate dueDate = contract.getDate().plusMonths(i);
			double interest = ops.interest(basicquota, i);
			double payfee = ops.paymentFee(basicquota + interest);
			double quota = basicquota + interest +payfee;
			contract.getInstallment().add(new Installment(quota, dueDate));
		}
	}
}
