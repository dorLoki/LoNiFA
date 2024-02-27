package de.sachsenCompany.common;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.rmi.UnexpectedException;

import javax.naming.AuthenticationException;

public class RCON {
	private SocketChannel socketChannel;
	private String hostname = "0.0.0.0";
	private String password = "test";
	private int port = -1;
	private String name;
	private int counter = 0;

	public RCON(String hostname, String password, int port, String name) {
		this.hostname = hostname;
		this.password = password;
		this.port = port;
		this.name = name;
	}

	public boolean reconnect() {
		// check if connection must be closed
		if (socketChannel != null) {
			try {
				socketChannel.close();
			} catch (IOException e) {
				// return false;
			}
		}
		// connect
		try {
			socketChannel = SocketChannel.open(new InetSocketAddress(hostname, port));
		} catch (IOException e) {
			System.err.println("Fehler beim Initialisieren des " + name + " Server Sockets!");
			return false;
		}
		// auth via password
		try {
			sendRaw(3, password);
			readResponse();
			return true;
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		} catch (AuthenticationException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public String sendCommand(String command) {
		if(socketChannel == null) {
			reconnect();
		}
		if(socketChannel == null) {
			return "Keine Verbindung zum Server. Ist dieser Offline?";
		}
		if(!socketChannel.isConnected()) {
			reconnect();
		}
		if(command == null) {
			return "Command was Null";
		}
		if(command.length() == 0) {
			return "Command was empty";
		}
		try {
			sendRaw(2, command);
			String response = readResponse();
			return response;
		} catch (IOException e) {
			reconnect();
			return "Keine Verbindung zum Server. Ist dieser Offline?";
		} catch (AuthenticationException e) {
			reconnect();
			return "AuthenticationException - Try later again.";
		}
	}

	private void sendRaw(int i, String command) throws IOException {
		final ByteBuffer bytesToSend = createRconByteBuffer(counter++, i, command);
		socketChannel.write(bytesToSend);
	}

	private ByteBuffer createRconByteBuffer(int requestCount, int requestType, String command) {
		// In accordance with the RCON format: Length + Request ID + Type + Payload +
		// Two nil bytes
		ByteBuffer byteBuffer = ByteBuffer.allocate((3 * Integer.BYTES) + command.length() + (2 * Byte.BYTES));
		byteBuffer.order(ByteOrder.LITTLE_ENDIAN);

		byteBuffer.putInt((2 * Integer.BYTES) + command.length() + (2 * Byte.BYTES));
		byteBuffer.putInt(requestCount);
		byteBuffer.putInt(requestType);
		byteBuffer.put(command.getBytes());
		byteBuffer.put((byte) 0);
		byteBuffer.put((byte) 0);

		byteBuffer.position(0);

		return byteBuffer;
	}

	private String readResponse() throws AuthenticationException, IOException, UnexpectedException {
		final int byteSize = readData(Integer.BYTES).getInt();
		final ByteBuffer dataBytes = readData(byteSize - (2 * Byte.BYTES));
		final ByteBuffer packageTailBytes = readData(2 * Byte.BYTES);

		final int responseId = dataBytes.getInt();

		if (responseId == -1) {
			throw new AuthenticationException();
		}

		final byte byteOne = packageTailBytes.get(0);
		final byte byteTwo = packageTailBytes.get(1);

		if (byteOne != 0 || byteTwo != 0) {
			System.out.println("Error: Unexpected package tail bytes");
			throw new UnexpectedException("Unexpected package tail bytes");
		}
		final byte[] dataBytesArray = new byte[dataBytes.remaining()];
		dataBytes.get(dataBytesArray);
		return new String(dataBytesArray, StandardCharsets.UTF_8).trim();
	}

	private ByteBuffer readData(int bytes) throws IOException {
		final ByteBuffer buffer = ByteBuffer.allocate(bytes);
		final int readCount = socketChannel.read(buffer);
		if (readCount != bytes) {
			throw new UnexpectedException("readCount != bytes");
		}
		buffer.position(0);
		buffer.order(ByteOrder.LITTLE_ENDIAN);
		return buffer;
	}
}
