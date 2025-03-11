import java.awt.Component;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class TextSource {
	public Vector <String> wordVector = new Vector <String>(30000);//vector ()<-사이즈 넣는 곳 default 10개 /초과시 바로 20개
	public TextSource(Component parent) {
		try {
			Scanner scanner =new Scanner(new FileReader("words.txt"));
		
			while(scanner.hasNext()) {
				String word = scanner.nextLine();
				wordVector.add(word);
			}
			scanner.close();
			//JOptionPane.showMessageDialog(parent, "단어 읽기 완료");
		}
		catch(IOException e){   //FileNotFoundException e
			System.out.print("파일이 없음");
			System.exit(0);
		}
	}
	public String next() {
		int n = wordVector.size();
		int index = (int)(Math.random()*n);
		return wordVector.get(index);
	}
}
