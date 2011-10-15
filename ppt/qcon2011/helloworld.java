// http://timyang.net/programming/c-erlang-java-performance/

public class HttpServer {
    public static void main(String[] args) throws Exception {
        SocketAcceptor acceptor = new NioSocketAcceptor(4);
        acceptor.setReuseAddress( true );

		int port = 8080;
		String hostname = null;
		if (args.length > 1) {
			hostname = args[0];
			port = Integer.parseInt(args[1]);
		}

        // Bind
        acceptor.setHandler(new HttpProtocolHandler());
        if (hostname != null)
        	acceptor.bind(new InetSocketAddress(hostname, port));
        else
        	acceptor.bind(new InetSocketAddress(port));

        System.out.println("Listening on port " + port);
        Thread.currentThread().join();
    }
}

public class HttpProtocolHandler extends IoHandlerAdapter {
    public void sessionCreated(IoSession session) {
        session.getConfig().setIdleTime(IdleStatus.BOTH_IDLE, 10);
        session.setAttribute(SslFilter.USE_NOTIFICATION);
    }

    public void sessionClosed(IoSession session) throws Exception {}
    public void sessionOpened(IoSession session) throws Exception {}
    public void sessionIdle(IoSession session, IdleStatus status) {}
    public void exceptionCaught(IoSession session, Throwable cause) {
        session.close(true);
    }

    static IoBuffer RESULT = null;
	public static String HTTP_200 = "HTTP/1.1 200 OK\r\nContent-Length: 13\r\n\r\n" +
			"hello world\r\n";
	static {
    	RESULT = IoBuffer.allocate(32).setAutoExpand(true);
    	RESULT.put(HTTP_200.getBytes());
    	RESULT.flip();
    }
    public void messageReceived(IoSession session, Object message)
            throws Exception {
        if (message instanceof IoBuffer) {
        	IoBuffer buf = (IoBuffer) message;
        	int c = buf.get();
        	if (c == 'G' || c == 'g') {
        		session.write(RESULT.duplicate());
        	}
        	session.close(false);
        }
    }
}