package com.tuxdave;

import JComponents.JPlaceHolderTextField;
import com.intellij.uiDesigner.core.GridConstraints;
import com.intellij.uiDesigner.core.GridLayoutManager;
import com.intellij.uiDesigner.core.Spacer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class AnimeDownloader extends JFrame {
    private JPanel panel1;
    private JLabel info2;
    private JTextField info3;
    private JLabel info1;
    private JTextArea showLinkArea;
    private JButton copiaButton;
    private JButton salvaLinksSuFileButton;
    private JPlaceHolderTextField linkEdit;
    private JButton downloadEpisodiComingSoonButton;
    private JProgressBar progressBar1;
    private JButton findEpisodesButton;
    private JScrollPane scrollPane;

    private boolean working = false;

    //START AUTOGENERATED CODE

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        panel1 = new JPanel();
        panel1.setLayout(new GridLayoutManager(5, 2, new Insets(0, 0, 0, 0), -1, -1));
        panel1.setBackground(new Color(-4390921));
        Font panel1Font = this.$$$getFont$$$("Ubuntu", Font.PLAIN, 14, panel1.getFont());
        if (panel1Font != null) panel1.setFont(panel1Font);
        linkEdit = new JPlaceHolderTextField();
        Font linkEditFont = this.$$$getFont$$$("Ubuntu", Font.PLAIN, 14, linkEdit.getFont());
        if (linkEditFont != null) linkEdit.setFont(linkEditFont);
        linkEdit.setPlaceHolder("https://www.animeworld.tv/...");
        panel1.add(linkEdit, new GridConstraints(1, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        info2 = new JLabel();
        Font info2Font = this.$$$getFont$$$("Ubuntu", Font.PLAIN, 14, info2.getFont());
        if (info2Font != null) info2.setFont(info2Font);
        info2.setText("Inserisci il link della serie Anime:");
        panel1.add(info2, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        info1 = new JLabel();
        info1.setBackground(new Color(-1));
        Font info1Font = this.$$$getFont$$$("Ubuntu", Font.PLAIN, 14, info1.getFont());
        if (info1Font != null) info1.setFont(info1Font);
        info1.setForeground(new Color(-16777216));
        info1.setText("Recupera il link della serie Anime da Qui  (Occhio alle publicità) Click->");
        panel1.add(info1, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_EAST, GridConstraints.FILL_NONE, GridConstraints.SIZEPOLICY_FIXED, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        info3 = new JTextField();
        info3.setBackground(new Color(-1));
        info3.setEditable(false);
        Font info3Font = this.$$$getFont$$$("Ubuntu", Font.PLAIN, 14, info3.getFont());
        if (info3Font != null) info3.setFont(info3Font);
        info3.setForeground(new Color(-9613313));
        info3.setText("https://www.animeworld.tv/");
        panel1.add(info3, new GridConstraints(0, 1, 1, 1, GridConstraints.ANCHOR_WEST, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, new Dimension(150, -1), null, 0, false));
        final JPanel panel2 = new JPanel();
        panel2.setLayout(new GridLayoutManager(3, 1, new Insets(0, 0, 0, 0), -1, -1));
        panel1.add(panel2, new GridConstraints(3, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, null, null, null, 0, false));
        salvaLinksSuFileButton = new JButton();
        Font salvaLinksSuFileButtonFont = this.$$$getFont$$$("Ubuntu", Font.BOLD, 14, salvaLinksSuFileButton.getFont());
        if (salvaLinksSuFileButtonFont != null) salvaLinksSuFileButton.setFont(salvaLinksSuFileButtonFont);
        salvaLinksSuFileButton.setText("Salva links su file! (Coming Soon)");
        panel2.add(salvaLinksSuFileButton, new GridConstraints(1, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        copiaButton = new JButton();
        Font copiaButtonFont = this.$$$getFont$$$("Ubuntu", Font.BOLD, 14, copiaButton.getFont());
        if (copiaButtonFont != null) copiaButton.setFont(copiaButtonFont);
        copiaButton.setText("Copia!");
        panel2.add(copiaButton, new GridConstraints(0, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        downloadEpisodiComingSoonButton = new JButton();
        Font downloadEpisodiComingSoonButtonFont = this.$$$getFont$$$("Ubuntu", Font.BOLD, 14, downloadEpisodiComingSoonButton.getFont());
        if (downloadEpisodiComingSoonButtonFont != null)
            downloadEpisodiComingSoonButton.setFont(downloadEpisodiComingSoonButtonFont);
        downloadEpisodiComingSoonButton.setText("Download Episodi(Coming Soon)");
        panel2.add(downloadEpisodiComingSoonButton, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_NORTH, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        final Spacer spacer1 = new Spacer();
        panel1.add(spacer1, new GridConstraints(4, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_VERTICAL, 1, GridConstraints.SIZEPOLICY_WANT_GROW, null, null, null, 0, false));
        progressBar1 = new JProgressBar();
        panel1.add(progressBar1, new GridConstraints(2, 0, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        findEpisodesButton = new JButton();
        findEpisodesButton.setText("Trova Episodi!");
        panel1.add(findEpisodesButton, new GridConstraints(2, 1, 1, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_HORIZONTAL, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_CAN_GROW, GridConstraints.SIZEPOLICY_FIXED, null, null, null, 0, false));
        scrollPane = new JScrollPane();
        panel1.add(scrollPane, new GridConstraints(3, 0, 2, 1, GridConstraints.ANCHOR_CENTER, GridConstraints.FILL_BOTH, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, GridConstraints.SIZEPOLICY_CAN_SHRINK | GridConstraints.SIZEPOLICY_WANT_GROW, new Dimension(400, 500), null, null, 0, false));
        showLinkArea = new JTextArea();
        showLinkArea.setEditable(false);
        Font showLinkAreaFont = this.$$$getFont$$$("Ubuntu", Font.PLAIN, 14, showLinkArea.getFont());
        if (showLinkAreaFont != null) showLinkArea.setFont(showLinkAreaFont);
        showLinkArea.setLineWrap(false);
        showLinkArea.setText("");
        scrollPane.setViewportView(showLinkArea);
    }

    /**
     * @noinspection ALL
     */
    private Font $$$getFont$$$(String fontName, int style, int size, Font currentFont) {
        if (currentFont == null) return null;
        String resultName;
        if (fontName == null) {
            resultName = currentFont.getName();
        } else {
            Font testFont = new Font(fontName, Font.PLAIN, 10);
            if (testFont.canDisplay('a') && testFont.canDisplay('1')) {
                resultName = fontName;
            } else {
                resultName = currentFont.getName();
            }
        }
        return new Font(resultName, style >= 0 ? style : currentFont.getStyle(), size >= 0 ? size : currentFont.getSize());
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return panel1;
    }

    //END AUTOGENERATED CODE

    {//setup JFrame
        add(panel1);
        setResizable(false);
        setTitle("AnimeDownloader");
    }

    {//insert listeners
        info3.addMouseListener(new AnimeDownloaderListener());
        findEpisodesButton.addActionListener(new AnimeDownloaderListener());
    }

    private class AnimeDownloaderListener implements ActionListener, MouseListener {

        private ParseAnimeWorld parser = null;
        private String[] links = null;

        private class Work extends Thread {
            @Override
            public void run() {
                super.run();

            }
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            if (actionEvent.getSource() == findEpisodesButton && !working) {
                working = true;
                linkEdit.setBackground(Color.WHITE);
                progressBar1.setValue(0);
                try {
                    parser = new ParseAnimeWorld(linkEdit.getText());
                    try {
                        links = new String[0];
                        parser.scrapeAllEpisodeDownloadLink();//todo:eseguire ParseAnimWorld in un thread
                        showLinkArea.setText("");
                    } catch (IllegalArgumentException | IOException ignored) {
                    } finally {
                        new Thread() {
                            @Override
                            public void run() {
                                super.run();
                                while (parser.isScraping()) {
                                    progressBar1.setValue(parser.getCurrent() * 100 / parser.getEpisodes());
                                    try {
                                        Thread.sleep(500);
                                    } catch (InterruptedException ignored) {
                                    }
                                }
                                links = parser.getAllEpisodeDownloadLink();
                                showLinkArea.setText("");
                                for (String s1 : links) {
                                    showLinkArea.append(s1 + "\n");
                                }
                                progressBar1.setValue(100);
                            }
                        }.start();
                    }

                } catch (IOException e) {
                    final JDialog dialog = new JDialog();
                    dialog.getContentPane().setLayout(new BoxLayout(dialog.getContentPane(), BoxLayout.Y_AXIS));
                    dialog.add(new JLabel("Impossibile trovare una serie Anime all'indirizzo specificato"));
                    JButton b = new JButton("OK!");
                    b.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent actionEvent) {
                            dialog.setVisible(false);
                        }
                    });
                    dialog.add(b);
                    dialog.setVisible(true);
                    dialog.pack();
                    linkEdit.setBackground(Color.RED);
                }
                working = false;
            }
        }

        @Override
        public void mouseClicked(MouseEvent mouseEvent) {
            if (mouseEvent.getSource() == info3) {
                try {
                    Desktop.getDesktop().browse(new URI(info3.getText()));
                } catch (IOException e) {//todo: modificare per non crashare
                    e.printStackTrace();
                } catch (URISyntaxException e) {
                    e.printStackTrace();
                }
            }
        }

        //Implementazioni obbligatorie ma non implementate volutamente
        @Override
        public void mousePressed(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseReleased(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseEntered(MouseEvent mouseEvent) {

        }

        @Override
        public void mouseExited(MouseEvent mouseEvent) {

        }
    }
}
