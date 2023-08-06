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

import hospital.UserFrame;
 
class UserFrame extends Frame implements ActionListener, ItemListener
{
	Label lblTitle, lblName, lblCity, lblAge, lblStatus,lbldeptno,lbldeptname,lbldate,lblgender;
	TextField txtName,   txtAge,txtdeptno,txtdeptname,txtdate;
	Button btnSave;//,btngetdeptno,btngetdeptname;
	CheckboxGroup radioGroup;
	Checkbox radio1;
	Checkbox radio2;
	Checkbox radio3;
	List optionList;
	
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
		//	txtgender.setText("");
			txtName.setText("");
			txtAge.setText("");
		//	txtCity.setText("");
			txtdeptno.setText("");
			txtdeptname.setText("");
			txtName.requestFocus();
		}
 
	public UserFrame() {
		connect();
		Date f = new Date();
		SimpleDateFormat  d =new SimpleDateFormat("yyyy-MM-dd");
		String formattedDate = d.format(f);
		this.setVisible(true);
		this.setSize(1000, 600);
		this.setTitle("Out-Patient Form");
		this.setLayout(null);
		Color formColor = new Color(53, 59, 72);
		this.setBackground(formColor);
 
		Font titleFont = new Font("arial", Font.BOLD, 25);
		Font labelFont = new Font("arial", Font.PLAIN, 18);
		Font textFont = new Font("arial", Font.PLAIN, 18);
 
		lblTitle = new Label("Out-Patient Form");
		lblTitle.setBounds(250, 40, 400, 50);
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.YELLOW);
		add(lblTitle);
 
		lbldate = new Label("Date");
		lbldate.setBounds(250, 100, 150, 30);
		lbldate.setFont(labelFont);
		lbldate.setForeground(Color.WHITE);
		add(lbldate);
 
		txtdate = new TextField(formattedDate);
		txtdate.setBounds(400, 100, 400, 30);
		txtdate.setFont(textFont);
		txtdate.addActionListener(this);
		add(txtdate);
 
 
		lblName = new Label("Name");
		lblName.setBounds(250, 150, 150, 30);
		lblName.setFont(labelFont);
		lblName.setForeground(Color.WHITE);
		add(lblName);
 
		txtName = new TextField();
		txtName.setBounds(400, 150, 400, 30);
		txtName.setFont(textFont);
		add(txtName);
 
		lblAge = new Label("Age");
		lblAge.setBounds(250, 200, 150, 30);
		lblAge.setFont(labelFont);
		lblAge.setForeground(Color.WHITE);
		add(lblAge);
 
		txtAge = new TextField();
		txtAge.setBounds(400, 200, 400, 30);
		txtAge.setFont(textFont);
		add(txtAge);
		
		 radioGroup = new CheckboxGroup();

	        // Create the first radio button
	        radio1 = new Checkbox("Male", radioGroup, false);
	        radio1.setBounds(400, 250, 150, 30);
	        radio1.setFont(labelFont);
	        radio1.setForeground(Color.white);;
	        radio1.addItemListener(this);
	        add(radio1);

	        // Create the second radio button
	        radio2 = new Checkbox("Female", radioGroup, false);
	        radio2.setBounds(550, 250, 150, 30);
	        radio2.setFont(labelFont);
	        radio2.setForeground(Color.white);;
	        radio2.addItemListener(this);
	        add(radio2);
	        
	        radio3 = new Checkbox("Other", radioGroup, false);
	        radio3.setBounds(750, 250, 150, 30);
	        radio3.setFont(labelFont);
	        radio3.setForeground(Color.white);;
	        radio3.addItemListener(this);
	        add(radio3);

        
		
		lblgender = new Label("Gender");
		lblgender.setBounds(250, 250, 150, 30);
		lblgender.setFont(labelFont);
		lblgender.setForeground(Color.WHITE);
		add(lblgender);
 
//		txtgender = new TextField();
//		txtgender.setBounds(400, 250, 400, 30);
//		txtgender.setFont(textFont);
//		add(txtgender);
 
		lblCity = new Label("City");
		lblCity.setBounds(250, 300, 150, 30);
		lblCity.setFont(labelFont);
		lblCity.setForeground(Color.WHITE);
		add(lblCity);
 
