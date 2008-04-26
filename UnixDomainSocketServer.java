import java.io.IOException;
import java.io.OutputStream;

public class UnixDomainSocketServer extends UnixDomainSocket {
	/**
	 * Creates a unix domain socket and connects it to the server specified by
	 * the socket file.
	 * 
	 * @param socketFile
	 *            Name of the socket file
	 * 
	 * @return the
	 * @throws IOException
	 *             If unable to construct the socket
	 */

	public UnixDomainSocketServer(String socketFile, int socketType)
			throws IOException {
		super.socketFile = socketFile;
		super.socketType = socketType;

		if ((nativeSocketFileHandle = nativeCreate(socketFile, socketType)) == -1)
			throw new IOException("Unable to open Unix Domain Socket");

		// Initialize the socket input and output streams
		in = new UnixDomainSocketInputStream();
		if (socketType == UnixDomainSocket.SOCK_STREAM)
			out = new UnixDomainSocketOutputStream();
	}

	/**
	 * Returns an output stream for this socket.
	 * 
	 * @return An output stream for writing bytes to this socket
	 */
	@Override
	public OutputStream getOutputStream() {
		if (socketType == UnixDomainSocket.SOCK_STREAM)
			return (OutputStream) out;
		else
			throw new UnsupportedOperationException();
	}
}