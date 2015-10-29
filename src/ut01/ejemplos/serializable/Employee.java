package ut01.ejemplos.serializable;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Employee implements Serializable {

	private static final long serialVersionUID = 8L;

	private int emp_no;
	private String lastname;
	private String name;
	private int job;
	private Date date;
	private double salary;
	private double commission;
	private int dept_number;
	
	private final int TAM = 20;

	Employee(int emp_no, String lastname, String name, int job, String sDate,
			double salary, double commission, int dept_number) {
		Date date;
		
		this.emp_no = emp_no;
		
		if (lastname.length()>TAM)
			this.lastname = this.lastname.substring(0, TAM);
		else this.lastname = lastname;
				
		if (name.length()>TAM)
			this.name = name.substring(0, TAM);		
		else this.name = name;
		
		this.job = job;
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			date = null;
		}
		this.date = date;
		this.salary = salary;
		this.commission = commission;
		this.dept_number = dept_number;
		
		
	}

	public int getEmp_no() {
		return emp_no;
	}

	public void setEmp_no(int emp_no) {
		this.emp_no = emp_no;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		
		if (lastname.length()>TAM)
			lastname = this.lastname.substring(0, TAM);
		else this.lastname = lastname;
		}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		if (name.length()>TAM)
			name = this.name.substring(0, TAM);
		else this.name = name;
	}

	public int getJob() {
		return job;
	}

	public void setJob(int job) {
		this.job = job;
	}

	public String getDate() {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		return formatter.format(date);
	}

	public void setDate(String sDate) {
		
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
		try {
			date = formatter.parse(sDate);
		} catch (ParseException e) {
			date = null;
		}
		this.date = date;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	public double getCommission() {
		return commission;
	}

	public void setCommission(double commission) {
		this.commission = commission;
	}

	public int getDept_number() {
		return dept_number;
	}

	public void setDept_number(int dept_number) {
		this.dept_number = dept_number;
	}
	
	@Override
	public String toString(){
		
		 SimpleDateFormat  formatter = new SimpleDateFormat("dd/MM/yyyy");
		 
		return getEmp_no()+ " " + getName() + " "
					+ getLastname() + " fecha "
					+ formatter.format(getDate())+
					" job "+ getJob() + " salario " + getSalary()
					+" comision " + getCommission() + 
					" dept "+ getDept_number();
	}

}