//		txtCity = new TextField();
//		txtCity.setBounds(400, 300, 400, 30);
//		txtCity.setFont(textFont);
//		add(txtCity);
		optionList = new List(3, false);
        optionList.add("Puducherry");
        optionList.add("Tamilnadu");
        optionList.add("Other");
        optionList.setBounds(400, 300, 200, 30);
        optionList.setFont(labelFont);
        add(optionList);
 
		txtdeptno = new TextField();
		txtdeptno.setBounds(400, 350, 200, 30);
		txtdeptno.setFont(textFont);
		  txtdeptno.addActionListener(this);
		//txtdeptno.getActionListeners();
		add(txtdeptno);
		
		lbldeptno = new Label("Department no");
		lbldeptno.setBounds(250, 350, 150, 30);
		lbldeptno.setFont(labelFont);
		lbldeptno.setForeground(Color.WHITE);
		add(lbldeptno);
		
		txtdeptname = new TextField();
		txtdeptname.setBounds(400, 400, 200, 30);
		txtdeptname.setFont(textFont);
		  txtdeptname.addActionListener(this);
		add(txtdeptname);
		
		lbldeptname = new Label("Department name");
		lbldeptname.setBounds(250, 400, 150, 30);
		lbldeptname.setFont(labelFont);
		lbldeptname.setForeground(Color.WHITE);
		add(lbldeptname);
		
 
		btnSave = new Button("PRINT");
		btnSave.setBounds(400, 450, 100, 30);
		btnSave.setBackground(Color.BLUE);
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(labelFont);
		btnSave.addActionListener(this);
		add(btnSave);
		
