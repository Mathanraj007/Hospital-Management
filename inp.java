package hospital;

import java.awt.*;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.awt.event.*;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.sql.*;
import java.awt.print.*;


import hospital.frame;
 
class frame extends Frame implements ActionListener , ItemListener 
{
	Label lblwarde,lblcity,lblmlcno,lblmlcre,lblrefno,lblrefin,lblUnit,lblwar,lbldeptno,lbldeptname,lblunit,lbldr,lblTitle,lblcato,lblmo, lblId, lblName, lblCity, lblAge, lblStatus,lbldate,lblinp,lblop,lblgender,lblmarstatus,lblre,lblrelation;
	TextField txtName,txtcity,txtunit,txtmlcno,txtmlcre,txtrefno,txtrefin,txtd,txtwarno,txtwarname,txtdeptno,txtdeptname,txtdr, txtAge,txtdate,txtinp,txtop,txtgender,txtmo;
	Button btnSave, btnClear, btnDelete;
	CheckboxGroup radioGroup;
	CheckboxGroup radioGroup2;
	Checkbox radio1;
	Checkbox radio2;
	Checkbox ref;
	Checkbox mlc;
	Button btsave,btclear;
	
	List optionList1,optionList2,optionList3,optionlist4,optionlist5,optionlist6,optionlist7;
 
	String qry = "";
	Connection con = null;
	PreparedStatement st = null;
	ResultSet rs = null;
	Statement stmt = null;
 
