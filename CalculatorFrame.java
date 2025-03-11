import java.awt.*;
import java.awt.event.*;
import java.util.StringTokenizer;
import javax.swing.*;

public class CalculatorFrame extends JFrame{ //JFrame을 상속 받는 CalculatorFrame
	private JTextField tf1 = new JTextField(20); //선택하는 값을 보여주는 textfield
	private JTextField tf2 = new JTextField(20); //결과값을 출력하는 textfield
	private String click =new String();//tf1에 입력되는 내용 저장
	private String operator =new String(); //연산자 저장
	public CalculatorFrame() { //CalculatorFrame 생성자
 		super("자바 스윙 계산기"); //생성자를 호출해서 타이틀 달기
		setSize(300,300); //프레임 크기 300*300 설정
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//프레임 윈도우 닫으면 프로그램 종료

		Container c = getContentPane(); //컨텐트팬
		c.setLayout(new BorderLayout());//BorderLayout 배치관리자 달기
		
		c.add(new NorthPanel(),BorderLayout.NORTH); //BorderLayout의 북쪽
		c.add(new CenterPanel(),BorderLayout.CENTER); //BorderLayout의 중앙
		c.add(new SouthPanel(),BorderLayout.SOUTH); //BorderLayout의 남쪽
		
		setVisible(true); //화면에 프레임 출력
		setResizable(false); //프레임 크기 조절 불가
	}
	public void Calculate(String click,String operator) {//입력받은 값 연산자로 계산하는 함수
		StringTokenizer token = new StringTokenizer(click,"/x-+%");
		// x,/,-,+,%가 있을때 token으로 나눔
		if(token.countTokens()>2) {//연산이 2개 이상이면 계산 불가
			tf2.setText("한 개의 연산만 할 수 있음");
			return;
		}
		String total=null;
		double x=Double.parseDouble(token.nextToken());//첫번째 수
		double y=Double.parseDouble(token.nextToken());//두번째 수
		if(y==0&&operator.equals("/")) {//0으로는 나눌 수 없음
			tf2.setText("0으로 나눌 수 없음");
			return;
		}
		switch(operator) {//연산자에따라 다르게 계산
			case"/"://나누기의 경우
				total=Double.toString(x/=y);
				tf2.setText(total); //tf2에 출력
					break;
			case"x"://곱셈의 경우
				total=Double.toString(x*=y);
				tf2.setText(total);//tf2에 출력
				break;
			case"-"://뺄셈의 경우
				total=Double.toString(x-=y);
				tf2.setText(total);//tf2에 출력
				break;
			case"+"://덧셈의 경우
				total=Double.toString(x+=y);
				tf2.setText(total);//tf2에 출력
				break;
			case"%"://나머지연산의 경우
				total=Double.toString(x%=y);
				tf2.setText(total);//tf2에 출력
				break;
		}
			
	}
	class  NorthPanel extends JPanel{//JPanel을 상속 받는 NorthPanel
		public NorthPanel() {//NorthPanel 생성자
			this.setBackground(Color.LIGHT_GRAY);//JPanel의 색 설정
			add(new JLabel("수식"));//JLabel 생성 & 컨텐트팬에 라벨 달기
			add(tf1);//JTextField 생성 & 컨텐트팬에 텍스트피드 달기
		}
	}

	class SouthPanel extends JPanel{//JPanel을 상속 받는 SouthPanel
		public SouthPanel() {//SouthPanel 생성자
			this.setBackground(Color.YELLOW); //JPanel의 색 설정
			JLabel textLabel =new JLabel("계산 결과"); //JLabel 생성
			textLabel.setHorizontalAlignment(JLabel.LEFT); //왼쪽정렬
			add(textLabel);//컨텐트팬에 라벨 달기
			
			tf2.setHorizontalAlignment(JLabel.LEFT);//왼쪽정렬
			add(tf2);//컨텐트팬에 라벨 달기
		}
	}
	