//		btngetdeptno = new Button("GET");
//		btngetdeptno.setBounds(650, 350, 50, 30);
//		btngetdeptno.setBackground(Color.YELLOW);
//		btngetdeptno.setForeground(Color.black);
//		btngetdeptno.setFont(labelFont);
//		btngetdeptno.addActionListener(this);
//		add(btngetdeptno);
//		
//		btngetdeptname = new Button("GET");
//		btngetdeptname.setBounds(650, 400, 50, 30);
//		btngetdeptname.setBackground(Color.YELLOW);
//		btngetdeptname.setForeground(Color.black);
//		btngetdeptname.setFont(labelFont);
//		btngetdeptname.addActionListener(this);
//		add(btngetdeptname);
// 
		
 
		lblStatus = new Label();
		lblStatus.setFont(labelFont);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBounds(400, 500, 300, 30);
		add(lblStatus);
 
		
 
 
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent we) {
				System.exit(0);
			}
		});
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		
		try
		{
			String date = txtdate.getText();
			String deptno= txtdeptno.getText();
			String deptname = txtdeptname.getText();
			String name = txtName.getText();
			String age = txtAge.getText();
			
			//System.out.println("hi");	
 
/*
             dei ethu tha if tha da database la irunthu onaku deptno kutatha dept name veranum 
             athuku kila irukura if deptname kututha deptno veranum da 
 */
			if (e.getSource().equals(txtdeptno) ) {
				//Get User By ID
				//System.out.println("hi");
			//	qry = "SELECT * from dept where DEPTNO=" + txtdeptno.getText();
//				stmt =con.createStatement();
//				rs = stmt.executeQuery("SELECT * FROM dept WHERE code='" + txtdeptno.getText() + "'");
				qry = "SELECT deptname from de where deptno = '" + txtdeptno.getText()+"'";
					stmt =con.createStatement();
					rs = stmt.executeQuery(qry);
				if (rs.next()) {
				//	txtdeptno.setText(rs.getString("DEPTNO"));
//					txtName.setText(rs.getString("NAME"));
				//	System.out.println(rs.getString("DEPTNAME"));
//					txtAge.setText(rs.getString("AGE"));
					txtdeptname.setText(rs.getString("DEPTNAME"));
 
				} else {
					clear();
					lblStatus.setText("Invalid ID");
 
				}
			}
			if (e.getSource().equals(txtdeptname)) {
				//Get User By ID
				qry = "SELECT deptno from de where DEPTNAME='" + txtdeptname.getText()+"'";
				stmt =con.createStatement();
				rs = stmt.executeQuery(qry);
				if (rs.next()) {
					txtdeptno.setText(rs.getString("DEPTNO"));
//					txtName.setText(rs.getString("NAME"));
//					txtAge.setText(rs.getString("AGE"));
				//	txtdeptname.setText(rs.getString("DEPTNAME"));
 
				} else {
					clear();
					lblStatus.setText("Invalid ID");
 
				}
			}
//			
// 
//              if(e.getSource().equals(btngetdeptno)) {
//            	  if(deptname.isEmpty()||deptname.equals("")) {
// 					 lblStatus.setText("Enter the Department Name");
// 				 }else {
// 					 try {
// 					qry = "SELECT id,deptno,deptname from dept where deptname =" + txtdeptname.getText();
// 					stmt =con.createStatement();
// 					rs = stmt.executeQuery(qry);
// 					if (rs.next()) {
// 						txtdeptno.setText(rs.getString("DEPTNO"));
//// 						txtName.setText(rs.getString("NAME"));
//// 						txtAge.setText(rs.getString("AGE"));
// 					//	txtdeptname.setText(rs.getString("DEPTNAME"));
// 	 
// 					} else {
// 						clear();
// 						lblStatus.setText("Invalid Department Name");
// 	 
// 					}
// 				 }catch (Exception ex) {
// 					ex.printStackTrace();
// 				}
// 				 }
//
//              }
//              if(e.getSource().equals(btngetdeptname)) {
//            	  if(deptno.isEmpty()||deptno.equals("")) {
// 					 lblStatus.setText("Enter the Department No");
// 				 }else {
// 					 try {
// 					qry = "SELECT id,deptno,deptname from dept where deptno =" + txtdeptno.getText();
// 					stmt =con.createStatement();
// 					rs = stmt.executeQuery(qry);
// 					if (rs.next()) {
// 						//txtdeptno.setText(rs.getString("deptno"));
//// 						txtName.setText(rs.getString("NAME"));
//// 						txtAge.setText(rs.getString("AGE"));
// 					txtdeptname.setText(rs.getString("DEPTNAME"));
// 	 
// 					} else {
// 						clear();
// 						lblStatus.setText("Invalid Department No");
// 	 
// 					}
// 				 }catch (Exception ex) {
// 					ex.printStackTrace();
// 				}
// 				 }
//
//              }
		
			
			 if (e.getSource().equals(btnSave)) {
					String city = optionList.getSelectedItem();
					//	String gender = txtgender.getText();
						String gender = radioGroup.getSelectedCheckbox().getLabel();
				 int count = 1;
				 if(name.isEmpty()||name.equals("")) {
					 count=0;
				 }
				 if(age.isEmpty()||age.equals("")) {
					 count=0;
				 }
				 
				 if(gender.isEmpty()||gender.equals("")) {
					 count=0;
				 }
				 if(deptno.isEmpty()||deptno.equals("")) {
					 count=0;
				 }
				 if(deptname.isEmpty()||deptname.equals("")) {
					 count=0;
				 }
 
				if (count==1) {
					//Save Details
					qry = "insert into opd (DATE,NAME,AGE,CITY,GENDER,DEPTNO,DEPTNAME) values(?,?,?,?,?,?,?)";
					st = con.prepareStatement(qry);
					st.setString(1, date);
					st.setString(2, name);
					st.setString(3, age);
					st.setString(4, city);
					st.setString(5, gender);
					st.setString(6, deptno);
					st.setString(7, deptname);
					
					st.executeUpdate();
					// String details = "name = " + name + ", age = " + age + ", city = " + city;
				        
//					  PrinterJob job = PrinterJob.getPrinterJob();
//				        
//				        // Set the printable object to a new Printable that will print the details
//				        Printable printable = new Printable() {
//				            @Override
//				            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
//				                if (pageIndex > 0) {
//				                    return NO_SUCH_PAGE;
//				                }
//				                graphics.drawString(details, 100, 100);
//				                return PAGE_EXISTS;
//				            }
//				        };
//				          String hospitalName = "Goverment Hosptal puducheery";
					 String hospitalName = "Indira Gandhi Goverment Hosptal ";
//			        String date1 = "21/12/2023";
//			        String state = "chennai";
//			        String patientName = "mathan";
//			        int patientAge = 21;
//			        String patientCity = "pondy";
			        
			        // Create a PrinterJob object
			        PrinterJob job = PrinterJob.getPrinterJob();
			        
			        // Set the printable object to a new Printable that will print the details
			        Printable printable = new Printable() {
			            @Override
			            public int print(Graphics graphics, PageFormat pageFormat, int pageIndex) throws PrinterException {
			                if (pageIndex > 0) {
			                    return NO_SUCH_PAGE;
			                }
			                
			                // Set up the font and position for the hospital name
			                Font fontHospital = new Font("Arial", Font.BOLD, 20);
			                graphics.setFont(fontHospital);
			                graphics.drawString(hospitalName, 100, 100);
			                
			                // Draw a horizontal line to separate the hospital name from the details
			                graphics.drawLine(100, 120, 500, 120);
			                
			                // Set up the font and position for the date and state
			                Font fontDateState = new Font("Arial", Font.PLAIN, 14);
			                graphics.setFont(fontDateState);
			                graphics.drawString("date : " + date, 100, 140);
			                graphics.drawString("state : " + city, 400, 140);
			                
			                // Draw a horizontal line to separate the details from the patient details
			                graphics.drawLine(100, 160, 500, 160);
			                
			                // Set up the font and position for the patient details
			                Font fontPatientDetails = new Font("Arial", Font.BOLD, 14);
			                graphics.setFont(fontPatientDetails);
			                graphics.drawString("patiend details :", 100, 180);
			                
			                // Set up the font and position for the patient name, age, and city
			                Font fontPatient = new Font("Arial", Font.PLAIN, 14);
			                graphics.setFont(fontPatient);
			                graphics.drawString("name : " + name, 100, 200);
			                graphics.drawString("age : " + age, 100, 220);
			                graphics.drawString("city : " + city, 100, 240);
			                graphics.drawString("Deparment no : " + deptno, 100, 260);
			                graphics.drawString("Deparment name : " + deptname, 100, 280);
			                
			                // Draw a horizontal line to separate the patient details from the bottom of the page
			                graphics.drawLine(100, 300, 500, 300);
			                
			                // Return PAGE_EXISTS to indicate that the page was printed
			                return PAGE_EXISTS;
			            }
			        };
			        
				        // Set the printable object to the PrinterJob object
				        job.setPrintable(printable);
				        
				        // Show the print dialog to the user
				        if (job.printDialog()) {
				            // Start the print job
				            try {
				                job.print();
				            } catch (PrinterException ex) {
				                ex.printStackTrace();
				            }
				        }
				    
					clear();
 
				lblStatus.setText("Data Insert Success");
				}else {
					lblStatus.setText("Insert data");
				}
			}
		
 
		} catch (Exception ex) {
			ex.printStackTrace();
		}		
 
	}
	public void itemStateChanged(ItemEvent e) {
        // Handle radio button events here
    }
 
}
 
