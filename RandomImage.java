import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

//이미지를 4x4 격자로 나누고, 클릭 시 두 이미지 조각의 위치를 변경
public class RandomImage extends JFrame {
    private JLabel[] labels = new JLabel[16];  // 이미지 조각을 표시할 레이블 배열
    private ImageIcon[] images = new ImageIcon[16];   // 잘라진 이미지 조각들을 저장할 아이콘 배열
    private int firstClickedIndex = -1;   // 첫 번째 클릭된 레이블의 인덱스 저장 변수
    private int arr[]=new int[16];

    public RandomImage() {
        super("Image Swap Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(250, 350);
        setResizable(false);

        JPanel panel = new JPanel(new GridLayout(4, 4));
        loadAndCutImage("image.jpg");     // 이미지를 로드하고 4x4로 자르는 메소드 호출

        // 16개의 레이블 생성 및 패널에 추가
        for (int i = 0; i < 16; i++) {
            labels[i] = new JLabel(images[i]);   // 각 레이블에 이미지 설정
            labels[i].addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent e) {
                    handleLabelClick(e);     // 레이블 클릭 시 호출될 메소드
                }
            });
            //panel.add(labels[i]);    // 레이블을 패널에 추가
            arr[i]=99;
        }
        int x = 0;
        while (x < 16) {
            int k = (int) (Math.random() * 16);
            for (int i = 0; i < x; i++) {
                if (arr[i] == k) {
                    k = (int) (Math.random() * 16);
                    i = -1;
                }
            }
            //number[x] =new JLabel(images[k]);
            arr[x] = k;
            panel.add(labels[k]);
            x++;
        }
        add(panel);
        setVisible(true);
    }

 // 레이블 클릭 이벤트 처리 메소드
    private void handleLabelClick(MouseEvent e) {
        JLabel clickedLabel = (JLabel) e.getSource();   // 클릭된 레이블 객체
        int clickedIndex = findLabelIndex(clickedLabel);   // 클릭된 레이블의 인덱스 검색
     // 첫 번째 클릭 처리
        if (firstClickedIndex == -1) {
            firstClickedIndex = clickedIndex;    // 첫 번째 클릭된 인덱스 저장

        } else {
            swapImages(firstClickedIndex, clickedIndex);   // 두 번째 클릭 시 이미지 위치 교환
            firstClickedIndex = -1;   // 인덱스 초기화

        }
    }

    // 두 이미지 위치를 교환하는 메소드
    private void swapImages(int firstIndex, int secondIndex) {
        ImageIcon temp = images[firstIndex];
        images[firstIndex] = images[secondIndex];
        images[secondIndex] = temp;

        labels[firstIndex].setIcon(images[firstIndex]);
        labels[secondIndex].setIcon(images[secondIndex]);
    }

 // 레이블의 인덱스를 찾는 메소드
    private int findLabelIndex(JLabel label) {
        for (int i = 0; i < labels.length; i++) {
            if (labels[i] == label) {
                return i;
            }
        }
        return -1; // Not found
    }

 // 이미지를 로드하고 4x4로 자르는 메소드
    private void loadAndCutImage(String path) {
        try {
            BufferedImage original = ImageIO.read(new File(path));
            int width = original.getWidth() / 4;
            int height = original.getHeight() / 4;

            for (int i = 0; i < 16; i++) {
                int x = (i % 4) * width;
                int y = (i / 4) * height;
                BufferedImage cropped = original.getSubimage(x, y, width, height);
                images[i] = new ImageIcon(cropped);
            }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "이미지 로드 실패: " + e.getMessage(), "오류", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static void main(String[] args) {
        new RandomImage();
    }
}