	//Database Connection
		public void connect() {
			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				String url = "jdbc:mysql://localhost:3306/hospital?characterEncoding=utf8";
				String username = "root";
				String password = "";
				con = DriverManager.getConnection(url, username, password);
			} catch (Exception ex) {
				ex.printStackTrace();
			}
 
		}
 
		//Clear Form Details
		public void clear() {
			//txtId.setText("");
			txtName.setText("");
			txtAge.setText("");
			txtcity.setText("");
			txtgender.setText("");
		//	txtmlcno.setText("");
//			txtmlcre.setText("");
//			txtrefno.setText("");
			txtdeptno.setText("");
			txtdeptname.setText("");
			//txtdr.setText("");
			txtd.setText("");
			txtunit.setText("");
			txtmo.setText("");
			txtop.setText("");
			txtwarno.setText("");
			txtwarname.setText("");
			txtop.requestFocus();
		}
 
	public frame() {
		connect();
//		Panel panel = new Panel();
//		panel.setLayout(getLayout());
//		ScrollPane scroll = new ScrollPane();
//		
		this.setVisible(true);
		this.setSize(1000, 700);
		//scroll.add(panel);
		this.setTitle("Hospital Management System");
		this.setLayout(null);
		Color formColor = new Color(200, 100, 30);
		this.setBackground(formColor);
		Date f = new Date();
		SimpleDateFormat  d =new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = d.format(f);
 
		Font titleFont = new Font("arial", Font.BOLD, 25);
		Font labelFont = new Font("arial", Font.PLAIN, 18);
		Font textFont = new Font("arial", Font.PLAIN, 18);
 
 
		lblTitle = new Label(" In-Patient Form");
		lblTitle.setBounds(250, 40, 400, 50);
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.YELLOW);
		add(lblTitle);
 
		lbldate = new Label("Date");
		lbldate.setBounds(300, 100, 100, 30);
		lbldate.setFont(labelFont);
		lbldate.setForeground(Color.WHITE);
		add(lbldate);
		
		lblinp = new Label("INP no");
		lblinp.setBounds(50, 100, 100, 30);
		lblinp.setFont(labelFont);
		lblinp.setForeground(Color.WHITE);
		add(lblinp);
		
		lblop = new Label("OP no");
		lblop.setBounds(700, 100, 100, 30);
		lblop.setFont(labelFont);
		lblop.setForeground(Color.WHITE);
		add(lblop);
 
		txtdate = new TextField(formattedDate);
		txtdate.setBounds(400, 100, 150, 30);
		txtdate.setFont(textFont);
		txtdate.addActionListener(this);
		add(txtdate);
		
		txtinp = new TextField();
		txtinp.setBounds(150, 100, 100, 30);
		txtinp.setFont(textFont);
		txtinp.setEditable(false);
	//	txtinp.addActionListener(this);
		add(txtinp);
		
		txtop = new TextField();
		txtop.setBounds(850, 100, 100, 30);
		txtop.setFont(textFont);
		txtop.addActionListener(this);
		add(txtop);

 
		lblName = new Label("Name");
		lblName.setBounds(50, 150, 100, 30);
		lblName.setFont(labelFont);
		lblName.setForeground(Color.WHITE);
		add(lblName);
 
		txtName = new TextField();
		txtName.setBounds(150, 150, 200, 30);
		txtName.setFont(textFont);
		add(txtName);
 
		lblAge = new Label("Age");
		lblAge.setBounds(50, 200, 100, 30);
		lblAge.setFont(labelFont);
		lblAge.setForeground(Color.WHITE);
		add(lblAge);
 
		txtAge = new TextField();
		txtAge.setBounds(150, 200, 200, 30);
		txtAge.setFont(textFont);
		add(txtAge);
		
		lblgender = new Label("Gender");
		lblgender.setBounds(50, 250, 100, 30);
		lblgender.setFont(labelFont);
		lblgender.setForeground(Color.WHITE);
		add(lblgender);
		
		txtgender = new TextField();
		txtgender.setBounds(150, 250, 200, 30);
		txtgender.setFont(textFont);
		add(txtgender);
		
		lblcity = new Label("City");
		lblcity.setBounds(50, 300, 100, 30);
		lblcity.setFont(labelFont);
		lblcity.setForeground(Color.WHITE);
		add(lblcity);
		
		txtcity = new TextField();
		txtcity.setBounds(150, 300, 200, 30);
		txtcity.setFont(textFont);
		add(txtcity);
 
		lblmarstatus = new Label("Mar Status");
		lblmarstatus.setBounds(50, 350, 100, 30);
		lblmarstatus.setFont(labelFont);
		lblmarstatus.setForeground(Color.WHITE);
		add(lblmarstatus);
		 radioGroup = new CheckboxGroup();

	        // Create the first radio button
	        radio1 = new Checkbox("Married", radioGroup, false);
	        radio1.setBounds(150, 350, 100, 30);
	        radio1.setFont(labelFont);
	        radio1.setForeground(Color.white);;
	        radio1.addItemListener(this);
	        add(radio1);

	        // Create the second radio button
	        radio2 = new Checkbox("UnMarried", radioGroup, false);
	        radio2.setBounds(250, 350, 100, 30);
	        radio2.setFont(labelFont);
	        radio2.setForeground(Color.white);;
	        radio2.addItemListener(this);
	        add(radio2);
	 
	        lblre = new Label("Religion");
			lblre.setBounds(50, 400, 100, 30);
			lblre.setFont(labelFont);
			lblre.setForeground(Color.WHITE);
			add(lblre);
	     
	        optionList1 = new List(4, false);
	        optionList1.add("Hindu");
	        optionList1.add("Christian");
	        optionList1.add("Muslim");
	        optionList1.add("Other");
	        optionList1.setBounds(150, 400, 200, 30);
	        optionList1.setFont(labelFont);
	        add(optionList1);
	        
	        lblrelation = new Label("Relation");
			lblrelation.setBounds(50, 450, 100, 30);
			lblrelation.setFont(labelFont);
			lblrelation.setForeground(Color.WHITE);
			add(lblrelation);
	     
	        optionList2 = new List(7, false);
	        optionList2.add("Father");
	        optionList2.add("Mother");
	        optionList2.add("Husband");
	        optionList2.add("Wife");
	        optionList2.add("Son");
	        optionList2.add("Daugter");
	        optionList2.add("Other");
	        optionList2.setBounds(150, 450, 200, 30);
	        optionList2.setFont(labelFont);
	        add(optionList2);
	        
	        lblmo = new Label("Phone");
			lblmo.setBounds(50, 500, 100, 30);
			lblmo.setFont(labelFont);
			lblmo.setForeground(Color.WHITE);
			add(lblmo);
	 
			txtmo = new TextField();
			txtmo.setBounds(150, 500, 200, 30);
			txtmo.setFont(textFont);
			add(txtmo);
			
			lblcato = new Label("Category");
			lblcato.setBounds(50, 550, 100, 30);
			lblcato.setFont(labelFont);
			lblcato.setForeground(Color.WHITE);
			add(lblcato);
	     
	        optionList3 = new List(4, false);
	        optionList3.add("General");
	        optionList3.add("Emergency");
	        //optionList1.add("Muslim");
	        optionList3.add("Other");
	        optionList3.setBounds(150, 550, 200, 30);
	        optionList3.setFont(labelFont);
	        add(optionList3);
	        
	        lblwarde = new Label("Ward Details");
			lblwarde.setBounds(450, 150, 150, 30);
			lblwarde.setFont(labelFont);
			lblwarde.setForeground(Color.WHITE);
			add(lblwarde);
			
			 lbldeptno = new Label("Department");
				lbldeptno.setBounds(450, 200, 100, 30);
				lbldeptno.setFont(labelFont);
				lbldeptno.setForeground(Color.WHITE);
				add(lbldeptno);
				
				txtdeptno = new TextField();
				txtdeptno.setBounds(600, 200, 50, 30);
				txtdeptno.setFont(textFont);
				add(txtdeptno);
				
				txtdeptname = new TextField();
				txtdeptname.setBounds(650, 200, 150, 30);
				txtdeptname.setFont(textFont);
			//	  txtdeptname.addActionListener(this);
				add(txtdeptname);
				
				lblunit = new Label("Unit");
				lblunit.setBounds(450, 250, 100, 30);
				lblunit.setFont(labelFont);
				lblunit.setForeground(Color.WHITE);
				add(lblunit);
				
				optionlist4 = new List(3, false);
		        optionlist4.add("1");
		        optionlist4.add("2");
		        optionlist4.add("3");
				optionlist4.setBounds(600, 250, 50, 30);
				optionlist4.setFont(textFont);
				optionlist4.addActionListener(this);
				add(optionlist4);
				
