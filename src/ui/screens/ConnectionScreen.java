package ui.screens;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

import ui.buttons.DefaultButton;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Net.Protocol;
import com.badlogic.gdx.net.ServerSocket;
import com.badlogic.gdx.net.ServerSocketHints;
import com.badlogic.gdx.net.Socket;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextArea;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

public class ConnectionScreen extends TableScreen {
	private TextArea _messageField;
	
	@Override
	public void initialize() {
		super.initialize();

		List<String> addresses = new ArrayList<>();
		Skin skin = new Skin(Gdx.files.internal("assets/uiskin.json"));
		TextArea addressesArea = new TextArea("", skin);
		_messageField = new TextArea("", skin);
		addressesArea.setWidth(200);
		addressesArea.setHeight(200);
		
		_messageField.setWidth(400);
		_messageField.setHeight(200);
		
		try {
			Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces();
			for(NetworkInterface ni : Collections.list(interfaces)) {
				for(InetAddress ia : Collections.list(ni.getInetAddresses())) {
					if(ia instanceof Inet4Address) {
						String adr = ia.getHostAddress();
						addressesArea.setText(addressesArea.getText() + adr + "\n");
						addresses.add(adr);
					}
				}
			}
			
		} catch(SocketException e) {
			e.printStackTrace();
		}
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				ServerSocketHints hints = new ServerSocketHints();
				hints.acceptTimeout = 0;
				
				ServerSocket serverSocket = Gdx.net.newServerSocket(Protocol.TCP, 9021, hints);
				
				while(true) {
					Socket socket = serverSocket.accept(null);
					
					BufferedReader buffer = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					
					try {
						_messageField.setText(buffer.readLine());
					} catch(IOException e) {
						e.printStackTrace();
					}
				}
			}
		}).start();
		
		setupHelperButtons();
		
		_messageField.setPosition(700, 250);
		addressesArea.setPosition(250, 250);
		_stage.addActor(addressesArea);
		_stage.addActor(_messageField);
	}
	
	private void setupHelperButtons() {
		DefaultButton exitButton = new DefaultButton("Beenden");
		exitButton.addListener(new ClickListener() {
			@Override
			public void clicked(InputEvent event, float x, float y) {
				Gdx.app.exit();
			}
		});
		
		exitButton.setPosition(60, Gdx.graphics.getHeight()-50);
		_stage.addActor(exitButton);
	}
}