	class CenterPanel extends JPanel{//JPanel을 상속 받는 CenterPanel
		private JButton b[] = {new JButton("x"),new JButton("-"),new JButton("+")}; //버튼 컴포넌트 생성
		private JButton btn[] = new JButton[10];//10개의 숫자 버튼 레퍼런스 생성
		public CenterPanel() {//CenterPanel 생성자
			setLayout(new GridLayout(5,4,5,5)); //4*5 그리드 형식과 5만큼의 간격 벌리기
			setBackground(Color.WHITE); //색 설정
			JButton c = new JButton("C");
			add(c); //"C"버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
			c.addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					click=""; 
					tf1.setText(click);//tf1 초기화
				}
			});
			add(new JButton("UN"));//"UN"버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
			JButton bk = new JButton("BK");//"BK"버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
			add(bk); //"C"버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
			bk.addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					if(click.equals(""))
						tf1.setText(click); //tf1가 비었을 경우 아무일도 일어나지 않는다
					else{
						click=click.substring(0, click.length() - 1);
						tf1.setText(click);//tf1의 마지막 글자 삭제 후 출력
					}
				}
			});
			JButton division= new JButton("/");
			add(division);//"/"버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
			division.addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					click+="/";//버튼에 해당하는 문자 click에 추가
					operator="/";//연산자 입력받음
					tf1.setText(click);//tf1에 출력
				}
			});
			int index=0;
			for(int i=7;i>0;i=i-3) {
				for(int j=i;j<i+3;j++) {
					btn[j]=new JButton(Integer.toString(j));
					add(btn[j]); //버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
					}
				add(b[index++]);//컨텐트팬에 버튼 달기
			}
			btn[0]=new JButton(Integer.toString(0));
			add(btn[0]);//버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
			for(int i=0;i<10;i++) {
				int number =i;
				btn[i].addActionListener(new ActionListener() { //버튼 이벤트 발생시
					public void actionPerformed(ActionEvent e) {
						click+=Integer.toString(number);//버튼에 해당하는 숫자 click에 추가
						tf1.setText(click);//tf1에 출력
					}
				});
			}
			JButton dot =new JButton(".");
			add(dot);//버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
			dot.addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					click+=".";//버튼에 해당하는 문자 click에 추가
					tf1.setText(click);//tf1에 출력
				}
			});

			JButton result = new JButton("=");//"="버튼 컴포넌트 생성
			result.setBackground(Color.CYAN);//CYAN으로 버튼 색 변경
			add(result);//컨텐트팬에 버튼 달기
			result.addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					Calculate(click,operator);//click에 입력받은 값들 계산 후 tf2에 출력
				}
			});
			
			JButton rest = new JButton("%");//"%"버튼 컴포넌트 생성 & 컨텐트팬에 버튼 달기
			add(rest);//컨텐트팬에 버튼 달기
			rest.addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					click+="%";//버튼에 해당하는 문자 click에 추가
					operator="%";//연산자 입력받음
					tf1.setText(click);//tf1에 출력
				}
			});
			b[0].addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					click+="x";//버튼에 해당하는 문자 click에 추가
					operator="x";//연산자 입력받음
					tf1.setText(click);//tf1에 출력
				}
			});
			b[1].addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					click+="-";//버튼에 해당하는 문자 click에 추가
					operator="-";//연산자 입력받음
					tf1.setText(click);//tf1에 출력
				}
			});
			b[2].addActionListener(new ActionListener() { //버튼 이벤트 발생시
				public void actionPerformed(ActionEvent e) {
					click+="+";//버튼에 해당하는 문자 click에 추가
					operator="+";//연산자 입력받음
					tf1.setText(click);//tf1에 출력
				}
			});
		}
	}
	
	public static void main(String [] args) {
		new CalculatorFrame();//CalculatorFrame 생성
	}
}