//				txtd= new TextField();
//				txtd.setBounds(650, 250, 150, 30);
//				txtd.setFont(textFont);
//				txtd.setEditable(false);
//			//	  txtdeptname.addActionListener(this);
//				add(txtd);
				optionlist5 = new List(10, false);
	   //        optionlist5.add("1");
//		        optionlist4.add("2");
//		        optionlist4.add("3");
				optionlist5.setBounds(650, 250, 250, 30);
				optionlist5.setFont(textFont);
				optionlist5.addActionListener(this);
				add(optionlist5);
				
				lblwar = new Label("Ward");
				lblwar.setBounds(450, 300, 100, 30);
				lblwar.setFont(labelFont);
				lblwar.setForeground(Color.WHITE);
				add(lblwar);
				
				txtwarno = new TextField();
				txtwarno.setBounds(600, 300, 50, 30);
				txtwarno.setFont(textFont);
				txtwarno.addActionListener(this);
				add(txtwarno);
				
				txtwarname = new TextField();
				txtwarname.setBounds(650, 300, 300, 30);
				txtwarname.setFont(textFont);
		     	txtwarname.addActionListener(this);
				add(txtwarname);

//				lblref = new Label("Refered Case");
//				lblref.setBounds(400, 350, 150, 30);
//				lblref.setFont(labelFont);
//				lblref.setForeground(Color.WHITE);
//				add(lblref);
				 radioGroup2 = new CheckboxGroup();
				 
				ref = new Checkbox("Refered Case", radioGroup2, false);
		        ref.setBounds(400, 350, 150, 30);
		        ref.setFont(labelFont);
		        ref.setForeground(Color.white);;
		        ref.addItemListener(this);
		//        ref.addActionListener(this);
		        add(ref);
		        
		        
		        
		        lblrefno = new Label("Refered No");
				lblrefno.setBounds(400, 400, 100, 30);
				lblrefno.setFont(labelFont);
				lblrefno.setForeground(Color.WHITE);
				add(lblrefno);
				
				txtrefno = new TextField();
				txtrefno.setBounds(600, 400, 100, 30);
				txtrefno.setFont(textFont);
				txtrefno.setEditable(false);
				add(txtrefno);
				
				lblrefin = new Label("Refered Institution");
				lblrefin.setBounds(400, 450, 150, 30);
				lblrefin.setFont(labelFont);
				lblrefin.setForeground(Color.WHITE);
			
				add(lblrefin);
				
				txtrefin = new TextField();
				txtrefin.setBounds(600, 450, 350, 30);
				txtrefin.setFont(textFont);
				txtrefin.setEditable(false);
				add(txtrefin);
				
				mlc = new Checkbox("M.L.C Case", radioGroup2, false);
		        mlc.setBounds(400, 500, 150, 30);
		        mlc.setFont(labelFont);
		        mlc.setForeground(Color.white);;
		        mlc.addItemListener(this);
		        add(mlc);
		        
		        lblmlcno = new Label("M L C at");
				lblmlcno.setBounds(400, 550, 150, 30);
				lblmlcno.setFont(labelFont);
				lblmlcno.setForeground(Color.WHITE);
				add(lblmlcno);
				
				txtmlcno = new TextField();
				txtmlcno.setBounds(600, 550, 150, 30);
				txtmlcno.setFont(textFont);
				txtmlcno.setEditable(false);
				add(txtmlcno);
				
				 lblmlcre = new Label("M.L.C reason");
					lblmlcre.setBounds(400, 600, 150, 30);
					lblmlcre.setFont(labelFont);
					lblmlcre.setForeground(Color.WHITE);
					add(lblmlcre);
					
					txtmlcre = new TextField();
					txtmlcre.setBounds(600, 600, 350, 30);
					txtmlcre.setFont(textFont);
					txtmlcre.setEditable(false);
					add(txtmlcre);
			      
					btclear = new Button("CLEAR");
					btclear.setBounds(50, 600, 75, 30);
					btclear.setBackground(Color.BLUE);
					btclear.setForeground(Color.WHITE);
					btclear.setFont(labelFont);
					btclear.addActionListener(this);
					add(btclear);
					
					btsave = new Button("PRINT");
					btsave.setBounds(150, 600, 75, 30);
					btsave.setBackground(Color.GREEN);
					btsave.setForeground(Color.WHITE);
					btsave.setFont(labelFont);
					btsave.addActionListener(this);
					add(btsave);
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
	
		try {
			String op = txtop.getText();
			String warno = txtwarno.getSelectedText();
			String warname = txtwarname.getText();
			if (e.getSource().equals(txtop)) {
				qry = "SELECT ID, DATE,NAME,AGE,CITY,GENDER,DEPTNO,DEPTNAME from opd where ID =" + txtop.getText();
				stmt =con.createStatement();
				rs = stmt.executeQuery(qry);
			if (rs.next()) {
				txtdeptno.setText(rs.getString("DEPTNO"));
				txtName.setText(rs.getString("NAME"));
				txtgender.setText(rs.getString("GENDER"));
			//	System.out.println(rs.getString("DEPTNAME"));
				txtcity.setText(rs.getString("CITY"));
				txtAge.setText(rs.getString("AGE"));
				txtdeptname.setText(rs.getString("DEPTNAME"));

			}
			
			}
			if (e.getSource().equals(txtwarno) ) {
				//Get User By ID
				//System.out.println("hi");
			//	qry = "SELECT * from dept where DEPTNO=" + txtdeptno.getText();
//				stmt =con.createStatement();
//				rs = stmt.executeQuery("SELECT * FROM dept WHERE code='" + txtdeptno.getText() + "'");
				qry = "SELECT wardname from ward where wardno = '" + txtwarno.getText()+"'";
					stmt =con.createStatement();
					rs = stmt.executeQuery(qry);
				if (rs.next()) {
				//	txtdeptno.setText(rs.getString("DEPTNO"));
//					txtName.setText(rs.getString("NAME"));
				//	System.out.println(rs.getString("DEPTNAME"));
//					txtAge.setText(rs.getString("AGE"));
					txtwarname.setText(rs.getString("wardname"));
 
				} else {
					clear();
					lblStatus.setText("Invalid ID");
 
				}
			}
			if (e.getSource().equals(txtwarname)) {
				//Get User By ID
				qry = "SELECT wardno from ward where wardname='" + txtwarname.getText()+"'";
				stmt =con.createStatement();
				rs = stmt.executeQuery(qry);
				if (rs.next()) {
					txtwarno.setText(rs.getString("wardno"));
//					txtName.setText(rs.getString("NAME"));
//					txtAge.setText(rs.getString("AGE"));
				//	txtdeptname.setText(rs.getString("DEPTNAME"));
 
				} else {
					clear();
					lblStatus.setText("Invalid ID");
 
				}
			}
			if(e.getSource().equals(optionlist4)) {
				//txtd.disable();
				//System.out.print("hi");
			//	optionlist5 = new List(3, false);
				String option =optionlist4.getSelectedItem();
//		        optionlist5.add("Unit 1");
//		        optionlist5.add("Unit 2");
//		        optionlist5.add("Unit 3");
				//optionlist5.setBounds(650, 250, 150, 30);
				//optionlist4.setFont(textFont);
				//optionlist5.addActionListener(this);
			//	add(optionlist5);
				if(option.equals("1")) {
					optionlist5.add("Dr.K.Ayyappan");
			        optionlist5.add("Dr.Allirani");
			        optionlist5.add("Dr.Sevvel");
			        optionlist5.add("Dr.M.Uthrapathy");
			        optionlist5.add("Dr.C.T.Suresh");
			        optionlist5.add("Dr.G.Rajaraman");
			        optionlist5.add("Dr.S.Guneshwari");
			        optionlist5.add("Dr.S.Vasudevan");
			        optionlist5.add("Dr.Kumar");
			        optionlist5.add("Dr.A.Manimarane");
			        
			        optionlist5.remove("Dr.Suresh");
			        optionlist5.remove("Dr.Sudhakar");
			        optionlist5.remove("Dr.S.Anandaraja");
			        optionlist5.remove("Dr.P.Muthaiyan");
			        optionlist5.remove("Dr.Devi");
			        optionlist5.remove("Dr.R.Baskaran");
			        optionlist5.remove("Dr.Navin Nagaraj");
			        optionlist5.remove("Dr.Jhon Bosco");
			        optionlist5.remove("Dr.Rajini S Gupta");
			      
			        optionlist5.remove("Dr.Venkatesh");
					optionlist5.remove("Dr.Santhamoorthi");
					optionlist5.remove("Dr.G.Babu");
					optionlist5.remove("Dr.Senthamizhan Rene");
			        
				}
				if(option.equals("2")) {
					optionlist5.add("Dr.Suresh");
			        optionlist5.add("Dr.Sudhakar");
			        optionlist5.add("Dr.S.Anandaraja");
			        optionlist5.add("Dr.P.Muthaiyan");
			        optionlist5.add("Dr.Devi");
			        optionlist5.add("Dr.R.Baskaran");
			        optionlist5.add("Dr.Navin Nagaraj");
			        optionlist5.add("Dr.Jhon Bosco");
			        optionlist5.add("Dr.Rajini S Gupta");
			        
			        optionlist5.remove("Dr.K.Ayyappan");
			        optionlist5.remove("Dr.Allirani");
			        optionlist5.remove("Dr.Sevvel");
			        optionlist5.remove("Dr.M.Uthrapathy");
			        optionlist5.remove("Dr.C.T.Suresh");
			        optionlist5.remove("Dr.G.Rajaraman");
			        optionlist5.remove("Dr.S.Guneshwari");
			        optionlist5.remove("Dr.S.Vasudevan");
			        optionlist5.remove("Dr.Kumar");
			        optionlist5.remove("Dr.A.Manimarane");
			        
			        optionlist5.remove("Dr.Venkatesh");
					optionlist5.remove("Dr.Santhamoorthi");
					optionlist5.remove("Dr.G.Babu");
					optionlist5.remove("Dr.Senthamizhan Rene");
			        
				}
				if(option.equals("3")) {
					optionlist5.add("Dr.Venkatesh");
					optionlist5.add("Dr.Santhamoorthi");
					optionlist5.add("Dr.G.Babu");
					optionlist5.add("Dr.Senthamizhan Rene");
					
					optionlist5.remove("Dr.K.Ayyappan");
			        optionlist5.remove("Dr.Allirani");
			        optionlist5.remove("Dr.Sevvel");
			        optionlist5.remove("Dr.M.Uthrapathy");
			        optionlist5.remove("Dr.C.T.Suresh");
			        optionlist5.remove("Dr.G.Rajaraman");
			        optionlist5.remove("Dr.S.Guneshwari");
			        optionlist5.remove("Dr.S.Vasudevan");
			        optionlist5.remove("Dr.Kumar");
			        optionlist5.remove("Dr.A.Manimarane");
			        
			        optionlist5.remove("Dr.Suresh");
			        optionlist5.remove("Dr.Sudhakar");
			        optionlist5.remove("Dr.S.Anandaraja");
			        optionlist5.remove("Dr.P.Muthaiyan");
			        optionlist5.remove("Dr.Devi");
			        optionlist5.remove("Dr.R.Baskaran");
			        optionlist5.remove("Dr.Navin Nagaraj");
			        optionlist5.remove("Dr.Jhon Bosco");
			        optionlist5.remove("Dr.Rajini S Gupta");
				
				}
			}
            if(e.getSource().equals(optionlist4.equals(1))) {
				
			}
            if(e.getSource().equals(optionlist4.equals(2))) {
	
            }
			if(e.getSource().equals(btclear)) {
				clear();
			}
			if(e.getSource().equals(btsave)) {
				String date = txtdate.getText();
				String deptno= txtdeptno.getText();
				String deptname = txtdeptname.getText();
				String name = txtName.getText();
				String age = txtAge.getText();
				String city = txtcity.getText();
			//	String gender = txtgender.getText();
				String mar = radioGroup.getSelectedCheckbox().getLabel();
				String religion = optionList1.getSelectedItem();
				String relation = optionList2.getSelectedItem();
				String phone = txtmo.getText();
				String category =  optionList3.getSelectedItem();
				String gender = txtgender.getText();
				
				String unitno = optionlist4.getSelectedItem();
				String unitname = optionlist5.getSelectedItem();
				String refno=" -",refin=" -",mlcno=" -",mlcre=" -";
				
			//	if(radioGroup2.getSelectedCheckbox().getLabel().equals(ref)) {
					 refno = txtrefno.getText();
					 refin = txtrefin.getText();
//					 mlcno = " -";
//					 mlcre = " -";
			//	}
			//	if(radioGroup2.getSelectedCheckbox().getLabel().equals(mlc)) {
					 mlcno =txtmlcno.getText();
					 mlcre = txtmlcre.getText();
				//	 refno = " -";
				//	 refin = " -";
			//	}
				try {
				qry = "insert into ind ( DATE, NAME, AGE, GENDER, CITY, MAR, RELIGION, RELATION, PHONE, CATEGORY, DEPTNO, DEPTNAME, UNITNO, UNITNAME, WARDNO,WARDNAME, REFNO, REFNAME,MLCNO, MLCNAME) values(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				st = con.prepareStatement(qry);
				st.setString(1, date);
				st.setString(2, name);
				st.setString(3, age);
				st.setString(5, city);
				st.setString(4, gender);
				st.setString(6, mar);
				st.setString(7, religion);
				st.setString(8, relation);
				st.setString(9, phone);
				st.setString(10, category);
				st.setString(11, deptno);
				st.setString(12, deptname);
				st.setString(13, unitno);
				st.setString(14, unitname);
				st.setString(15, warno);
				st.setString(16, warname);
				st.setString(17, refno);
				st.setString(18, refin);
				st.setString(19, mlcno);
				st.setString(20, mlcre);
				String r1 = refno;
				String r2=refin;
				String m1=mlcno;
				String m2=mlcre;
				
				st.executeUpdate();
		//		clear();
				
//					  txtrefno.setText("");
//					  txtrefin.setText("");
					 
				
				
					 txtmlcno.setText("");
					  txtmlcre.setText("");
					
				
				//PrintoutExample printout = new PrintoutExample();

			      // Get the default printer
			      PrinterJob job = PrinterJob.getPrinterJob();
			      Printable printable = new Printable() {
			      @Override
		            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
		                if (pageIndex > 0) {
		                    return NO_SUCH_PAGE;
		                }
		                graphics.setFont(new Font("Arial", Font.PLAIN, 12));

		                // Draw the header
		                graphics.drawString("Indira Gandhi Goverment Hospital ", 150, 50);
		                graphics.drawString("date : "+date, 50, 70);
		                graphics.drawString("state : Puducherry", 400, 70);
		                graphics.drawLine(50, 80, 550, 80);

		                // Draw the patient information
		                graphics.drawString("Name : "+name, 50, 100);
		                graphics.drawString("Age : "+age, 50, 120);
		                graphics.drawString("Gender : "+gender, 50, 140);
		                graphics.drawString("city : "+city, 50, 160);
		                graphics.drawString("religion : "+religion, 50, 180);
		                graphics.drawString("relation : "+relation, 50, 200);
		                graphics.drawString("phone : "+phone, 50, 220);
		                graphics.drawString("category : "+category, 50, 240);
		                graphics.drawString(" deperment no :"+deptno,400,100);
		                graphics.drawString(  "deperment name :"+deptname, 400, 120);
		                graphics.drawString("ward no : "+warno, 400, 140);
		                graphics.drawString("ward name : "+warname, 400, 160);
		                graphics.drawString("unit :"+unitno+" "+unitname, 400, 180);
		                graphics.drawString("ref no :"+r1+" mlc no :"+m1, 400, 200);
		                graphics.drawString("ref.re : "+r2, 400, 220);
		                graphics.drawString("mlc.re : "+m2, 400, 240);
		                graphics.drawString("|", 250, 100);
		                graphics.drawString("|", 250, 120);
		                graphics.drawString("|", 250, 140);
		                graphics.drawString("|", 250, 160);
		                graphics.drawString("|", 250, 180);
		                graphics.drawString("|", 250, 200);
		                graphics.drawString("|", 250, 220);
		                graphics.drawString("|", 250, 240);
		               


		                // Draw the footer
		                graphics.drawLine(50, 260, 600, 260);

		                // Tell the printer that the page has been printed successfully
		                return PAGE_EXISTS;
		             }
			      };

			      job.setPrintable(printable);

			      // Show the print dialog to the user
			      if (job.printDialog()) {
			         try {
			            // Print the document
			            job.print();
			         } catch (PrinterException ee) {
			            ee.printStackTrace();
			         }
			      }
			   
				}catch (Exception ex) {
					ex.printStackTrace();
				}
			}
			
		}catch(Exception ex) {
			
		}
	}
	public void itemStateChanged(ItemEvent e) {
        // Handle radio button events here
		if(e.getSource().equals(ref)) {
			txtrefno.setEditable(true);
			txtrefin.setEditable(true);
			txtmlcre.setEditable(false);
			txtmlcno.setEditable(false);
			txtmlcre.setText("");
			txtmlcno.setText("");	
			}
		if(e.getSource().equals(mlc)) {
			txtmlcre.setEditable(true);
			txtmlcno.setEditable(true);
			txtrefno.setEditable(false);
			txtrefin.setEditable(false);
			txtrefno.setText("");
			txtrefin.setText("");
		}
    }
 
}
 
public class inp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		frame frm =new frame();
	}

}
