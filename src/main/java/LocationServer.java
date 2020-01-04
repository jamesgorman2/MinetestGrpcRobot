import io.grpc.Server;
import io.grpc.netty.NettyServerBuilder;

import java.io.IOException;

public class LocationServer {
  public static final int PORT = 12345;
  private Server server;

  public static void main(String[] args) throws IOException, InterruptedException {
    LocationServer server = new LocationServer();
    server.start();
    server.blockUntilShutdown();
  }

  private void blockUntilShutdown() throws InterruptedException {
    if (server != null) {
      server.awaitTermination();
    }
  }

  private void start() throws IOException {
    System.out.println("Starting server...");
    server = NettyServerBuilder.forPort(PORT)
      .addService(new LocationImpl())
      .build()
      .start();

    Runtime.getRuntime().addShutdownHook(new Thread() {
      @Override
      public void run() {
        // Use stderr here since the logger may have been reset by its JVM shutdown hook.
        System.err.println("*** shutting down gRPC server since JVM is shutting down");
        LocationServer.this.stop();
        System.err.println("*** server shut down");
      }
    });
  }

  private void stop() {
    if (server != null) {
      server.shutdown();
    }
  }}
