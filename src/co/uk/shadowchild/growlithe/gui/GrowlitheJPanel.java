package co.uk.shadowchild.growlithe.gui;

import co.uk.shadowchild.growlithe.Growlithe;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class GrowlitheJPanel extends JPanel {

    public GrowlitheJPanel() {

        super(null);

        JTextField channel = new JTextField();
        channel.setBounds(0, 0, 100, 20);
        channel.setVisible(true);
        this.add(channel);

        JTextField text = new JTextField();
        text.setBounds(100, 0, 900, 20);
        text.setVisible(true);
        this.add(text);

        JButton sendRaw = new JButton("SendRaw");
        sendRaw.setBounds(1000 / 2 + 50, 30, 100, 20);
        sendRaw.setVisible(true);
        sendRaw.addActionListener(new SendActionListener(channel, text));
        this.add(sendRaw);

        JButton send = new JButton("Send");
        send.setBounds(1000 / 2 - 60, 30, 80, 20);
        send.setVisible(true);
        send.addActionListener(new SendActionListener(channel, text));
        this.add(send);
    }

    private class SendActionListener implements ActionListener {

        public JTextField channel;
        public JTextField textBox;

        public SendActionListener(JTextField channel, JTextField text) {

            super();
            this.channel = channel;
            this.textBox = text;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

            if(e.getActionCommand().equals("SendRaw") && !textBox.getText().isEmpty()) {

                Growlithe.bot.sendRawLine(textBox.getText());
                textBox.setText("");
            } else if(e.getActionCommand().equals("Send") && !textBox.getText().isEmpty()) {

                Growlithe.bot.sendMessage(channel.getText(), textBox.getText());
                textBox.setText("");
            }
        }
    }
}