public class op {
 
	public static void main(String[] args) {
 
		UserFrame frm =new UserFrame();
 
	}
 
}
/*
 
public class DisplayDepartmentName extends Frame implements ActionListener {
    Label lblCode, lblName;
    TextField txtCode, txtName;
    Button btnDisplay;
    Connection con;

    public DisplayDepartmentName() {
        super("Display Department Name");

        // create GUI components
        lblCode = new Label("Department Code:");
        lblName = new Label("Department Name:");
        txtCode = new TextField(10);
        txtName = new TextField(20);
        btnDisplay = new Button("Display");

        // add components to the frame
        setLayout(new FlowLayout());
        add(lblCode);
        add(txtCode);
        add(btnDisplay);
        add(lblName);
        add(txtName);

        // add event listener to the button
        btnDisplay.addActionListener(this);

        // create database connection
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", "root", "");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        // set frame properties
        setSize(300, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnDisplay) {
            String code = txtCode.getText();
            try {
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM department WHERE code='" + code + "'");
                if (rs.next()) {
                    String name = rs.getString("name");
                    txtName.setText(name);
                } else {
                    txtName.setText("Department not found");
                }
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        new DisplayDepartmentName();
    }
}
 */
/*
 	Font titleFont = new Font("arial", Font.BOLD, 25);
		Font labelFont = new Font("arial", Font.PLAIN, 18);
		Font textFont = new Font("arial", Font.PLAIN, 18);
 
 
		lblTitle = new Label("Hospital Management System");
		lblTitle.setBounds(250, 40, 400, 50);
		lblTitle.setFont(titleFont);
		lblTitle.setForeground(Color.YELLOW);
		add(lblTitle);
 
		lbldate = new Label("Date");
		lbldate.setBounds(250, 100, 150, 30);
		lbldate.setFont(labelFont);
		lbldate.setForeground(Color.WHITE);
		add(lbldate);
 
		txtdate = new TextField(formattedDate);
		txtdate.setBounds(400, 100, 400, 30);
		txtdate.setFont(textFont);
		txtdate.addActionListener(this);
		add(txtdate);
 
 
		lblName = new Label("Name");
		lblName.setBounds(250, 150, 150, 30);
		lblName.setFont(labelFont);
		lblName.setForeground(Color.WHITE);
		add(lblName);
 
		txtName = new TextField();
		txtName.setBounds(400, 150, 400, 30);
		txtName.setFont(textFont);
		add(txtName);
 
		lblAge = new Label("Age");
		lblAge.setBounds(250, 200, 150, 30);
		lblAge.setFont(labelFont);
		lblAge.setForeground(Color.WHITE);
		add(lblAge);
 
		txtAge = new TextField();
		txtAge.setBounds(400, 200, 400, 30);
		txtAge.setFont(textFont);
		add(txtAge);
		
		lblgender = new Label("Gender");
		lblgender.setBounds(250, 250, 150, 30);
		lblgender.setFont(labelFont);
		lblgender.setForeground(Color.WHITE);
		add(lblgender);
 
		txtgender = new TextField();
		txtgender.setBounds(400, 250, 400, 30);
		txtgender.setFont(textFont);
		add(txtgender);
 
		lblCity = new Label("City");
		lblCity.setBounds(250, 300, 150, 30);
		lblCity.setFont(labelFont);
		lblCity.setForeground(Color.WHITE);
		add(lblCity);
 
		txtCity = new TextField();
		txtCity.setBounds(400, 300, 400, 30);
		txtCity.setFont(textFont);
		add(txtCity);
 
		txtdeptno = new TextField();
		txtdeptno.setBounds(400, 350, 400, 30);
		txtdeptno.setFont(textFont);
		add(txtdeptno);
		
		lbldeptno = new Label("Department no");
		lbldeptno.setBounds(250, 350, 150, 30);
		lbldeptno.setFont(labelFont);
		lbldeptno.setForeground(Color.WHITE);
		add(lbldeptno);
		
		txtdeptname = new TextField();
		txtdeptname.setBounds(400, 400, 400, 30);
		txtdeptname.setFont(textFont);
		add(txtdeptname);
		
		lbldeptname = new Label("Department name");
		lbldeptname.setBounds(250, 400, 150, 30);
		lbldeptname.setFont(labelFont);
		lbldeptname.setForeground(Color.WHITE);
		add(lbldeptname);
		
 
		btnSave = new Button("REGISTER");
		btnSave.setBounds(400, 450, 100, 30);
		btnSave.setBackground(Color.BLUE);
		btnSave.setForeground(Color.WHITE);
		btnSave.setFont(labelFont);
		btnSave.addActionListener(this);
		add(btnSave);
 
		
 
		lblStatus = new Label("----------------");
		lblStatus.setFont(labelFont);
		lblStatus.setForeground(Color.WHITE);
		lblStatus.setBounds(400, 500, 300, 30);
		add(lblStatus);
 
 */
