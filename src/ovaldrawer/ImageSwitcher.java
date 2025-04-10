/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package ovaldrawer;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class ImageSwitcher extends JFrame {
    private Image currentImage;
    private final String[] imagePaths = {
        "C:/Users/Денис/Documents/NetBeansProjects/OvalDrawer/src/ovaldrawer/resources/BakeryLogo.jpg",  // Путь к первому изображению
        "C:/Users/Денис/Documents/NetBeansProjects/OvalDrawer/src/ovaldrawer/resources/BakeryOutside.jpeg",  // Путь ко второму изображению
        "C:/Users/Денис/Documents/NetBeansProjects/OvalDrawer/src/ovaldrawer/resources/BakeryInside.jpeg"   // Путь к третьему изображению
    };
    private int currentIndex = 0;

    public ImageSwitcher() {
        setTitle("Переключатель изображений");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Панель для отображения изображения
        JPanel imagePanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (currentImage != null) {
                    // Рисуем изображение с сохранением пропорций
                    int panelWidth = getWidth();
                    int panelHeight = getHeight();
                    double imageRatio = (double) currentImage.getWidth(null) / currentImage.getHeight(null);
                    
                    int drawWidth, drawHeight;
                    if (panelWidth / panelHeight > imageRatio) {
                        drawHeight = panelHeight;
                        drawWidth = (int) (drawHeight * imageRatio);
                    } else {
                        drawWidth = panelWidth;
                        drawHeight = (int) (drawWidth / imageRatio);
                    }
                    
                    int x = (panelWidth - drawWidth) / 2;
                    int y = (panelHeight - drawHeight) / 2;
                    g.drawImage(currentImage, x, y, drawWidth, drawHeight, null);
                }
            }
        };

        // Кнопка для переключения изображений
        JButton switchButton = new JButton("Следующее изображение");
        switchButton.addActionListener((ActionEvent e) -> {
            currentIndex = (currentIndex + 1) % imagePaths.length; // Циклическое переключение
            loadImage();
            imagePanel.repaint();
        });

        // Загрузка первого изображения
        loadImage();

        // Размещение компонентов
        setLayout(new BorderLayout());
        add(imagePanel, BorderLayout.CENTER);
        add(switchButton, BorderLayout.SOUTH);
    }

    private void loadImage() {
        try {
            currentImage = new ImageIcon(imagePaths[currentIndex]).getImage();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Ошибка загрузки изображения: " + imagePaths[currentIndex]);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new ImageSwitcher().setVisible(true);
        });
    }
}