/*
 try
		{
			String date = txtdate.getText();
			String deptno= txtdeptno.getText();
			String deptname = txtdeptname.getText();
			String name = txtName.getText();
			String age = txtAge.getText();
			String city = txtCity.getText();
			String gender = txtgender.getText();
 
 
			if (e.getSource().equals(txtdeptno)) {
				//Get User By ID
				qry = "SELECT * from dept where DEPTNO=" + txtdeptno.getText();
				stmt =con.createStatement();
				rs = stmt.executeQuery(qry);
				if (rs.next()) {
					txtdeptno.setText(rs.getString("DEPTNO"));
//					txtName.setText(rs.getString("NAME"));
//					txtAge.setText(rs.getString("AGE"));
					txtdeptname.setText(rs.getString("DEPTNAME"));
 
				} else {
					clear();
					lblStatus.setText("Invalid ID");
 
				}
			}
			
 
 
			
			 if (e.getSource().equals(btnSave)) {
				 int count = 1;
				 if(name.isEmpty()||name.equals("")) {
					 count=0;
				 }
				 if(age.isEmpty()||age.equals("")) {
					 count=0;
				 }
				 if(city.isEmpty()||city.equals("")) {
					 count=0;
				 }
				 if(gender.isEmpty()||gender.equals("")) {
					 count=0;
				 }
				 if(deptno.isEmpty()||deptno.equals("")) {
					 count=0;
				 }
				 if(deptname.isEmpty()||deptname.equals("")) {
					 count=0;
				 }
 
				if (count==1) {
					//Save Details
					qry = "insert into opd (DATE,NAME,AGE,CITY,GENDER,DEPTNO,DEPTNAME) values(?,?,?,?,?,?,?)";
					st = con.prepareStatement(qry);
					st.setString(1, date);
					st.setString(2, name);
					st.setString(3, age);
					st.setString(4, city);
					st.setString(5, gender);
					st.setString(6, deptno);
					st.setString(7, deptname);
					st.executeUpdate();
					clear();
 
				lblStatus.setText("Data Insert Success");
				}else {
					lblStatus.setText("Insert data");
				}
			}
		
 
		} catch (Exception ex) {
			ex.printStackTrace();
		}
 */
